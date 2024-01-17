package com.starer.controller;

import com.alibaba.fastjson2.JSONArray;
import com.starer.pojo.entity.Project;
import com.starer.pojo.entity.user.Buyer;
import com.starer.pojo.entity.user.Manager;
import com.starer.pojo.entity.user.Admin;
import com.starer.pojo.vo.UserDto;
import com.starer.service.IBuyerService;
import com.starer.service.IManagerService;
import com.starer.service.IAdminService;
import com.starer.service.IProjectService;
import com.starer.util.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.nio.charset.CharsetDecoder;
import java.sql.SQLIntegrityConstraintViolationException;

/**
 * @Author: pengxiong
 * @Date: 2023/12/04 19:48:42
 * @Version: V1.0
 * @Description:
 **/
@Controller
public class AdminController {

    private IAdminService adminService;
    private IProjectService projectService;
    private IManagerService managerService;
    private IBuyerService buyerService;

    @Autowired
    public AdminController(IAdminService adminService, IProjectService projectService,
                           IManagerService managerService, IBuyerService buyerService) {
        this.adminService = adminService;
        this.projectService = projectService;
        this.managerService = managerService;
        this.buyerService = buyerService;
    }



    @ResponseBody
    @RequestMapping(value = "/admin/all_project_information", produces = "application/json; charset=utf-8",
            method = RequestMethod.GET)
    public String returnProjectInformationByJson(HttpServletResponse rsp) {
        Project[] allProject = projectService.getAllProject();
        JSONArray jsonArray = new JSONArray();
        for (Project project : allProject) {
            jsonArray.add(project);
        }
        String jsonString = jsonArray.toString();
        return jsonString;
    }

    @ResponseBody
    @RequestMapping(value = "/admin/all_user_information", produces = "application/json; charset=utf-8",
            method = RequestMethod.GET)
    public String returnAllUserInformationByJson(HttpServletResponse rsp) {
        Manager[] allManagers =
                managerService.getAllManager(null, null, null);
        JSONArray jsonArray = new JSONArray();
        for (Manager manager : allManagers) {
            UserDto user = new UserDto(manager.getManagerId(), manager.getManagerName(),
                    manager.getPhone(), manager.getEmail(),
                    manager.getCreateTime().toString(),
                    manager.getHandlingProject(), RoleType.MANAGER.getRoleTypeInt());
            jsonArray.add(user);
        }
        Buyer[] buyers = buyerService.getBuyers(null, null, null);
        for (Buyer buyer : buyers) {
            UserDto user = new UserDto(buyer.getId(), buyer.getName(), buyer.getPhone(), buyer.getEmail()
                    ,buyer.getCreateTime().toString(),
                    buyer.getHandlingProject(), RoleType.BUYER.getRoleTypeInt());
            jsonArray.add(user);
        }
        String jsonString = jsonArray.toString();
        return jsonString;
    }

    @ResponseBody
    @GetMapping("admin/data/all_buyer")
    public String getAllBuyer() {
        Buyer[] buyers = buyerService.getBuyers(null, null, null);
        JSONArray jsonArray = new JSONArray();
        for (Buyer buyer : buyers) {
            jsonArray.add(new UserDto(buyer.getId(), buyer.getName()));
        }
        String string = jsonArray.toString();
        return string;
    }

    @ResponseBody
    @GetMapping("/admin/data/all_developer_manager")
    public String getAllDeveloperManager() {
        Manager[] allDeveloperManager =
                managerService.getAllManager(null, null, null);
        JSONArray jsonArray = new JSONArray();
        for (Manager developerManager : allDeveloperManager) {
            jsonArray.add(new UserDto(developerManager.getManagerId(), developerManager.getManagerName()));
        }
        String string = jsonArray.toString();
        return string;
    }

    @PostMapping("/admin/op/add_project")
    public String addProject(HttpServletRequest req, HttpServletResponse rsp, Model model) throws UnsupportedEncodingException {
        req.setCharacterEncoding("utf-8");
        String projectName = req.getParameter("project_name");
        String buyerId = req.getParameter("buyer_id");
        String developerManagerId = req.getParameter("developer_manager_id");

        String result = null;
        Buyer[] buyers = buyerService.getBuyers(buyerId, null, null);
        if(buyers == null || buyers.length == 0) result = "failure";
                Manager[] allDeveloperManager = managerService.getAllManager(developerManagerId, null, null);
        if(allDeveloperManager == null || allDeveloperManager.length == 0) result = "购买方或项目经理不存在";
        int r = projectService.addProject(projectName, buyerId, developerManagerId);
        if(r == 1) result = "success";
        else result = "failure";
        model.addAttribute("message", result);
        model.addAttribute("ahead_link", "/page/admin/project_management");
        return "/Message.jsp";
    }
    @PostMapping("/admin/op/delete_project")
    public String deleteProject(HttpServletRequest req, Model model) {
        String projectId = req.getParameter("project_id");

        String result = null;
        int i = projectService.deleteProject(projectId);
        if(i == 1) result = "success";
        else result = "failure";
        model.addAttribute("message", result);
        model.addAttribute("ahead_link", "/page/admin/project_management");
        return "/Message.jsp";
    }

