package com.starer.controller;

import com.starer.pojo.entity.Project;
import com.starer.pojo.entity.user.Buyer;
import com.starer.pojo.entity.user.Manager;
import com.starer.pojo.vo.ProjectDto;
import com.starer.pojo.vo.UserDto;
import com.starer.service.*;
import com.starer.util.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/page")
public class SpecialPageController {

    private IAdminService adminService;
    private IBuyerService buyerService;
    private IManagerService managerService;
    private IProjectService projectService;
    private IStageService stageService;
    private ITaskService taskService;

    @Autowired
    public SpecialPageController(IAdminService adminService, IBuyerService buyerService,
                                 IManagerService managerService, IProjectService projectService,
                                 IStageService stageService, ITaskService taskService) {
        this.adminService = adminService;
        this.buyerService = buyerService;
        this.managerService = managerService;
        this.projectService = projectService;
        this.stageService = stageService;
        this.taskService = taskService;
    }

    /**
     * 系统管理员页面请求处理
     */

    @GetMapping("/admin/project_management")
    public String projectManagement(HttpServletResponse response, Model model) {
        response.setHeader("Cache-Control", "max-age=100");
        return "/WEB-INF/jsp/admin/project_management.jsp";
    }
    @GetMapping("/admin/user_management")
    public String userManagement(Model model) {
        return "/WEB-INF/jsp/admin/user_management.jsp";
    }


    /**
     * 甲方页面请求处理
     */
    @GetMapping("/buyer/project_information")
    public String getPagePorjectInformationForBuyer(HttpServletRequest request, Model model) {

        String userId = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if("userId".equals(cookie.getName())) userId = cookie.getValue();
        }
        //TODO: 判空
        if(userId == null) {
            model.addAttribute("msg", "cookie中未找到用户ID");
            return "/error.jsp";
        }

        Buyer buyer = buyerService.getInformation(userId, null, null);
        Project project =
                projectService.getProjectInformationById(buyer.getHandlingProject());
        Manager manager =
                managerService.getInformation(project.getDeveloperManagerId(), null, null);

        UserDto buyerDto = new UserDto(buyer.getId(), buyer.getName(), buyer.getPhone(),
                buyer.getEmail(), RoleType.BUYER.getRoleTypeInt());
        model.addAttribute("buyerDto", buyerDto);

        ProjectDto projectDto = new ProjectDto(project.getProjectId(), project.getProjectName(),
                project.getCreateTime(), project.getFinishTime());
        model.addAttribute("projectDto", projectDto);


        if(manager != null) {
            UserDto managerDto = new UserDto(manager.getManagerId(), manager.getManagerName(), manager.getPhone(),
                    manager.getEmail(), RoleType.MANAGER.getRoleTypeInt());
            model.addAttribute("managerDto", managerDto);
        } else {
            UserDto managerDto = new UserDto();
            model.addAttribute("managerDto", managerDto);
        }

        return "/WEB-INF/jsp/manager/project_information.jsp";
    }

    /**
     * 项目经理页面请求
     */
    @GetMapping("/manager/project_information")
    public String getPageProjectForManager(HttpServletRequest request, Model model) {

        String userId = null;
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if("userId".equals(cookie.getName())) userId = cookie.getValue();
        }
        //TODO: 判空
        if(userId == null) {
            model.addAttribute("msg", "cookie中未找到用户ID");
            return "/error.jsp";
        }

        Manager manager =
                managerService.getInformation(userId, null, null);
        Project project =
                projectService.getProjectInformationById(manager.getHandlingProject());
        Buyer buyer = buyerService.getInformation(project.getBuyerId(), null, null);


        UserDto managerDto = new UserDto(manager.getManagerId(), manager.getManagerName(), manager.getPhone(),
                manager.getEmail(), RoleType.MANAGER.getRoleTypeInt());
        model.addAttribute("managerDto", managerDto);

        ProjectDto projectDto = new ProjectDto(project.getProjectId(), project.getProjectName(),
                project.getCreateTime(), project.getFinishTime());
        model.addAttribute("projectDto", projectDto);

        if(buyer != null) {
            UserDto buyerDto = new UserDto(buyer.getId(), buyer.getName(), buyer.getPhone(),
                    buyer.getEmail(), RoleType.BUYER.getRoleTypeInt());
            model.addAttribute("buyerDto", buyerDto);
        } else {
            UserDto buyerDto = new UserDto();
            model.addAttribute("buyerDto", buyerDto);
        }

        return "/WEB-INF/jsp/manager/project_information.jsp";
    }
}
