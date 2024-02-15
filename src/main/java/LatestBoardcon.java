import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import DAO.BoardDAO;
import DTO.MemberDTO;

@WebServlet("/latestBoard")
public class LatestBoardcon extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		BoardDAO board = new BoardDAO();
		
		board.homeBoard(request, response);
		
		RequestDispatcher rd = request.getRequestDispatcher("./latestBoard.jsp");
		rd.forward(request, response);

	}
}
