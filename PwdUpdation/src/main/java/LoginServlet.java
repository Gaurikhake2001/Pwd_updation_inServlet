import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	PrintWriter pw = response.getWriter();
	response.setContentType("text/html");
	
	
	try
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet","root", "root");

		String s1 = request.getParameter("t1");
		String s2 = request.getParameter("t2");
		
		PreparedStatement ps = con.prepareStatement("select * from Login where User_nm =? and Pwd = ?");
		
		ps.setString(1, s1);
		ps.setString(2, s2);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next())
		{
			pw.println("<body style='min-height: 100vh;background-color:white;'>"
					+"<div style='margin:auto;width: 300px;max-width: 90%;'>"
					+ "<form method='post' action='PasswordUpdation'style='width:100%;height:50%;padding:20px;background:silver;border-radius: 4px;box-shadow: 0 8px 16px;'>"
					+"<h3 style='text-align: center; margin-bottom: 24px;'>Update Password</h3>"
					+ "<table>"
					+ "<tr>"
					+ "<td>Username: </td>"
					+ "<td>"
					+ "<input type='text' name='t1' value='"+s1+"' required>"
					+ "</td>"
					+ "</tr>"
					+ "<tr>"
					+ "<td>Old Password: </td>"
					+ "<td>"
					+ "<input type='password' name='t2' value='"+s2+"' required>"
					+ "</td>"
					+ "</tr>"
					+ "<tr>"
					+ "<td>New Password: </td>"
					+ "<td>"
					+ "<input type='password' name='t3' required>"
					+ "</td>"
					+ "</tr>"
					+ "<tr>"
					+ "<td>"
					+ "<input type='submit' value='Update' style=' margin-left: 70%; width: 120px; height: 34px;background: blue;border-radius:4px;cursor: pointer;margin-top: 20px;' >"
					+ "</td>"
					+ "</tr>"
					+"</table>"
					+ "</form></div></body>");
			
			
			
			
			
		}
		else
		{
			
			pw.println("<center><h2> Invalid User...</h2></center>");
			
			RequestDispatcher rd = request.getRequestDispatcher("Login.html");
			rd.include(request, response);
		}
		rs.close();
		con.close();
		pw.close();
	}
	catch(Exception e)
	{
		System.out.println(e);
	}
	
	}

}
