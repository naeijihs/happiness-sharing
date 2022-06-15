package com.example.happinesssharing.service;

import com.example.happinesssharing.entity.Recommend;
import com.example.happinesssharing.entity.Report;
import com.example.happinesssharing.entity.Slideshow;
import com.example.happinesssharing.repository.RecommendRepository;
import com.example.happinesssharing.repository.ReportRepository;
import com.example.happinesssharing.repository.ShareRepository;
import com.example.happinesssharing.repository.SlideshowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class AdminService {
    @Autowired
    private SlideshowRepository slideshowRepository;
    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private ShareRepository shareRepository;
    @Autowired
    private RecommendRepository recommendRepository;
    public Slideshow addSlideshow(Slideshow slideshow){
        return slideshowRepository.save(slideshow);
    }
    public void deleteSlideshow(int id){
        slideshowRepository.deleteById(id);
    }
    public List<Slideshow> getAllSlideshows(){
        return slideshowRepository.findAll();
    }
    public List<Report> getAllUnsettledReports(){
        return reportRepository.findByState();
    }
    public void acceptReport(int id){
        Report report=reportRepository.findById(id).orElse(null);
        report.setState(Report.State.ACCEPT);
        shareRepository.delete(report.getShare());
    }
    public void refuseReport(int id){
        Report report=reportRepository.findById(id).orElse(null);
        report.setState(Report.State.REFUSE);
    }
    public void deleteRecommend(int id){
        recommendRepository.deleteById(id);
    }
    public Recommend addRecommend(Recommend recommend){
        return recommendRepository.save(recommend);
    }
    public Recommend getRecommend(int id){
        return recommendRepository.findById(id).orElse(null);
    }
    public List<Recommend> getRecommends(){
        return recommendRepository.findAll();
    }
}
