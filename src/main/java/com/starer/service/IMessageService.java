package com.starer.service;



public interface IMessageService {

    Long sendIdentifyCode(String receiver, int messageSendPlatform, String role);

    boolean identify(String messageId, String userInputCode);

    boolean identifyByReceiver(String receiver, String userInputCode);


}
