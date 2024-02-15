import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BoardDAO;
import DAO.CommentsDAO;

@WebServlet("/boardView")
public class BoardView extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		BoardDAO board = new BoardDAO();
		board.boardCounting(request, response);
		board.boardView(request, response);
		board.pictureList(request, response);
		
		CommentsDAO com = new CommentsDAO();
		com.comment(request, response);
		
		RequestDispatcher rd = request.getRequestDispatcher("./view.jsp");
		rd.forward(request, response);
		
	}
}
