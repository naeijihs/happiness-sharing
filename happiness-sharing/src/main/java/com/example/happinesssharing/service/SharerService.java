package com.example.happinesssharing.service;

import com.example.happinesssharing.component.RequestComponent;
import com.example.happinesssharing.entity.*;
import com.example.happinesssharing.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class SharerService {
    @Autowired
    private RequestComponent requestComponent;
    @Autowired
    private SharerRepository sharerRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private ShareRepository shareRepository;
    @Autowired
    private CollectionRepository collectionRepository;
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private PasswordEncoder encoder;

    public Sharer addSharer(User user){
        Sharer sharer=new Sharer();
        user.setRole(User.Role.SHARER);
        user.setPassword(encoder.encode(user.getPassword()));
        sharer.setUser(user);
        return sharerRepository.save(sharer);
    }
    public Sharer getOwnInfo(){
        return sharerRepository.findById(requestComponent.getUid()).orElse(null);
    }
    public Sharer modifySharer(Sharer newSharer) {
        Sharer sharer = sharerRepository.findById(requestComponent.getUid()).orElse(null);
        sharer.setName(newSharer.getName());
        sharer.setAge(newSharer.getAge());
        sharer.setContact(newSharer.getContact());
        sharer.setProvince(newSharer.getProvince());
        sharer.setSex(newSharer.getSex());
        return sharerRepository.save(sharer);
    }
    public List<Comment> getOwnSendComments(){
        return sharerRepository.findById(requestComponent.getUid()).orElse(null).getComments();
    }
    public void deleteSendComment(int id){
        commentRepository.deleteById(id);
    }
    public List<Report> getSendReports(){
        return sharerRepository.findById(requestComponent.getUid()).orElse(null).getReports();
    }
    public String addCollection(int id){
        Share share=shareRepository.findById(id).orElse(null);
        Sharer collector=sharerRepository.findById(requestComponent.getUid()).orElse(null);
        List<Collection> c=collector.getCollections().stream().filter(collection -> collection.getShare()==share).collect(Collectors.toList());
        if(c.size()==0){
            Collection collection=new Collection();
            collection.setCollector(collector);
            collection.setShare(share);
            collectionRepository.save(collection);
            return "收藏成功";
        }
        else
            return "您已收藏此分享";

    }
    public void cancelCollection(int id){
        collectionRepository.deleteById(id);
    }
    public List<Collection> getOwnCollections(){
        return sharerRepository.findById(requestComponent.getUid()).orElse(null).getCollections();
    }
    public Message sendMessage(Message message){
        message.setReceiver(sharerRepository.findById(message.getReceiver().getId()).orElse(null));
        message.setSender(sharerRepository.findById(requestComponent.getUid()).orElse(null));
        return messageRepository.save(message);
    }
    public List<Message> getSendMessages(){
        return sharerRepository.findById(requestComponent.getUid()).orElse(null).getSends();
    }
    public List<Message> getReceiveMessages(){
        return sharerRepository.findById(requestComponent.getUid()).orElse(null).getReceives();
    }
    public void deleteMessage(int id){
        messageRepository.deleteById(id);
    }
}
