package com.starer.pojo.dto;

import java.sql.Timestamp;

/**
 * @Author: pengxiong
 * @Date: 2024/01/01 14:21:19
 * @Version: V1.0
 * @Description:
 **/
public class MessageDto {

    private String messageContent;
    private int validTimeInterval;
    private int timeUnit;

    public MessageDto() {
    }

    public MessageDto(String messageContent) {
        this.messageContent = messageContent;
    }


    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public int getValidTimeInterval() {
        return validTimeInterval;
    }

    public void setValidTimeInterval(int validTimeInterval) {
        this.validTimeInterval = validTimeInterval;
    }

    public int getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(int timeUnit) {
        this.timeUnit = timeUnit;
    }

    @Override
    public String toString() {
        return "MessageDto{" +
                "messageContent='" + messageContent + '\'' +
                ", validTimeInterval=" + validTimeInterval +
                ", timeUnit=" + timeUnit +
                '}';
    }
}
