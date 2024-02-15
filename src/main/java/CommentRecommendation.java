import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BoardDAO;
import DAO.CommentsDAO;

@WebServlet("/comRecommendation")
public class CommentRecommendation extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		CommentsDAO com = new CommentsDAO();
		int check = com.comRecommendationCheck(request, response);
		
		if(!(check == 0)) {
			out.print(check);
		}else {
			com.comRecommendation(request, response);
			com.comRecommendationUpdate(request, response);
			out.print(check);
		}
		
	}
}
