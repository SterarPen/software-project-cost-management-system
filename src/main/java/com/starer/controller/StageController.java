package com.starer.controller;

import com.alibaba.fastjson2.JSON;
import com.starer.pojo.entity.Stage;
import com.starer.pojo.entity.user.Manager;
import com.starer.service.IManagerService;
import com.starer.service.IStageService;
import com.starer.util.CookieUtil;
import com.starer.util.ResponseResult;
import com.starer.util.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @Author: pengxiong
 * @Date: 2024/01/01 19:59:34
 * @Version: V1.0
 * @Description:
 **/
@Controller
public class StageController {

    private IManagerService managerService;
    private IStageService stageService;

    @Autowired
    public StageController(IManagerService managerService, IStageService stageService) {
        this.managerService = managerService;
        this.stageService = stageService;
    }

    @GetMapping("/page/manager/project_stage")
    public String getPage() {
        return "/WEB-INF/jsp/manager/project_stage.jsp";
    }

    @GetMapping(value = "/data/stage/all", produces = {
            "application/json; charset=utf-8"})
    @ResponseBody
    public String getAllStagesForCurrentUser(HttpServletRequest request) {
        String[] userIdAndRole = CookieUtil.getUserIdAndRole(request.getCookies());
        if(userIdAndRole[0] == null || userIdAndRole[1] == null) {
            return JSON.toJSONString(ResponseResult.error("cookie中未找到用户ID或用户角色"));
        }

        if(!"2".equals(userIdAndRole[1])) {
            return JSON.toJSONString(ResponseResult.error("您没有权限获取项目的所有阶段"));
        }

        Manager manager = managerService.getInformation(userIdAndRole[0], null, null);
        if(manager == null) {
            return JSON.toJSONString(ResponseResult.error("您的ID对应的账号未找到"));
        }

        Stage[] stages = stageService.queryAllStagesForAProject(manager.getHandlingProject());
        if(stages != null) {
            return JSON.toJSONString(ResponseResult.success("项目阶段获取成功", stages));
        } else {
            return JSON.toJSONString(ResponseResult.error("项目阶段获取失败"));
        }

    }

    @GetMapping(value = "/data/stage/id", produces = {
            "application/json; charset=utf-8"})
    @ResponseBody
    public String getStageById(HttpServletRequest request) {
        String[] userIdAndRole = CookieUtil.getUserIdAndRole(request.getCookies());
        if(userIdAndRole[0] == null || userIdAndRole[1] == null) {
            return JSON.toJSONString(ResponseResult.error("cookie中未找到用户ID或用户角色"));
        }

        if(!"2".equals(userIdAndRole[1])) {
            return JSON.toJSONString(ResponseResult.error("您没有权限获取项目的所有阶段"));
        }

        Manager manager = managerService.getInformation(userIdAndRole[0], null, null);
        if(manager == null) {
            return JSON.toJSONString(ResponseResult.error("您的ID对应的账号未找到"));
        }

        String stageId = request.getParameter("stageId");
        if(stageId == null || stageId.length() == 0) {
            return JSON.toJSONString(ResponseResult.error("查询的阶段ID不能为空"));
        }

        Stage stage = stageService.queryStageById(stageId);

        if(stage != null) {
            return JSON.toJSONString(ResponseResult.success("项目阶段获取成功", stage));
        } else {
            return JSON.toJSONString(ResponseResult.error("项目阶段获取失败"));
        }
    }

//    @GetMapping(value = "/data/stage/allIdAndName", produces = {
//            "application/json; charset=utf-8"})
//    @ResponseBody
//    public String getStageIdAndStage(HttpServletRequest request) {
//
//    }

    @PostMapping(value = "/operate/stage/add", produces = {
            "application/json; charset=utf-8"})
    @ResponseBody
    public String addStage(HttpServletRequest request) throws ParseException, UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        String[] userIdAndRole = CookieUtil.getUserIdAndRole(request.getCookies());
        if(userIdAndRole[0] == null || userIdAndRole[1] == null) {
            return JSON.toJSONString(ResponseResult.error("cookie中未找到用户ID或用户角色"));
        }

        if(!"2".equals(userIdAndRole[1])) {
            return JSON.toJSONString(ResponseResult.error("您没有权限获取项目的所有阶段"));
        }

        Manager manager = managerService.getInformation(userIdAndRole[0], null, null);
        if(manager == null) {
            return JSON.toJSONString(ResponseResult.error("您的ID对应的账号未找到"));
        }

        String stageName = request.getParameter("stage_name");
        String startTime = request.getParameter("start_date");
        String endTime = request.getParameter("end_date");
        String predictCost = request.getParameter("predict_cost");
        if(stageName == null || stageName.length() == 0 || startTime == null || endTime == null ||
            predictCost == null || predictCost.length() == 0) {
            return JSON.toJSONString(ResponseResult.error("添加阶段中信息不完整"));
        }

