package com.atguigu.crm.handler;

import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.atguigu.crm.entity.User;
import com.atguigu.crm.service.UserService;

@Controller
@RequestMapping(value="/user")
public class UserHandler {
	@Autowired
	private ResourceBundleMessageSource messageResource;
	@Autowired
	private UserService userService;
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String login(@RequestParam(value="username",required=false) String name,
						@RequestParam(value="password",required=false) String password,
						RedirectAttributes attributes,
						Locale locale,HttpSession session){
		User user = userService.login(name, password);
		if(user != null){
			session.setAttribute("user", user);
			return "success";
		}
		attributes.addFlashAttribute("message", messageResource.getMessage("error.user.login", null, locale));
		return "redirect:/index";
	}
}
