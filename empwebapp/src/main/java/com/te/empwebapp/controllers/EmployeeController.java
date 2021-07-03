package com.te.empwebapp.controllers;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.te.empwebapp.beans.EmployeeInfoBean;
import com.te.empwebapp.beans.User_info;
import com.te.empwebapp.beans.mail_info;
import com.te.empwebapp.service.EmployeeService;

@Controller
public class EmployeeController {

	String id1;
	String email1;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		System.out.println("init Binder");
		CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true);
		binder.registerCustomEditor(Date.class, editor);
	}
	
	@Autowired
	private EmployeeService service;

	@GetMapping("/login")
	public String getEmpForm() {
		return "empLogin";
	}// getEmpForm

	@PostMapping("/login")
	public String authenticate(String email, String password, HttpServletRequest request, ModelMap map) {
		User_info user = service.authenticate(email, password);
		if (user != null) {
			this.id1 = user.getUid();
			this.email1=user.getEmail();
			
			System.out.print(id1+" "+email1 +"is set successfully---------------------------------------");
			HttpSession session = request.getSession();
			session.setAttribute("loggedIn", user);
//			session.setMaxInactiveInterval(3600);
			map.addAttribute("name", user.getUsername());
			return "employeeHome";
		} else {
			map.addAttribute("errMsg", "Invalid Credentials");
			return "empLogin";
		}
	}// authenticate
	
	@GetMapping("/addEmp")
	public String getAddFrom(@SessionAttribute(name = "loggedIn", required = false) User_info user,
			ModelMap map) {
		if (user != null) {
			return "insertEmployee";
		} else {
			map.addAttribute("errMsg", "Please Login First");
			return "empLogin";
		}

	}//

	@PostMapping("/add")
	public String addEmployee(String mid ,String email,String subject,String message,String status,
			@SessionAttribute(name = "loggedIn", required = false) User_info user, ModelMap map) {
		System.out.println(mid+" "+email);
		if (user != null) {
			if (service.addEmployee(mid,email,email1,subject,message,status,id1)) {
				map.addAttribute("msg", "Successfully Inserted");
			} else {
				map.addAttribute("msg", "Failed to Insert");
			}
			return "insertEmployee";
		} else {
			map.addAttribute("errMsg", "Please Login First");
			return "empLogin";
		}

	}// add Employee
	
	@GetMapping("/getAll")
	public String getAllRecords(@SessionAttribute(name = "loggedIn", required = false) User_info user,
			ModelMap map) {
		if (user != null) {
			List<mail_info> mail = service.getAllEmployees(id1);
			if (mail != null) {
				map.addAttribute("infos", mail);
			}else {
				map.addAttribute("errMsg", "No Records Found");
			}
			map.addAttribute("name", user.getUsername());	
			return "employeeHome";
		} else {
			map.addAttribute("errMsg", "Please Login First");
			return "empLogin";
		}
	}
	
	@GetMapping("/getAllSent")
	public String getAllSentRecords(@SessionAttribute(name = "loggedIn", required = false) User_info user,
			ModelMap map) {
		if (user != null) {
			List<mail_info> mail = service.getAllSent(id1);
			if (mail != null) {
				
				map.addAttribute("infos", mail);
			}else {
				map.addAttribute("errMsg", "No Records Found");
			}
			map.addAttribute("name", user.getUsername());	
			return "empSearchPage";
		} else {
			map.addAttribute("errMsg", "Please Login First");
			return "empLogin";
		}
	}
	
	

