package net.xinqushi.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.DigestUtils;

import net.xinqushi.dao.UserDao;
import net.xinqushi.mapper.UserMapper;
import net.xinqusi.pojo.User;
@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private UserMapper userMapper;
	//用户注册
	@Override
	public int addUser(User user) {
		md5Digest(user);
		return userMapper.insert(user);
	}
	//查找用户数据
	@Override
	public List<User> selectUsers(User user) {
		md5Digest(user);
		return userMapper.selectUsers(user);
	}
	@Override
	public int updateById(User user) {
		md5Digest(user);
		return userMapper.update(user);
	}

	private void md5Digest(User user) {
		if(user.getPassword()!=null){
			user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
		}
	}

}
