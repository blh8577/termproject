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
import DTO.NoteDTO;

public class NoteDAO {
	
	public void noteList(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		
		String sql = "SELECT content,\r\n"
				+ "       wrdate,\r\n"
				+ "       member_mid,\r\n"
				+ "       recipients,\r\n"
				+ "       (SELECT mbti_idx\r\n"
				+ "        FROM   member\r\n"
				+ "        WHERE  mid = n.recipients) reMbti\r\n"
				+ "FROM   note n\r\n"
				+ "WHERE  member_mid = ?\r\n"
				+ "       AND recipients = ?\r\n"
				+ "        OR member_mid = ?\r\n"
				+ "           AND recipients = ?\r\n"
				+ "ORDER  BY wrdate,\r\n"
				+ "          noteno";
		
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
			ps.setString(2, request.getParameter("recipients"));
			ps.setString(3, request.getParameter("recipients"));
			ps.setString(4, mem.getMid());
			
			ArrayList<NoteDTO> list = new ArrayList<NoteDTO>();
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				NoteDTO note = new NoteDTO();
				
				note.setContent(rs.getString(1));
				note.setWrdate(rs.getString(2));
				note.setMember_mid(rs.getString(3));
				note.setRecipients(rs.getString(4));
				note.setReMbti(rs.getString(5).toUpperCase());
				
				list.add(note);
			}
			
			request.setAttribute("notelist", list);
			
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
	
	public void noteInput(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(true);
		
		String sql = "INSERT INTO note VALUES (note_sq.nextval, ?, sysdate, ?, ?)";
		
		Connection con = null;
		PreparedStatement ps = null;

		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/orcl");
			con = ds.getConnection();

			ps = con.prepareStatement(sql);
			
			MemberDTO mem = (MemberDTO) session.getAttribute("member");
			
			ps.setString(1, request.getParameter("text"));
			ps.setString(2, mem.getMid());
			ps.setString(3, request.getParameter("recipients"));
			
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
