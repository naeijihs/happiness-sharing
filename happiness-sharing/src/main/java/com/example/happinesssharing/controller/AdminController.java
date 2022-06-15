package com.example.happinesssharing.controller;

import com.example.happinesssharing.entity.Recommend;
import com.example.happinesssharing.entity.Slideshow;
import com.example.happinesssharing.service.AdminService;
import com.example.happinesssharing.service.ShareService;
import com.example.happinesssharing.service.SharerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private SharerService sharerService;
    @Autowired
    private ShareService shareService;
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
    @PostMapping("common/recommend/add")
    public Map addRecommend(@RequestBody Recommend recommend){
        return Map.of("recommend",adminService.addRecommend(recommend));
    }
    @GetMapping("common/recommend/getAll")
    public Map getRecommends(){
        return Map.of("recommends",adminService.getRecommends());
    }
    @GetMapping("common/recommend/getOne/{recommendId}")
    public Map getRecommend(@PathVariable int recommendId){
        return Map.of("recommend",adminService.getRecommend(recommendId));
    }
    @GetMapping("common/recommend/delete/{recommendId}")
    public void deleteRecommend(@PathVariable int recommendId){
        adminService.deleteRecommend(recommendId);
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
    @GetMapping("admin/sharer/all")
    public Map getSharers(){
        return  Map.of("sharers",sharerService.getSharers());
    }
    @GetMapping("admin/sharer/delete/{sharerId}")
    public void deleteSharer(@PathVariable int sharerId){
        sharerService.deleteSharer(sharerId);
    }
    @GetMapping("admin/share/all")
    public Map getShares(){
        return Map.of("allShares",shareService.getAllShares());
    }
    @GetMapping("admin/share/delete/{shareId}")
    public void deleteShare(@PathVariable int shareId){
        shareService.deleteShare(shareId);
    }
    @GetMapping("admin/comment/all")
    public Map getComments(){
        return Map.of("comments",sharerService.getComments());
    }
    @GetMapping("admin/comment/delete/{commentId}")
    public void deleteComment(@PathVariable int commentId){
        sharerService.deleteSendComment(commentId);
    }
    @GetMapping("admin/message/all")
    public Map getMessages(){
        return Map.of("messages",sharerService.getMessages());
    }
    @GetMapping("admin/message/delete/{messageId}")
    public void deleteMessage(@PathVariable int messageId){
        sharerService.deleteMessage(messageId);
    }
}
