package com.starer.controller.data;

import com.alibaba.fastjson2.JSON;
import com.starer.pojo.dto.DemandDto;
import com.starer.pojo.entity.Demand;
import com.starer.pojo.entity.Project;
import com.starer.pojo.entity.Stage;
import com.starer.pojo.entity.Task;
import com.starer.pojo.entity.user.Buyer;
import com.starer.pojo.entity.user.Manager;
import com.starer.pojo.vo.Cost;
import com.starer.pojo.vo.StageExpense;
import com.starer.pojo.vo.StageTaskNum;
import com.starer.service.*;
import com.starer.util.CookieUtil;
import com.starer.util.ResponseResult;
import com.starer.util.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;

@RestController
@RequestMapping("/data")
public class DataController {

    private IBuyerService buyerService;
    private IManagerService managerService;
    private IProjectService projectService;
    private IStageService stageService;
    private ITaskService taskService;
    private IDemandService demandService;

    @Autowired
    public DataController(IBuyerService buyerService, IManagerService managerService,
                          IProjectService projectService, IStageService stageService,
                          ITaskService taskService, IDemandService demandService) {
        this.buyerService = buyerService;
        this.managerService = managerService;
        this.projectService = projectService;
        this.stageService = stageService;
        this.taskService = taskService;
        this.demandService = demandService;
    }

    /**
     * 饼状图格式:
     * [
     *      {"", ""},
     *      {"", ""}
     * ]
     * @param request
     * @param model
     * @return
     */
    @GetMapping(value = "/cost/predict", produces = {
            "application/json; charset=utf-8"})
    public String getCostPredict(HttpServletRequest request, Model model) {

        String[] userIdAndRole = CookieUtil.getUserIdAndRole(request.getCookies());
        if(userIdAndRole[0] == null || userIdAndRole[1] == null) {
            model.addAttribute("msg", "用户ID或用户角色Cookie未找到");
            return "/error.jsp";
        }
        String userId = userIdAndRole[0];
        RoleType role = RoleType.getRoleTypeByInt(Integer.parseInt(userIdAndRole[1]));

        String projectId = null;
        switch (role) {
            case BUYER:
                Buyer buyer = buyerService.getInformation(userId, null, null);
                projectId = buyer.getHandlingProject();
                break;
            case MANAGER:
                Manager manager = managerService.getInformation(userId, null, null);
                projectId = manager.getHandlingProject();
                break;
        }

        if(projectId == null) {
            model.addAttribute("msg", "当前用户未拥有项目");
            return "/error.jsp";
        }
        Stage[] stages = stageService.queryAllStagesForAProject(projectId);
        Cost[] costs = new Cost[stages.length];
        for (int i = 0; i < stages.length; i++) {
            costs[i] = new Cost(stages[i].getStageName(), stages[i].getPredictCost().toString());
        }
        return JSON.toJSONString(costs);
    }

    /**
     * 饼状图：
     * [
     *      {"", ""},
     *      {"", ""}
     * ]
     * @param request
     * @param model
     * @return
     */
    @GetMapping(value = "/cost/now", produces = {
            "application/json; charset=utf-8"})
    public String getCostNow(HttpServletRequest request, Model model) {
        String[] userIdAndRole = CookieUtil.getUserIdAndRole(request.getCookies());
        if(userIdAndRole[0] == null || userIdAndRole[1] == null) {
            model.addAttribute("msg", "用户ID或用户角色Cookie未找到");
            return "/error.jsp";
        }
        String userId = userIdAndRole[0];
        RoleType role = RoleType.getRoleTypeByInt(Integer.parseInt(userIdAndRole[1]));

        String projectId = null;
        switch (role) {
            case BUYER:
                Buyer buyer = buyerService.getInformation(userId, null, null);
                projectId = buyer.getHandlingProject();
                break;
            case MANAGER:
                Manager manager = managerService.getInformation(userId, null, null);
                projectId = manager.getHandlingProject();
                break;
        }

        if(projectId == null) {
            model.addAttribute("msg", "当前用户未拥有项目");
            return "/error.jsp";
        }

        Stage[] stages = stageService.queryAllStagesForAProject(projectId);
        Cost[] costs = new Cost[stages.length];
        for (int i = 0; i < stages.length; i++) {
            Task[] tasks = taskService.selectTaskByStageId(stages[i].getStageId());
            BigDecimal currentCost = new BigDecimal("0");
            for (Task task : tasks) {
                if(task.getCost() != null) currentCost = currentCost.add(task.getCost());
            }
            costs[i] = new Cost(stages[i].getStageName(), currentCost.toString());
        }
        return JSON.toJSONString(costs);
    }

