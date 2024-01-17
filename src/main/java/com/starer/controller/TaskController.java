package com.starer.controller;

import com.alibaba.fastjson2.JSON;
import com.starer.pojo.entity.Stage;
import com.starer.pojo.entity.Task;
import com.starer.pojo.entity.user.Manager;
import com.starer.pojo.vo.TaskDto;
import com.starer.service.IManagerService;
import com.starer.service.IStageService;
import com.starer.service.ITaskService;
import com.starer.util.CookieUtil;
import com.starer.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: pengxiong
 * @Date: 2024/01/02 10:40:26
 * @Version: V1.0
 * @Description:
 **/
@Controller
public class TaskController {

    private IManagerService managerService;
    private IStageService stageService;
    private ITaskService taskService;

    @Autowired
    public TaskController(IManagerService managerService, IStageService stageService, ITaskService taskService) {
        this.managerService = managerService;
        this.stageService = stageService;
        this.taskService = taskService;
    }

    @GetMapping("/page/manager/project_task")
    public String getPage() {
        return "/WEB-INF/jsp/manager/project_task.jsp";
    }

    @GetMapping(value = "/data/task/all", produces = {
            "application/json; charset=utf-8"})
    @ResponseBody
    public String getAllTaskByUserId(HttpServletRequest request) {
        String[] userIdAndRole = CookieUtil.getUserIdAndRole(request.getCookies());
        if(userIdAndRole[0] == null || userIdAndRole[0].length() == 0 ||
            userIdAndRole[1] == null || userIdAndRole[1].length() == 0) {
            return JSON.toJSONString(ResponseResult.error("cookie未找到用户ID或用户角色"));
        }

        if(!"2".equals(userIdAndRole[1])) {
            return JSON.toJSONString(ResponseResult.error("您没有权限获得所有任务信息"));
        }

        Manager manager = managerService.getInformation(userIdAndRole[0], null, null);
        if(manager == null) {
            return JSON.toJSONString(ResponseResult.error("未找到您的账号信息"));
        }

        Stage[] stages = stageService.queryAllStagesForAProject(manager.getHandlingProject());
        if(stages == null) {
            return JSON.toJSONString(ResponseResult.error("阶段获取失败"));
        }
        if(stages.length == 0) {
            return JSON.toJSONString(ResponseResult.error("当前项目未设置阶段"));
        }

        Map<String, String> map = new HashMap<>();
        for (Stage stage : stages) {
            map.put(stage.getStageId(), stage.getStageName());
        }


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Task[] tasks = taskService.selectTaskByProjectId(manager.getHandlingProject());
        TaskDto[] taskDto = new TaskDto[tasks != null ? tasks.length : 0];
        for (int i = 0; i < tasks.length; i++) {
            taskDto[i] = new TaskDto(tasks[i].getTaskId(), tasks[i].getTaskContent(),
                    sdf.format(tasks[i].getStartTime()), sdf.format(tasks[i].getEndTime()),
                    tasks[i].getStatus(),tasks[i].getCost().toString(),
                    map.get(tasks[i].getStageId()));
        }

        return JSON.toJSONString(ResponseResult.success("所有任务获取成功", taskDto));
    }

