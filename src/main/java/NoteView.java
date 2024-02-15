import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.NoteDAO;

@WebServlet("/noteView")
public class NoteView extends HttpServlet{
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		NoteDAO note = new NoteDAO();
		note.noteList(request, response);
		
		RequestDispatcher rd = request.getRequestDispatcher("./note.jsp");
		rd.forward(request, response);
		
	}
}
