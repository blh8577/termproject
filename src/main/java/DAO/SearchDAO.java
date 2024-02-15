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

import DTO.BoardDTO;
import DTO.MemberDTO;

public class SearchDAO {
public int paging(HttpServletRequest request, HttpServletResponse response){
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		HttpSession session = request.getSession(true);
		int totalChat = 0;
		String sql = "";
		if((request.getParameter("cat") == null)||
				request.getParameter("cat").equals("")){
			//0번게시판 쿼리
			sql = "SELECT count(boardno)\r\n"
					+ "FROM   (SELECT boardno,\r\n"
					+ "               wrdate,\r\n"
					+ "               title,\r\n"
					+ "               count,\r\n"
					+ "               viewcheck,\r\n"
					+ "               category,\r\n"
					+ "               recommendation,\r\n"
					+ "               opposition,\r\n"
					+ "               member_mid,\r\n"
					+ "               mbti_idx,\r\n"
					+ "               content,\r\n"
					+ "               relationgood,\r\n"
					+ "               relationbad,\r\n"
					+ "               lovegood,\r\n"
					+ "               lovebad\r\n"
					+ "        FROM   board b\r\n"
					+ "               inner join mbti m\r\n"
					+ "                       ON b.mbti_idx = m.idx) bm\r\n"
					+ "WHERE  ( ( bm.category = 0\r\n"
					+ "           AND bm.mbti_idx = ? )\r\n"
					+ "          OR ( bm.category = 1\r\n"
					+ "               AND bm.mbti_idx = (SELECT relationgood\r\n"
					+ "                                  FROM   mbti\r\n"
					+ "                                  WHERE  idx = ?) )\r\n"
					+ "          OR ( bm.category = 1\r\n"
					+ "               AND bm.mbti_idx = ? )\r\n"
					+ "          OR ( bm.category = 2\r\n"
					+ "               AND bm.mbti_idx = (SELECT relationbad\r\n"
					+ "                                  FROM   mbti\r\n"
					+ "                                  WHERE  idx = ?) )\r\n"
					+ "          OR ( bm.category = 2\r\n"
					+ "               AND bm.mbti_idx = ? )\r\n"
					+ "          OR ( bm.category = 3\r\n"
					+ "               AND bm.mbti_idx = (SELECT lovegood\r\n"
					+ "                                  FROM   mbti\r\n"
					+ "                                  WHERE  idx = ?) )\r\n"
					+ "          OR ( bm.category = 3\r\n"
					+ "               AND bm.mbti_idx = ? )\r\n"
					+ "          OR ( bm.category = 4\r\n"
					+ "               AND bm.mbti_idx = (SELECT lovebad\r\n"
					+ "                                  FROM   mbti\r\n"
					+ "                                  WHERE  idx = ?) )\r\n"
					+ "          OR ( bm.category = 4\r\n"
					+ "               AND bm.mbti_idx = ? ) )\r\n"
					+ "       AND bm.title LIKE ?";
			
		}else if(request.getParameter("cat").equals("0")) {
			//0번게시판 쿼리
			sql = "SELECT Count(boardno)\r\n"
					+ "FROM   (SELECT boardno,\r\n"
					+ "               wrdate,\r\n"
					+ "               title,\r\n"
					+ "               count,\r\n"
					+ "               viewcheck,\r\n"
					+ "               category,\r\n"
					+ "               recommendation,\r\n"
					+ "               opposition,\r\n"
					+ "               member_mid,\r\n"
					+ "               mbti_idx,\r\n"
					+ "               content,\r\n"
					+ "               relationgood,\r\n"
					+ "               relationbad,\r\n"
					+ "               lovegood,\r\n"
					+ "               lovebad\r\n"
					+ "        FROM   board b\r\n"
					+ "               inner join mbti m\r\n"
					+ "                       ON b.mbti_idx = m.idx) bm\r\n"
					+ "WHERE  ( ( bm.category = 0\r\n"
					+ "           AND bm.mbti_idx = ? )\r\n"
					+ "           )\r\n"
					+ "       AND bm.title LIKE ?";
		}
		else if(request.getParameter("cat").equals("1")) {
			//1번게시판 쿼리
			sql = "SELECT count(boardno)\r\n"
					+ "FROM   (SELECT boardno,\r\n"
					+ "               wrdate,\r\n"
					+ "               title,\r\n"
					+ "               count,\r\n"
					+ "               viewcheck,\r\n"
					+ "               category,\r\n"
					+ "               recommendation,\r\n"
					+ "               opposition,\r\n"
					+ "               member_mid,\r\n"
					+ "               mbti_idx,\r\n"
					+ "               content,\r\n"
					+ "               relationgood,\r\n"
					+ "               relationbad,\r\n"
					+ "               lovegood,\r\n"
					+ "               lovebad\r\n"
					+ "        FROM   board b\r\n"
					+ "               inner join mbti m\r\n"
					+ "                       ON b.mbti_idx = m.idx) bm\r\n"
					+ "WHERE  ( ( bm.category = 1\r\n"
					+ "               AND bm.mbti_idx = (SELECT relationgood\r\n"
					+ "                                  FROM   mbti\r\n"
					+ "                                  WHERE  idx = ?) )\r\n"
					+ "          OR ( bm.category = 1\r\n"
					+ "               AND bm.mbti_idx = ? )\r\n"
					+ "           )\r\n"
					+ "       AND bm.title LIKE ?";
			
		}else if (request.getParameter("cat").equals("2")) {
			//2번게시판 쿼리
			sql = "SELECT count(boardno)\r\n"
					+ "FROM   (SELECT boardno,\r\n"
					+ "               wrdate,\r\n"
					+ "               title,\r\n"
					+ "               count,\r\n"
					+ "               viewcheck,\r\n"
					+ "               category,\r\n"
					+ "               recommendation,\r\n"
					+ "               opposition,\r\n"
					+ "               member_mid,\r\n"
					+ "               mbti_idx,\r\n"
					+ "               content,\r\n"
					+ "               relationgood,\r\n"
					+ "               relationbad,\r\n"
					+ "               lovegood,\r\n"
					+ "               lovebad\r\n"
					+ "        FROM   board b\r\n"
					+ "               inner join mbti m\r\n"
					+ "                       ON b.mbti_idx = m.idx) bm\r\n"
					+ "WHERE  ( ( bm.category = 2\r\n"
					+ "               AND bm.mbti_idx = (SELECT relationbad\r\n"
					+ "                                  FROM   mbti\r\n"
					+ "                                  WHERE  idx = ?) )\r\n"
					+ "          OR ( bm.category = 2\r\n"
					+ "               AND bm.mbti_idx = ? )\r\n"
					+ "           )\r\n"
					+ "       AND bm.title LIKE ?";
			
		}else if (request.getParameter("cat").equals("3")) {
			//3번게시판 쿼리
			sql = "SELECT count(boardno)\r\n"
					+ "FROM   (SELECT boardno,\r\n"
					+ "               wrdate,\r\n"
					+ "               title,\r\n"
					+ "               count,\r\n"
					+ "               viewcheck,\r\n"
					+ "               category,\r\n"
					+ "               recommendation,\r\n"
					+ "               opposition,\r\n"
					+ "               member_mid,\r\n"
					+ "               mbti_idx,\r\n"
					+ "               content,\r\n"
					+ "               relationgood,\r\n"
					+ "               relationbad,\r\n"
					+ "               lovegood,\r\n"
					+ "               lovebad\r\n"
					+ "        FROM   board b\r\n"
					+ "               inner join mbti m\r\n"
					+ "                       ON b.mbti_idx = m.idx) bm\r\n"
					+ "WHERE  ( ( bm.category = 3\r\n"
					+ "               AND bm.mbti_idx = (SELECT lovegood\r\n"
					+ "                                  FROM   mbti\r\n"
					+ "                                  WHERE  idx = ?) )\r\n"
					+ "          OR ( bm.category = 3\r\n"
					+ "               AND bm.mbti_idx = ? )\r\n"
					+ "           )\r\n"
					+ "       AND bm.title LIKE ?";
		}else {
			//4번게시판 쿼리
			sql = "SELECT count(boardno)\r\n"
					+ "FROM   (SELECT boardno,\r\n"
					+ "               wrdate,\r\n"
					+ "               title,\r\n"
					+ "               count,\r\n"
					+ "               viewcheck,\r\n"
					+ "               category,\r\n"
					+ "               recommendation,\r\n"
					+ "               opposition,\r\n"
					+ "               member_mid,\r\n"
					+ "               mbti_idx,\r\n"
					+ "               content,\r\n"
					+ "               relationgood,\r\n"
					+ "               relationbad,\r\n"
					+ "               lovegood,\r\n"
					+ "               lovebad\r\n"
					+ "        FROM   board b\r\n"
					+ "               inner join mbti m\r\n"
					+ "                       ON b.mbti_idx = m.idx) bm\r\n"
					+ "WHERE  ( ( bm.category = 4\r\n"
					+ "               AND bm.mbti_idx = (SELECT lovebad\r\n"
					+ "                                  FROM   mbti\r\n"
					+ "                                  WHERE  idx = ?) )\r\n"
					+ "          OR ( bm.category = 4\r\n"
					+ "               AND bm.mbti_idx = ? )\r\n"
					+ "           )\r\n"
					+ "       AND bm.title LIKE ?";
		}
		
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/orcl");
			con = ds.getConnection();

			ps = con.prepareStatement(sql);

			MemberDTO mem = (MemberDTO) session.getAttribute("member");
			

			
			if((request.getParameter("cat") == null)||
					request.getParameter("cat").equals("")){
				
				ps.setString(1, mem.getMbti_idx().toUpperCase());
				ps.setString(2, mem.getMbti_idx().toUpperCase());
				ps.setString(3, mem.getMbti_idx().toUpperCase());
				ps.setString(4, mem.getMbti_idx().toUpperCase());
				ps.setString(5, mem.getMbti_idx().toUpperCase());
				ps.setString(6, mem.getMbti_idx().toUpperCase());
				ps.setString(7, mem.getMbti_idx().toUpperCase());
				ps.setString(8, mem.getMbti_idx().toUpperCase());
				ps.setString(9, mem.getMbti_idx().toUpperCase());
				ps.setString(10, "%" + request.getParameter("search") + "%");

			}else if(request.getParameter("cat").equals("0")) {
				
				ps.setString(1, mem.getMbti_idx().toUpperCase());
				ps.setString(2, "%" + request.getParameter("search") + "%");

			
			}else {
				ps.setString(1, mem.getMbti_idx().toUpperCase());
				ps.setString(2, mem.getMbti_idx().toUpperCase());
				ps.setString(3, "%" + request.getParameter("search") + "%");

			}
			
			
			
			
			rs = ps.executeQuery();
			rs.next();
			totalChat = rs.getInt(1);
			
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
		
		return totalChat;
	}
	
