import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CommentsDAO;

@WebServlet("/commentInput")
public class CommentsInput extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		CommentsDAO com = new CommentsDAO();
		com.commentInput(request, response);
		
		String view = request.getParameter("view");
		
		response.sendRedirect("./boardView?view="+view);
		
	}
}
