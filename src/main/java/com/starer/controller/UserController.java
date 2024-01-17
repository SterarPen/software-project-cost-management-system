package com.starer.controller;

import com.alibaba.fastjson2.JSON;
import com.starer.Enum.MessageSendPlatform;
import com.starer.pojo.entity.user.Buyer;
import com.starer.pojo.entity.user.Manager;
import com.starer.service.IBuyerService;
import com.starer.service.IManagerService;
import com.starer.service.IMessageService;
import com.starer.util.CookieUtil;
import com.starer.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Author: pengxiong
 * @Date: 2024/01/01 15:11:49
 * @Version: V1.0
 * @Description:
 **/
@RestController
public class UserController {

    private IBuyerService buyerService;
    private IManagerService managerService;
    private IMessageService messageService;

    @Autowired
    public UserController(IBuyerService buyerService, IManagerService managerService, IMessageService messageService) {
        this.buyerService = buyerService;
        this.managerService = managerService;
        this.messageService = messageService;
    }

//    public String sendCode(HttpServletRequest request) {
//        messageService.sendIdentifyCode()
//
//    }

    @PostMapping(value = "/update_password", produces = {
            "application/json; charset=utf-8"})
    public String updatePassword(HttpServletRequest request) {
        String[] userIdAndRole = CookieUtil.getUserIdAndRole(request.getCookies());
        if(userIdAndRole[0] == null || userIdAndRole[1] == null) {
            return JSON.toJSONString(ResponseResult.error("cookie中找不到用户ID或用户角色"));
        }

        String code = request.getParameter("code");
        if(code == null) {
            return JSON.toJSONString(ResponseResult.error("验证码不能为空"));
        }
        String newPassword = request.getParameter("new_password");
        if(newPassword == null) {
            return JSON.toJSONString(ResponseResult.error("新密码不能为空"));
        }

        HttpSession session = request.getSession();
        if(session == null) {
            return JSON.toJSONString(ResponseResult.error("请点击发送验证码按钮"));
        }

        String identifyCodeId = (String) session.getAttribute(userIdAndRole[0] + '-' + userIdAndRole[1]);

        boolean identify = messageService.identify(identifyCodeId, code);
        if(identify) {
            switch (userIdAndRole[1]) {
                case "1":
                    int b = buyerService.updateBuyer(
                            new Buyer(userIdAndRole[0], newPassword,
                                    null, null, null, null));
                    if (b == 1) {
                        return JSON.toJSONString(ResponseResult.error("修改成功"));
                    } else {
                        return JSON.toJSONString(ResponseResult.error("修改失败"));
                    }
                case "2":
                    b = managerService.updateManager(
                            new Manager(userIdAndRole[0], newPassword,
                                    null, null, null));
                    if (b == 1) {
                        return JSON.toJSONString(ResponseResult.error("修改成功"));
                    } else {
                        return JSON.toJSONString(ResponseResult.error("修改失败"));
                    }
            }
        }
            return JSON.toJSONString(ResponseResult.error("验证码错误"));
    }

    @PostMapping(value = "/update_phone", produces = {
            "application/json; charset=utf-8"})
    public String updatePhone(HttpServletRequest request) {
        String[] userIdAndRole = CookieUtil.getUserIdAndRole(request.getCookies());
        if(userIdAndRole[0] == null || userIdAndRole[1] == null) {
            return JSON.toJSONString(ResponseResult.error("cookie中找不到用户ID或用户角色"));
        }

        String code = request.getParameter("code");
        if(code == null) {
            return JSON.toJSONString(ResponseResult.error("验证码不能为空"));
        }
        String newPhone = request.getParameter("new_phone");
        if(newPhone == null) {
            return JSON.toJSONString(ResponseResult.error("新手机号不能为空"));
        }

        HttpSession session = request.getSession();
        if(session == null) {
            return JSON.toJSONString(ResponseResult.error("请点击发送验证码按钮"));
        }

        String identifyCodeId = (String) session.getAttribute(userIdAndRole[0] + '-' + userIdAndRole[1]);

        boolean identify = messageService.identify(identifyCodeId, code);
        if(identify) {
            switch (userIdAndRole[1]) {
                case "1":
                    int b = buyerService.updateBuyer(
                            new Buyer(userIdAndRole[0], null,
                                    newPhone, null, null, null));
                    if (b == 1) {
                        return JSON.toJSONString(ResponseResult.error("修改成功"));
                    } else {
                        return JSON.toJSONString(ResponseResult.error("修改失败"));
                    }
                case "2":
                    b = managerService.updateManager(
                            new Manager(userIdAndRole[0], null,
                            newPhone, null, null));
                    if (b ==1) {
                        return JSON.toJSONString(ResponseResult.error("修改成功"));
                    } else {
                        return JSON.toJSONString(ResponseResult.error("修改失败"));
                    }
            }
        }
            return JSON.toJSONString(ResponseResult.error("验证码错误"));
    }


