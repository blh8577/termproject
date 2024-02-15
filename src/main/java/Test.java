import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test")
public class Test extends HttpServlet{
	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();

		//kwonht.synology.me:38443/bordI/test
		String url = "jdbc:oracle:thin:@kwonht.synology.me:31521:xe";
		String id = "blh8577"; //계정 ID
		String pw = "!as1642211"; //계정 PassWord

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		// 드라이버 검색 (db와 연동 준비)
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 검색 성공");
		} catch (ClassNotFoundException e) {
			System.err.println("드라이버 검색 실패");
			System.exit(0);
		}

		try {
			con = DriverManager.getConnection(url, id, pw);
			stmt = con.createStatement();
			String sql = "select * from mbti";
			rs = stmt.executeQuery(sql);

			while(rs.next()) {
				out.print("idx : " + rs.getString(1) + "<br>");
				out.print("rg  : " + rs.getString(2) + "<br>");
				out.print("rb  : " + rs.getString(3) + "<br>");
				out.print("lg  : " + rs.getString(4) + "<br>");
				out.print("lb  : " + rs.getString(5) + "<br>");
			}
			out.println("종료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					stmt.close();
				if (con != null)
					con.close();
				if(rs != null)
					rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		System.out.println("종료");
	}
}
