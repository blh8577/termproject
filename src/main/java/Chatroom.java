import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ChatDAO;

@WebServlet("/chatroom")
public class Chatroom extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		ChatDAO chat = new ChatDAO();
		chat.startChat(request, response);
		
		RequestDispatcher rd = request.getRequestDispatcher("./chatroom.jsp");
		rd.forward(request, response);
		
	}
}
