package com.starer.dao;

import com.starer.pojo.entity.Task;
import org.apache.ibatis.annotations.Param;

public interface ITaskDao {

    Task selectTaskById(String taskId);
    Task[] selectTaskByStageId(String stageId);
    Task[] selectTaskByProjectId(String projectId);
    Task[] selectTaskByCondition(@Param("taskId") String taskId,@Param("status") String status,
                                 @Param("stageId") String stageId, @Param("projectId") String projectId);

    int insertTask(@Param("task") Task task, @Param("projectId") String projectId);
    int deleteTask(@Param("taskId") String taskId);
    int updateTask(@Param("task") Task task);
}
