import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BoardDAO;

@WebServlet("/board")
public class Board extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");

		BoardDAO board = new BoardDAO();
		board.boardList(request, response);
		board.popBoardList(request, response);
		
		int page;
		
		if(request.getParameter("page") == null || Integer.parseInt(request.getParameter("page")) < 1) {
			page = 1;
		}else {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		Paging paging = new Paging();
		paging.setPage(page);
		paging.setTotalCount(board.paging(request, response));
		paging.paging();
		
		request.setAttribute("paging", paging);
		
		RequestDispatcher rd = request.getRequestDispatcher("./board.jsp");
		rd.forward(request, response);
		
	}
}
