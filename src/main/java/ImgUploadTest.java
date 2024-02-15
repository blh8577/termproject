import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import DAO.CreateBoardDAO;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.ArrayList;

//멀티파트 어노테이션 (필수)
@MultipartConfig(
        location = "/usr/local/tomcat/upload/tmp",
        maxFileSize = -1,
        maxRequestSize = -1,
        fileSizeThreshold = 1024)
@WebServlet("/imguploadTest")
public class ImgUploadTest extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        ImageUpload imageUpload = new ImageUpload();

        request.setAttribute("imglist", imageUpload.saveImage(request,"productImg"));
        
        CreateBoardDAO cb = new CreateBoardDAO();
        cb.createBoard(request, response);
        cb.createPicture(request, response, (ArrayList<String>) request.getAttribute("imglist"));
 
        response.sendRedirect("./board?cat=" + request.getParameter("cat"));
        
    }
}
