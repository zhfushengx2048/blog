/**
 * @FileName: AdminController
 * @Author: fusheng
 * @Date: 2020/3/12 11:21
 * Description:
 */
package xyz.fusheng.blog.controller;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;
import xyz.fusheng.blog.enums.ResultEnum;
import xyz.fusheng.blog.pojo.Admin;
import xyz.fusheng.blog.utils.Result;
import xyz.fusheng.blog.utils.ShiroUtils;
import xyz.fusheng.blog.utils.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    /**
     *
     * @param admin
     * @return
     */
    @PostMapping("/login")
    public Result<Object> login(@RequestBody Admin admin){
        // 登录失败
        if(admin == null || StringUtils.isBlank(admin.getUsername()) || StringUtils.isBlank(admin.getPassword())){
            return new Result<>(ResultEnum.PARAMS_NULL.getCode(),"用户名或密码错误");
        }
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken authenticationToken = new UsernamePasswordToken(admin.getUsername(),admin.getPassword());
        try {
            subject.login(authenticationToken);
        }catch (Exception e){
            e.printStackTrace();
            return new Result<>(ResultEnum.PARAMS_NULL.getCode(),"用户名或密码错误");
        }
        // 登录成功
        Serializable sessionId = subject.getSession().getId();
        Map<String, Object> returnMap = new HashMap<>(2);
        returnMap.put("token", sessionId);
        return new Result<>(returnMap);
    }

    /**
     * 获取当前登用户信息
     * @return
     */
    @GetMapping("/info")
    public Result<Admin> getLoginInfo(){
        Admin loginAdmin = (Admin) ShiroUtils.getLoginUser();
        loginAdmin.setPassword("******");
        return new Result<>(loginAdmin);
    }

}
