package com.starer.dao;

import com.starer.pojo.entity.Message;
import org.apache.ibatis.annotations.Param;

public interface IMessageDao {

    Message selectMessageById(String messageId);
    Message[] selectAllMessageByRole(@Param("role") String role,@Param("expire") char expire);
    Message[] selectAllMessageByReceiver(@Param("receiver") String receiver, @Param("expire") char expire);
    int insertMessage(Message message);
    int updateMessage(Message message);
    int deleteMessage(String messageId);
}
