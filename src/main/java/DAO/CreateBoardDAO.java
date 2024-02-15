package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import DTO.MemberDTO;

public class CreateBoardDAO {
	public void createPicture(HttpServletRequest request, 
			HttpServletResponse response, ArrayList<String> list) {
		Connection con = null;
		PreparedStatement ps = null;
		
		try {

			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/orcl");
			con = ds.getConnection();

			String sql = "insert into PICTURE VALUES(Picture_sq.NEXTVAL, ?, board_sq.currval)";
			ps = con.prepareStatement(sql);
			
			for (String path : list) {
				ps.setString(1, path);
				ps.executeUpdate();			
			}

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
	
	public void createBoard(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html; charset=UTF-8");

		Connection con = null;
		PreparedStatement ps = null;
		HttpSession session = request.getSession(true);
		MemberDTO mem = (MemberDTO) session.getAttribute("member");
		
		try {

			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/orcl");
			con = ds.getConnection();

			String sql = "insert into board VALUES(board_sq.NEXTVAL, "
					+ "sysdate, "
					+ "?, "
					+ "0, "
					+ "?, "
					+ "?, "
					+ "0, "
					+ "0, "
					+ "?, "
					+ "?, "
					+ "?"
					+ ")";
			ps = con.prepareStatement(sql);
			
			ps.setString(1, request.getParameter("title"));
			if(request.getParameter("anonymous") == null) {
				ps.setString(2, "n");
			}else {				
				ps.setString(2, request.getParameter("anonymous"));
			}
			ps.setString(3, request.getParameter("cat"));
			ps.setString(4, mem.getMid());
			ps.setString(5,  mem.getMbti_idx().toUpperCase());
			ps.setString(6, request.getParameter("context"));

			ps.executeUpdate();

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
	
	public void testBoard() {

		Connection con = null;
		PreparedStatement ps = null;
		
		try {

			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/orcl");
			con = ds.getConnection();

			String sql = "insert into board VALUES(board_sq.NEXTVAL, "
					+ "sysdate, "
					+ "?, "
					+ "0, "
					+ "?, "
					+ "?, "
					+ "0, "
					+ "0, "
					+ "?, "
					+ "?, "
					+ "?"
					+ ")";
			ps = con.prepareStatement(sql);
			
			String mbti[] = {
					"ISTJ",
					"ISTP",
					"ISFJ",
					"ISFP",
					"INTJ",
					"INTP",
					"INFJ",
					"INFP",
					"ESTJ",
					"ESTP",
					"ESFJ",
					"ESFP",
					"ENTJ",
					"ENTP",
					"ENFJ",
					"ENFP"
			};
			
			for(int i = 0; i < 100000; i++) {
				ps.setString(1, "title" + i);
				ps.setString(2, "y");
				ps.setInt(3, i%5);
				ps.setString(4, "blh"+i);
				ps.setString(5,  mbti[i%16]);
				ps.setString(6, "testtestestestestestestestes" + i);
				ps.executeUpdate();
			}
			


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
