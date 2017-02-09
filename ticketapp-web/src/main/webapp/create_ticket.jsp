<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ page import="java.sql.*" %>
<%ResultSet resultset =null;%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <link rel="stylesheet" type="text/css" media="screen" href="//d85wutc1n854v.cloudfront.net/live/css/screen_preview_legacy.css">
<title>Ticket Create</title>
</head>
<body>
<%
    try{
Class.forName("com.mysql.jdbc.Driver").newInstance();
Connection connection = 
         DriverManager.getConnection
            ("jdbc:mysql://localhost:3306/ticket_management_system?user=root&password=toor");

       Statement statement = connection.createStatement() ;

       resultset =statement.executeQuery("SELECT NAME FROM DEPARTMENTS") ;
%>

	<form action="/tickets/create_ticket" method="GET">
 <h3>Email Id :</h3>   <input type="email" name="EmailId" required placeholder="yourmail@yourdomain.com" required autofocus/>
	    <h3>Password :</h3> <input type="password" name="Password" required/>
	    <h3>Subject:</h3><input type="text" name="Subject" required/>
	   <h3>Description:</h3> <input type="text" name="Description" required/>
	   <h3>Department:</h3>
	    <select name="Department" required>
        <%  while(resultset.next()){ %>
            <option><%= resultset.getString(1)%></option>
         
        <% } %>
        </select> 
	    <h3>Priority:</h3>  <input type="radio" name="Priority" value="High"> High<br>
 					 <input type="radio" name="Priority" value="Medium"> Medium<br>
 					 <input type="radio" name="Priority" value="Low"> Low <br>
		<button type="submit"><h4>Create Ticket</h4></button>
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