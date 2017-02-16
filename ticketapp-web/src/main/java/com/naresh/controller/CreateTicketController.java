package com.naresh.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ticket.dao.UserDAO;
import com.ticket.exception.ServiceException;
import com.ticket.model.Employee;
import com.ticket.model.Issue;
import com.ticket.model.User;
import com.ticket.service.CreateTicketService;

@Controller
@RequestMapping("/tickets")
public class CreateTicketController {
	private static final Logger LOGGER = Logger.getLogger(CreateTicketController.class.getName());
	CreateTicketService cts=new CreateTicketService();
	User user=new User();
	Employee employee=new Employee();
	@GetMapping
	public String index(ModelMap modelmap) throws ServiceException {
		System.out.println("department->index");
		Issue issues=new Issue();
		List<Issue> issue;
		issue = cts.findUserDetails(issues);
		modelmap.addAttribute("User_Details", issue);
		return "index.jsp";
	}

	

	@GetMapping("/register")
	public String registerNewUser(@RequestParam("Name") String name, @RequestParam("EmailId") String emailId,
			@RequestParam("Password") String password,ModelMap map) throws ServiceException {

		System.out
				.println("TicketController-> register- name:" + name + ",emailid=" + emailId + ",password:" + password);
		CreateTicketService createTicketService = new CreateTicketService();
		try {
			createTicketService.registration(name, emailId, password);
			return "redirect:../index.jsp";

		} catch (ServiceException e) {
			map.addAttribute("ERROR", e.getMessage());
			LOGGER.log(Level.SEVERE, "Registration Failed Exception Occured!!", e);
			return "../register.jsp";

		}

	}
	
	@GetMapping("/login")
	public String login(@RequestParam("EmailId") String emailId,
			@RequestParam("Password") String password,ModelMap map,HttpSession session) throws ServiceException {
	
		System.out.println("TicketController-> register-  "+",emailid=" + emailId + ",password:" + password);
		
			try {
				
				cts.login(emailId, password);
				user.setEmailId(emailId);
				user.setPassword(password);
				session.setAttribute("USER_LOGGED_IN", user);
				
			} catch (ServiceException e) {
				map.addAttribute("ERROR", e.getMessage());
				LOGGER.log(Level.SEVERE, "Registration Failed Exception Occured!!", e);
				return "../Login.jsp";
			}
			return "redirect:../MainPage.jsp";
	}

	@GetMapping("/employeeLogin")
	public String employeeLogin(@RequestParam("EmailId") String emailId,
			@RequestParam("Password") String password,ModelMap map,HttpSession session) throws ServiceException {
	
		System.out.println("TicketController-> register-  "+",emailid=" + emailId + ",password:" + password);

			try {
				cts.employeeLogin(emailId, password);
				employee.setEmailId(emailId);
				employee.setPassword(password);
				session.setAttribute("Employee_Logged_In", employee);
				
			} catch (ServiceException e) {
				map.addAttribute("ERROR", e.getMessage());
				LOGGER.log(Level.SEVERE, "Registration Failed Exception Occured!!", e);
				return "../EmployeeLogin.jsp";
			}
			return "redirect:../EmployeeMainPage.jsp";
	}
	
	
	
	
	@GetMapping("/create_ticket")
	public String createTicket(	@RequestParam("Subject") String subject, @RequestParam("Description") String description,
			@RequestParam("Department") String department, @RequestParam("Priority") String priority,ModelMap map,HttpSession session)
			throws ServiceException {

		System.out.println("TicketController->create ticket- "+",Subject"
				+ subject + ",Description:" + description + ",Department:" + department + description + ",Priority:"
				+ priority);
		CreateTicketService createTicketService = new CreateTicketService();

		try {
			session.getAttribute("USER_LOGGED_IN");
			createTicketService.createTicket(user.getEmailId(),user.getPassword(), subject, description, department, priority);
			return "redirect:../ticket_create_sucess.jsp";

		} catch (ServiceException e) {
			map.addAttribute("ERROR", e.getMessage());
			LOGGER.log(Level.SEVERE, "Ticket Creation Failed Exception Occured!!", e);
			return "../create_ticket.jsp";

		}

	}

	@GetMapping("/update_ticket")
	public String updateTicket(@RequestParam("IssueId") int issueId,
			@RequestParam("UpdateDescription") String updateDescription,ModelMap map,HttpSession session) throws ServiceException {


		CreateTicketService createTicketService = new CreateTicketService();

		try {
			session.getAttribute("USER_LOGGED_IN");
			createTicketService.updateTicket(user.getEmailId(),user.getPassword(), issueId, updateDescription);
			return "redirect:../description_updated.jsp";

		} catch (ServiceException e) {
			map.addAttribute("ERROR", e.getMessage());
			LOGGER.log(Level.SEVERE, "Updating Description Exception Occured!!", e);
			return "../update_ticket.jsp";

		}

	}

