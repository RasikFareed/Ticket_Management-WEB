<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
   String name = request.getParameter( "EmailId" );
   session.setAttribute( "theEmail", name );
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<title>Main Page</title>
</head>
<body>
<button onclick="location.href='create_ticket.jsp'" type="button">
     Create Ticket</button>
     <button onclick="location.href='update_ticket.jsp'" type="button">
     Update Ticket</button>
     <button onclick="location.href='close_ticket.jsp'" type="button">
     Close Ticket</button>
     <button onclick="location.href='find_user_tickets.jsp'" type="button">
     View My Tickets</button>
${ERROR}
</body>
</html>