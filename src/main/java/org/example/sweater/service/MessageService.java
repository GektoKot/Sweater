package org.example.sweater.service;

import org.example.sweater.domain.Message;
import org.example.sweater.domain.User;
import org.example.sweater.domain.dto.MessageDto;
import org.example.sweater.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Service
public class MessageService {

    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private EntityManager em;

    public Page<MessageDto> messageList(Pageable pageable, String filter, User user) {

        if (filter == null || filter.isEmpty()) {
            return messageRepo.findAll(pageable, user);
        } else {
            return messageRepo.findByTag(filter, pageable, user);
        }

    }

    public Page<MessageDto> messageListForUser(Pageable pageable, User currentUser, User author) {
        return messageRepo.findByUser(pageable, author, currentUser);// currentUser,
    }
//    public Page<MessageDto> messageListAll(Pageable pageable) {
//        return messageRepo.findAll(pageable);
//    }
}
