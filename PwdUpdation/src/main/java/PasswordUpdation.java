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

@WebServlet("/PasswordUpdation")
public class PasswordUpdation extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		PrintWriter pw = response.getWriter();
		response.setContentType("text/html");
		
		try
		{

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/servlet","root", "root");
		
		PreparedStatement pst = con.prepareStatement("Update Login set Pwd=? where User_nm =?");
		pst.setString(1, request.getParameter("t3"));
		pst.setString(2, request.getParameter("t1"));
		pst.executeUpdate();
		
		pw.println("<h2> Password Updated Successfully</h2>");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		pw.close();
	}

}
