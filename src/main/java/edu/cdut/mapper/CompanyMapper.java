package edu.cdut.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.cdut.entity.Company;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CompanyMapper extends BaseMapper<Company> {
}
