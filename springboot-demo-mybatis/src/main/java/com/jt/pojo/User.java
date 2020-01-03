package com.jt.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@TableName	//(value="user")
//如果对象名称与表名一致可省略
public class User {
	@TableId(type = IdType.AUTO)//标识主键
	private Integer id;//主键自增
	//@TableField("name")
	//如果名称一致(包含驼峰规则)，注解可省略
	private String name;
	private Integer age;
	private String sex;

}
