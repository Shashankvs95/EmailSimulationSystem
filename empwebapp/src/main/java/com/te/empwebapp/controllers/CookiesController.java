package com.te.empwebapp.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cookies")
public class CookiesController {

	@GetMapping("/showcookiepage")
	public String getCookiePage() {
		return "cookies";
	}

	@GetMapping("/createCookie")
	public String createCookies(HttpServletResponse res, ModelMap map) {
		Cookie cookie = new Cookie("JFSA", "SpringMVC");
		cookie.setMaxAge(60);
		res.addCookie(cookie);
		
		map.addAttribute("msg", "Cookies Created Successfully");
		return "cookies";
	}

	@GetMapping("/readCookie")
	public String readCookie(HttpServletRequest request, @CookieValue(name = "JSESSIONID", required = false) String val,
			ModelMap map) {
		Cookie[] cookies = request.getCookies();
		System.out.println(cookies[0].getValue() );
		System.out.println(cookies[1].getValue() );
		if (val != null) {
			map.addAttribute("cookie", val);
		} else {
			map.addAttribute("msg", "Cookie Not Found");
		}
		return "cookies";
	}
}
