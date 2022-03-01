package com.example.happinesssharing.controller;

import com.example.happinesssharing.entity.Recommend;
import com.example.happinesssharing.entity.Slideshow;
import com.example.happinesssharing.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @PostMapping("admin/recommend/add")
    public Map addRecommend(@RequestBody Recommend recommend){
        return Map.of("recommend",adminService.addRecommend(recommend));
    }
    @GetMapping("admin/recommend/delete/{recommendId}")
    public void deleteRecommend(@PathVariable int recommendId){
        adminService.deleteRecommend(recommendId);
    }
    @GetMapping("common/getOneRecommend/{recommendId}")
    public Map getOneRecommend(@PathVariable int recommendId){
        return Map.of("recommend",adminService.getOneRecommend(recommendId));
    }
    @GetMapping("common/getAllRecommends")
    public Map getAllRecommends(){
        return Map.of("recommends",adminService.getAllRecommends());
    }
    @PostMapping("admin/slideshow/add")
    public Map addSlideshow(@RequestBody Slideshow slideshow){
        return Map.of("slideshow",adminService.addSlideshow(slideshow));
    }
    @GetMapping("admin/slideshow/delete/{slideshowId}")
    public void deleteSlideshow(@PathVariable int slideshowId){
        adminService.deleteSlideshow(slideshowId);
    }
    @GetMapping("common/getAllSlideshows")
    public Map getAllSlideshows(){
        return Map.of("slideshows",adminService.getAllSlideshows());
    }
    @GetMapping("admin/report/getAllUnsettledReports")
    public Map getAllUnsettled(){
        return Map.of("allUnsettledReports",adminService.getAllUnsettledReports());
    }
    @GetMapping("admin/report/accept/{reportId}")
    public void acceptReport(@PathVariable int reportId){
        adminService.acceptReport(reportId);
    }
    @GetMapping("admin/report/refuse/{reportId}")
    public void refuseReport(@PathVariable int reportId){
        adminService.refuseReport(reportId);
    }
}
