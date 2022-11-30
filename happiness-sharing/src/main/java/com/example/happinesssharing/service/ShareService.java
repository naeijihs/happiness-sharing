package com.example.happinesssharing.service;

import com.example.happinesssharing.component.RequestComponent;
import com.example.happinesssharing.entity.*;
import com.example.happinesssharing.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class ShareService {
    @Autowired
    private RequestComponent requestComponent;
    @Autowired
    private SharerRepository sharerRepository;
    @Autowired
    private ShareRepository shareRepository;
    @Autowired
    private AgreeRepository agreeRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ReportRepository reportRepository;
    public Share addShare(Share share){
        Sharer sharer=sharerRepository.findById(requestComponent.getUid()).orElse(null);
        share.setSharer(sharer);
        return  shareRepository.save(share);
    }
    public String addVideo(MultipartFile video, HttpServletRequest request) throws IOException {
        //把视频存储在服务器部署的临时文件中(删除项目static文件),改config
        /*
        String rootPath=request.getSession().getServletContext().getRealPath("/static/");
        String path=rootPath+video.getOriginalFilename();
        File videoFile=new File(path);
        File parentFile=videoFile.getParentFile();
        if(!parentFile.exists()){
            parentFile.mkdirs();
        }
        video.transferTo(videoFile);
        return path;
        */
        //把视频存储在项目文件中，需要在项目中新建static目录，改config
        String rootPath=request.getSession().getServletContext().getRealPath("/");
        String path=rootPath+video.getOriginalFilename();
        video.transferTo(new File(path));
        return "http://localhost:8080/video/"+video.getOriginalFilename();
    }
    public void deleteShare(int id){
        shareRepository.deleteById(id);
    }
    public List<Share> getOwnShares(){
        return sharerRepository.findById(requestComponent.getUid()).orElse(null).getShares();
    }
    public List<Share> getAllShares(){
        return shareRepository.findAll();
    }
    public String agree(int id){
        Share share=shareRepository.findById(id).orElse(null);
        Sharer agreer=sharerRepository.findById(requestComponent.getUid()).orElse(null);
        List<Agree> a=share.getAgrees().stream().filter(agree -> agree.getAgreer()==agreer).collect(Collectors.toList());
        if(a.size()!=0)
            return "您已点过赞";
        else {
            Agree agree=new Agree();
            agree.setShare(share);
            agree.setAgreer(agreer);
            agreeRepository.save(agree);
            int n=share.getAgreeCount()+1;
            share.setAgreeCount(n);
            shareRepository.save(share);
            return "点赞成功";
        }
    }
    public Comment addComment(Comment comment){
        Share share=shareRepository.findById(comment.getShare().getId()).orElse(null);
        Sharer commenter=sharerRepository.findById(requestComponent.getUid()).orElse(null);
        comment.setCommenter(commenter);
        comment.setShare(share);
        return commentRepository.save(comment);
    }
    public Report addReport(Report report){
        Sharer reporter=sharerRepository.findById(requestComponent.getUid()).orElse(null);
        Share share=shareRepository.findById(report.getShare().getId()).orElse(null);
        report.setReporter(reporter);
        report.setShare(share);
        report.setState(Report.State.UNSETTLED);
        return reportRepository.save(report);
    }
}
