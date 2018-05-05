package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.*;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import model.Dbconn;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.codec.digest.DigestUtils;

/**
 * Servlet implementation class CreateAccount
 */
@WebServlet("/CreateAccount")
public class CreateAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection con=Dbconn.connect();
		PrintWriter printWriter=response.getWriter();
		try{
			String storepass;
			//SecureRandom random=SecureRandom.getInstance("SHA1PRNG","SUN");
		//	byte seedbyte[]=random.generateSeed(20);
			// String seed = org.apache.commons.codec.binary.Base64.encodeBase64String(seedbyte);
			String acc_num=request.getParameter("account_no");
			String name=request.getParameter("username");
			String password=request.getParameter("password");
			//String cpass=request.getParameter("repassword");
			String dob=request.getParameter("date");
			String acc_type=request.getParameter("selection");
			Float amt=Float.parseFloat(request.getParameter("amount"));
			String address=request.getParameter("address");
			int phone=Integer.parseInt(request.getParameter("phone"));
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-dd-MM");
			java.util.Date date= dateFormat.parse(dob);
			java.sql.Date dateobj=new java.sql.Date(date.getTime()); 
			//storepass=password+seed;
		//	storepass=DigestUtils.sha1Hex(storepass);
			String sql1="SELECT * FROM newaccount";
			PreparedStatement ps=con.prepareStatement(sql1);
			ResultSet rs=ps.executeQuery();
			if(rs.next() && rs.getString("acc_num").equals(acc_num)){
			
					RequestDispatcher rd=request.getRequestDispatcher("createerror.jsp");
					rd.forward(request, response);
				}
				else{
					String sql="INSERT INTO newaccount VALUES(?,?,?,?,?,?,?,?,default)";
					PreparedStatement preparedStatement=con.prepareStatement(sql);
					preparedStatement.setInt(1,Integer.parseInt(acc_num));
					preparedStatement.setString(2, name);
					preparedStatement.setString(3, password);
				//	preparedStatement.setString(4, cpass);
					preparedStatement.setFloat(4, amt);
					preparedStatement.setString(5, address);
					preparedStatement.setInt(6, phone);
					preparedStatement.setDate(7, dateobj);
					preparedStatement.setString(8, acc_type);
					preparedStatement.execute();
					RequestDispatcher rd=request.getRequestDispatcher("login_balance.jsp");
					rd.forward(request, response);
					//printWriter.println("success");
					
				}
					
		
			
			
			
			
		}catch(Exception exception){
			exception.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
