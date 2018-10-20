package net.xinqushi.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.xinqushi.common.XqsResult;
import net.xinqushi.dao.UserDao;
import net.xinqushi.service.UserService;
import net.xinqusi.pojo.User;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public XqsResult addUser(User user) {
		user.setCreattime(new Date());
		if(userDao.addUser(user)>0)
		{
			return XqsResult.build(200, "恭喜！新用户注册成功！",user);
		}else {
			return XqsResult.build(500, "注册失败！");
		}
		
	}

	@Override
	public XqsResult checkUser(User user) {
	
		User user1 = new User();
		User user2 = new User();
		user1.setUsername(user.getUsername());
		user1.setPassword(user.getPassword());
		List<User> list = userDao.selectUsers(user1);
		if(list.size()>0) {
			user2.setUpdatetime(new Date());
			user2.setId(list.get(0).getId());
			userDao.updateById(user2);
			user.setId(list.get(0).getId());
			return XqsResult.build(200, "用户名和密码正确",user);
		}else {
			user2.setUsername(user.getUsername());
			if(userDao.selectUsers(user2).size()>0) {	
				return XqsResult.build(500, "密码错误");
			}else {
				return XqsResult.build(300, "用户名错误！");
			}
		}
	}

	@Override
	public XqsResult showUsers() {
		try {
			return XqsResult.ok(userDao.selectUsers(new User()));
		} catch (Exception e) {
			return XqsResult.build(500, "获取用户列表失败！");
		}
		
	}

	@Override
	public XqsResult updateUser(User user) {
		return null;
	}

}