    @GetMapping(value = "/send_phone", produces = {
            "application/json; charset=utf-8"})
    public String sendPhone() {
        return JSON.toJSONString(ResponseResult.success("发送成功"));
    }

    @GetMapping(value = "/send_email", produces = {
            "application/json; charset=utf-8"})
    public String sendEmail(HttpServletRequest request) {
        String[] userIdAndRole = CookieUtil.getUserIdAndRole(request.getCookies());
        if(userIdAndRole[0] == null || userIdAndRole[0].length() == 0 ||
            userIdAndRole[1] == null || userIdAndRole[1].length() == 0) {
            return JSON.toJSONString(ResponseResult.error("cookie中未找到用户ID或用户角色"));
        }

        String receiver = null;
        switch (userIdAndRole[1]) {
            case "1":
                Buyer buyer = buyerService.getInformation(userIdAndRole[0], null, null);
                if(buyer == null) {
                    return JSON.toJSONString(ResponseResult.error("未找到用户信息"));
                }
                receiver = buyer.getEmail();
                break;
            case "2":
                Manager manager = managerService.getInformation(userIdAndRole[0], null, null);
                if(manager == null) {
                    return JSON.toJSONString(ResponseResult.error("未找到用户信息"));
                }
                receiver = manager.getEmail();
                break;
        }
        if(receiver == null) {
            return JSON.toJSONString(ResponseResult.error("用户未绑定邮箱"));
        }
        Long aLong = messageService.sendIdentifyCode(receiver, MessageSendPlatform.EMAIL, userIdAndRole[1]);
        return JSON.toJSONString(ResponseResult.success("发送成功"));
    }

    @Transactional
    @PostMapping(value = "/update_email", produces = {
            "application/json; charset=utf-8"} )
    public String updateEmail(HttpServletRequest request) {
        String[] userIdAndRole = CookieUtil.getUserIdAndRole(request.getCookies());
        if(userIdAndRole[0] == null || userIdAndRole[1] == null) {
            return JSON.toJSONString(ResponseResult.error("cookie中找不到用户ID或用户角色"));
        }

        String code = request.getParameter("code");
        if(code == null) {
            return JSON.toJSONString(ResponseResult.error("验证码不能为空"));
        }
        String newEmail = request.getParameter("new_email");
        if(newEmail == null) {
            return JSON.toJSONString(ResponseResult.error("新邮箱不能为空"));
        }

        String receiver = null;
        switch (userIdAndRole[1]) {
            case "1":
                Buyer buyer = buyerService.getInformation(userIdAndRole[0], null, null);
                if(buyer == null) {
                    return JSON.toJSONString(ResponseResult.error("未找到用户信息"));
                }
                receiver = buyer.getEmail();
                if(receiver == null) {
                    return JSON.toJSONString(ResponseResult.error("用户未绑定邮箱"));
                }

                if (messageService.identifyByReceiver(receiver, code)) {
                    buyer.setEmail(newEmail);
                    buyerService.updateBuyer(buyer);
                    return JSON.toJSONString(ResponseResult.success("修改成功"));
                } else {
                    return JSON.toJSONString(ResponseResult.error("验证码错误"));
                }
            case "2":
                Manager manager = managerService.getInformation(userIdAndRole[0], null, null);
                if(manager == null) {
                    return JSON.toJSONString(ResponseResult.error("未找到用户信息"));
                }
                receiver = manager.getEmail();
                if(receiver == null) {
                    return JSON.toJSONString(ResponseResult.error("用户未绑定邮箱"));
                }
                if (messageService.identifyByReceiver(receiver, code)) {
                    manager.setEmail(newEmail);
                    managerService.updateManager(manager);
                    return JSON.toJSONString(ResponseResult.success("修改成功"));
                } else {
                    return JSON.toJSONString(ResponseResult.error("验证码错误"));
                }
            default:
                return JSON.toJSONString(ResponseResult.error("用户角色不存在"));
        }

    }
}

