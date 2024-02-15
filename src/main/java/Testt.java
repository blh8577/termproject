import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/testt")
public class Testt extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.print("start");
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String sql = "select * from mbti";
		
		try {
			
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/orcl");
			con = ds.getConnection();
			
			if(con == null)
				out.print("연결 실패<br>");
			else
				out.print("연결 성공<br>");
			
			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			
			while(rs.next()) {
				out.println(rs.getString(1) + "<br>");
				out.println(rs.getString(2) + "<br>");
				out.println(rs.getString(3) + "<br>");
				out.println(rs.getString(4) + "<br>");
				out.println(rs.getString(5) + "<br>");	
			}
			
			out.println("end");
			
		}catch (Exception e){
			out.print(e);
			System.out.println(e);
		
		}finally {
			
			try {
				if(con != null)
					con.close();
				if(ps != null)
					ps.close();
				if(rs != null)
					rs.close();
				
			}catch (Exception e) {
				System.out.println(e);
				out.print(e);
			}
			
		}
		
	}
}