    /**
     * 柱状图：
     * []
     * @param request
     * @param model
     * @return
     */
    @GetMapping(value = "/cost/saveZ" , produces = {
            "application/json; charset=utf-8"})
    public String getCostSaveZ(HttpServletRequest request, Model model) {
        String[] userIdAndRole = CookieUtil.getUserIdAndRole(request.getCookies());
        if(userIdAndRole[0] == null || userIdAndRole[1] == null) {
            model.addAttribute("msg", "用户ID或用户角色Cookie未找到");
            return "/error.jsp";
        }
        String userId = userIdAndRole[0];
        RoleType role = RoleType.getRoleTypeByInt(Integer.parseInt(userIdAndRole[1]));

        String projectId = null;
        switch (role) {
            case BUYER:
                Buyer buyer = buyerService.getInformation(userId, null, null);
                projectId = buyer.getHandlingProject();
                break;
            case MANAGER:
                Manager manager = managerService.getInformation(userId, null, null);
                projectId = manager.getHandlingProject();
                break;
        }

        if(projectId == null) {
            model.addAttribute("msg", "当前用户未拥有项目");
            return "/error.jsp";
        }

        Stage[] stages = stageService.queryAllStagesForAProject(projectId);
        String[] stageNames = new String[stages.length];
        String[] costs = new String[stages.length];
        for (int i = 0; i < stages.length; i++) {
            stageNames[i] = stages[i].getStageName();
            Task[] tasks = taskService.selectTaskByStageId(stages[i].getStageId());
            BigDecimal currentCost = new BigDecimal("0");
            for (Task task : tasks) {
                if(task.getCost() != null) currentCost = currentCost.add(task.getCost());
            }
            costs[i] = stages[i].getPredictCost().subtract(currentCost).toString();
        }

        StageExpense stageExpense = new StageExpense(stageNames, costs);
        return JSON.toJSONString(stageExpense);
    }

    @GetMapping(value = "/cost/nowZ", produces = {
            "application/json; charset=utf-8"})
    public String getCostNowZ(HttpServletRequest request, Model model) {
        String[] userIdAndRole = CookieUtil.getUserIdAndRole(request.getCookies());
        if(userIdAndRole[0] == null || userIdAndRole[1] == null) {
            model.addAttribute("msg", "用户ID或用户角色Cookie未找到");
            return "/error.jsp";
        }
        String userId = userIdAndRole[0];
        RoleType role = RoleType.getRoleTypeByInt(Integer.parseInt(userIdAndRole[1]));

        String projectId = null;
        switch (role) {
            case BUYER:
                Buyer buyer = buyerService.getInformation(userId, null, null);
                projectId = buyer.getHandlingProject();
                break;
            case MANAGER:
                Manager manager = managerService.getInformation(userId, null, null);
                projectId = manager.getHandlingProject();
                break;
        }

        if(projectId == null) {
            model.addAttribute("msg", "当前用户未拥有项目");
            return "/error.jsp";
        }

        Stage[] stages = stageService.queryAllStagesForAProject(projectId);
        String[] stageNames = new String[stages.length];
        String[] costs = new String[stages.length];
        for (int i = 0; i < stages.length; i++) {
            stageNames[i] = stages[i].getStageName();
            Task[] tasks = taskService.selectTaskByStageId(stages[i].getStageId());
            BigDecimal currentCost = new BigDecimal("0");
            for (Task task : tasks) {
                if(task.getCost() != null) currentCost = currentCost.add(task.getCost());
            }
            costs[i] = currentCost.toString();
        }

        StageExpense stageExpense = new StageExpense(stageNames, costs);
        return JSON.toJSONString(stageExpense);
    }

