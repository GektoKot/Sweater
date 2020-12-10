package org.example.sweater.service;

import org.example.sweater.domain.Message;
import org.example.sweater.domain.User;
import org.example.sweater.repos.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    private MessageRepo messageRepo;

    public Page<Message> messageList(Pageable pageable, String filter) {

        if (filter == null || filter.isEmpty()) {
            return messageRepo.findAll(pageable);
        } else {
            return messageRepo.findByTag(filter, pageable);
        }

    }

    public Page<Message> messageListForUser(Pageable pageable, User currentUser, User author) {
        return messageRepo.findByUser(pageable, author);// currentUser,
    }
    public Page<Message> messageListAll(Pageable pageable) {
        return messageRepo.findAll(pageable);
    }
}
