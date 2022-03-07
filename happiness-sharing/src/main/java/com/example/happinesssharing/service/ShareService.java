package com.example.happinesssharing.service;

import com.example.happinesssharing.component.RequestComponent;
import com.example.happinesssharing.entity.*;
import com.example.happinesssharing.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
