package com.jt;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.jt.mapper.UserMapper;
import com.jt.pojo.User;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestMybatis {
	@Autowired
	private UserMapper userMapper;
	@Test
	public void testFindAll() {
		System.out.println(userMapper.findAll());
	}

	/**
	 * 参数说明:
	* 	queryWrapper表示where条件构造器
	*  sql:select * from user;
	*/
	@Test public void testSelect() { 
		List<User> list =
				userMapper.selectList(null); 
		System.out.println(list); 
	}
	
	/**
	 * 要求:查询id=1的数据
	*/
	@Test
	public void testFindOne() {
		//方法一
		User user = userMapper.selectById(1);
		System.out.println(user);
		//方法二
		QueryWrapper<User> queryWrapper=new QueryWrapper<User>();
		queryWrapper.eq("id", 1);
		User user2 = userMapper.selectOne(queryWrapper);
		System.out.println(user2);
	}
	
	/**
	 * 	要求:查询age>1 and age<150 的数据
	 *     参数说明: > gt  < lt = eq
	 *     		>=ge  <= le
	 */
	@Test
	public void testFindByAge() {
		//方法一
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.gt("age", 1)
					.lt("age", 150);
		List<User> list = userMapper.selectList(queryWrapper);
		System.out.println(list);
		//方法二
		QueryWrapper<User> queryWrapper2=new QueryWrapper<User>();
		queryWrapper2.between("age", 1, 150);
		List<User> list2 = userMapper.selectList(queryWrapper2);
		System.out.println(list2);
	}
	
	/**
	 * 3. 模糊查询 like %xxxx%
	 *       查询名称 %精%
	*/
	@Test
	public void testFindByLike() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.like("name", "精");
		List<User> list = userMapper.selectList(queryWrapper);
		System.out.println(list);
	}
	
	/**
	 * 4. 查询age=18岁 或者 女神仙 age>=300 sex=女
	 *      
	 */
	@Test
	public void testFindByWhere() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.eq("age", 18)
					.or()
					.ge("age", 300)
					.eq("sex", "女 ");
		List<User> list = userMapper.selectList(queryWrapper);
		System.out.println(list);
	}
	/**
	 * 插入一条数据
	 */
	@Test
	public void insert() {
		User user = new User();
		user.setId(null)
			.setName("wh")
			.setAge(22)
			.setSex("男");
		userMapper.insert(user);
	}
	/**
	 * 1.将wh名称改为cq
	 * 2.年龄改为18
	 * 3.性别改为女
	 * sql:update user set name=xxx,age=xxx,sex=xxx
	 * 		where name=wh
	 * 
	 * 修改参数说明:
	 * 	1.entity 代表需要修改后的数据
	 *  2.updateWrapper 修改的条件构造器
	 */
	@Test
	public void update() {
		User user = new User();
		user.setName("cq")
			.setAge(18)
			.setSex("女");
		UpdateWrapper<User> updateWrapper = new UpdateWrapper<User>();
		updateWrapper.eq("name", "wh");
		userMapper.update(user, updateWrapper);
	}
	
	/**
	 *	删除数据
	 *	name=cq 
	 */
	@Test
	public void delete() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.eq("name", "cq");
		userMapper.delete(queryWrapper);
	}
}