	@GetMapping("/update_close")
	public String updateClose(@RequestParam("IssueId") int issueId,ModelMap map,HttpSession session) throws ServiceException {

		System.out.println("TicketController-> updateTicket- name:EmailId" 
				+ ",IssueId" + issueId);
		CreateTicketService createTicketService = new CreateTicketService();

		try {
			session.getAttribute("USER_LOGGED_IN");
			createTicketService.updateClose(user.getEmailId(),user.getPassword(), issueId);
			return "../ticket_closed.jsp";

		} catch (ServiceException e) {
			map.addAttribute("ERROR", e.getMessage());
			LOGGER.log(Level.SEVERE, "Closing TicketException Occured!!", e);
			return "../close_ticket.jsp";

		}

	}

	@GetMapping("/find_user_details")
	public String findUserDetails(ModelMap map,HttpSession session)
			throws ServiceException {

		CreateTicketService createTicketService = new CreateTicketService();
	try{
		session.getAttribute("USER_LOGGED_IN");
		Issue issue=new Issue();
	
		
		UserDAO userDao=new UserDAO();
		user.setId(userDao.findUserId(user.getEmailId()).getId());
		
		issue.setUserId(user);
		List<Issue> i=createTicketService.findUserDetails(issue);
		map.addAttribute("list", i);
		return "../find_user_tickets.jsp";
	}
	catch (ServiceException e) {
		map.addAttribute("ERROR", e.getMessage());
		LOGGER.log(Level.SEVERE, "Assigning Employee  Ticket Exception Occured!!", e);
		return "../find_user_details.jsp";

	}
	}

	@GetMapping("/assign_employee")
	public String assignEmployee(@RequestParam("IssueId") int issueId, @RequestParam("EmployeeId") int employeeId,ModelMap map,HttpSession session)
			throws ServiceException {

		System.out.println("TicketController-> updateTicket- name:EmailId" +",IssueId"+issueId+",EmployeeId:"+employeeId);
		CreateTicketService createTicketService = new CreateTicketService();

		try {
			session.getAttribute("Employee_Logged_In");
			createTicketService.assignEmployee(employee.getEmailId(),employee.getPassword(), issueId, employeeId);
			return "redirect:../employee_assigned.jsp";

		} catch (ServiceException e) {
			map.addAttribute("ERROR", e.getMessage());
			LOGGER.log(Level.SEVERE, "Assigning Employee  Ticket Exception Occured!!", e);
			return "../assign_employee.jsp";

		}

	}
	
	@GetMapping("/ticket_solution")
	public String ticketSolution(@RequestParam("IssueId") int issueId, @RequestParam("TicketSolution") String ticketSolution,ModelMap map,HttpSession session)
			throws ServiceException {

		System.out.println("TicketController-> updateTicket- name:EmailId" +",IssueId"+issueId+",TicketSolution:"+ticketSolution);
		CreateTicketService createTicketService = new CreateTicketService();

		try {
			session.getAttribute("Employee_Logged_In");
			createTicketService.ticketSolution(employee.getEmailId(),employee.getPassword(), issueId, ticketSolution);
			return "redirect:../solution_given.jsp";

		} catch (ServiceException e) {
			map.addAttribute("ERROR", e.getMessage());
			LOGGER.log(Level.SEVERE, "Assigning Solution to  Ticket Exception Occured!!", e);
			return "../ticket_solution.jsp";

		}

	}
	

	@GetMapping("/find_employee_tickets")
	public String findEmployeeTickets(ModelMap map,HttpSession session)
			throws ServiceException {

		
		CreateTicketService createTicketService = new CreateTicketService();

		try{
			session.getAttribute("Employee_Logged_In");		
			List<Issue> i=createTicketService.findEmployeeTickets(employee.getEmailId(),employee.getPassword());
			map.addAttribute("list", i);
			return "../find_employee_tickets.jsp";
		}
 catch (ServiceException e) {
			map.addAttribute("ERROR", e.getMessage());
			LOGGER.log(Level.SEVERE, "Viewing  Ticket Exception Occured!!", e);
			return "../find_employee_tickets.jsp";

		}

	}
	@GetMapping("/delete_ticket")
	public String deleteTicket(@RequestParam("IssueId") int issueId,ModelMap map,HttpSession session)
			throws ServiceException {

		System.out.println("TicketController-> updateTicket- name:EmailId" +",IssueId:"+issueId);
		CreateTicketService createTicketService = new CreateTicketService();

		try {
			session.getAttribute("Employee_Logged_In");
			createTicketService.deleteTickets(employee.getEmailId(),employee.getPassword(), issueId);
			return "../ticket_deleted.jsp";

		} catch (ServiceException e) {
			map.addAttribute("ERROR", e.getMessage());
			LOGGER.log(Level.SEVERE, "Deleting Ticket Exception Occured!!", e);
			return "../delete_ticket.jsp";

		}

	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session)
	{
		session.invalidate();
			return "redirect:/";


	}
	
}
