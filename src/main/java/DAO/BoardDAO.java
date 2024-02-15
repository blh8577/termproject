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
import DTO.PictureDTO;

public class BoardDAO {

	public void boardRecommendationUpdate(HttpServletRequest request, HttpServletResponse response) {
		String sql = "UPDATE board\r\n"
				+ "SET    recommendation = (SELECT Count(*)\r\n"
				+ "                         FROM   recommendation\r\n"
				+ "                         WHERE  board_boardno = ?)\r\n"
				+ "WHERE  boardno = ?";
		
		Connection con = null;
		PreparedStatement ps = null;

		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/orcl");
			con = ds.getConnection();

			ps = con.prepareStatement(sql);
			
			ps.setInt(1, Integer.parseInt(request.getParameter("view")));
			ps.setInt(2, Integer.parseInt(request.getParameter("view")));
			
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
	
	public int boardRecommendationCheck(HttpServletRequest request, HttpServletResponse response) {
		String sql = "SELECT count(*) FROM recommendation WHERE member_mid = ? AND board_boardno = ?";
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
			ps.setInt(2, Integer.parseInt(request.getParameter("view")));
			
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
	
	public void boardRecommendation(HttpServletRequest request, HttpServletResponse response) {
		String sql = "INSERT INTO recommendation values(?, ?)";
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
			ps.setInt(2, Integer.parseInt(request.getParameter("view")));
			
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
	
	public void boardCounting(HttpServletRequest request, HttpServletResponse response) {
		String sql = "UPDATE board SET count = count + 1 WHERE boardno = ?";
		
		Connection con = null;
		PreparedStatement ps = null;

		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/orcl");
			con = ds.getConnection();

			ps = con.prepareStatement(sql);
			
			ps.setInt(1, Integer.parseInt(request.getParameter("view")));
			
			
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
	
	public void boardView(HttpServletRequest request, HttpServletResponse response) {
		BoardDTO board = null;
		String sql = "SELECT BOARDNO, WRDATE, TITLE, COUNT, VIEWCHECK, CATEGORY,"
				+ " RECOMMENDATION, OPPOSITION, MEMBER_MID, MBTI_IDX, "
				+ "CONTENT FROM BOARD WHERE BOARDNO = ?";
		
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
				board = new BoardDTO();
				board.setBoardno(rs.getInt(1));
				board.setWrdate(rs.getString(2));
				board.setTitle(rs.getString(3));
				board.setCount(rs.getInt(4));
				board.setViewcheck(rs.getString(5));
				board.setCategory(rs.getInt(6));
				board.setRecommendation(rs.getInt(7));
				board.setOpposition(rs.getInt(8));
				board.setMember_mid(rs.getString(9));
				board.setMbti_idx(rs.getString(10));
				board.setContent(rs.getString(11));
			}
			
			request.setAttribute("boardview", board);

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
	
	public void pictureList(HttpServletRequest request, HttpServletResponse response) {

		String sql = "SELECT PICTURENO, PICTUREPATH, BOARD_BOARDNO FROM picture WHERE BOARD_BOARDNO = ?";
		
		ArrayList<String> list = new ArrayList<String>();
		
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

			while (rs.next()) {
				PictureDTO pic = new PictureDTO();
				
				pic.setPictureno(rs.getInt(1));
				pic.setPicturepath(rs.getString(2));
				pic.setBoard_board(rs.getInt(3));

				list.add(pic.getPicturepath());
			}
			
			request.setAttribute("piclist", list);
			

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
	
	public int paging(HttpServletRequest request, HttpServletResponse response){
		
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		HttpSession session = request.getSession(true);
		int totalBoard = 0;
		String sql = "";
		if((request.getParameter("cat") == null)||
				(request.getParameter("cat").equals("0"))||
				request.getParameter("cat").equals("")){
			sql = "SELECT count(boardno) FROM board WHERE MBTI_IDX = ? AND CATEGORY = 0";
		
		}else if(request.getParameter("cat").equals("1")) {
			sql = "SELECT COUNT(boardno) FROM board WHERE MBTI_IDX = ? AND"
					+ " CATEGORY = 1 OR MBTI_IDX IN "
					+ "(SELECT relationgood FROM MBTI WHERE IDX = ? AND CATEGORY = 1)";
		}
		else if(request.getParameter("cat").equals("2")) {
			sql = "SELECT COUNT(boardno) FROM board WHERE MBTI_IDX = ? AND"
					+ " CATEGORY = 2 OR MBTI_IDX IN "
					+ "(SELECT relationbad FROM MBTI WHERE IDX = ? AND CATEGORY = 2)";			
		}
		else if(request.getParameter("cat").equals("3")) {
			sql = "SELECT COUNT(boardno) FROM board WHERE MBTI_IDX = ? AND"
					+ " CATEGORY = 3 OR MBTI_IDX IN "
					+ "(SELECT lovegood FROM MBTI WHERE IDX = ? AND CATEGORY = 3)";			
		}else {
			sql = "SELECT COUNT(boardno) FROM board WHERE MBTI_IDX = ? AND"
					+ " CATEGORY = 4 OR MBTI_IDX IN "
					+ "(SELECT LOVEBAD FROM MBTI WHERE IDX = ? AND CATEGORY = 4)";			
		}
		
		try {
			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/orcl");
			con = ds.getConnection();

			ps = con.prepareStatement(sql);

			MemberDTO mem = (MemberDTO) session.getAttribute("member");
			
			if((request.getParameter("cat") == null)||
					(request.getParameter("cat").equals("0"))||
					request.getParameter("cat").equals("")){
				ps.setString(1, mem.getMbti_idx().toUpperCase());
			}else {
				ps.setString(1, mem.getMbti_idx().toUpperCase());
				ps.setString(2, mem.getMbti_idx().toUpperCase());
			}
			
			rs = ps.executeQuery();
			rs.next();
			totalBoard = rs.getInt(1);
			
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
		
		return totalBoard;
	}
	
	public ArrayList<BoardDTO> popBoardList(HttpServletRequest request, HttpServletResponse response) {

		String sql = "";
		
		if((request.getParameter("cat") == null)||
				(request.getParameter("cat").equals("0"))||
				request.getParameter("cat").equals("")){
			//0번게시판 쿼리
			sql = "SELECT title,\r\n"
					+ "       mbti_idx,\r\n"
					+ "       member_mid,\r\n"
					+ "       count,\r\n"
					+ "       boardno,\r\n"
					+ "       recommendation,\r\n"
					+ "       To_char(wrdate, 'yyyy-mm-dd') wrdate\r\n"
					+ "FROM   (SELECT title,\r\n"
					+ "               mbti_idx,\r\n"
					+ "               member_mid,\r\n"
					+ "               count,\r\n"
					+ "               boardno,\r\n"
					+ "               recommendation,\r\n"
					+ "               wrdate\r\n"
					+ "        FROM   board\r\n"
					+ "        WHERE  mbti_idx = ?\r\n"
					+ "               AND wrdate BETWEEN SYSDATE - 7 AND SYSDATE\r\n"
					+ "               AND category = 0\r\n"
					+ "        ORDER  BY recommendation DESC)\r\n"
					+ "WHERE  ROWNUM BETWEEN 1 AND 5";

			
		}else if(request.getParameter("cat").equals("1")) {
			//1번게시판 쿼리
			sql = "SELECT title,\r\n"
					+ "       mbti_idx,\r\n"
					+ "       member_mid,\r\n"
					+ "       count,\r\n"
					+ "       boardno,\r\n"
					+ "       recommendation,\r\n"
					+ "       To_char(wrdate, 'yyyy-mm-dd') wrdate\r\n"
					+ "FROM   (SELECT title,\r\n"
					+ "               mbti_idx,\r\n"
					+ "               member_mid,\r\n"
					+ "               count,\r\n"
					+ "               boardno,\r\n"
					+ "               recommendation,\r\n"
					+ "               wrdate\r\n"
					+ "        FROM   board\r\n"
					+ "        WHERE  mbti_idx = ?\r\n"
					+ "               AND wrdate BETWEEN sysdate - 7 AND sysdate\r\n"
					+ "               AND category = 1\r\n"
					+ "                OR mbti_idx IN (SELECT relationgood\r\n"
					+ "                                FROM   mbti\r\n"
					+ "                                WHERE  idx = ?)\r\n"
					+ "                   AND category = 1\r\n"
					+ "        ORDER  BY recommendation DESC,\r\n"
					+ "                  boardno DESC)\r\n"
					+ "WHERE  rownum BETWEEN 1 AND 5";
			
		}else if (request.getParameter("cat").equals("2")) {
			//2번게시판 쿼리
			sql = "SELECT title,\r\n"
					+ "       mbti_idx,\r\n"
					+ "       member_mid,\r\n"
					+ "       count,\r\n"
					+ "       boardno,\r\n"
					+ "       recommendation,\r\n"
					+ "       To_char(wrdate, 'yyyy-mm-dd') wrdate\r\n"
					+ "FROM   (SELECT title,\r\n"
					+ "               mbti_idx,\r\n"
					+ "               member_mid,\r\n"
					+ "               count,\r\n"
					+ "               boardno,\r\n"
					+ "               recommendation,\r\n"
					+ "               wrdate\r\n"
					+ "        FROM   board\r\n"
					+ "        WHERE  mbti_idx = ?\r\n"
					+ "               AND wrdate BETWEEN sysdate - 7 AND sysdate\r\n"
					+ "               AND category = 2\r\n"
					+ "                OR mbti_idx IN (SELECT relationbad\r\n"
					+ "                                FROM   mbti\r\n"
					+ "                                WHERE  idx = ?)\r\n"
					+ "                   AND category = 2\r\n"
					+ "        ORDER  BY recommendation DESC,\r\n"
					+ "                  boardno DESC)\r\n"
					+ "WHERE  rownum BETWEEN 1 AND 5";
			
		}else if (request.getParameter("cat").equals("3")) {
			//3번게시판 쿼리
			sql = "SELECT title,\r\n"
					+ "       mbti_idx,\r\n"
					+ "       member_mid,\r\n"
					+ "       count,\r\n"
					+ "       boardno,\r\n"
					+ "       recommendation,\r\n"
					+ "       To_char(wrdate, 'yyyy-mm-dd') wrdate\r\n"
					+ "FROM   (SELECT title,\r\n"
					+ "               mbti_idx,\r\n"
					+ "               member_mid,\r\n"
					+ "               count,\r\n"
					+ "               boardno,\r\n"
					+ "               recommendation,\r\n"
					+ "               wrdate\r\n"
					+ "        FROM   board\r\n"
					+ "        WHERE  mbti_idx = ?\r\n"
					+ "               AND wrdate BETWEEN sysdate - 7 AND sysdate\r\n"
					+ "               AND category = 3\r\n"
					+ "                OR mbti_idx IN (SELECT lovegood\r\n"
					+ "                                FROM   mbti\r\n"
					+ "                                WHERE  idx = ?)\r\n"
					+ "                   AND category = 3\r\n"
					+ "        ORDER  BY recommendation DESC,\r\n"
					+ "                  boardno DESC)\r\n"
					+ "WHERE  rownum BETWEEN 1 AND 5";
		}else {
			//4번게시판 쿼리
			sql = "SELECT title,\r\n"
					+ "       mbti_idx,\r\n"
					+ "       member_mid,\r\n"
					+ "       count,\r\n"
					+ "       boardno,\r\n"
					+ "       recommendation,\r\n"
					+ "       To_char(wrdate, 'yyyy-mm-dd') wrdate\r\n"
					+ "FROM   (SELECT title,\r\n"
					+ "               mbti_idx,\r\n"
					+ "               member_mid,\r\n"
					+ "               count,\r\n"
					+ "               boardno,\r\n"
					+ "               recommendation,\r\n"
					+ "               wrdate\r\n"
					+ "        FROM   board\r\n"
					+ "        WHERE  mbti_idx = ?\r\n"
					+ "               AND wrdate BETWEEN sysdate - 7 AND sysdate\r\n"
					+ "               AND category = 4\r\n"
					+ "                OR mbti_idx IN (SELECT lovebad\r\n"
					+ "                                FROM   mbti\r\n"
					+ "                                WHERE  idx = ?)\r\n"
					+ "                   AND category = 4\r\n"
					+ "        ORDER  BY recommendation DESC,\r\n"
					+ "                  boardno DESC)\r\n"
					+ "WHERE  rownum BETWEEN 1 AND 5";
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
					(request.getParameter("cat").equals("0"))||
					request.getParameter("cat").equals("")){
				ps.setString(1, mem.getMbti_idx().toUpperCase());	
			}else {
				ps.setString(1, mem.getMbti_idx().toUpperCase());
				ps.setString(2, mem.getMbti_idx().toUpperCase());
			}
			
			rs = ps.executeQuery();

			while (rs.next()) {
				BoardDTO board = new BoardDTO();
				
				board.setTitle(rs.getString(1));
				board.setMbti_idx(rs.getString(2).toUpperCase());
				board.setMember_mid(rs.getString(3));
				board.setCount(rs.getInt(4));
				board.setBoardno(rs.getInt(5));
				board.setRecommendation(rs.getInt(6));
				board.setWrdate(rs.getString(7));

				list.add(board);
			}
			
			request.setAttribute("popboardlist", list);
			
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
	
	public ArrayList<BoardDTO> boardList(HttpServletRequest request, HttpServletResponse response) {

		Integer page = null;
		
		String sql = "";
		
		if((request.getParameter("cat") == null)||
				(request.getParameter("cat").equals("0"))||
				request.getParameter("cat").equals("")){
			//0번게시판 쿼리
			sql = "SELECT title,\r\n"
					+ "       mbti_idx,\r\n"
					+ "       member_mid,\r\n"
					+ "       count,\r\n"
					+ "       boardno,\r\n"
					+ "       recommendation,\r\n"
					+ "       To_char(wrdate, 'yyyy-mm-dd') wrdate\r\n"
					+ "FROM   (SELECT Row_number()\r\n"
					+ "                 over (\r\n"
					+ "                   ORDER BY wrdate DESC, boardno DESC) rnum,\r\n"
					+ "               title,\r\n"
					+ "               mbti_idx,\r\n"
					+ "               member_mid,\r\n"
					+ "               count,\r\n"
					+ "               boardno,\r\n"
					+ "               recommendation,\r\n"
					+ "               wrdate\r\n"
					+ "        FROM   board\r\n"
					+ "        WHERE  mbti_idx = ?\r\n"
					+ "               AND category = 0\r\n"
					+ "        ORDER  BY wrdate DESC,\r\n"
					+ "                  boardno DESC)\r\n"
					+ "WHERE  rnum BETWEEN ? AND ?";
			
		}else if(request.getParameter("cat").equals("1")) {
			//1번게시판 쿼리
			sql = "SELECT title,\r\n"
					+ "       mbti_idx,\r\n"
					+ "       member_mid,\r\n"
					+ "       count,\r\n"
					+ "       boardno,\r\n"
					+ "       recommendation,\r\n"
					+ "       To_char(wrdate, 'yyyy-mm-dd') wrdate\r\n"
					+ "FROM   (SELECT Row_number()\r\n"
					+ "                 OVER (\r\n"
					+ "                   ORDER BY wrdate DESC, boardno DESC) rnum,\r\n"
					+ "               title,\r\n"
					+ "               mbti_idx,\r\n"
					+ "               member_mid,\r\n"
					+ "               count,\r\n"
					+ "               boardno,\r\n"
					+ "               recommendation,\r\n"
					+ "               wrdate\r\n"
					+ "        FROM   board\r\n"
					+ "        WHERE  mbti_idx = ?\r\n"
					+ "               AND category = 1\r\n"
					+ "                OR mbti_idx IN (SELECT relationgood\r\n"
					+ "                                FROM   mbti\r\n"
					+ "                                WHERE  idx = ?)\r\n"
					+ "                   AND category = 1\r\n"
					+ "        ORDER  BY wrdate DESC,\r\n"
					+ "                  boardno DESC)\r\n"
					+ "WHERE  rnum BETWEEN ? AND ?";
			
		}else if (request.getParameter("cat").equals("2")) {
			//2번게시판 쿼리
			sql = "SELECT title,\r\n"
					+ "       mbti_idx,\r\n"
					+ "       member_mid,\r\n"
					+ "       count,\r\n"
					+ "       boardno,\r\n"
					+ "       recommendation,\r\n"
					+ "       To_char(wrdate, 'yyyy-mm-dd') wrdate\r\n"
					+ "FROM   (SELECT Row_number()\r\n"
					+ "                 OVER (\r\n"
					+ "                   ORDER BY wrdate DESC, boardno DESC) rnum,\r\n"
					+ "               title,\r\n"
					+ "               mbti_idx,\r\n"
					+ "               member_mid,\r\n"
					+ "               count,\r\n"
					+ "               boardno,\r\n"
					+ "               recommendation,\r\n"
					+ "               wrdate\r\n"
					+ "        FROM   board\r\n"
					+ "        WHERE  mbti_idx = ?\r\n"
					+ "               AND category = 2\r\n"
					+ "                OR mbti_idx IN (SELECT relationbad\r\n"
					+ "                                FROM   mbti\r\n"
					+ "                                WHERE  idx = ?)\r\n"
					+ "                   AND category = 2\r\n"
					+ "        ORDER  BY wrdate DESC,\r\n"
					+ "                  boardno DESC)\r\n"
					+ "WHERE  rnum BETWEEN ? AND ?";
			
		}else if (request.getParameter("cat").equals("3")) {
			//3번게시판 쿼리
			sql = "SELECT title,\r\n"
					+ "       mbti_idx,\r\n"
					+ "       member_mid,\r\n"
					+ "       count,\r\n"
					+ "       boardno,\r\n"
					+ "       recommendation,\r\n"
					+ "       To_char(wrdate, 'yyyy-mm-dd') wrdate\r\n"
					+ "FROM   (SELECT Row_number()\r\n"
					+ "                 OVER (\r\n"
					+ "                   ORDER BY wrdate DESC, boardno DESC) rnum,\r\n"
					+ "               title,\r\n"
					+ "               mbti_idx,\r\n"
					+ "               member_mid,\r\n"
					+ "               count,\r\n"
					+ "               boardno,\r\n"
					+ "               recommendation,\r\n"
					+ "               wrdate\r\n"
					+ "        FROM   board\r\n"
					+ "        WHERE  mbti_idx = ?\r\n"
					+ "               AND category = 3\r\n"
					+ "                OR mbti_idx IN (SELECT lovegood\r\n"
					+ "                                FROM   mbti\r\n"
					+ "                                WHERE  idx = ?)\r\n"
					+ "                   AND category = 3\r\n"
					+ "        ORDER  BY wrdate DESC,\r\n"
					+ "                  boardno DESC)\r\n"
					+ "WHERE  rnum BETWEEN ? AND ?";
		}else {
			//4번게시판 쿼리
			sql = "SELECT title,\r\n"
					+ "       mbti_idx,\r\n"
					+ "       member_mid,\r\n"
					+ "       count,\r\n"
					+ "       boardno,\r\n"
					+ "       recommendation,\r\n"
					+ "       To_char(wrdate, 'yyyy-mm-dd') wrdate\r\n"
					+ "FROM   (SELECT Row_number()\r\n"
					+ "                 OVER (\r\n"
					+ "                   ORDER BY wrdate DESC, boardno DESC) rnum,\r\n"
					+ "               title,\r\n"
					+ "               mbti_idx,\r\n"
					+ "               member_mid,\r\n"
					+ "               count,\r\n"
					+ "               boardno,\r\n"
					+ "               recommendation,\r\n"
					+ "               wrdate\r\n"
					+ "        FROM   board\r\n"
					+ "        WHERE  mbti_idx = ?\r\n"
					+ "               AND category = 4\r\n"
					+ "                OR mbti_idx IN (SELECT lovebad\r\n"
					+ "                                FROM   mbti\r\n"
					+ "                                WHERE  idx = ?)\r\n"
					+ "                   AND category = 4\r\n"
					+ "        ORDER  BY wrdate DESC,\r\n"
					+ "                  boardno DESC)\r\n"
					+ "WHERE rnum BETWEEN ? AND ?";
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
					(request.getParameter("cat").equals("0"))||
					request.getParameter("cat").equals("")){
				
				ps.setString(1, mem.getMbti_idx().toUpperCase());
				if(request.getParameter("page") == null) {
					page = 1;
					ps.setInt(2, page*10-9);
					ps.setInt(3, page*10);
				}else {
					page = Integer.parseInt(request.getParameter("page"));
					ps.setInt(2, page*10-9);
					ps.setInt(3, page*10);
				}
				
			}else {
				
				ps.setString(1, mem.getMbti_idx().toUpperCase());
				ps.setString(2, mem.getMbti_idx().toUpperCase());
			
				if(request.getParameter("page") == null || Integer.parseInt(request.getParameter("page")) < 1) {
					page = 1;
					ps.setInt(3, (page-1)*10+1);
					ps.setInt(4, page*10);
				}else {
					page = Integer.parseInt(request.getParameter("page"));
					ps.setInt(3, page*10-9);
					ps.setInt(4, page*10);
				}
			}
			
			
			rs = ps.executeQuery();

			while (rs.next()) {
				BoardDTO board = new BoardDTO();
				
				board.setTitle(rs.getString(1));
				board.setMbti_idx(rs.getString(2).toUpperCase());
				board.setMember_mid(rs.getString(3));
				board.setCount(rs.getInt(4));
				board.setBoardno(rs.getInt(5));
				board.setRecommendation(rs.getInt(6));
				board.setWrdate(rs.getString(7));

				list.add(board);
			}
			
			request.setAttribute("boardlist", list);
			
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
	
	public ArrayList<BoardDTO> homeBoard(HttpServletRequest request, HttpServletResponse response) {

		String sql = "";
		
		
		if (request.getParameter("homeboard").equals("1")) {
			System.out.println("1s");
			sql = "select " + "rownum, title, mbti_idx, member_mid, count, boardno, " + "recommendation "
					+ "from (select" + " title, mbti_idx, member_mid, count, boardno, " + "recommendation from "
					+ "board " + "where mbti_idx = ? and" + " category = ? order by wrdate desc) a "
					+ "where rownum between 1 and 5 order by rownum";

		} else{
			System.out.println("2s");
			sql = "select rownum, title, mbti_idx, member_mid, count, boardno, "
					+ "recommendation from (select title, mbti_idx, member_mid, count, "
					+ "boardno, recommendation from board where mbti_idx = ? "
					+ "and wrdate between sysdate-7 and sysdate and category = ? ORDER BY"
					+ " recommendation desc) where rownum between 1 and 5";
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
			ps.setString(1, mem.getMbti_idx().toUpperCase());
			
			if(request.getParameter("cat") == null) {
				ps.setInt(2, 0);
			}else {
				ps.setInt(2, Integer.parseInt(request.getParameter("cat")));
			}
			
			rs = ps.executeQuery();

			while (rs.next()) {
				BoardDTO board = new BoardDTO();
				board.setTitle(rs.getString(2));
				board.setMbti_idx(rs.getString(3));
				board.setMember_mid(rs.getString(4));
				board.setCount(rs.getInt(5));
				board.setBoardno(rs.getInt(6));
				board.setRecommendation(rs.getInt(7));
				list.add(board);
			}

			if (request.getParameter("homeboard").equals("1")) {
				request.setAttribute("latestBoard", list);

			} else {
				request.setAttribute("popBoard", list);
			}
			
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
