package net.xinqushi.mapper;

import java.util.List;
import net.xinqusi.pojo.User;

public interface UserMapper {
 
	public int insert(User user);
	public List<User> selectUsers(User user);
	public int update(User user);
}