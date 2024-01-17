package com.starer.service;

import com.starer.pojo.entity.Task;
import org.apache.ibatis.annotations.Param;

public interface ITaskService {

    Task selectTaskById(String taskId);
    Task[] selectTaskByStageId(String stageId);
    Task[] selectTaskByProjectId(String projectId);
    Task[] queryTaskByCondition(String taskId, String status, String stageId, String projectId);

    int insertTask(Task task, String projectId);
    int deleteTask(String taskId);
    int updateTask(Task task);
}
