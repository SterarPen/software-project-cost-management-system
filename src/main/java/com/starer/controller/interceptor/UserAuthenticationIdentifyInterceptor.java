package com.starer.controller.interceptor;

import com.starer.pojo.entity.Authentication;
import com.starer.service.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

public class UserAuthenticationIdentifyInterceptor implements HandlerInterceptor {

    private IAuthenticationService authenticationService;

    @Autowired
    public UserAuthenticationIdentifyInterceptor(IAuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String userId = null;
        String token = null;
        String role = null;

        Cookie[] cookies = request.getCookies();
        if(cookies == null) {
            request.setAttribute("msg", "用户校验不通过，请重新登录！");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/error.jsp");
            requestDispatcher.forward(request, response);
            return false;
        }
        for (Cookie cookie : cookies) {
            if("userId".equals(cookie.getName())) userId = cookie.getValue();
            if("token".equals(cookie.getName())) token = cookie.getValue();
            if("role".equals(cookie.getName())) role = cookie.getValue();
        }

        if(userId == null || token == null || role == null || !role.matches("^-?([0-3])$")) {
            //TODO：前端提示
            request.setAttribute("msg", "用户校验不通过，请重新登录！");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/error.jsp");
            requestDispatcher.forward(request, response);
            return false;
        }

        // 判断Cookie中的登录凭证是否有效
        Authentication authentication = authenticationService.queryAuthentication(userId, Integer.parseInt(role));
        if(authentication == null || new Timestamp(System.currentTimeMillis()).after(authentication.getDeleteTime())
                || !token.equals(authentication.getToken()) || !role.equals(String.valueOf(authentication.getRole()))) {
            //TODO：认证失效，需重新登陆,前端提示
            request.setAttribute("msg", "用户校验不通过，请重新登录！");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/error.jsp");
            requestDispatcher.forward(request, response);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
