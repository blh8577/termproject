import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.CreateBoardDAO;

@WebServlet("/testBoard")
public class TestBoard extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CreateBoardDAO testboard = new CreateBoardDAO();
		testboard.testBoard();
		
		PrintWriter out = response.getWriter();
		out.println("저장완료");
	}
}