    /***
     * 新建用户表单提交后运行的代码
     * @param req
     * @param rsp
     * @param model
     * @return
     */
    @PostMapping("/admin/op/add_user")
    public String addUser(HttpServletRequest req, HttpServletResponse rsp, Model model) throws UnsupportedEncodingException {
        req.setCharacterEncoding("utf-8");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String projectId = req.getParameter("projectId");

        if(name == null || password == null || email == null || phone == null || projectId == null) {
            model.addAttribute("message", "用户信息不能为空");
            model.addAttribute("ahead_link", "/page/admin/user_management");
            return "/Message.jsp";
        }

        // 检验手机号的格式
        if(!phone.matches("1[0-9]{10}")) {
            model.addAttribute("message", "手机号格式错误");
            model.addAttribute("ahead_link", "/page/admin/user_management");
            return "/Message.jsp";
        }

        //检验邮箱的格式
        if(!email.matches("[0-9a-zA-Z]+@[0-9a-zA-Z]+\\.[a-zA-Z]+")) {
            model.addAttribute("message", "邮箱格式错误");
            model.addAttribute("ahead_link", "/page/admin/user_management");
            return "/Message.jsp";
        }

        Project projectInformationById = projectService.getProjectInformationById(projectId);
        if(projectInformationById == null) {
            model.addAttribute("message", "不存在ID为" + projectId + "的项目");
            model.addAttribute("ahead_link", "/page/admin/user_management");
            return "/Message.jsp";
        }

        String createBy = null;
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if("userId".equals(cookie.getName())) createBy = cookie.getValue();
        }
//        String createBy = (String) req.getSession().getAttribute("uid");
        RoleType role = RoleType.getRoleTypeByInt(Integer.parseInt(req.getParameter("role")));

        String result = null;
        Admin admin = adminService.getAdminInformation(createBy);
        if(admin == null) result = "id为" + createBy + "的系统管理员不存在";
        switch (role) {
            case BUYER:
                if(buyerService.getInformation(null, phone, null) != null) {
                    result =  "手机号已被注册";
                    break;
                }
                if(buyerService.getInformation(null, null, email) != null) {
                    result = "邮箱已被注册";
                    break;
                }

                Buyer register = buyerService.register(name, password, email, phone, createBy, projectId);
                if(register == null) result = "注册失败";
                result = "Success, ID: " + register.getId();
                break;
            case MANAGER:

                if(managerService.getInformation(null, phone, null) != null) {
                    result =  "手机号已被注册";
                    break;
                }
                if(managerService.getInformation(null, null, email) != null) {
                    result = "邮箱已被注册";
                    break;
                }
                Manager developerManager = managerService.register(name, password, email, phone, createBy, projectId);

                if(developerManager == null) result = "注册失败";
                result = "Success, ID: " + developerManager.getManagerId();
                break;
        }

        model.addAttribute("message", result);
        model.addAttribute("ahead_link", "/page/admin/user_management");
        return "/Message.jsp";
    }

    /***
     * 删除用户表单提交后所运行的代码
     * @param req
     * @param model
     * @return
     */
    @PostMapping("/admin/op/delete_user")
    public String deleteUser(HttpServletRequest req, Model model) {
        String id = req.getParameter("id");
        RoleType role = RoleType.getRoleTypeByInt(Integer.parseInt(req.getParameter("role")));
        String result = null;
        switch (role) {
            case BUYER:
                int i1 = buyerService.deleteBuyer(id);
                if(i1 == 1) result = "删除成功";
                else result = "删除失败";
                break;
            case MANAGER:
                int i = managerService.deleteManager(id);
                if(i == 1) result = "删除成功";
                else result = "删除失败";
                break;
        }
        model.addAttribute("message", result);
        model.addAttribute("ahead_link", "/page/admin/user_management");
        return "/Message.jsp";
    }
}
