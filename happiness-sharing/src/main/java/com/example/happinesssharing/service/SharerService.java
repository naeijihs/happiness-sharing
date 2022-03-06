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
        Sharer reporter=sharerRepository.findById(requestComponent.getUid()).orElse(null);
        return reportRepository.findByReporter(reporter);
    }
    public Collection addCollection(int id){
        Share share=shareRepository.findById(id).orElse(null);
        Sharer collector=sharerRepository.findById(requestComponent.getUid()).orElse(null);
        Collection collection=new Collection();
        collection.setCollector(collector);
        collection.setShare(share);
        return collectionRepository.save(collection);
    }
    public void cancelCollection(int id){
        collectionRepository.deleteById(id);
    }
    public List<Collection> getOwnCollections(){
        Sharer collector=sharerRepository.findById(requestComponent.getUid()).orElse(null);
        return collectionRepository.findByCollector(collector);
    }
    public Message sendMessage(Message message){
        message.setSender(sharerRepository.findById(requestComponent.getUid()).orElse(null));
        return messageRepository.save(message);
    }
    public List<Message> getSendMessages(){
        Sharer sender=sharerRepository.findById(requestComponent.getUid()).orElse(null);
        return messageRepository.findBySender(sender);
    }
    public List<Message> getReceiveMessages(){
        Sharer receiver=sharerRepository.findById(requestComponent.getUid()).orElse(null);
        return messageRepository.findByReceiver(receiver);
    }
    public void deleteMessage(int id){
        messageRepository.deleteById(id);
    }
}
