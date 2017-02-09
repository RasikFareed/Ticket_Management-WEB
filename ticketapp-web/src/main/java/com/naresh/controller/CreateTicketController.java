package com.naresh.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public String registerNewUser(@RequestParam("EmailId") String emailId,
			@RequestParam("Password") String password,ModelMap map) throws ServiceException {
	
		System.out.println("TicketController-> register-  "+",emailid=" + emailId + ",password:" + password);

			try {
				cts.login(emailId, password);
				
			} catch (ServiceException e) {
				map.addAttribute("ERROR", e.getMessage());
				LOGGER.log(Level.SEVERE, "Registration Failed Exception Occured!!", e);
				return "../Login.jsp";
			}
			return "redirect:../MainPage.jsp";
	}

	@GetMapping("/create_ticket")
	public String createTicket(@RequestParam("EmailId") String emailId, @RequestParam("Password") String password,
			@RequestParam("Subject") String subject, @RequestParam("Description") String description,
			@RequestParam("Department") String department, @RequestParam("Priority") String priority,ModelMap map)
			throws ServiceException {

		System.out.println("TicketController->create ticket-EmailId" + emailId + ",Password:" + password + ",Subject"
				+ subject + ",Description:" + description + ",Department:" + department + description + ",Priority:"
				+ priority);
		CreateTicketService createTicketService = new CreateTicketService();

		try {
			createTicketService.createTicket(emailId, password, subject, description, department, priority);
			return "redirect:../ticket_create_sucess.jsp";

		} catch (ServiceException e) {
			map.addAttribute("ERROR", e.getMessage());
			LOGGER.log(Level.SEVERE, "Ticket Creation Failed Exception Occured!!", e);
			return "../create_ticket.jsp";

		}

	}

	@GetMapping("/update_ticket")
	public String updateTicket(@RequestParam("EmailId") String emailId,
			@RequestParam("Password") String password, @RequestParam("IssueId") int issueId,
			@RequestParam("UpdateDescription") String updateDescription,ModelMap map) throws ServiceException {

		System.out.println("TicketController-> updateTicket- name:EmailId" + emailId + ",Password:" + password
				+ ",IssueId" + issueId + ",Description:" + updateDescription);
		CreateTicketService createTicketService = new CreateTicketService();

		try {
			createTicketService.updateTicket(emailId, password, issueId, updateDescription);
			return "redirect:../description_updated.jsp";

		} catch (ServiceException e) {
			map.addAttribute("ERROR", e.getMessage());
			LOGGER.log(Level.SEVERE, "Updating Description Exception Occured!!", e);
			return "../update_ticket.jsp";

		}

	}

	@GetMapping("/update_close")
	public String updateClose(@RequestParam("EmailId") String emailId,
			@RequestParam("Password") String password, @RequestParam("IssueId") int issueId,ModelMap map) throws ServiceException {

		System.out.println("TicketController-> updateTicket- name:EmailId" + emailId + ",Password:" + password
				+ ",IssueId" + issueId);
		CreateTicketService createTicketService = new CreateTicketService();

		try {
			createTicketService.updateClose(emailId, password, issueId);
			return "redirect:../ticket_closed.jsp";

		} catch (ServiceException e) {
			map.addAttribute("ERROR", e.getMessage());
			LOGGER.log(Level.SEVERE, "Closing TicketException Occured!!", e);
			return "../update_close.jsp";

		}

	}

	@GetMapping("/find_user_details")
	public String findUserDetails(@RequestParam("EmailId") String emailId, @RequestParam("Password") String password,ModelMap map)
			throws ServiceException {

		System.out.println("TicketController-> updateTicket- name:EmailId" + emailId + ",Password:" + password);
		CreateTicketService createTicketService = new CreateTicketService();
	try{
		Issue issue=new Issue();
	
		User user=new User();
		user.setId(4);
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
	public String assignEmployee(@RequestParam("EmailId") String emailId,@RequestParam("Password") String password, @RequestParam("IssueId") int issueId, @RequestParam("EmployeeId") int employeeId,ModelMap map)
			throws ServiceException {

		System.out.println("TicketController-> updateTicket- name:EmailId" + emailId + ",Password:" + password+",IssueId:0"+issueId+",EmployeeId:"+employeeId);
		CreateTicketService createTicketService = new CreateTicketService();

		try {
			createTicketService.assignEmployee(emailId, password, issueId, employeeId);
			return "redirect:../employee_assigned.jsp";

		} catch (ServiceException e) {
			map.addAttribute("ERROR", e.getMessage());
			LOGGER.log(Level.SEVERE, "Assigning Employee  Ticket Exception Occured!!", e);
			return "../assign_employee.jsp";

		}

	}
	
	@GetMapping("/ticket_solution")
	public String ticketSolution(@RequestParam("EmailId") String emailId,@RequestParam("Password") String password, @RequestParam("IssueId") int issueId, @RequestParam("TicketSolution") String ticketSolution,ModelMap map)
			throws ServiceException {

		System.out.println("TicketController-> updateTicket- name:EmailId" + emailId + ",Password:" + password+",IssueId:0"+issueId+",TicketSolution:"+ticketSolution);
		CreateTicketService createTicketService = new CreateTicketService();

		try {
			createTicketService.ticketSolution(emailId, password, issueId, ticketSolution);
			return "redirect:../solution_given.jsp";

		} catch (ServiceException e) {
			map.addAttribute("ERROR", e.getMessage());
			LOGGER.log(Level.SEVERE, "Assigning Solution to  Ticket Exception Occured!!", e);
			return "../ticket_solution.jsp";

		}

	}
	

	@GetMapping("/find_employee_tickets")
	public String findEmployeeTickets(@RequestParam("EmailId") String emailId, @RequestParam("Password") String password,ModelMap map)
			throws ServiceException {

		System.out.println("TicketController-> updateTicket- name:EmailId" + emailId + ",Password:" + password);
		CreateTicketService createTicketService = new CreateTicketService();

		try{
						
			List<Issue> i=createTicketService.findEmployeeTickets(emailId, password);
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
	public String deleteTicket(@RequestParam("EmailId") String emailId,@RequestParam("Password") String password, @RequestParam("IssueId") int issueId,ModelMap map)
			throws ServiceException {

		System.out.println("TicketController-> updateTicket- name:EmailId" + emailId + ",Password:" + password+",IssueId:0"+issueId);
		CreateTicketService createTicketService = new CreateTicketService();

		try {
			createTicketService.deleteTickets(emailId, password, issueId);
			return "redirect:../tickest_deleted.jsp";

		} catch (ServiceException e) {
			map.addAttribute("ERROR", e.getMessage());
			LOGGER.log(Level.SEVERE, "Deleting Ticket Exception Occured!!", e);
			return "../delete_ticket.jsp";

		}

	}
	
}
