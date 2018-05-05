package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.*;
/**
 * Servlet implementation class Login_admin
 */
@WebServlet("/Login_admin")
public class Login_admin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		Connection connection=Dbconn.connect();
		try{
			String sql="SELECT * FROM admin where username=? AND password=?";
			String username=request.getParameter("username");
			String password=request.getParameter("password");
			PreparedStatement preparedStatement=connection.prepareStatement(sql);
			ResultSet rs=preparedStatement.executeQuery();
					while(rs.next()){
						if(rs.getString("username").equals(username) && rs.getString("password").equals(password)){
							RequestDispatcher rd=request.getRequestDispatcher("Admin_home.jsp");
							rd.forward(request, response);
						}
						else{
							PrintWriter pw=response.getWriter();
							pw.println("error");
						}
					}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
