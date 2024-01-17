package com.starer.dao.impl;

import com.starer.dao.IMessageDao;
import com.starer.pojo.entity.Message;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @Author: pengxiong
 * @Date: 2024/01/01 14:28:14
 * @Version: V1.0
 * @Description:
 **/
@Repository
public class MessageDaoImpl implements IMessageDao {

    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }
    @Override
    public Message selectMessageById(String messageId) {
        return sqlSessionTemplate.getMapper(IMessageDao.class).selectMessageById(messageId);
    }

    @Override
    public Message[] selectAllMessageByRole(String role, char expire) {
        return sqlSessionTemplate.getMapper(IMessageDao.class).selectAllMessageByRole(role, expire);
    }

    @Override
    public Message[] selectAllMessageByReceiver(String receiver, char expire) {
        return sqlSessionTemplate.getMapper(IMessageDao.class).selectAllMessageByReceiver(receiver, expire);
    }

    @Override
    public int insertMessage(Message message) {
        return sqlSessionTemplate.getMapper(IMessageDao.class).insertMessage(message);
    }

    @Override
    public int updateMessage(Message message) {
        return sqlSessionTemplate.getMapper(IMessageDao.class).updateMessage(message);
    }

    @Override
    public int deleteMessage(String messageId) {
        return sqlSessionTemplate.getMapper(IMessageDao.class).deleteMessage(messageId);
    }
}
