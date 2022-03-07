package com.example.happinesssharing.controller;

import com.example.happinesssharing.entity.*;
import com.example.happinesssharing.service.ShareService;
import com.example.happinesssharing.service.SharerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/sharer/")
public class SharerController {
    @Autowired
    private SharerService sharerService;
    @Autowired
    private ShareService shareService;
    @GetMapping("info/getOwnInfo")
    public Map getOwnInfo(){
        return Map.of("sharer",sharerService.getOwnInfo());
    }
    @PostMapping("info/modifyInfo")
    public Map modifyInfo(@RequestBody Sharer newSharer){
        return Map.of("sharer",sharerService.modifySharer(newSharer),"info","个人信息修改成功");
    }
    @PostMapping("share/add")
    public Map addShare(@RequestBody Share share){
        return Map.of("share",shareService.addShare(share),"info","分享成功");
    }
    @GetMapping("share/delete/{shareId}")
    public void deleteShare(@PathVariable int shareId){
        shareService.deleteShare(shareId);
    }
    @GetMapping("share/getOwnShares")
    public Map getOwnShares(){
        return Map.of("ownShares",shareService.getOwnShares());
    }
    @GetMapping("share/getAllShares")
    public Map getAllShares(){
        return Map.of("allShares",shareService.getAllShares());
    }
    @GetMapping("share/agree/{shareId}")
    public Map agree(@PathVariable int shareId){
        return Map.of("info", shareService.agree(shareId));
    }
    @PostMapping("share/comment/add")
    public Map addComment(@RequestBody Comment comment){
        return Map.of("comment",shareService.addComment(comment),"info","评论发布成功");
    }
    @GetMapping("share/comment/getOwnSendComments")
    public Map getOwnSendComments(){
        return Map.of("comments",sharerService.getOwnSendComments());
    }
    @GetMapping("share/comment/deleteSendComment/{commentId}")
    public void deleteSendComments(@PathVariable int commentId) {
        sharerService.deleteSendComment(commentId);
    }
    @PostMapping("share/report/send")
    public Map addReport(@RequestBody Report report){
        return Map.of("report",shareService.addReport(report),"info","举报已提交");
    }
    @GetMapping("report/getSendReports")
    public Map getSendReports(){
        return Map.of("reports",sharerService.getSendReports());
    }
    @GetMapping("collection/addCollection/{shareId}")
    public Map addCollection(@PathVariable int shareId){
        return Map.of("info",sharerService.addCollection(shareId));
    }
    @GetMapping("collection/cancelCollection/{shareId}")
    public void cancelCollection(@PathVariable int shareId){
        sharerService.cancelCollection(shareId);
    }
    @GetMapping("collection/getOwnCollections")
    public Map getOwnCollections(){
        return Map.of("collections",sharerService.getOwnCollections());
    }
    @PostMapping("message/send")
    public Map sendMessage(@RequestBody Message message){
        return Map.of("message",sharerService.sendMessage(message),"info","留言成功");
    }
    @GetMapping("message/getMessages")
    public Map getMessages(){
        return Map.of("sendMessages",sharerService.getSendMessages(),"receiveMessages",sharerService.getReceiveMessages());
    }
    @GetMapping("message/delete/{messageId}")
    public void deleteMessage(@PathVariable int messageId){
        sharerService.deleteMessage(messageId);
    }
}
