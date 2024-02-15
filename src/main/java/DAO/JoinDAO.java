package DAO;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class JoinDAO {

	public int idCheck(HttpServletRequest request) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/orcl");
			con = ds.getConnection();

			String sql = "select count(*) from member where mid = ?";
			ps = con.prepareStatement(sql);

			ps.setString(1, request.getParameter("userId"));
			
			rs = ps.executeQuery();
			rs.next();
			
			return rs.getInt("count(*)");
			
		} catch (Exception e) {
			System.out.print(e);
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
				if (rs != null)
					rs.close();
			} catch (Exception e2) {
				System.out.print(e2);
			}
		}
		return 0;

	}

	public void join(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");
		System.out.println("joindao");
		Connection con = null;
		PreparedStatement ps = null;

		try {

			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/orcl");
			con = ds.getConnection();

			String sql = "insert into member VALUES(?, ?, ?)";
			ps = con.prepareStatement(sql);

			ps.setString(1, request.getParameter("id"));
			ps.setString(2, request.getParameter("pw"));
			ps.setString(3, request.getParameter("mbti"));

			ps.executeQuery();

		} catch (Exception e) {
			System.out.print(e);
		} finally {
			try {
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (Exception e2) {
				System.out.print(e2);
			}
		}
	}
}