    @GetMapping(value = "/task/count", produces = {
            "application/json; charset=utf-8"})
    public String getTaskCount(HttpServletRequest request, Model model) {
        String[] userIdAndRole = CookieUtil.getUserIdAndRole(request.getCookies());
        if(userIdAndRole[0] == null || userIdAndRole[1] == null) {
            model.addAttribute("msg", "用户ID或用户角色Cookie未找到");
            return "/error.jsp";
        }
        String userId = userIdAndRole[0];
        RoleType role = RoleType.getRoleTypeByInt(Integer.parseInt(userIdAndRole[1]));

        String projectId = null;
        switch (role) {
            case BUYER:
                Buyer buyer = buyerService.getInformation(userId, null, null);
                projectId = buyer.getHandlingProject();
                break;
            case MANAGER:
                Manager manager = managerService.getInformation(userId, null, null);
                projectId = manager.getHandlingProject();
                break;
        }

        if(projectId == null) {
            model.addAttribute("msg", "当前用户未拥有项目");
            return "/error.jsp";
        }

        Stage[] stages = stageService.queryAllStagesForAProject(projectId);
        String[] stagesName = new String[stages != null ? stages.length : 0];
        int[] finishTaskNum = new int[stages != null ? stages.length : 0];
        int[] noFinishTaskNum = new int[stages != null ? stages.length : 0];

        for (int i = 0; i < stagesName.length; i++) {
            stagesName[i] = stages[i].getStageName();
            Task[] tasks = taskService.selectTaskByStageId(stages[i].getStageId());
            for (Task task : tasks) {
                if(task.getStatus() == 1) {
                    finishTaskNum[i]++;
                } else {
                    noFinishTaskNum[i]++;
                }
            }
        }

        return JSON.toJSONString(new StageTaskNum(stagesName, finishTaskNum, noFinishTaskNum));
    }

    @GetMapping(value = "/demand/all", produces = {
            "application/json; charset=utf-8"})
    public String getDemandAll(HttpServletRequest request, Model model) {
        String[] userIdAndRole = CookieUtil.getUserIdAndRole(request.getCookies());
        if(userIdAndRole[0] == null || userIdAndRole[1] == null) {
            model.addAttribute("msg", "用户ID或用户角色Cookie未找到");
            return "/error.jsp";
        }
        String userId = userIdAndRole[0];
        RoleType role = RoleType.getRoleTypeByInt(Integer.parseInt(userIdAndRole[1]));

        String projectId = null;
        switch (role) {
            case BUYER:
                Buyer buyer = buyerService.getInformation(userId, null, null);
                projectId = buyer.getHandlingProject();
                break;
            case MANAGER:
                Manager manager = managerService.getInformation(userId, null, null);
                projectId = manager.getHandlingProject();
                break;
        }

        if(projectId == null) {
            model.addAttribute("msg", "当前用户未拥有项目");
            return "/error.jsp";
        }

        Demand[] demands = demandService.queryDemandByProjectId(projectId);
        DemandDto[]  demandDtos = new DemandDto[demands.length];
        for (int i = 0; i < demands.length; i++) {
            demandDtos[i] = new DemandDto(demands[i]);
        }
        return JSON.toJSONString(demands);
    }

//    @GetMapping(value = "/task/all", produces = {
//            "application/json; charset=utf-8"})
//    public String getTaskAll(HttpServletRequest request, Model model) {
//        String[] userIdAndRole = CookieUtil.getUserIdAndRole(request.getCookies());
//        if(userIdAndRole[0] == null || userIdAndRole[1] == null) {
//            model.addAttribute("msg", "用户ID或用户角色Cookie未找到");
//            return "/error.jsp";
//        }
//        String userId = userIdAndRole[0];
//        RoleType role = RoleType.getRoleTypeByInt(Integer.parseInt(userIdAndRole[1]));
//
//        String projectId = null;
//        switch (role) {
//            case BUYER:
//                Buyer buyer = buyerService.getInformation(userId, null, null);
//                projectId = buyer.getHandlingProject();
//                break;
//            case MANAGER:
//                Manager manager = managerService.getInformation(userId, null, null);
//                projectId = manager.getHandlingProject();
//                break;
//        }
//
//        if(projectId == null) {
//            model.addAttribute("msg", "当前用户未拥有项目");
//            return "/error.jsp";
//        }
//
//        Task[] tasks = taskService.selectTaskByProjectId(projectId);
//        return JSON.toJSONString(tasks);
//    }

//    @GetMapping(value = "/stage/all", produces = {
//            "application/json; charset=utf-8"})
//    public String getStageAll(HttpServletRequest request, Model model) {
//        String[] userIdAndRole = CookieUtil.getUserIdAndRole(request.getCookies());
//        if(userIdAndRole[0] == null || userIdAndRole[1] == null) {
//            model.addAttribute("msg", "用户ID或用户角色Cookie未找到");
//            return "/error.jsp";
//        }
//        String userId = userIdAndRole[0];
//        RoleType role = RoleType.getRoleTypeByInt(Integer.parseInt(userIdAndRole[1]));
//
//        String projectId = null;
//        switch (role) {
//            case BUYER:
//                Buyer buyer = buyerService.getInformation(userId, null, null);
//                projectId = buyer.getHandlingProject();
//                break;
//            case MANAGER:
//                Manager manager = managerService.getInformation(userId, null, null);
//                projectId = manager.getHandlingProject();
//                break;
//        }
//
//        if(projectId == null) {
//            model.addAttribute("msg", "当前用户未拥有项目");
//            return "/error.jsp";
//        }
//
//        Stage[] stages = stageService.queryAllStagesForAProject(projectId);
//        return JSON.toJSONString(stages);
//    }

