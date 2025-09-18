package edu.cdut.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.cdut.entity.Admin;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminMapper  extends BaseMapper<Admin> {
}
