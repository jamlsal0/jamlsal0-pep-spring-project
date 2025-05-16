package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.example.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Integer>{
    @Transactional
    @Modifying
    @Query("delete from Message m where m.id = ?1")
    int deleteMessageById(Integer messageId);

    @Query("select m from Message m where m.postedBy = ?1")
    List<Message> findAllPostedBy(Integer postedBy);

}
