package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import DTO.ChatroomDTO;
import DTO.MemberDTO;

public class ChatDAO {
	
	public void startChat(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		
		
		String sql = "SELECT sysdate FROM dual";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/orcl");
			con = ds.getConnection();

			ps = con.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			rs.next();
			
			session.setAttribute("chatstart", rs.getString(1));
			
			System.out.println("chatstart: " + session.getAttribute("chatstart"));
			
			
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
	
	public void chatList(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		
		String sql = "SELECT chatno,\r\n"
				+ "       wrdate,\r\n"
				+ "       chat,\r\n"
				+ "       mbti_idx,\r\n"
				+ "       member_mid\r\n"
				+ "FROM   chatroom\r\n"
				+ "WHERE  mbti_idx = ?\r\n"
				+ "       AND wrdate >= to_date(?, 'YYYY-MM-DD HH24:MI:SS')\r\n"
				+ "ORDER  BY wrdate, chatno";
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/orcl");
			con = ds.getConnection();

			ps = con.prepareStatement(sql);
			
			MemberDTO mem = (MemberDTO) session.getAttribute("member");
			
			ps.setString(1, mem.getMbti_idx().toUpperCase());
			ps.setString(2, (String)session.getAttribute("chatstart"));
			
			ArrayList<ChatroomDTO> list = new ArrayList<ChatroomDTO>();
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ChatroomDTO chat = new ChatroomDTO();
				chat.setChatno(rs.getInt(1));
				chat.setWrdate(rs.getString(2));
				chat.setChat(rs.getString(3));
				chat.setMbti_idx(rs.getString(4).toUpperCase());
				chat.setMember_mid(rs.getString(5));
				list.add(chat);
			}
			
			request.setAttribute("chatlist", list);
			
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
	
	public void chatInput(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		
		String sql = "INSERT INTO chatroom values(chatroom_sq.NEXTVAL, sysdate, ?, ?, ?)";
		
		Connection con = null;
		PreparedStatement ps = null;

		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/orcl");
			con = ds.getConnection();

			ps = con.prepareStatement(sql);
			
			MemberDTO mem = (MemberDTO) session.getAttribute("member");
			
			ps.setString(1, request.getParameter("text"));
			ps.setString(2, mem.getMbti_idx().toUpperCase());
			ps.setString(3, mem.getMid());
			
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
}