	public ArrayList<BoardDTO> searchList(HttpServletRequest request, HttpServletResponse response) {

		Integer page = null;
		
		String sql = "";
		
		if((request.getParameter("cat") == null)||
				request.getParameter("cat").equals("")){
			//0번게시판 쿼리
			sql = "SELECT boardno,\r\n"
					+ "       wrdate,\r\n"
					+ "       title,\r\n"
					+ "       count,\r\n"
					+ "       viewcheck,\r\n"
					+ "       category,\r\n"
					+ "       recommendation,\r\n"
					+ "       opposition,\r\n"
					+ "       member_mid,\r\n"
					+ "       mbti_idx,\r\n"
					+ "       content\r\n"
					+ "FROM   (SELECT Row_number() over (ORDER BY wrdate DESC, boardno DESC) rnum, boardno,\r\n"
					+ "               wrdate,\r\n"
					+ "               title,\r\n"
					+ "               count,\r\n"
					+ "               viewcheck,\r\n"
					+ "               category,\r\n"
					+ "               recommendation,\r\n"
					+ "               opposition,\r\n"
					+ "               member_mid,\r\n"
					+ "               mbti_idx,\r\n"
					+ "               content\r\n"
					+ "        FROM   (SELECT boardno,\r\n"
					+ "                       wrdate,\r\n"
					+ "                       title,\r\n"
					+ "                       count,\r\n"
					+ "                       viewcheck,\r\n"
					+ "                       category,\r\n"
					+ "                       recommendation,\r\n"
					+ "                       opposition,\r\n"
					+ "                       member_mid,\r\n"
					+ "                       mbti_idx,\r\n"
					+ "                       content,\r\n"
					+ "                       relationgood,\r\n"
					+ "                       relationbad,\r\n"
					+ "                       lovegood,\r\n"
					+ "                       lovebad\r\n"
					+ "                FROM   board b\r\n"
					+ "                       inner join mbti m\r\n"
					+ "                               ON b.mbti_idx = m.idx) bm\r\n"
					+ "        WHERE  ( ( bm.category = 0\r\n"
					+ "                   AND bm.mbti_idx = ? )\r\n"
					+ "                  OR ( bm.category = 1\r\n"
					+ "                       AND bm.mbti_idx = (SELECT relationgood\r\n"
					+ "                                          FROM   mbti\r\n"
					+ "                                          WHERE  idx = ?) )\r\n"
					+ "                  OR ( bm.category = 1\r\n"
					+ "                       AND bm.mbti_idx = ? )\r\n"
					+ "                  OR ( bm.category = 2\r\n"
					+ "                       AND bm.mbti_idx = (SELECT relationbad\r\n"
					+ "                                          FROM   mbti\r\n"
					+ "                                          WHERE  idx = ?) )\r\n"
					+ "                  OR ( bm.category = 2\r\n"
					+ "                       AND bm.mbti_idx = ? )\r\n"
					+ "                  OR ( bm.category = 3\r\n"
					+ "                       AND bm.mbti_idx = (SELECT lovegood\r\n"
					+ "                                          FROM   mbti\r\n"
					+ "                                          WHERE  idx = ?) )\r\n"
					+ "                  OR ( bm.category = 3\r\n"
					+ "                       AND bm.mbti_idx = ? )\r\n"
					+ "                  OR ( bm.category = 4\r\n"
					+ "                       AND bm.mbti_idx = (SELECT lovebad\r\n"
					+ "                                          FROM   mbti\r\n"
					+ "                                          WHERE  idx = ?) )\r\n"
					+ "                  OR ( bm.category = 4\r\n"
					+ "                       AND bm.mbti_idx = ? ) )\r\n"
					+ "               AND bm.title LIKE ?\r\n"
					+ "        ORDER  BY wrdate DESC,\r\n"
					+ "                  boardno DESC)\r\n"
					+ "WHERE  rnum BETWEEN ? AND ?";
			
		}else if(request.getParameter("cat").equals("0")) {
			//0번게시판 쿼리
			sql = "SELECT boardno,\r\n"
					+ "       wrdate,\r\n"
					+ "       title,\r\n"
					+ "       count,\r\n"
					+ "       viewcheck,\r\n"
					+ "       category,\r\n"
					+ "       recommendation,\r\n"
					+ "       opposition,\r\n"
					+ "       member_mid,\r\n"
					+ "       mbti_idx,\r\n"
					+ "       content\r\n"
					+ "FROM   (SELECT Row_number() over (ORDER BY wrdate DESC, boardno DESC) rnum, boardno,\r\n"
					+ "               wrdate,\r\n"
					+ "               title,\r\n"
					+ "               count,\r\n"
					+ "               viewcheck,\r\n"
					+ "               category,\r\n"
					+ "               recommendation,\r\n"
					+ "               opposition,\r\n"
					+ "               member_mid,\r\n"
					+ "               mbti_idx,\r\n"
					+ "               content\r\n"
					+ "        FROM   (SELECT boardno,\r\n"
					+ "                       wrdate,\r\n"
					+ "                       title,\r\n"
					+ "                       count,\r\n"
					+ "                       viewcheck,\r\n"
					+ "                       category,\r\n"
					+ "                       recommendation,\r\n"
					+ "                       opposition,\r\n"
					+ "                       member_mid,\r\n"
					+ "                       mbti_idx,\r\n"
					+ "                       content,\r\n"
					+ "                       relationgood,\r\n"
					+ "                       relationbad,\r\n"
					+ "                       lovegood,\r\n"
					+ "                       lovebad\r\n"
					+ "                FROM   board b\r\n"
					+ "                       inner join mbti m\r\n"
					+ "                               ON b.mbti_idx = m.idx) bm\r\n"
					+ "        WHERE  ( bm.category = 0\r\n"
					+ "                   AND bm.mbti_idx = ? )\r\n"
					+ "               AND bm.title LIKE ?\r\n"
					+ "        ORDER  BY wrdate DESC,\r\n"
					+ "                  boardno DESC)\r\n"
					+ "WHERE  rnum BETWEEN ? AND ?";
		}
		else if(request.getParameter("cat").equals("1")) {
			//1번게시판 쿼리
			sql = "SELECT boardno,\r\n"
					+ "       wrdate,\r\n"
					+ "       title,\r\n"
					+ "       count,\r\n"
					+ "       viewcheck,\r\n"
					+ "       category,\r\n"
					+ "       recommendation,\r\n"
					+ "       opposition,\r\n"
					+ "       member_mid,\r\n"
					+ "       mbti_idx,\r\n"
					+ "       content\r\n"
					+ "FROM   (SELECT Row_number() over (ORDER BY wrdate DESC, boardno DESC) rnum, boardno,\r\n"
					+ "               wrdate,\r\n"
					+ "               title,\r\n"
					+ "               count,\r\n"
					+ "               viewcheck,\r\n"
					+ "               category,\r\n"
					+ "               recommendation,\r\n"
					+ "               opposition,\r\n"
					+ "               member_mid,\r\n"
					+ "               mbti_idx,\r\n"
					+ "               content\r\n"
					+ "        FROM   (SELECT boardno,\r\n"
					+ "                       wrdate,\r\n"
					+ "                       title,\r\n"
					+ "                       count,\r\n"
					+ "                       viewcheck,\r\n"
					+ "                       category,\r\n"
					+ "                       recommendation,\r\n"
					+ "                       opposition,\r\n"
					+ "                       member_mid,\r\n"
					+ "                       mbti_idx,\r\n"
					+ "                       content,\r\n"
					+ "                       relationgood,\r\n"
					+ "                       relationbad,\r\n"
					+ "                       lovegood,\r\n"
					+ "                       lovebad\r\n"
					+ "                FROM   board b\r\n"
					+ "                       inner join mbti m\r\n"
					+ "                               ON b.mbti_idx = m.idx) bm\r\n"
					+ "        WHERE  ( ( bm.category = 1\r\n"
					+ "                       AND bm.mbti_idx = (SELECT relationgood\r\n"
					+ "                                          FROM   mbti\r\n"
					+ "                                          WHERE  idx = ?) )\r\n"
					+ "                  OR ( bm.category = 1\r\n"
					+ "                       AND bm.mbti_idx = ? )\r\n"
					+ "                  )\r\n"
					+ "               AND bm.title LIKE ?\r\n"
					+ "        ORDER  BY wrdate DESC,\r\n"
					+ "                  boardno DESC)\r\n"
					+ "WHERE  rnum BETWEEN ? AND ?";
			
		}else if (request.getParameter("cat").equals("2")) {
			//2번게시판 쿼리
			sql = "SELECT boardno,\r\n"
					+ "       wrdate,\r\n"
					+ "       title,\r\n"
					+ "       count,\r\n"
					+ "       viewcheck,\r\n"
					+ "       category,\r\n"
					+ "       recommendation,\r\n"
					+ "       opposition,\r\n"
					+ "       member_mid,\r\n"
					+ "       mbti_idx,\r\n"
					+ "       content\r\n"
					+ "FROM   (SELECT Row_number() over (ORDER BY wrdate DESC, boardno DESC) rnum, boardno,\r\n"
					+ "               wrdate,\r\n"
					+ "               title,\r\n"
					+ "               count,\r\n"
					+ "               viewcheck,\r\n"
					+ "               category,\r\n"
					+ "               recommendation,\r\n"
					+ "               opposition,\r\n"
					+ "               member_mid,\r\n"
					+ "               mbti_idx,\r\n"
					+ "               content\r\n"
					+ "        FROM   (SELECT boardno,\r\n"
					+ "                       wrdate,\r\n"
					+ "                       title,\r\n"
					+ "                       count,\r\n"
					+ "                       viewcheck,\r\n"
					+ "                       category,\r\n"
					+ "                       recommendation,\r\n"
					+ "                       opposition,\r\n"
					+ "                       member_mid,\r\n"
					+ "                       mbti_idx,\r\n"
					+ "                       content,\r\n"
					+ "                       relationgood,\r\n"
					+ "                       relationbad,\r\n"
					+ "                       lovegood,\r\n"
					+ "                       lovebad\r\n"
					+ "                FROM   board b\r\n"
					+ "                       inner join mbti m\r\n"
					+ "                               ON b.mbti_idx = m.idx) bm\r\n"
					+ "        WHERE  ( ( bm.category = 2\r\n"
					+ "                       AND bm.mbti_idx = (SELECT relationbad\r\n"
					+ "                                          FROM   mbti\r\n"
					+ "                                          WHERE  idx = ?) )\r\n"
					+ "                  OR ( bm.category = 2\r\n"
					+ "                       AND bm.mbti_idx = ? ) )\r\n"
					+ "               AND bm.title LIKE ?\r\n"
					+ "        ORDER  BY wrdate DESC,\r\n"
					+ "                  boardno DESC)\r\n"
					+ "WHERE  rnum BETWEEN ? AND ?";
			
		}else if (request.getParameter("cat").equals("3")) {
			//3번게시판 쿼리
			sql = "SELECT boardno,\r\n"
					+ "       wrdate,\r\n"
					+ "       title,\r\n"
					+ "       count,\r\n"
					+ "       viewcheck,\r\n"
					+ "       category,\r\n"
					+ "       recommendation,\r\n"
					+ "       opposition,\r\n"
					+ "       member_mid,\r\n"
					+ "       mbti_idx,\r\n"
					+ "       content\r\n"
					+ "FROM   (SELECT Row_number() over (ORDER BY wrdate DESC, boardno DESC) rnum, boardno,\r\n"
					+ "               wrdate,\r\n"
					+ "               title,\r\n"
					+ "               count,\r\n"
					+ "               viewcheck,\r\n"
					+ "               category,\r\n"
					+ "               recommendation,\r\n"
					+ "               opposition,\r\n"
					+ "               member_mid,\r\n"
					+ "               mbti_idx,\r\n"
					+ "               content\r\n"
					+ "        FROM   (SELECT boardno,\r\n"
					+ "                       wrdate,\r\n"
					+ "                       title,\r\n"
					+ "                       count,\r\n"
					+ "                       viewcheck,\r\n"
					+ "                       category,\r\n"
					+ "                       recommendation,\r\n"
					+ "                       opposition,\r\n"
					+ "                       member_mid,\r\n"
					+ "                       mbti_idx,\r\n"
					+ "                       content,\r\n"
					+ "                       relationgood,\r\n"
					+ "                       relationbad,\r\n"
					+ "                       lovegood,\r\n"
					+ "                       lovebad\r\n"
					+ "                FROM   board b\r\n"
					+ "                       inner join mbti m\r\n"
					+ "                               ON b.mbti_idx = m.idx) bm\r\n"
					+ "        WHERE  ( ( bm.category = 3\r\n"
					+ "                       AND bm.mbti_idx = (SELECT lovegood\r\n"
					+ "                                          FROM   mbti\r\n"
					+ "                                          WHERE  idx = ?) )\r\n"
					+ "                  OR ( bm.category = 3\r\n"
					+ "                       AND bm.mbti_idx = ? )\r\n"
					+ "                   )\r\n"
					+ "               AND bm.title LIKE ?\r\n"
					+ "        ORDER  BY wrdate DESC,\r\n"
					+ "                  boardno DESC)\r\n"
					+ "WHERE  rnum BETWEEN ? AND ?";
		}else {
			//4번게시판 쿼리
			sql = "SELECT boardno,\r\n"
					+ "       wrdate,\r\n"
					+ "       title,\r\n"
					+ "       count,\r\n"
					+ "       viewcheck,\r\n"
					+ "       category,\r\n"
					+ "       recommendation,\r\n"
					+ "       opposition,\r\n"
					+ "       member_mid,\r\n"
					+ "       mbti_idx,\r\n"
					+ "       content\r\n"
					+ "FROM   (SELECT Row_number() over (ORDER BY wrdate DESC, boardno DESC) rnum, boardno,\r\n"
					+ "               wrdate,\r\n"
					+ "               title,\r\n"
					+ "               count,\r\n"
					+ "               viewcheck,\r\n"
					+ "               category,\r\n"
					+ "               recommendation,\r\n"
					+ "               opposition,\r\n"
					+ "               member_mid,\r\n"
					+ "               mbti_idx,\r\n"
					+ "               content\r\n"
					+ "        FROM   (SELECT boardno,\r\n"
					+ "                       wrdate,\r\n"
					+ "                       title,\r\n"
					+ "                       count,\r\n"
					+ "                       viewcheck,\r\n"
					+ "                       category,\r\n"
					+ "                       recommendation,\r\n"
					+ "                       opposition,\r\n"
					+ "                       member_mid,\r\n"
					+ "                       mbti_idx,\r\n"
					+ "                       content,\r\n"
					+ "                       relationgood,\r\n"
					+ "                       relationbad,\r\n"
					+ "                       lovegood,\r\n"
					+ "                       lovebad\r\n"
					+ "                FROM   board b\r\n"
					+ "                       inner join mbti m\r\n"
					+ "                               ON b.mbti_idx = m.idx) bm\r\n"
					+ "        WHERE  ( ( bm.category = 4\r\n"
					+ "                       AND bm.mbti_idx = (SELECT lovebad\r\n"
					+ "                                          FROM   mbti\r\n"
					+ "                                          WHERE  idx = ?) )\r\n"
					+ "                  OR ( bm.category = 4\r\n"
					+ "                       AND bm.mbti_idx = ? ) )\r\n"
					+ "               AND bm.title LIKE ?\r\n"
					+ "        ORDER  BY wrdate DESC,\r\n"
					+ "                  boardno DESC)\r\n"
					+ "WHERE  rnum BETWEEN ? AND ?";
		}
		

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		HttpSession session = request.getSession(true);
		ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();

		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/orcl");
			con = ds.getConnection();

			ps = con.prepareStatement(sql);

			MemberDTO mem = (MemberDTO) session.getAttribute("member");
			
			
			if((request.getParameter("cat") == null)||
					request.getParameter("cat").equals("")){
				
				ps.setString(1, mem.getMbti_idx().toUpperCase());
				ps.setString(2, mem.getMbti_idx().toUpperCase());
				ps.setString(3, mem.getMbti_idx().toUpperCase());
				ps.setString(4, mem.getMbti_idx().toUpperCase());
				ps.setString(5, mem.getMbti_idx().toUpperCase());
				ps.setString(6, mem.getMbti_idx().toUpperCase());
				ps.setString(7, mem.getMbti_idx().toUpperCase());
				ps.setString(8, mem.getMbti_idx().toUpperCase());
				ps.setString(9, mem.getMbti_idx().toUpperCase());
				ps.setString(10, "%" + request.getParameter("search") + "%");
				if(request.getParameter("page") == null || Integer.parseInt(request.getParameter("page")) < 1) {
					page = 1;
					ps.setInt(11, (page-1)*10+1);
					ps.setInt(12, page*10);
				}else {
					page = Integer.parseInt(request.getParameter("page"));
					ps.setInt(11, (page-1)*10+1);
					ps.setInt(12, page*10);
				}
			}else if(request.getParameter("cat").equals("0")) {
				
				ps.setString(1, mem.getMbti_idx().toUpperCase());
				ps.setString(2, "%" + request.getParameter("search") + "%");
				if(request.getParameter("page") == null || Integer.parseInt(request.getParameter("page")) < 1) {
					page = 1;
					ps.setInt(3, (page-1)*10+1);
					ps.setInt(4, page*10);
				}else {
					page = Integer.parseInt(request.getParameter("page"));
					ps.setInt(3, (page-1)*10+1);
					ps.setInt(4, page*10);
				}
			
			}else {
				ps.setString(1, mem.getMbti_idx().toUpperCase());
				ps.setString(2, mem.getMbti_idx().toUpperCase());
				ps.setString(3, "%" + request.getParameter("search") + "%");
				if(request.getParameter("page") == null || Integer.parseInt(request.getParameter("page")) < 1) {
					page = 1;
					ps.setInt(4, (page-1)*10+1);
					ps.setInt(5, page*10);
				}else {
					page = Integer.parseInt(request.getParameter("page"));
					ps.setInt(4, (page-1)*10+1);
					ps.setInt(5, page*10);
				}
			}
			
			rs = ps.executeQuery();

			while (rs.next()) {
				BoardDTO board = new BoardDTO();
				
				board.setBoardno(rs.getInt(1));
				board.setWrdate(rs.getString(2));
				board.setTitle(rs.getString(3));
				board.setCount(rs.getInt(4));
				board.setViewcheck(rs.getString(5));
				board.setCategory(rs.getInt(6));
				board.setRecommendation(rs.getInt(7));
				board.setOpposition(rs.getInt(8));
				board.setMember_mid(rs.getString(9));
				board.setMbti_idx(rs.getString(10).toUpperCase());
				board.setContent(rs.getString(11));
				
				list.add(board);
			}
			
			request.setAttribute("searchlist", list);
			
			return list;

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
		return list;
	}
}
