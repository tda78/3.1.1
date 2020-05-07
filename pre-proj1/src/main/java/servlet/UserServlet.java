package servlet;

import model.User;
import service.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            User user = UserServiceImpl.getInstance().getTempUser();
            req.setAttribute("user", user);
            req.getServletContext().getRequestDispatcher("/user.jsp").forward(req, resp);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

