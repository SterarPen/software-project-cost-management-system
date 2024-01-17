package com.starer.service;

import com.starer.pojo.entity.Project;


public interface IProjectService {

    Project[] getAllProject();
    Project getProjectInformationById(String projectId);
    String[] getAllProjectIdOfBuyer(String buyerId);
    String[] getAllProjectIdOfDeveloperManager(String developerManagerId);

    int addProject(String projectName, String buyerId, String developerManagerId);
    int deleteProject(String projectId);

}