    @GetMapping(value = "/demand/get", produces = {
            "application/json; charset=utf-8"})
    public String getDemand(HttpServletRequest request) {

        String[] userIdAndRole = CookieUtil.getUserIdAndRole(request.getCookies());
        if(userIdAndRole[0] == null || userIdAndRole[1] == null) {
            return JSON.toJSONString(ResponseResult.error("cookie中缺少用户ID或角色"));
        }

        String demandId = request.getParameter("demandId");
        if(demandId == null || demandId.length() == 0) {
            return JSON.toJSONString(ResponseResult.error("未指定修改内容的需求ID"));
        }

        String projectId = null;
        Demand demand = demandService.queryDemandById(demandId);
        switch (userIdAndRole[1]) {
            case "1":
                Buyer buyer = buyerService.getInformation(userIdAndRole[0], null ,null);
                projectId = buyer.getHandlingProject();
                break;
            case "2":
                Manager manager = managerService.getInformation(userIdAndRole[0], null, null);
                projectId = manager.getHandlingProject();
                break;
        }
        if(projectId == null || "".equals(projectId)) {
            return JSON.toJSONString(ResponseResult.error("您未拥有项目"));
        }
        if(!projectId.equals(demand.getProjectId())) {
            return JSON.toJSONString(ResponseResult.error("您当前项目中不存在该ID的需求"));
        }
        if(demand != null) {
            return JSON.toJSONString(ResponseResult.success("需求获取成功", demand));
        } else {
            return JSON.toJSONString(ResponseResult.error("ID为"  + demandId + "的需求查询失败"));
        }
    }

    @PostMapping(value = "/demand/condition", produces = {
            "application/json; charset=utf-8"})
    public String getConditionalDemand(HttpServletRequest request) {

        String[] userIdAndRole = CookieUtil.getUserIdAndRole(request.getCookies());
        if(userIdAndRole[0] == null || userIdAndRole[1] == null) {
            return JSON.toJSONString(ResponseResult.error("cookie中缺少用户ID或角色"));
        }

        String demandId = request.getParameter("demandId");
        String day = request.getParameter("day");
        String status = request.getParameter("status");
        String type = request.getParameter("type");

        String projectId = null;
        Demand demand = demandService.queryDemandById(demandId);
        switch (userIdAndRole[1]) {
            case "1":
                Buyer buyer = buyerService.getInformation(userIdAndRole[0], null ,null);
                projectId = buyer.getHandlingProject();
                break;
            case "2":
                Manager manager = managerService.getInformation(userIdAndRole[0], null, null);
                projectId = manager.getHandlingProject();
                break;
        }
        if(projectId == null || "".equals(projectId)) {
            return JSON.toJSONString(ResponseResult.error("您未拥有项目"));
        }
        Demand[] demands = demandService.queryConditionDemand(projectId, demandId, day, status, type);

        return JSON.toJSONString(ResponseResult.success("需求获取成功", demands));

    }
}
