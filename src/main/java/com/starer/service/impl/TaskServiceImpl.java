package com.starer.service.impl;

import com.starer.dao.ITaskDao;
import com.starer.pojo.entity.Task;
import com.starer.service.ITaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: pengxiong
 * @Date: 2023/12/28 17:15:26
 * @Version: V1.0
 * @Description:
 **/
@Service
public class TaskServiceImpl implements ITaskService {

    private ITaskDao taskDao;

    @Autowired
    public TaskServiceImpl(ITaskDao taskDao) {
        this.taskDao = taskDao;
    }

    @Override
    public Task selectTaskById(String taskId) {
        return taskDao.selectTaskById(taskId);
    }

    @Override
    public Task[] selectTaskByStageId(String stageId) {
        return taskDao.selectTaskByStageId(stageId);
    }

    @Override
    public Task[] selectTaskByProjectId(String projectId) {
        return taskDao.selectTaskByProjectId(projectId);
    }

    @Override
    public Task[] queryTaskByCondition(String taskId, String status, String stageId, String projectId) {
        return taskDao.selectTaskByCondition(taskId, status, stageId, projectId);
    }

    @Override
    public int insertTask(Task task, String projectId) {
        return taskDao.insertTask(task, projectId);
    }

    @Override
    public int deleteTask(String taskId) {
        return  taskDao.deleteTask(taskId);
    }

    @Override
    public int updateTask(Task task) {
        return taskDao.updateTask(task);
    }
}
