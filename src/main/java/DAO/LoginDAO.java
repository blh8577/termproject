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
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import DTO.MemberDTO;

public class LoginDAO {

	public void login(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession(true);
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String mid = request.getParameter("id");
		String mpw = request.getParameter("pw");

		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/orcl");
			con = ds.getConnection();
			String sql = "select mid, mpw, upper(mbti_idx) from member where mid = ? and mpw = ?";
			ps = con.prepareStatement(sql);

			ps.setString(1, mid);
			ps.setString(2, mpw);

			rs = ps.executeQuery();

			String color = "";
			if (rs.next()) {
				MemberDTO mem = new MemberDTO();
				mem.setMid(rs.getString(1));
				mem.setMpw(rs.getString(2));
				mem.setMbti_idx(rs.getString(3));
				
				String mbti = mem.getMbti_idx();
				
				if(mbti.equals("INTJ")||
					mbti.equals("INTP")||
					mbti.equals("ENTJ")||
					mbti.equals("ENTP"))
					color = "pink";
				else if(mbti.equals("INFJ")||
						mbti.equals("INFP")||
						mbti.equals("ENFJ")||
						mbti.equals("ENFP"))
					color = "green";
				else if(mbti.equals("ISTJ")||
						mbti.equals("ISFJ")||
						mbti.equals("ESTJ")||
						mbti.equals("ESFJ"))
					color = "blue";
				else
					color = "yellow";
				
				session.setAttribute("color", color);
				session.setAttribute("member", mem);
				
			}
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

	}

}
