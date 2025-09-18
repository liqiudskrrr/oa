package edu.cdut.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.cdut.entity.Employee;
import edu.cdut.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 分页 + 模糊搜索用户名
     * GET /emp/page?page=1&limit=10&key=张三
     */
    @GetMapping("/page")
    public Map<String, Object> page(@RequestParam(defaultValue = "1") long page,
                                    @RequestParam(defaultValue = "10") long limit,
                                    @RequestParam(defaultValue = "") String key) {
        QueryWrapper<Employee> qw = new QueryWrapper<>();
        qw.like(!key.isEmpty(), "id", key)
                .orderByDesc("id");

        IPage<Employee> iPage = employeeMapper.selectPage(new Page<>(page, limit), qw);

        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", iPage.getTotal());   // ← 原来是 0，改成真实总数
        map.put("data", iPage.getRecords());
        return map;
    }
}