    @PostMapping(value = "/data/task/condition", produces = {
            "application/json; charset=utf-8"})
    @ResponseBody
    public String getAllTaskByCondition(HttpServletRequest request) {
        String[] userIdAndRole = CookieUtil.getUserIdAndRole(request.getCookies());
        if(userIdAndRole[0] == null || userIdAndRole[0].length() == 0 ||
                userIdAndRole[1] == null || userIdAndRole[1].length() == 0) {
            return JSON.toJSONString(ResponseResult.error("cookie未找到用户ID或用户角色"));
        }

        if(!"2".equals(userIdAndRole[1])) {
            return JSON.toJSONString(ResponseResult.error("您没有权限获得所有任务信息"));
        }

        String taskId = request.getParameter("task_id");
        String status = request.getParameter("status");
        String stageId = request.getParameter("stage");
        if(status != null && !"1".equals(status) && !"0".equals(status) && !"".equals(status)) {
            return JSON.toJSONString(ResponseResult.error("不存在的任务状态"));
        }

        Manager manager = managerService.getInformation(userIdAndRole[0], null, null);
        if(manager == null) {
            return JSON.toJSONString(ResponseResult.error("未找到您的账号信息"));
        }

        Stage[] stages = stageService.queryAllStagesForAProject(manager.getHandlingProject());
        if(stages == null) {
            return JSON.toJSONString(ResponseResult.error("阶段获取失败"));
        }
        if(stages.length == 0) {
            return JSON.toJSONString(ResponseResult.error("当前项目未设置阶段"));
        }

        Map<String, String> map = new HashMap<>();
        for (Stage stage : stages) {
            map.put(stage.getStageId(), stage.getStageName());
        }
        if (stageId != null && stageId.length() != 0 && !map.containsKey(stageId)) {
            return JSON.toJSONString(ResponseResult.error("当前项目不存在您查询的阶段"));
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Task[] tasks = taskService.queryTaskByCondition(taskId, status,
                stageId, manager.getHandlingProject());
        TaskDto[] taskDto = new TaskDto[tasks != null ? tasks.length : 0];
        for (int i = 0; i < tasks.length; i++) {
            taskDto[i] = new TaskDto(tasks[i].getTaskId(), tasks[i].getTaskContent(),
                    sdf.format(tasks[i].getStartTime()), sdf.format(tasks[i].getEndTime()),
                    tasks[i].getStatus(),tasks[i].getCost().toString(),
                    map.get(tasks[i].getStageId()));
        }

        return JSON.toJSONString(ResponseResult.success("所有任务获取成功", taskDto));
    }

    @GetMapping(value = "/data/task/get", produces = {
            "application/json; charset=utf-8"})
    @ResponseBody
    public String getTaskByDemandId(HttpServletRequest request) {
        String[] userIdAndRole = CookieUtil.getUserIdAndRole(request.getCookies());
        if(userIdAndRole[0] == null || userIdAndRole[0].length() == 0 ||
                userIdAndRole[1] == null || userIdAndRole[1].length() == 0) {
            return JSON.toJSONString(ResponseResult.error("cookie未找到用户ID或用户角色"));
        }

        if(!"2".equals(userIdAndRole[1])) {
            return JSON.toJSONString(ResponseResult.error("您没有权限获得任务信息"));
        }

        String taskId = request.getParameter("taskId");
        if(taskId == null || taskId.length() == 0) {
            return JSON.toJSONString(ResponseResult.error("查询的任务ID不能为空"));
        }

        Manager manager = managerService.getInformation(userIdAndRole[0], null, null);
        if(manager == null) {
            return JSON.toJSONString(ResponseResult.error("未找到您的账号信息"));
        }

        Stage[] stages = stageService.queryAllStagesForAProject(manager.getHandlingProject());
        if(stages == null) {
            return JSON.toJSONString(ResponseResult.error("阶段获取失败"));
        }
        if(stages.length == 0) {
            return JSON.toJSONString(ResponseResult.error("当前项目未设置阶段"));
        }

        Map<String, String> map = new HashMap<>();
        for (Stage stage : stages) {
            map.put(stage.getStageId(), stage.getStageName());
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Task task = taskService.selectTaskById(taskId);
        if(task == null || !map.containsKey(task.getStageId())) {
            return JSON.toJSONString(ResponseResult.error("您当前项目未找到对应ID的任务"));
        }

        TaskDto taskDto = new TaskDto(task.getTaskId(), task.getTaskContent(),
                sdf.format(task.getStartTime()), sdf.format(task.getEndTime()),
                task.getStatus(), task.getCost().toString(), map.get(task.getStageId()));
        return JSON.toJSONString(ResponseResult.success("任务获取成功", taskDto));
    }

    @PostMapping(value = "/operate/task/add", produces = {
            "application/json; charset=utf-8"})
    @ResponseBody
    public String addTask(HttpServletRequest request) throws ParseException {
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        String[] userIdAndRole = CookieUtil.getUserIdAndRole(request.getCookies());
        if(userIdAndRole[0] == null || userIdAndRole[0].length() == 0 ||
                userIdAndRole[1] == null || userIdAndRole[1].length() == 0) {
            return JSON.toJSONString(ResponseResult.error("cookie未找到用户ID或用户角色"));
        }

        if(!"2".equals(userIdAndRole[1])) {
            return JSON.toJSONString(ResponseResult.error("您没有权限添加任务"));
        }

        String taskContent = request.getParameter("task_content");
        String startTime = request.getParameter("start_time");
        String endTime = request.getParameter("end_time");
        String cost = request.getParameter("cost");
        String stageId = request.getParameter("stage_id");
        if(taskContent == null || taskContent.length() == 0 ||
                startTime == null || endTime == null ||
                cost == null || stageId == null ||
                stageId.length() == 0) {
            return JSON.toJSONString(ResponseResult.error("添加的任务信息不全"));
        }

        if(!cost.matches("-?\\d+") && !cost.matches("-?\\d+(\\.\\d+)?")) {
            return JSON.toJSONString(ResponseResult.error("已支出格式错误只能包含整数小数"));
        }

        Manager manager = managerService.getInformation(userIdAndRole[0], null, null);
        if(manager == null) {
            return JSON.toJSONString(ResponseResult.error("未找到您的账号信息"));
        }

        Stage[] stages = stageService.queryAllStagesForAProject(manager.getHandlingProject());
        if(stages == null) {
            return JSON.toJSONString(ResponseResult.error("阶段获取失败"));
        }
        if(stages.length == 0) {
            return JSON.toJSONString(ResponseResult.error("当前项目未设置阶段"));
        }

        Map<String, String> map = new HashMap<>();
        for (Stage stage : stages) {
            map.put(stage.getStageId(), stage.getStageName());
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(dateFormat.parse(startTime).getTime());
        Date date1 = new Date(dateFormat.parse(endTime).getTime());
        if(date1.before(date)) {
            return JSON.toJSONString(ResponseResult.error("结束时间不能小于开始时间"));
        }

        Stage stage = stageService.queryStageById(stageId);
        if(stage.getStartTime().after(date)) {
            return JSON.toJSONString(ResponseResult.error("任务开始时间不能晚于任务所属阶段的开始时间"));
        }
        if(stage.getCostTime().before(date1)) {
            return JSON.toJSONString(ResponseResult.error("任务结束时间不能早于任务所属阶段的结束时间"));
        }
        if(!map.containsKey(stageId)) {
            return JSON.toJSONString(ResponseResult.error("您添加的任务所设置阶段ID不存在于当前项目中"));
        }

        int i = taskService.insertTask(
                new Task(taskContent, date, date1, new BigDecimal(cost), stageId), manager.getHandlingProject());
        if(i == 1) {
            return JSON.toJSONString(ResponseResult.success("任务添加成功"));
        } else {
            return JSON.toJSONString(ResponseResult.error("任务添加失败"));
        }
    }

    @PostMapping(value = "/operate/task/update", produces = {
            "application/json; charset=utf-8"})
    @ResponseBody
    public String updateTask(HttpServletRequest request) throws ParseException {
        String[] userIdAndRole = CookieUtil.getUserIdAndRole(request.getCookies());
        if(userIdAndRole[0] == null || userIdAndRole[0].length() == 0 ||
                userIdAndRole[1] == null || userIdAndRole[1].length() == 0) {
            return JSON.toJSONString(ResponseResult.error("cookie未找到用户ID或用户角色"));
        }

        if(!"2".equals(userIdAndRole[1])) {
            return JSON.toJSONString(ResponseResult.error("您没有权限添加任务"));
        }

        String taskId = request.getParameter("task_id");
        String taskContent = request.getParameter("task_content");
        String startTime = request.getParameter("start_time");
        String endTime = request.getParameter("end_time");
        String cost = request.getParameter("cost");
        String status = request.getParameter("status");
        if(taskId == null || taskId.length() == 0 ||
                taskContent == null || taskContent.length() == 0 ||
                startTime == null || endTime == null ||
                cost == null || status == null ||
                !("1".equals(status) || "0".equals(status))) {
            return JSON.toJSONString(ResponseResult.error("添加的任务信息不全"));
        }

        if(!cost.matches("-?\\d+") && !cost.matches("-?\\d+(\\.\\d+)?")) {
            return JSON.toJSONString(ResponseResult.error("已支出格式错误只能包含整数小数"));
        }

        Manager manager = managerService.getInformation(userIdAndRole[0], null, null);
        if(manager == null) {
            return JSON.toJSONString(ResponseResult.error("未找到您的账号信息"));
        }

        Stage[] stages = stageService.queryAllStagesForAProject(manager.getHandlingProject());
        if(stages == null) {
            return JSON.toJSONString(ResponseResult.error("阶段获取失败"));
        }
        if(stages.length == 0) {
            return JSON.toJSONString(ResponseResult.error("当前项目未设置阶段"));
        }

        Map<String, String> map = new HashMap<>();
        for (Stage stage : stages) {
            map.put(stage.getStageId(), stage.getStageName());
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(dateFormat.parse(startTime).getTime());
        Date date1 = new Date(dateFormat.parse(endTime).getTime());
        if(date1.before(date)) {
            return JSON.toJSONString(ResponseResult.error("结束时间不能小于开始时间"));
        }

        String stageId = taskService.selectTaskById(taskId).getStageId();
        Stage stage = stageService.queryStageById(stageId);
        if(stage.getStartTime().after(date)) {
            return JSON.toJSONString(ResponseResult.error("任务开始时间不能晚于任务所属阶段的开始时间"));
        }
        if(stage.getCostTime().before(date1)) {
            return JSON.toJSONString(ResponseResult.error("任务结束时间不能早于任务所属阶段的结束时间"));
        }
        if(!map.containsKey(stageId)) {
            return JSON.toJSONString(ResponseResult.error("您添加的任务所设置阶段ID不存在于当前项目中"));
        }

        int i = taskService.updateTask(
                new Task(taskId, taskContent, date, date1, Integer.parseInt(status), new BigDecimal(cost), stageId));
        if(i == 1) {
            return JSON.toJSONString(ResponseResult.success("任务修改成功"));
        } else {
            return JSON.toJSONString(ResponseResult.error("任务修改失败"));
        }
    }

    @PostMapping(value = "/operate/task/delete", produces = {
            "application/json; charset=utf-8"})
    @ResponseBody
    public String deleteTask(HttpServletRequest request) {
        String[] userIdAndRole = CookieUtil.getUserIdAndRole(request.getCookies());
        if(userIdAndRole[0] == null || userIdAndRole[0].length() == 0 ||
                userIdAndRole[1] == null || userIdAndRole[1].length() == 0) {
            return JSON.toJSONString(ResponseResult.error("cookie未找到用户ID或用户角色"));
        }

        if(!"2".equals(userIdAndRole[1])) {
            return JSON.toJSONString(ResponseResult.error("您没有权限添加任务"));
        }

        String taskId = request.getParameter("task_id");

        if(taskId == null || taskId.length() == 0) {
            return JSON.toJSONString(ResponseResult.error("删除的任务ID不能为空"));
        }

        Manager manager = managerService.getInformation(userIdAndRole[0], null, null);
        if(manager == null) {
            return JSON.toJSONString(ResponseResult.error("未找到您的账号信息"));
        }

        Task[] tasks = taskService.selectTaskByProjectId(manager.getHandlingProject());
        if(tasks == null) {
            return JSON.toJSONString(ResponseResult.error("所有任务获取失败"));
        }
        boolean flag = false;
        for (Task task : tasks) {
            if(taskId.equals(task.getTaskId()))  flag = true;
        }
        if(!flag) {
            return JSON.toJSONString(ResponseResult.error("您当前项目中没有对应ID的任务"));
        }

        int i = taskService.deleteTask(taskId);
        if(i == 1) {
            return JSON.toJSONString(ResponseResult.success("任务删除成功"));
        } else {
            return JSON.toJSONString(ResponseResult.error("任务删除失败"));
        }
    }
}
