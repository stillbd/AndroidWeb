package net.xinqushi.dao;

import java.util.List;

import net.xinqusi.pojo.User;

public interface UserDao {

	public int addUser(User user);
	public List<User> selectUsers(User user);
	public int updateById(User user);
}
