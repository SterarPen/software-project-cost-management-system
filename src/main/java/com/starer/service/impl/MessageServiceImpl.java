package com.starer.service.impl;

import com.starer.Enum.MessageSendPlatform;
import com.starer.Enum.MessageType;
import com.starer.dao.IMessageDao;
import com.starer.pojo.entity.Message;
import com.starer.service.IMessageService;
import com.starer.util.SendMessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;


@Service
public class MessageServiceImpl implements IMessageService {

    private IMessageDao messageDao;

    @Autowired
    public MessageServiceImpl(IMessageDao messageDao) {
        this.messageDao = messageDao;
    }

    @Transactional
    @Override
    public Long sendIdentifyCode(String receiver, int messageSendPlatform, String role) {
        Integer identifyCode = null;
        switch (messageSendPlatform) {
            case MessageSendPlatform.PHONE:
                identifyCode =SendMessageUtil.sendIdentifyCodeByPhone(receiver);
                break;
            case MessageSendPlatform.EMAIL:
                identifyCode = SendMessageUtil.sendIdentifyCodeByEmail(receiver);
                break;
        }

        if(identifyCode == null) {
            return null;
        }
        long current = System.currentTimeMillis();
        Message[] messages = messageDao.selectAllMessageByRole(role, '1');
        for (Message message : messages) {
            message.setExpire('0');
            messageDao.updateMessage(message);
        }
        Message message = new Message(MessageType.IDENTIFY_CODE, MessageSendPlatform.PHONE,
                identifyCode.toString(), new Timestamp(current), new Timestamp(current + 1000*60*2),
                "11111111111", receiver, role);
        int i = messageDao.insertMessage(message);
        return i==1?message.getMessageId():null;
    }

    @Override
    public boolean identify(String messageId, String userInputCode) {
        if(userInputCode == null || userInputCode.length() == 0) {
            return false;
        }

        Message message = messageDao.selectMessageById(messageId);
        if(message.getFinishTime().before(new Timestamp(System.currentTimeMillis())) ||
                message.getExpire() != '1' ||
                !userInputCode.equals(message.getMessageContent())) {
            return false;
        }
        message.setExpire('0');
        messageDao.updateMessage(message);
        return true;
    }

    @Override
    public boolean identifyByReceiver(String receiver, String userInputCode) {
        if(userInputCode == null || userInputCode.length() == 0) {
            return false;
        }

        Message[] messages = messageDao.selectAllMessageByReceiver(receiver, '1');
        if(messages == null || messages.length == 0) {
            return false;
        }

        boolean equals = messages[0].getMessageContent().equals(userInputCode);
        if(!equals) return false;
        messages[0].setExpire('0');
        messageDao.updateMessage(messages[0]);
        return true;
    }
}
