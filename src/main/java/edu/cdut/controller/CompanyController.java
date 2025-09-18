package edu.cdut.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;
import edu.cdut.mapper.CompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CompanyController {


    @Autowired
    private CompanyMapper companyMapper;


    @GetMapping("showcompany")
    @ResponseBody
    public String showCompanyAll() throws JsonProcessingException {
        Map<String,Object> company = new HashMap<>();
        company.put("code",0);
        company.put("msg","");// "count": 1000,
        company.put( "count",(companyMapper.selectList(new QueryWrapper<>()).size()));
        company.put("data",companyMapper.selectList(new QueryWrapper<>()));


        JsonMapper jsonMapper =new JsonMapper();

        return jsonMapper.writeValueAsString(company);
    }


}
