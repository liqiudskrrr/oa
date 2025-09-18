package edu.cdut.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import edu.cdut.entity.Admin;
import edu.cdut.mapper.AdminMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AdminController {

    @Resource
    private AdminMapper adminMapper;

    @GetMapping("login")
    @ResponseBody
    public String  adminLogin(String username ,String password ) throws JsonProcessingException {
        //TODO 用户判断的逻辑 你们实现
        JsonMapper jsonMapper =new JsonMapper();
        Map<String,Object> adminLogin = new HashMap<>();

        QueryWrapper<Admin> queryWrapper =new QueryWrapper<>();


        queryWrapper.eq("username",username).eq("admin_password",password);

        Admin admin  = adminMapper.selectOne(queryWrapper);
        if(admin==null){
            adminLogin.put("code",0);
            adminLogin.put("msg","fail");// 失败
            adminLogin.put("data",null);
        }else{
            adminLogin.put("code",0);
            adminLogin.put("msg","success");// 成功
            adminLogin.put("data",admin);
        }
        return jsonMapper.writeValueAsString(adminLogin);

    }

}
