package com.starer.controller;

import com.starer.pojo.entity.Authentication;
import com.starer.pojo.entity.user.Manager;
import com.starer.pojo.vo.*;
import com.starer.service.*;
import com.starer.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: pengxiong
 * @Date: 2023/12/04 21:17:13
 * @Version: V1.0
 * @Description:
 **/
@Controller
public class ManagerController {

    private IManagerService developerManagerService;
    private IProjectService projectService;
    private IBuyerService buyerService;

    private IStageService stageService;
    private ITaskService taskService;
    private IAuthenticationService authenticationService;

    @Autowired
    public ManagerController(IManagerService developerManagerService,
                             IProjectService projectService,
                             IBuyerService buyerService,
                             IStageService stageService,
                             ITaskService taskService,
                             IAuthenticationService authenticationService) {
        this.developerManagerService = developerManagerService;
        this.projectService = projectService;
        this.buyerService = buyerService;
        this.stageService = stageService;
        this.taskService = taskService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login/projectMgr")
    public String login(HttpServletRequest req,
                            HttpServletResponse resp, Model model) {
        String loginType = req.getParameter("loginType"); // 登录类型：手机号、邮箱、账号
        String account = req.getParameter("account"); // 手机号或邮箱或账号
        String password = req.getParameter("password"); // 密码
        String role = req.getParameter("role"); // 登录的角色：甲方、项目经理、系统管理员


        if(role == null || !RoleType.MANAGER.getRoleTypeString().equals(role)) {
            ResponseResult.error("登录角色不存在");
            model.addAttribute("error", "登录角色错误");
            return "/error.jsp";
        }

        LoginType loginTypeByString = LoginType.getLoginTypeByString(loginType);
        if(loginTypeByString == null) {
            ResponseResult.error("登录方式不存在");
            model.addAttribute("error", "登录方式不存在");
            return "/error.jsp";
        }
        Manager developerManager =
                developerManagerService.login(account, password, loginTypeByString);

        if(developerManager != null) {
            Map<String, String> userInfo = new HashMap<>();
            userInfo.put("userId", developerManager.getManagerId());
            userInfo.put("token", "1212");



            req.getSession(true).setAttribute("userId", developerManager.getManagerId());

            SimpleDateFormat sdp = new SimpleDateFormat("yyyy-MM-dd");
            UserDto userDto = new UserDto(developerManager.getManagerId(), developerManager.getManagerName(),
                    developerManager.getPhone(), developerManager.getEmail(),
                    sdp.format(developerManager.getCreateTime()), developerManager.getHandlingProject(),
                    RoleType.MANAGER.getRoleTypeInt()
                    );
            model.addAttribute("user", userDto);

            Cookie cookie_userId = new Cookie("userId", developerManager.getManagerId());
            cookie_userId.setPath("/");
            cookie_userId.setHttpOnly(true);
//            cookie_userId.setMaxAge(2100);

            String token = RandomNumberGenerator.randomNumber();
            Authentication authentication = new Authentication(developerManager.getManagerId(), token, 2);
            authenticationService.addAuthentication(authentication);
            Cookie cookie_token = new Cookie("token", token);
            cookie_token.setPath("/");
            cookie_token.setHttpOnly(true);
//            cookie_token.setMaxAge(2100);

            Cookie cookie_role = new Cookie("role", "2");
            cookie_role.setPath("/");
            cookie_token.setHttpOnly(true);

            resp.addCookie(cookie_userId);
            resp.addCookie(cookie_token);

            return "/WEB-INF/jsp/mainframe/home.jsp";
        } else {
            ResponseResult.error("账号或密码错误");
            model.addAttribute("error", "账号或密码错误");
            return "/error.jsp";
        }

    }



    @RequestMapping(value = "/page/manager/user_information", produces = {
            "application/json; charset=utf-8"})
    public String getPageUserInformation(HttpServletRequest req, Model model) {
        Cookie[] cookies = req.getCookies();
        if(cookies == null || cookies.length < 2) {
            return "/error.jsp";
        }

        String userId = null;
        for (Cookie cookie : cookies) {
            if("userId".equals(cookie.getName()) && cookie.getValue() != null)
                userId = cookie.getValue();
        }

        Manager manager = developerManagerService.getInformation(userId, null, null);
        UserDto userDto = new UserDto(manager.getManagerId(), manager.getManagerName(), manager.getPhone(), manager.getEmail(),
                DateTimeUtil.convertTimestampToString(manager.getCreateTime()), RoleType.MANAGER.getRoleTypeInt());
        model.addAttribute("userDto", userDto);

        return "/WEB-INF/jsp/common/user_information.jsp";
    }

}


//    @GetMapping("/project_manager/page/project_summary/{managerId}")
//    public String projectSummary(@PathVariable String managerId, Model model) {
//
//        DeveloperManager developerManager = developerManagerService.getInformation(managerId, null, null);
//        Project project =
//                projectService.getProjectInformationById(developerManager.getHandlingProject());
//        Stage[] stages = stageService.queryAllStagesForAProject(developerManager.getHandlingProject());
//        model.addAttribute("project_name", project.getProjectName());
//
//        Task[] tasks = taskService.selectTaskByProjectId(developerManager.getHandlingProject());
//
//
//        float totalTaskNum = 0; // 总任务数量
//        float finishTaskNum = 0; // 已完成任务数量
//
//        // 已支出成本分析
//        BigDecimal totalCost = new BigDecimal("0");
//        Cost[] costs = new Cost[stages.length];
//        for (int i = 0; i < stages.length; i++) {
//            costs[i] = new Cost();
//            costs[i].setName(stages[i].getStageName());
//            BigDecimal cost1 = new BigDecimal("0");
//            Task[] tasks1 = taskService.selectTaskByStageId(stages[i].getStageId());
//            for (Task task : tasks1) {
//                if(task.getCost() != null) {
//                    cost1 = cost1.add(task.getCost());
//                }
//                // 判断任务是否完成，统计总任务数量和已完成任务数量
//                if(task.getStatus() == 1) {
//                    finishTaskNum++;
//                }
//                totalTaskNum++;
//            }
//            costs[i].setValue(cost1.toString());
//            totalCost = totalCost.add(cost1);
//        }
//        model.addAttribute("project_cost_information", JSON.toJSONString(costs));
//        System.out.println(JSON.toJSONString(costs));
//        model.addAttribute("project_cost", totalCost.toString());
//        System.out.println(totalCost);
//
//        BigDecimal totalPredictCost = new BigDecimal("0");
//        Cost[] predictCosts = new Cost[stages.length];
//        for (int i = 0; i < stages.length; i++) {
//            predictCosts[i] = new Cost();
//            predictCosts[i].setName(stages[i].getStageName());
//            predictCosts[i].setValue(stages[i].getPredictCost().toString());
//            if (stages[i].getPredictCost() != null) {
//                totalPredictCost = totalPredictCost.add(stages[i].getPredictCost());
//            }
//        }
//        model.addAttribute("project_predict_cost_information", JSON.toJSONString(predictCosts));
//        System.out.println(JSON.toJSONString(predictCosts));
//        model.addAttribute("project_predict_cost", totalPredictCost);
//        System.out.println(totalPredictCost);
//
//        String[] name = new String[stages.length];
//        String[] value = new String[stages.length];
//        for (int i = 0; i < stages.length; i++) {
//            name[i] = stages[i].getStageName();
//            value[i] = new BigDecimal(predictCosts[i].getValue()).subtract(
//                    new BigDecimal(costs[i].getValue())).toString();
//        }
//        model.addAttribute("project_benefit_name", JSON.toJSONString(name));
//        model.addAttribute("project_benefit_value", JSON.toJSONString(value));
//
//        // 通过已完成任务数量除以总任务数量，得到进度情况
//        model.addAttribute("project_schedule", finishTaskNum/totalTaskNum*100);
//
//        return "/WEB-INF/jsp/manager/project_summary.jsp";
//    }