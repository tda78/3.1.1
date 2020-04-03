package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("userID");
        if (id == "") {
            request.setAttribute("bdMethod", "CREATING NEW CLIENT");
            request.setAttribute("userID","");
            request.setAttribute("userName", "name");
            request.setAttribute("userPassword", "password");
            getServletContext().getRequestDispatcher("/update.jsp").forward(request, response);
//            response.setStatus(HttpServletResponse.SC_OK);

        } else {
            try {
                request.setAttribute("bdMethod", "UPDATE CLIENT");
                User user = UserService.getInstance().getUser(Long.parseLong(id));
                request.setAttribute("userName", user.getName());
                request.setAttribute("userPassword", user.getPassword());
                request.setAttribute("userID", user.getId());

                getServletContext().getRequestDispatcher("/update.jsp").forward(request, response);
 //               response.setStatus(HttpServletResponse.SC_OK);

            } catch (Exception e) {
                e.printStackTrace();
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }

    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws ServletException, IOException {
        try {
            String userName = request.getParameter("name");

            String userPassword = request.getParameter("password");
            if (request.getParameter("userID") == "") {

                UserService.getInstance().addUser(userName,userPassword);
            }
            else {
                UserService.getInstance().updateUser(
                        request.getParameter("userID"), userName,userPassword);
            }
            List<User> users = UserService.getInstance().getAllUsers();
            request.setAttribute("users",users);
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);

//            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
