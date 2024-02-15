import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ChatDAO;

@WebServlet("/chatview")
public class Chatview extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		ChatDAO chat = new ChatDAO();
		chat.chatList(request, response);
		
		RequestDispatcher rd = request.getRequestDispatcher("./chatview.jsp");
		rd.forward(request, response);
	}
}
