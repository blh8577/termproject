import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.NoteDAO;


@WebServlet("/noteInput")
public class NoteInput extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		NoteDAO note = new NoteDAO();
		note.noteInput(request, response);
		
		response.sendRedirect("./noteView?recipients=" + request.getParameter("recipients"));
	}
}
