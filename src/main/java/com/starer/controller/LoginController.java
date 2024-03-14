package com.starer.controller;

import com.starer.pojo.entity.Authentication;
import com.starer.pojo.entity.user.Buyer;
import com.starer.pojo.entity.user.Manager;
import com.starer.pojo.entity.user.Admin;
import com.starer.pojo.vo.UserDto;
import com.starer.service.IAuthenticationService;
import com.starer.service.IBuyerService;
import com.starer.service.IManagerService;
import com.starer.service.IAdminService;
import com.starer.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;

/**
 * 处理登录请求的控制器
 */
@Controller
public class LoginController {

    private IAdminService adminService;
    private IManagerService managerService;
    private IBuyerService buyerService;

    private IAuthenticationService authenticationService;

    @Autowired
    public LoginController(IAdminService adminService, IManagerService managerService, IBuyerService buyerService, IAuthenticationService authenticationService) {
        this.adminService = adminService;
        this.managerService = managerService;
        this.buyerService = buyerService;
        this.authenticationService = authenticationService;
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest req, HttpServletResponse rsp, Model model) {


        String loginType = req.getParameter("loginType");
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        if(loginType == null || account == null || password == null || role == null) {
            model.addAttribute("msg", "登录信息缺失");
            return "/error.jsp";
        }
//        if (!LoginType.strings.contains(loginType)) {
//            model.addAttribute("msg", "登录方式不存在");
//            return "/error.jsp";
//        }
//        if(!RoleType.strings.contains(role)) {
//            model.addAttribute("msg", "不存在这种登录角色");
//            return "/error.jsp";
//        }
        RoleType roleType = RoleType.getLoginTypeByString(role);
        LoginType loginTypeEnum = LoginType.getLoginTypeByString(loginType);
        if (roleType == null) {
            model.addAttribute("msg", "登录方式不存在");
            return "/error.jsp";
        }
        if(loginTypeEnum == null) {
            model.addAttribute("msg", "不存在这种登录角色");
            return "/error.jsp";
        }

        SimpleDateFormat sdp = new SimpleDateFormat("yyyy-MM-dd");
        switch (roleType) {
            case MANAGER:
                Manager manager = null;
                manager = managerService.login(account, password, loginTypeEnum);

                if(manager == null) {
                    model.addAttribute("msg", "账号或密码错误");
                    return "/error.jsp";
                }

                UserDto userDto = new UserDto(manager.getManagerId(), manager.getManagerName(), manager.getPhone(),
                        manager.getEmail(), sdp.format(manager.getCreateTime()),
                        manager.getHandlingProject(), RoleType.MANAGER.getRoleTypeInt()
                        );
                model.addAttribute("userDto", userDto);

                Cookie cookie_userId = new Cookie("userId", manager.getManagerId());
                cookie_userId.setPath("/");
                cookie_userId.setHttpOnly(true);
//            cookie_userId.setMaxAge(2100);

                String token = RandomNumberGenerator.randomNumber();
                Authentication authentication = new Authentication(manager.getManagerId(), token, 2);
                authenticationService.addAuthentication(authentication);
                Cookie cookie_token = new Cookie("token", token);
                cookie_token.setPath("/");
                cookie_token.setHttpOnly(true);
//            cookie_token.setMaxAge(2100);

                Cookie cookie_role = new Cookie("role", Integer.toString(RoleType.MANAGER.getRoleTypeInt()));
                cookie_role.setPath("/");
                cookie_role.setHttpOnly(true);

                rsp.addCookie(cookie_userId);
                rsp.addCookie(cookie_token);
                rsp.addCookie(cookie_role);

                return "/WEB-INF/jsp/mainframe/home.jsp";
            case BUYER:
                Buyer buyer = buyerService.login(account, password, LoginType.LOGIN_ID);

                if(buyer == null) {
                    model.addAttribute("msg", "账号或密码错误");
                    return "/error.jsp";
                }


                userDto = new UserDto(buyer.getId(), buyer.getName(), buyer.getPhone(), buyer.getEmail(),
                        sdp.format(buyer.getCreateTime()), buyer.getHandlingProject(),RoleType.BUYER.getRoleTypeInt()
                        );
                model.addAttribute("userDto", userDto);

                cookie_userId = new Cookie("userId", buyer.getId());
                cookie_userId.setPath("/");
                cookie_userId.setHttpOnly(true);
//            cookie_userId.setMaxAge(2100);

                token = RandomNumberGenerator.randomNumber();
                authentication = new Authentication(buyer.getId(), token, 1);
                authenticationService.addAuthentication(authentication);
                cookie_token = new Cookie("token", token);
                cookie_token.setPath("/");
                cookie_token.setHttpOnly(true);
//            cookie_token.setMaxAge(2100);

                cookie_role = new Cookie("role", Integer.toString(RoleType.BUYER.getRoleTypeInt()));
                cookie_role.setPath("/");
                cookie_role.setHttpOnly(true);

                rsp.addCookie(cookie_userId);
                rsp.addCookie(cookie_token);
                rsp.addCookie(cookie_role);

                return "/WEB-INF/jsp/mainframe/home.jsp";
            case ADMIN:
                Admin admin = adminService.getAdminInformation(account);
                if(admin == null || !password.equals(admin.getPassword())) {
                    model.addAttribute("msg", "账号或密码错误");
                    return "/error.jsp";
                }

                userDto = new UserDto(admin.getAdminId(), admin.getAdminName(), null,
                        null, null,
                        null, RoleType.ADMIN.getRoleTypeInt());
                model.addAttribute("userDto", userDto);

                cookie_userId = new Cookie("userId", admin.getAdminId());
                cookie_userId.setPath("/");
                cookie_userId.setHttpOnly(true);

                token = RandomNumberGenerator.randomNumber();
                authentication = new Authentication(admin.getAdminId(), token, 0);
                authenticationService.addAuthentication(authentication);
                cookie_token = new Cookie("token", token);
                cookie_token.setPath("/");
                cookie_token.setHttpOnly(true);

                cookie_role = new Cookie("role", Integer.toString(RoleType.ADMIN.getRoleTypeInt()));
                cookie_role.setPath("/");
                cookie_role.setHttpOnly(true);

                rsp.addCookie(cookie_userId);
                rsp.addCookie(cookie_token);
                rsp.addCookie(cookie_role);

                return "/WEB-INF/jsp/mainframe/home.jsp";
            default:
                model.addAttribute("msg", "不存在这种登录角色");
                return "/error.jsp";
        }

//        // 从请求中中查找是否存在键为uid的cookie，存在则取出对应cookie的值
//        String uid_client = null;
//        Cookie[] cookies = req.getCookies();
//        if(cookies != null) {
//            for (Cookie cookie : cookies) {
//                if ("uid".equals(cookie.getName()))
//                    uid_client = cookie.getValue();
//            }
//        }
//
//        //在服务器对应客户端的会话对象中查找是否有键为uid的键值对，存在则取出其值
//        HttpSession session = req.getSession(true);
//        String uid_server  = (String) session.getAttribute("uid");
//
//        //判断是否满足自动登录的条件
//        if(uid_client != null && uid_server != null && uid_server.equals(uid_client)) {
//            return "/WEB-INF/jsp/mainframe/home.jsp";
//        } else {
//            String loginType = req.getParameter("loginType");
//            String account = req.getParameter("account");
//            String password = req.getParameter("password");
//            Buyer buyer = null;
//            switch (loginType) {
//                case "id":
//                    buyer = (Buyer) buyerService.getInformation(account, null , null);
//                    break;
//                case "phone":
//                    buyer = (Buyer) buyerService.getInformation(null, account , null);
//                    break;
//                case "email":
//                    buyer = (Buyer) buyerService.getInformation(null, null , account);
//                    break;
//            }
//            if(buyer == null) return "/error.jsp";
//            Cookie cookie = new Cookie("uid", buyer.getId());
//            session.setAttribute("handling-project", buyer.getHandlingProject());
//
//            cookie.setMaxAge(241200);
//
//            rsp.addCookie(cookie);
//
//            SimpleDateFormat sdp = new SimpleDateFormat("yyyy-MM-dd");
//            User user = new User(buyer.getId(), buyer.getName(), 2, sdp.format(buyer.getCreateTime()),
//                    buyer.getHandlingProject(), buyer.getPhone(), buyer.getEmail());
//            model.addAttribute("user", user);
//            return "/WEB-INF/jsp/mainframe/home.jsp";
//        }
    }

    @GetMapping("/login_out")
    public String loginout(HttpServletRequest request, HttpServletResponse response, Model model) {
        Cookie[] cookies = request.getCookies();
        String userId = null;
        String role = null;
        for (Cookie cookie : cookies) {
            if("userId".equals(cookie.getName())) {
                userId = cookie.getValue();
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
            if("token".equals(cookie.getName())) {
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
            if("role".equals(cookie.getName())) {
                role = cookie.getValue();
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }

        if(userId == null || role == null || !role.matches("^-?([0-3])$")) {
            model.addAttribute("msg", "未找到当前您的登录信息");
            return "/error.jsp";
        }

        int i = authenticationService.deleteAuthentication(userId, Integer.parseInt(role));
        if(i == 1) {
            return "/index.html";
        } else {
            model.addAttribute("msg", "退出失败");
            return "/error.jsp";
        }
    }

}
