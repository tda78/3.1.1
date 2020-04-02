package servlet;

import exception.DBException;
import model.BankClient;
import service.BankClientService;
import util.PageGenerator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;

public class RegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(PageGenerator.getInstance().getPage("registrationPage.html", new HashMap<>()));
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        BankClientService bankClientService;
        try {
            bankClientService = new BankClientService();
            BankClient client = new BankClient(
                    req.getParameter("name"),
                    req.getParameter("password"),
                    Long.parseLong(req.getParameter("money")));
            if (bankClientService.addClient(client)) {
                resp.getWriter().println("Add client successful");
            }else{
                resp.getWriter().println("Client not add");
            }
        } catch (Exception e) {
            resp.getWriter().println("Client not add");
        }
        resp.setStatus(200);

    }
}
