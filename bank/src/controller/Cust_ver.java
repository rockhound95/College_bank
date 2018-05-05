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

/**
 * Servlet implementation class Cust_ver
 */
@WebServlet("/Cust_ver")
public class Cust_ver extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection connection=Dbconn.connect();
		try{
		RequestDispatcher rd=request.getRequestDispatcher("Verify_customer.jsp");
		String id=request.getParameter("query");
		String sql="SELECT * FROM newaccount where cust_id=?";
		String sql1="INSERT INTO verify(acc_num,username,password,amount,address,phone,dob,acc_type,cust_id) VALUES(?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps=connection.prepareStatement(sql);
		ps.setString(1, id);
		PreparedStatement ps2=connection.prepareStatement(sql1);
		ResultSet rs1=ps.executeQuery();
		if(rs1.next()){
			ps2.setString(1, rs1.getString("acc_num"));
			ps2.setString(2,rs1.getString("username"));
			ps2.setString(3,rs1.getString("password"));
			ps2.setFloat(4,rs1.getFloat("amount"));
			ps2.setString(5,rs1.getString("address"));
			ps2.setInt(6,rs1.getInt("phone"));
			ps2.setDate(7,rs1.getDate("dob"));
			ps2.setString(8,rs1.getString("acc_type"));
			ps2.setString(9,id);
			ps2.execute();
			ps2.close();
			PreparedStatement temp=connection.prepareStatement("DELETE FROM newaccount where cust_id=?");
			temp.setString(1, id);
			temp.execute();
			temp.close();
		}
		rd.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}

}
