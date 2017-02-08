<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <link rel="stylesheet" type="text/css" media="screen" href="//d85wutc1n854v.cloudfront.net/live/css/screen_preview_legacy.css">
<title>Find Employee Tickets</title>
</head>
<body>
<form action="/tickets/find_employee_tickets" method="GET">
	  <h3>Email Id :</h3>   <input type="text" name="EmailId" required autofocus/>
	    <h3>Password :</h3> <input type="password" name="Password" required/><br>
		<button type="submit"><h4>Find My Tickets</h4></button>
	</form>
	${ERROR}
</body>
</html>