        if(!predictCost.matches("-?\\d+") && !predictCost.matches("-?\\d+(\\.\\d+)?")) {
            return JSON.toJSONString(ResponseResult.error("预计支出格式错误只能包含整数小数"));
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(dateFormat.parse(startTime).getTime());
        Date date1 = new Date(dateFormat.parse(endTime).getTime());
        if(date1.before(date)) {
            return JSON.toJSONString(ResponseResult.error("结束时间不能小于开始时间"));
        }
        Stage stage = new Stage(null, stageName, date, date1,
                new BigDecimal(predictCost));
        int i = stageService.addStage(stage, manager.getHandlingProject());
        if(i ==1) {
            return JSON.toJSONString(ResponseResult.success("项目阶段添加成功"));
        } else {
            return JSON.toJSONString(ResponseResult.error("项目阶段添加失败"));
        }
    }

    @PostMapping(value = "/operate/stage/update", produces = {
            "application/json; charset=utf-8"})
    @ResponseBody
    public String updateStage(HttpServletRequest request) throws ParseException, UnsupportedEncodingException {
        request.setCharacterEncoding("utf-8");
        String[] userIdAndRole = CookieUtil.getUserIdAndRole(request.getCookies());
        if(userIdAndRole[0] == null || userIdAndRole[1] == null) {
            return JSON.toJSONString(ResponseResult.error("cookie中未找到用户ID或用户角色"));
        }

        if(!"2".equals(userIdAndRole[1])) {
            return JSON.toJSONString(ResponseResult.error("您没有权限获取项目的所有阶段"));
        }

        Manager manager = managerService.getInformation(userIdAndRole[0], null, null);
        if(manager == null) {
            return JSON.toJSONString(ResponseResult.error("您的ID对应的账号未找到"));
        }

        String stageId = request.getParameter("stageId");
        String stageName = request.getParameter("stage_name");
        String startTime = request.getParameter("start_date");
        String endTime = request.getParameter("end_date");
        String predictCost = request.getParameter("predict_cost");
        if(stageId == null || stageId.length() == 0 ||stageName == null ||
                stageName.length() == 0 || startTime == null || endTime == null ||
                predictCost == null || predictCost.length() == 0) {
            return JSON.toJSONString(ResponseResult.error("添加阶段中信息不完整"));
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(dateFormat.parse(startTime).getTime());
        Date date1 = new Date(dateFormat.parse(endTime).getTime());
        if(date1.before(date)) {
            return JSON.toJSONString(ResponseResult.error("结束时间不能小于开始时间"));
        }
        Stage stage = new Stage(stageId, stageName, date, date1,
                new BigDecimal(predictCost));
        int i = stageService.updateStage(stage, manager.getHandlingProject());
        if(i ==1) {
            return JSON.toJSONString(ResponseResult.success("项目阶段修改成功"));
        } else {
            return JSON.toJSONString(ResponseResult.error("项目阶段修改失败"));
        }
    }

    @PostMapping(value = "/operate/stage/delete", produces = {
            "application/json; charset=utf-8"})
    @ResponseBody
    public String deleteStage(HttpServletRequest request) {
        String[] userIdAndRole = CookieUtil.getUserIdAndRole(request.getCookies());
        if(userIdAndRole[0] == null || userIdAndRole[1] == null) {
            return JSON.toJSONString(ResponseResult.error("cookie中未找到用户ID或用户角色"));
        }

        if(!"2".equals(userIdAndRole[1])) {
            return JSON.toJSONString(ResponseResult.error("您没有权限获取项目的所有阶段"));
        }

        Manager manager = managerService.getInformation(userIdAndRole[0], null, null);
        if(manager == null) {
            return JSON.toJSONString(ResponseResult.error("您的ID对应的账号未找到"));
        }

        String stageId = request.getParameter("stageId");
        if(stageId == null || stageId.length() == 0) {
            return JSON.toJSONString(ResponseResult.error("阶段ID不能为空"));
        }

        Stage[] stages = stageService.queryAllStagesForAProject(manager.getHandlingProject());
        for (Stage stage : stages) {
            if(stageId.equals(stage.getStageId())) {
                int i = stageService.deleteStage(stageId);
                if(i ==1) {
                    return JSON.toJSONString(ResponseResult.success("项目阶段删除成功"));
                } else {
                    return JSON.toJSONString(ResponseResult.error("项目阶段删除失败"));
                }
            }
        }
        return JSON.toJSONString(ResponseResult.error("当前项目不存在该ID的阶段"));
    }


}
