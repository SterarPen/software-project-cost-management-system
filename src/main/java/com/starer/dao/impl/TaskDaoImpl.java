package com.starer.dao.impl;

import com.starer.dao.ITaskDao;
import com.starer.pojo.entity.Task;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @Author: pengxiong
 * @Date: 2023/12/28 16:55:34
 * @Version: V1.0
 * @Description:
 **/
@Repository
public class TaskDaoImpl implements ITaskDao {

    private SqlSessionTemplate sqlSessionTemplate;

    @Autowired
    public TaskDaoImpl(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }
    @Override
    public Task selectTaskById(String taskId) {
        return sqlSessionTemplate.getMapper(ITaskDao.class).selectTaskById(taskId);
    }

    @Override
    public Task[] selectTaskByStageId(String stageId) {
        return sqlSessionTemplate.getMapper(ITaskDao.class).selectTaskByStageId(stageId);
    }

    @Override
    public Task[] selectTaskByProjectId(String projectId) {
        return sqlSessionTemplate.getMapper(ITaskDao.class).selectTaskByProjectId(projectId);
    }

    @Override
    public Task[] selectTaskByCondition(String taskId, String status, String stageId, String projectId) {
        return sqlSessionTemplate.getMapper(ITaskDao.class).selectTaskByCondition(taskId, status, stageId, projectId);
    }

    @Override
    public int insertTask(Task task, String projectId) {
        return sqlSessionTemplate.getMapper(ITaskDao.class).insertTask(task, projectId);
    }

    @Override
    public int deleteTask(String taskId) {
        return sqlSessionTemplate.getMapper(ITaskDao.class).deleteTask(taskId);
    }

    @Override
    public int updateTask(Task task) {
        return sqlSessionTemplate.getMapper(ITaskDao.class).updateTask(task);
    }
}
