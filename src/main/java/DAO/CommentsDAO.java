package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import DTO.CommentsDTO;
import DTO.MemberDTO;

public class CommentsDAO {
	
	public void comRecommendationUpdate(HttpServletRequest request, HttpServletResponse response) {
		String sql = "UPDATE comments\r\n"
				+ "SET    recommendation = (SELECT Count(*)\r\n"
				+ "                         FROM   comrecommendation\r\n"
				+ "                         WHERE  comments_commentno = ?)\r\n"
				+ "WHERE  COMMENTNO = ?";
		
		Connection con = null;
		PreparedStatement ps = null;

		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/orcl");
			con = ds.getConnection();

			ps = con.prepareStatement(sql);
			
			ps.setInt(1, Integer.parseInt(request.getParameter("com")));
			ps.setInt(2, Integer.parseInt(request.getParameter("com")));
			
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
	
	public int comRecommendationCheck(HttpServletRequest request, HttpServletResponse response) {
		String sql = "SELECT count(*) FROM comrecommendation WHERE member_mid = ? AND comments_commentno = ?";
		HttpSession session = request.getSession(true);
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/orcl");
			con = ds.getConnection();

			ps = con.prepareStatement(sql);
			
			MemberDTO mem = (MemberDTO) session.getAttribute("member");
			
			
			ps.setString(1, mem.getMid());
			ps.setInt(2, Integer.parseInt(request.getParameter("com")));
			
			rs = ps.executeQuery();
			rs.next();
			
			return rs.getInt(1);

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
		return 0;
	}
	
	public void comRecommendation(HttpServletRequest request, HttpServletResponse response) {
		String sql = "INSERT INTO comrecommendation values(?, ?)";
		HttpSession session = request.getSession(true);
		
		Connection con = null;
		PreparedStatement ps = null;

		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/orcl");
			con = ds.getConnection();

			ps = con.prepareStatement(sql);
			
			MemberDTO mem = (MemberDTO) session.getAttribute("member");
			
			
			ps.setString(1, mem.getMid());
			ps.setInt(2, Integer.parseInt(request.getParameter("com")));
			
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
	
	public void commentInput(HttpServletRequest request, HttpServletResponse response) {
		
		String sql = "";
		HttpSession session = request.getSession(true);
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/orcl");
			con = ds.getConnection();
			
			MemberDTO mem = (MemberDTO) session.getAttribute("member");
			
			if(request.getParameter("mgr").equals("0")) {
				
				sql = "INSERT INTO COMMENTS values(com_sq.NEXTVAL, ?, null, sysdate, 0, 0, 'y', ?, ?)";

			}else {
				
				sql = "INSERT INTO COMMENTS values(com_sq.NEXTVAL, ?, ?, sysdate, 0, 0, 'y', ?, ?)";
				
			}
			
			ps = con.prepareStatement(sql);
			
			
			if(request.getParameter("mgr").equals("0")) {
				
				ps.setString(1, request.getParameter("content"));
				ps.setInt(2, Integer.parseInt(request.getParameter("view")));
				ps.setString(3, mem.getMid());
			}else {
				
				ps.setString(1, request.getParameter("content"));
				ps.setInt(2, Integer.parseInt(request.getParameter("mgr")));
				ps.setInt(3, Integer.parseInt(request.getParameter("view")));
				ps.setString(4, mem.getMid());
				
			}
			
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
	
	public void comment(HttpServletRequest request, HttpServletResponse response) {
		ArrayList<CommentsDTO> list = new ArrayList<CommentsDTO>();
		
		String sql = "SELECT commentno,\r\n"
				+ "       LEVEL,\r\n"
				+ "       content,\r\n"
				+ "       wrdate,\r\n"
				+ "       recommendation,\r\n"
				+ "       opposition,\r\n"
				+ "       member_mid,\r\n"
				+ "       upper(mbti_idx) mbti\r\n"
				+ "FROM   comments com\r\n"
				+ "       inner join member m\r\n"
				+ "               ON com.member_mid = m.mid\r\n"
				+ "WHERE  board_boardno = ?\r\n"
				+ "START WITH mgrno IS NULL\r\n"
				+ "CONNECT BY PRIOR commentno = mgrno\r\n"
				+ "ORDER  SIBLINGS BY wrdate";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/orcl");
			con = ds.getConnection();

			ps = con.prepareStatement(sql);
			
			ps.setInt(1, Integer.parseInt(request.getParameter("view")));
			
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				CommentsDTO com = new CommentsDTO();
				com.setCommentno(rs.getInt(1));
				com.setLevel(rs.getInt(2));
				com.setContent(rs.getString(3));
				com.setWrdate(rs.getString(4));
				com.setRecommendation(rs.getInt(5));
				com.setOpposition(rs.getInt(6));
				com.setMember_mid(rs.getString(7));
				com.setMbti_idx(rs.getString(8));
				list.add(com);
			}
			
			request.setAttribute("comlist", list);

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
