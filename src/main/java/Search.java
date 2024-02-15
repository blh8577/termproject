import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.BoardDAO;
import DAO.SearchDAO;

@WebServlet("/search")
public class Search extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");		

		SearchDAO search = new SearchDAO();
		search.searchList(request, response);
		
		int page;
		
		if(request.getParameter("page") == null || Integer.parseInt(request.getParameter("page")) < 1) {
			page = 1;
		}else {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		Paging paging = new Paging();
		paging.setPage(page);
		paging.setTotalCount(search.paging(request, response));
		paging.paging();
		
		request.setAttribute("searchPaging", paging);
		
		RequestDispatcher rd = request.getRequestDispatcher("./search.jsp");
		rd.forward(request, response);
		
	}
}