//	@GetMapping("/searchPage")
//	public String getSearchPage(HttpSession session, ModelMap map) {
//		User_info user = (User_info) session.getAttribute("loggedIn");
//		System.out.println(user);
//		if (user != null) {
//			System.out.println("valid");
//			map.addAttribute("name", user.getUsername());	
//			return "empSearchPage";
//		} else {
//			map.addAttribute("errMsg", "Please Login First");
//			return "empLogin";
//		}
//	}// getSearchPage
	
	
//	@GetMapping("/searchPage")
//	public String getAllsent(@SessionAttribute(name = "loggedIn", required = false) User_info user,
//			ModelMap map) {
//		if (user != null) {
//			List<mail_info> mail = service.getAllEmployees(from_id);
//			if (mail != null) {
//				
//				map.addAttribute("infos", mail);
//			}else {
//				map.addAttribute("errMsg", "No Records Found");
//			}
//			map.addAttribute("name", user.getUsername());	
//			return "employeeHome";
//		} else {
//			map.addAttribute("errMsg", "Please Login First");
//			return "empLogin";
//		}
//	}
//	@GetMapping("/search")
//	public String getAllsent(@SessionAttribute(name = "loggedIn", required = false) User_info user,
//			ModelMap map) {
//		if (user != null) {
//			List<mail_info> mail = service.getAllSentMails(user.getUid());
//			if (mail != null) {
//				
//				map.addAttribute("infos", mail);
//			}else {
//				map.addAttribute("errMsg", "No Records Found");
//			}
//			map.addAttribute("name", user.getUsername());	
//			return "employeeHome";
//		} else {
//			map.addAttribute("errMsg", "Please Login First");
//			return "empLogin";
//		}
//	}
//	@GetMapping("/search")
//	public String getEmployeeData(int id,
//			@SessionAttribute(name = "loggedIn", required = false) EmployeeInfoBean infoBean, ModelMap map) {
//		if (infoBean != null) {
//			EmployeeInfoBean employeeInfoBean = service.getEmployeeData(id);
//			if (employeeInfoBean != null) {
//				map.addAttribute("empData", employeeInfoBean);
//			} else {
//				map.addAttribute("errMsg", "Data not Found");
//			}
//			return "empSearchPage";
//		} else {
//			map.addAttribute("errMsg", "Please Login First");
//			return "empLogin";
//		}
//	}

	@GetMapping("/logout")
	public String logout(HttpSession session, ModelMap map) {
		session.invalidate();
		map.addAttribute("msg", "logout successfull");
		return "empLogin";
	}// logout

	@GetMapping("/getDeleteForm")
	public String getDeleteForm(@SessionAttribute(name = "loggedIn", required = false) User_info user,
			ModelMap map) {
		if (user != null) {
			return "deleteEmp";
		} else {
			map.addAttribute("errMsg", "Please Login First");
			return "empLogin";
		}
	}//
	
	//delete recived mails
	@GetMapping("/getDeleteForm1")
	public String getDeleteForm1(@SessionAttribute(name = "loggedIn", required = false) User_info user,
			ModelMap map) {
		if (user != null) {
			return "deleteEmp1";
		} else {
			map.addAttribute("errMsg", "Please Login First");
			return "empLogin";
		}
	}//
	
	@GetMapping("/delete1")
	public String deleteData1(String mid, @SessionAttribute(name = "loggedIn", required = false) User_info user,
			ModelMap map) {
		if (user != null) {
			if (service.deleteEmpData1(mid,id1)) {
				map.addAttribute("msg", "Data Deleted successfully for id : " + mid);
			} else {
				map.addAttribute("msg", "Could not find Record for id : " + mid);
			}
			return "deleteEmp1";
		} else {
			map.addAttribute("errMsg", "Please Login First");
			return "empLogin";
		}

	}//

	@GetMapping("/delete")
	public String deleteData(String mid, @SessionAttribute(name = "loggedIn", required = false) User_info user,
			ModelMap map) {
		if (user != null) {
			if (service.deleteEmpData(mid,id1)) {
				map.addAttribute("msg", "Data Deleted successfully for id : " + mid);
			} else {
				map.addAttribute("msg", "Could not find Record for id : " + mid);
			}
			return "deleteEmp";
		} else {
			map.addAttribute("errMsg", "Please Login First");
			return "empLogin";
		}

	}//

	

	@GetMapping("/updateEmployee")
	public String getUpadatePage(@SessionAttribute(name = "loggedIn", required = false) EmployeeInfoBean infoBean,
			ModelMap map) {
		if (infoBean != null) {
			map.addAttribute("id", infoBean);
			return "updateEmployee";
		} else {
			map.addAttribute("errMsg", "Please Login First");
			return "empLogin";

		}
	}//

	@PostMapping("/update")
	public String updateEmployeeData(@SessionAttribute(name = "loggedIn", required = false) EmployeeInfoBean infoBean,
			ModelMap map, EmployeeInfoBean employeeInfoBean) {
		if (infoBean != null) {
			if (service.updateRecord(employeeInfoBean)) {
				map.addAttribute("msg", "Updated Successfully");
				map.addAttribute("id", employeeInfoBean);
			} else {
				map.addAttribute("msg", "Updation Failed");
				map.addAttribute("id", infoBean);
			}
			return "updateEmployee";
		} else {
			map.addAttribute("errMsg", "Please Login First");
			return "empLogin";
		}
	}//

	

}
