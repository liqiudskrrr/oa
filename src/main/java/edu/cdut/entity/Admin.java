package edu.cdut.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("admin")
public class Admin {

    @TableId(value = "admin_id", type = IdType.AUTO)
    private Integer adminId;

    private String username;          // 表字段就叫 username

    @TableField("admin_password")     // 表字段叫 admin_password
    private String adminPassword; }    // 属性名也对应