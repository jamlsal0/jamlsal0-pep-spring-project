package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private AccountRepository accountRepository;

    public Message addMessage(Message message) {
        return messageRepository.save(message);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Optional<Message> getMessageById(Integer id) {
        return messageRepository.findById(id);
    }

    public int deleteMessageById(Integer id) {
        return messageRepository.deleteMessageById(id);
    }

    public int updateMessagebyId(Integer id, Message message) {
        if (!messageRepository.existsById(id)) {
            return -1;
        }

        if (!isValidMessageText(message)) {
            return -1;
        }

        if (messageRepository.save(message) != null) {
            return 1;
        }

        return -1;
    }

    public List<Message> getAllMessagesByAccountId(Integer id) {
        return messageRepository.findAllPostedBy(id);
    }

    public boolean isValidMessageText(Message message) {
        if (message.getMessageText().isBlank() || message.getMessageText().length() > 254) {
            return false;
        }
        return true;
    }

    public boolean messageAccountExists(Message message) {
        if (accountRepository.existsById(message.getPostedBy())) {
            return true;
        }

        return false;
    }
}
