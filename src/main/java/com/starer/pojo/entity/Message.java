package com.starer.pojo.entity;

import java.sql.Timestamp;

/**
 * @Author: pengxiong
 * @Date: 2024/01/01 14:05:22
 * @Version: V1.0
 * @Description:
 **/
public class Message {
    private Long messageId;
    private int messageType;
    private int messagePlatform;
    private String messageContent;
    private Timestamp sendTime;
    private Timestamp finishTime;
    private int validTimeInterval;
    private String sender;
    private String receiver;
    private int timeUnit;

    private char expire;
    private String role;

    public Message() {
    }

    public Message(int messageType, int messagePlatform,
                   String messageContent, Timestamp sendTime,
                   Timestamp finishTime,
                   String sender, String receiver) {
        this.messageType = messageType;
        this.messagePlatform = messagePlatform;
        this.messageContent = messageContent;
        this.sendTime = sendTime;
        this.finishTime = finishTime;
        this.sender = sender;
        this.receiver = receiver;
    }

    public Message(int messageType, int messagePlatform,
                   String messageContent, Timestamp sendTime,
                   int validTimeInterval, String sender,
                   String receiver, int timeUnit) {
        this.messageType = messageType;
        this.messagePlatform = messagePlatform;
        this.messageContent = messageContent;
        this.sendTime = sendTime;
        this.validTimeInterval = validTimeInterval;
        this.sender = sender;
        this.receiver = receiver;
        this.timeUnit = timeUnit;
    }

    public Message(int messageType, int messagePlatform,
                   String messageContent, Timestamp sendTime,
                   Timestamp finishTime,
                   String sender, String receiver, String role) {
        this.messageType = messageType;
        this.messagePlatform = messagePlatform;
        this.messageContent = messageContent;
        this.sendTime = sendTime;
        this.finishTime = finishTime;
        this.sender = sender;
        this.receiver = receiver;
        this.role = role;
    }

    public Message(Long messageId, int messageType, int messagePlatform,
                   String messageContent, Timestamp sendTime,
                   Timestamp finishTime, int validTimeInterval,
                   String sender, String receiver, int timeUnit) {
        this.messageId = messageId;
        this.messageType = messageType;
        this.messagePlatform = messagePlatform;
        this.messageContent = messageContent;
        this.sendTime = sendTime;
        this.finishTime = finishTime;
        this.validTimeInterval = validTimeInterval;
        this.sender = sender;
        this.receiver = receiver;
        this.timeUnit = timeUnit;
    }

    public Message(Long messageId, int messageType,
                   int messagePlatform, String messageContent,
                   Timestamp sendTime, Timestamp finishTime,
                   int validTimeInterval, String sender,
                   String receiver, int timeUnit,
                   char expire, String role) {
        this.messageId = messageId;
        this.messageType = messageType;
        this.messagePlatform = messagePlatform;
        this.messageContent = messageContent;
        this.sendTime = sendTime;
        this.finishTime = finishTime;
        this.validTimeInterval = validTimeInterval;
        this.sender = sender;
        this.receiver = receiver;
        this.timeUnit = timeUnit;
        this.expire = expire;
        this.role = role;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public int getMessagePlatform() {
        return messagePlatform;
    }

    public void setMessagePlatform(int messagePlatform) {
        this.messagePlatform = messagePlatform;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Timestamp getSendTime() {
        return sendTime;
    }

    public void setSendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
    }

    public Timestamp getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Timestamp finishTime) {
        this.finishTime = finishTime;
    }

    public int getValidTimeInterval() {
        return validTimeInterval;
    }

    public void setValidTimeInterval(int validTimeInterval) {
        this.validTimeInterval = validTimeInterval;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public int getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(int timeUnit) {
        this.timeUnit = timeUnit;
    }

    public char getExpire() {
        return expire;
    }

    public void setExpire(char expire) {
        this.expire = expire;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", messageType=" + messageType +
                ", messagePlatform=" + messagePlatform +
                ", messageContent='" + messageContent + '\'' +
                ", sendTime=" + sendTime +
                ", finishTime=" + finishTime +
                ", validTimeInterval=" + validTimeInterval +
                ", sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", timeUnit=" + timeUnit +
                ", expire=" + expire +
                ", role='" + role + '\'' +
                '}';
    }
}
