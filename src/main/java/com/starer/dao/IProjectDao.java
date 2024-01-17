package com.starer.dao;

import com.starer.pojo.entity.Project;
import org.apache.ibatis.annotations.Param;

public interface IProjectDao {

    Project queryProjectById(@Param("projectId") String projectId);
    Project[] queryProjectsByBuyerId(@Param("buyerId") String buyerId);
    Project[] queryProjectsByDeveloperManagerId(@Param("developerManagerId") String developerManagerId);
    String[] queryProjectsIdByBuyerId(@Param("buyerId") String buyerId);
    String[] queryProjectsIdByDeveloperManagerId(@Param("developerManagerId") String developerManagerId);

    Project[] selectProjects(@Param("buyerId") String buyerId, @Param("developerManagerId") String developerManagerId);

    boolean insertProject(Project project);
    boolean deleteProject(@Param("projectId") String projectId);

}
