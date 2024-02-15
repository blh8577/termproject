import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BoardDAO;

@WebServlet("/boardRecommendation")
public class BoardRecommendation extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		BoardDAO board = new BoardDAO();
		int check = board.boardRecommendationCheck(request, response);
		
		if(!(check == 0)) {
			out.print(check);
		}else {
			board.boardRecommendation(request, response);
			board.boardRecommendationUpdate(request, response);
			out.print(check);
		}
		
	}
}
