package com.starer.util;

import com.starer.pojo.entity.user.Admin;
import com.starer.pojo.entity.user.Buyer;
import com.starer.pojo.entity.user.Manager;
import com.starer.pojo.vo.UserDto;
import com.starer.service.IAdminService;
import com.starer.service.IBuyerService;
import com.starer.service.IManagerService;

/**
 * @Author: pengxiong
 * @Date: 2023/12/29 23:15:03
 * @Version: V1.0
 * @Description:
 **/
public class PojoConvertUtil {

    public static UserDto getUserDtoByUserId(String userId, String role, IAdminService adminService,
                                             IBuyerService buyerService, IManagerService managerService) {
        UserDto userDto = null;
        switch (role) {
            case "0":
                Admin admin = adminService.getAdminInformation(userId);
//                if(admin == null) {
//                    model.addAttribute("msg", "发生错误，用户ID不存在");
//                    return "/error.jsp";
//                }
                userDto = new UserDto(admin.getAdminId(), admin.getAdminName(), null, null,
                        null, RoleType.ADMIN.getRoleTypeInt());
                break;
            case "1":
                Buyer buyer = buyerService.getInformation(userId, null, null);
                userDto = new UserDto(buyer.getId(), buyer.getName(), buyer.getPhone(),
                        buyer.getEmail(), DateTimeUtil.convertTimestampToString(buyer.getCreateTime()),
                        buyer.getHandlingProject(), RoleType.BUYER.getRoleTypeInt());
                break;
            case "2":
                Manager manager = managerService.getInformation(userId, null ,null);
                userDto = new UserDto(manager.getManagerId(), manager.getManagerName(), manager.getPhone(),
                        manager.getEmail(), DateTimeUtil.convertTimestampToString(manager.getCreateTime()),
                        manager.getHandlingProject(), RoleType.MANAGER.getRoleTypeInt());
            default:
                break;
        }
        return userDto;
    }
}
