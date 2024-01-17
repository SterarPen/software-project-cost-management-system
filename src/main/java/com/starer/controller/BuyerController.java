package com.starer.controller;

import com.starer.pojo.entity.Project;
import com.starer.pojo.entity.user.Buyer;
import com.starer.service.IProjectService;
import com.starer.service.IStageService;
import com.starer.service.IBuyerService;
import com.starer.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;

@Controller
public class BuyerController {

    private IBuyerService buyerService;
    private IProjectService projectService;
    private IStageService stageService;

    @Autowired
    public BuyerController(IBuyerService buyerService, IProjectService projectService,
                           IStageService stageService) {
        this.buyerService = buyerService;
        this.projectService = projectService;
        this.stageService = stageService;
    }



    @PostMapping("/buyer/update_password/{buyerId}")
    public void update_password(@PathVariable String buyerId, HttpServletRequest rq, HttpServletResponse rsp,
                                Model model) {
        String ide_code = rq.getParameter("ide_code");
        String new_password = rq.getParameter("new_password");

        boolean b = buyerService.updatePassword(buyerId, new_password);
        System.out.println(b);
    }

    @GetMapping("/user_type")
    @ResponseBody
    public String getUserType(HttpServletRequest request) {
        String[] userIdAndRole = CookieUtil.getUserIdAndRole(request.getCookies());
        return userIdAndRole[1];
    }
}
