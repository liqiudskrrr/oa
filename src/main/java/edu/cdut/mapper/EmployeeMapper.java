package edu.cdut.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.cdut.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}