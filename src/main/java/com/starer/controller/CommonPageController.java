package com.starer.controller;

import com.alibaba.fastjson2.JSON;
import com.starer.pojo.entity.Project;
import com.starer.pojo.entity.Stage;
import com.starer.pojo.entity.Task;
import com.starer.pojo.vo.Cost;
import com.starer.pojo.vo.ProjectDto;
import com.starer.pojo.vo.UserDto;
import com.starer.service.*;
import com.starer.util.CookieUtil;
import com.starer.util.PojoConvertUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

@Controller
@RequestMapping("/page")
public class CommonPageController {

    private IAdminService adminService;
    private IBuyerService buyerService;
    private IManagerService managerService;

    private IProjectService projectService;
    private IStageService stageService;

    private ITaskService taskService;



    @Autowired
    public CommonPageController(IAdminService adminService, IBuyerService buyerService,
                                IManagerService managerService, IProjectService projectService,
                                IStageService stageService, ITaskService taskService) {
        this.adminService = adminService;
        this.buyerService = buyerService;
        this.managerService = managerService;
        this.projectService = projectService;
        this.stageService = stageService;
        this.taskService = taskService;
    }


    @GetMapping(value = "/common/user_information", produces = {
            "application/json; charset=utf-8"})
    public String getPageUserInformation(HttpServletRequest req, Model model) {
        Cookie[] cookies = req.getCookies();
        if(cookies == null || cookies.length < 2) {
            return "/error.jsp";
        }

        String userId = null;
        String role = null;
        for (Cookie cookie : cookies) {
            if("userId".equals(cookie.getName()))
                userId = cookie.getValue();
            if("role".equals(cookie.getName()))
                role = cookie.getValue();
        }

        if(userId == null || role == null) {
            model.addAttribute("msg", "登录身份已失效，请重新登录！");
            return "/error.jsp";
        }

        UserDto userDto = PojoConvertUtil.getUserDtoByUserId(userId, role,
                adminService, buyerService, managerService);

        if(userDto == null) {
            model.addAttribute("msg", "发生错误，用户ID不存在");
            return "/error.jsp";
        }

        model.addAttribute("userDto", userDto);

        return "/WEB-INF/jsp/common/user_information.jsp";
    }


    @GetMapping("/common/project_summary")
    public String projectSummary(HttpServletRequest request, Model model) {

        String userId = null;
        String role = null;

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if("userId".equals(cookie.getName())) userId = cookie.getValue();
            if("role".equals(cookie.getName())) role = cookie.getValue();
        }

        if(userId == null || role == null) {
            model.addAttribute("msg", "账号ID为空或角色不存在");
            return "/error.jsp";
        }

        UserDto userDto = PojoConvertUtil.getUserDtoByUserId(userId, role,
                adminService, buyerService, managerService);

        if(userDto == null) {
            model.addAttribute("msg", "发生错误，用户ID不存在");
            return "/error.jsp";
        }

        Project project = projectService.getProjectInformationById(userDto.getHandlingProject());
        if(project == null) {
            model.addAttribute("msg", "该账号未分配项目");
            return "/error.jsp";
        }

        Stage[] stages = stageService.queryAllStagesForAProject(project.getProjectId());
        Task[] tasks = taskService.selectTaskByProjectId(project.getProjectId());

        float totalTaskNum = 0; // 总任务数量
        float finishTaskNum = 0; // 已完成任务数量

        // 已支出成本分析
        BigDecimal totalCost = new BigDecimal("0");
        Cost[] costs = new Cost[stages.length];
        for (int i = 0; i < stages.length; i++) {
            costs[i] = new Cost();
            costs[i].setName(stages[i].getStageName());
            BigDecimal cost1 = new BigDecimal("0");
            Task[] tasks1 = taskService.selectTaskByStageId(stages[i].getStageId());
            for (Task task : tasks1) {
                if(task.getCost() != null) {
                    cost1 = cost1.add(task.getCost());
                }
                // 判断任务是否完成，统计总任务数量和已完成任务数量
                if(task.getStatus() == 1) {
                    finishTaskNum++;
                }
                totalTaskNum++;
            }
            costs[i].setValue(cost1.toString());
            totalCost = totalCost.add(cost1);
        }
        model.addAttribute("project_name", project.getProjectName());
        model.addAttribute("project_cost_information", JSON.toJSONString(costs));
        System.out.println(JSON.toJSONString(costs));
        model.addAttribute("project_cost", totalCost.toString());
        System.out.println(totalCost);

        BigDecimal totalPredictCost = new BigDecimal("0");
        Cost[] predictCosts = new Cost[stages.length];
        for (int i = 0; i < stages.length; i++) {
            predictCosts[i] = new Cost();
            predictCosts[i].setName(stages[i].getStageName());
            predictCosts[i].setValue(stages[i].getPredictCost().toString());
            if (stages[i].getPredictCost() != null) {
                totalPredictCost = totalPredictCost.add(stages[i].getPredictCost());
            }
        }
        model.addAttribute("project_predict_cost_information", JSON.toJSONString(predictCosts));
        System.out.println(JSON.toJSONString(predictCosts));
        model.addAttribute("project_predict_cost", totalPredictCost);
        System.out.println(totalPredictCost);

        String[] name = new String[stages.length];
        String[] value = new String[stages.length];
        for (int i = 0; i < stages.length; i++) {
            name[i] = stages[i].getStageName();
            value[i] = new BigDecimal(predictCosts[i].getValue()).subtract(
                    new BigDecimal(costs[i].getValue())).toString();
        }
        model.addAttribute("project_benefit_name", JSON.toJSONString(name));
        model.addAttribute("project_benefit_value", JSON.toJSONString(value));

        // 通过已完成任务数量除以总任务数量，得到进度情况
        model.addAttribute("project_schedule", finishTaskNum/totalTaskNum*100);

        return "/WEB-INF/jsp/common/project_summary.jsp";
    }

    @GetMapping("/common/project_requirement")
    public String getPageDemand(HttpServletRequest request, Model model) {
        String userId = null;
        String role = null;

        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if("userId".equals(cookie.getName())) userId = cookie.getValue();
            if("role".equals(cookie.getName())) role = cookie.getValue();
        }

        if(userId == null || role == null) {
            model.addAttribute("msg", "账号ID为空或角色不存在");
            return "/error.jsp";
        }

        UserDto userDto = PojoConvertUtil.getUserDtoByUserId(userId, role,
                adminService, buyerService, managerService);

        if(userDto == null) {
            model.addAttribute("msg", "发生错误，用户ID不存在");
            return "/error.jsp";
        }
        model.addAttribute("userDto", userDto);

        Project project = projectService.getProjectInformationById(userDto.getHandlingProject());
        if(project == null) {
            model.addAttribute("msg", "该账号未分配项目");
            return "/error.jsp";
        }
        model.addAttribute("projectDto", new ProjectDto(project));

        return "/WEB-INF/jsp/common/project_requirement.jsp";
    }

}
