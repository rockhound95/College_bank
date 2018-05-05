package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CheckBalance
 */
@WebServlet("/CheckBalance")
public class CheckBalance extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection connection=Dbconn.connect();
		try{
		
		String acc_num=request.getParameter("accountno");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String sql="SELECT * from newaccount where username=? AND password=? AND acc_num=?";
		PreparedStatement ps=connection.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, password);
		ps.setString(3, acc_num);
		ResultSet rs=ps.executeQuery();
				while(rs.next()){
HttpSession session=request.getSession();
session.setAttribute("acnum", acc_num);
RequestDispatcher rd=request.getRequestDispatcher("balanceshow.jsp");
rd.forward(request, response);
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
