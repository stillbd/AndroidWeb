package net.xinqushi.service;

import net.xinqushi.common.XqsResult;
import net.xinqusi.pojo.User;

public interface UserService {

	public XqsResult addUser(User user);
	public XqsResult checkUser(User user);
	public XqsResult showUsers();
	public XqsResult updateUser(User user);
	
}
