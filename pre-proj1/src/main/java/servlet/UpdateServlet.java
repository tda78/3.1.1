package servlet;

import model.User;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/update")
public class UpdateServlet extends HttpServlet {
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("userID");
        if (id == "") {
            request.setAttribute("bdMethod", "CREATING NEW CLIENT");
            request.setAttribute("userID","");
            request.setAttribute("userName", "name");
            request.setAttribute("userPassword", "password");
            request.setAttribute("userRole", "user");
            getServletContext().getRequestDispatcher("/update.jsp").forward(request, response);
//            response.setStatus(HttpServletResponse.SC_OK);

        } else {
            try {
                request.setAttribute("bdMethod", "UPDATE CLIENT");
                User user = UserServiceImpl.getInstance().getUser(Long.parseLong(id));
                request.setAttribute("userName", user.getName());
                request.setAttribute("userPassword", user.getPassword());
                request.setAttribute("userID", user.getId());
                request.setAttribute("userRole", user.getRole());

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
            String userRole = request.getParameter("role");
            String id = request.getParameter("userID");

            if (id == "") {
                User user = new User(userName, userPassword, userRole);
                UserServiceImpl.getInstance().addUser(user);
            }
            else {
                User user = new User(Long.parseLong(id), userName, userPassword, userRole);
                UserServiceImpl.getInstance().updateUser(user);
            }

            List<User> users = UserServiceImpl.getInstance().getAllUsers();
            request.setAttribute("users",users);
            getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);

//            response.setStatus(HttpServletResponse.SC_OK);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }
}
