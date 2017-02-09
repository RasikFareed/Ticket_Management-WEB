<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ page import="java.sql.*" %>
<%ResultSet resultset =null;%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <link rel="stylesheet" type="text/css" media="screen" href="//d85wutc1n854v.cloudfront.net/live/css/screen_preview_legacy.css">
<title>Assign Employee</title>
</head>
<body>
<%
    try{
Class.forName("com.mysql.jdbc.Driver").newInstance();
Connection connection = 
         DriverManager.getConnection
            ("jdbc:mysql://localhost:3306/ticket_management_system?user=root&password=toor");

       Statement statement = connection.createStatement() ;

       resultset =statement.executeQuery("SELECT ID FROM EMPLOYEES") ;
%>
<form action="/tickets/assign_employee" method="GET">
	  <h3>Email Id :</h3>   <input type="email" name="EmailId" required placeholder="yourmail@yourdomain.com" required autofocus/>
	    <h3>Password :</h3> <input type="password" name="Password" required/>
	    <h3>Issue Id:</h3><input type="text" name="IssueId" required/>
	    <h3>Employee Id:</h3>
	    <select name="EmployeeId" required>
        <%  while(resultset.next()){ %>
            <option><%= resultset.getString(1)%></option>
         
        <% } %>
        </select><br>
		<button type="submit"><h4>Assign Employee</h4></button>
	</form>
	${ERROR}
		<%
//**Should I input the codes here?**
        }
        catch(Exception e)
        {
             out.println("wrong entry"+e);
        }
%>
</body>
</html>