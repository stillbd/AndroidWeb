package net.xinqushi.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import net.xinqushi.common.XqsResult;
import net.xinqushi.service.UserService;
import net.xinqusi.pojo.User;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//跳转页面
	@RequestMapping(value="/page/{str}",method=RequestMethod.GET )
	public String getjsp(@PathVariable("str")String str) {
		return str;
	}
	
	//获取用户列表
	@RequestMapping("/getUsers")
	@ResponseBody
	public XqsResult getUsers(@RequestParam("currentPage")Integer currentPage,@RequestParam("pageSize")Integer pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		@SuppressWarnings("unchecked")
		PageInfo<User> pageInfo =new PageInfo<User>((ArrayList<User>)userService.showUsers().getData(), 5);
		return XqsResult.ok(pageInfo);
	}
	
	//用户注册
	@RequestMapping("/register")
	@ResponseBody
	public XqsResult register(User user) {
		//�ж��û��������벻Ϊ��
		if(user==null||user.getUsername()==""||user.getPassword()=="")
		{
			return XqsResult.build(500, "用户名和密码不能为空！");
		}else {
			XqsResult result = userService.checkUser(user);
			//�ж��û����Ƿ��Ѿ���ע��
			if(result.getStatus()==200||result.getStatus()==500) {
				return XqsResult.build(500, "该用户名已被注册！请重新注册！");
			}
			return userService.addUser(user);
		}
	}
	
	//用户登录
	@RequestMapping("/login")
	@ResponseBody
	public XqsResult login(User user) {
		//校验
		if(user==null||user.getUsername()==""||user.getPassword()=="")
		{
			return XqsResult.build(500, "用户名或密码不能为空");
		}else {
			
			return userService.checkUser(user);
		}
	}

}
