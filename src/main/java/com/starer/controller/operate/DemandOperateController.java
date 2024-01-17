package com.starer.controller.operate;

import com.alibaba.fastjson2.JSON;
import com.starer.pojo.entity.Demand;
import com.starer.pojo.entity.user.Buyer;
import com.starer.pojo.entity.user.Manager;
import com.starer.service.IBuyerService;
import com.starer.service.IDemandService;
import com.starer.service.IManagerService;
import com.starer.util.CookieUtil;
import com.starer.util.ResponseResult;
import com.starer.util.RoleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

@RestController
@RequestMapping("/operate/demand")
public class DemandOperateController {

    private IDemandService demandService;
    private IBuyerService buyerService;
    private IManagerService managerService;

    @Autowired
    public DemandOperateController(IDemandService demandService, IBuyerService buyerService, IManagerService managerService) {
        this.demandService = demandService;
        this.buyerService = buyerService;
        this.managerService = managerService;
    }

    @PostMapping(value = "/add", produces = {
            "application/json; charset=utf-8"})
    public String addDemand(HttpServletRequest request) {
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        String demandContent = request.getParameter("demandContent");
        if(demandContent == null || demandContent.length() == 0) {
            return JSON.toJSONString(ResponseResult.error("需求内容不能为空"));
        }

        String[] userIdAndRole = CookieUtil.getUserIdAndRole(request.getCookies());
        if(userIdAndRole[0] == null || userIdAndRole[1] == null) {
            return JSON.toJSONString(ResponseResult.error("cookie中缺少用户ID或角色"));
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
            return JSON.toJSONString(ResponseResult.error("当前用户未拥有项目"));
        }

        int i = demandService.addDemand(new Demand(projectId, demandContent));
        if(i == 1) {
            return JSON.toJSONString(ResponseResult.success("添加成功"));
        } else {
            return JSON.toJSONString(ResponseResult.error("添加失败"));
        }
    }

    @PostMapping(value = "/modify", produces = {
            "application/json; charset=utf-8"})
    public String modifyDemand(HttpServletRequest request) {
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
        String[] userIdAndRole = CookieUtil.getUserIdAndRole(request.getCookies());
        if(userIdAndRole[0] == null || userIdAndRole[1] == null) {
            return JSON.toJSONString(ResponseResult.error("cookie中缺少用户ID或角色"));
        }

        if(!"1".equals(userIdAndRole[1])) {
            return JSON.toJSONString(ResponseResult.error("您没有权限修改需求内容"));
        }

        Buyer buyer = buyerService.getInformation(userIdAndRole[0], null, null);

        String demandId = request.getParameter("demandId");
        if(demandId == null || demandId.length() == 0) {
            return JSON.toJSONString(ResponseResult.error("未指定修改内容的需求ID"));
        }

        Demand demand = demandService.queryDemandById(demandId);

        if(!demand.getProjectId().equals(buyer.getHandlingProject())) {
            return JSON.toJSONString(ResponseResult.error("您没有权限修改当前ID的需求内容"));
        }


        String demandContent = request.getParameter("demandContent");
        if(demandContent == null || demandContent.length() == 0) {
            return JSON.toJSONString(ResponseResult.error("需求内容不能为空"));
        }



        int i = demandService.updateDemandContent(demandId, demandContent);
        if(i == 1) {
            return JSON.toJSONString(ResponseResult.success("修改成功"));
        } else {
            return JSON.toJSONString(ResponseResult.error("修改失败"));
        }
    }

    @PostMapping(value = "/update_status", produces = {
            "application/json; charset=utf-8"})
    public String updateDemandStatus(HttpServletRequest request) {

        String[] userIdAndRole = CookieUtil.getUserIdAndRole(request.getCookies());
        if(userIdAndRole[0] == null || userIdAndRole[1] == null) {
            return JSON.toJSONString(ResponseResult.error("cookie中缺少用户ID或角色"));
        }

        String demandId = request.getParameter("demandId");
        if(demandId == null || demandId.length() == 0) {
            return JSON.toJSONString(ResponseResult.error("未指定修改内容的需求ID"));
        }
        String demandStatus = request.getParameter("demandStatus");
        if(demandStatus == null || demandStatus.length() == 0) {
            return JSON.toJSONString(ResponseResult.error("需求状态不能为空"));
        }


        if("2".equals(userIdAndRole[1]) && "3".equals(demandStatus)) {
            return JSON.toJSONString(ResponseResult.error("你没有权限请求删除需求"));
        }


        if("1".equals(userIdAndRole[1]) && ("1".equals(demandStatus) || "2".equals(demandStatus) ||
                "-1".equals(demandStatus))) {
            return JSON.toJSONString(ResponseResult.error("你没有权限同意删除、同意需求、反对需求"));
        }

        Demand demand = demandService.queryDemandById(demandId);
        if("-1".equals(demandStatus) && !(demand.getStatus() == 3)) {
            return JSON.toJSONString(ResponseResult.error("只有处于待删除状态的需求才能同意删除"));
        }

        if(!Arrays.asList("-1", "0", "1", "2", "3").contains(demandStatus)) {
            return JSON.toJSONString(ResponseResult.error("状态不存在"));
        }

        int i = demandService.updateDemand(
                new Demand(demandId, null, null, Integer.parseInt(demandStatus)));
        if(i == 1) {
            return JSON.toJSONString(ResponseResult.success("设置成功"));
        } else {
            return JSON.toJSONString(ResponseResult.error("设置失败"));
        }
    }


}
