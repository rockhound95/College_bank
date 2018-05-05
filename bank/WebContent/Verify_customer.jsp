<%@page import="model.*" %>
<%@page import="java.util.*" %>
<%@page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="" method="get">
<table>
<th>USERName</th><th>Address</th><th>DOB</th><th>Phone</th>><th>ACCOUNT_NUMBER</th><th>ACC_TYPE</th><th>AMOUNT</<th>Click to verify</th>
<%
Connection con=Dbconn.connect();
try{
String sql="SELECT * FROM newaccount ";
PreparedStatement ps=con.prepareStatement(sql);
ResultSet rs=ps.executeQuery();

while(rs.next()){
	
	
	
	%>
	<tr>
	<td><%= rs.getString("username") %></td><td><%= rs.getString("address") %></td><td><%= rs.getString("dob") %></td><td><%= rs.getString("phone") %></td><td><%= rs.getString("amount") %></td><td><%= rs.getString("acc_type") %></td><td><%= rs.getString("acc_num") %></td><td><a class="btn btn-success btn-lg" role="button" href="http://localhost:8080/bank/Cust_ver?query=<%= rs.getString("cust_id") %>">VERIFY</a></td>
	</tr>
	<%
}
}catch(Exception e){
	out.println(e.getMessage());
}finally{
	try{
	con.close();
	}catch(Exception e){
		out.println(e.getMessage());
	}
}
%>
</table>
</form>
</body>
</html>