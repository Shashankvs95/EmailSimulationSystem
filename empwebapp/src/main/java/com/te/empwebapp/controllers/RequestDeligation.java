package com.te.empwebapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RequestDeligation {

	// redirecting req to external resource
	@GetMapping("/linkedIn")
	public String redirectToLinkedIn() {
		return "redirect:https://www.linkedin.com";
	}	
	
	//redirecting to internal resource
	@GetMapping("/knowMore")
	public String redirectToInternal() {
		return "redirect:login";
	}
	
	//forwarding the req
	@GetMapping("/forward")
	public String forwardToInternal() {
	  return "forward:login";
	}
}
