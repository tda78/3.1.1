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

public class MoneyTransactionServlet extends HttpServlet {

    BankClientService bankClientService = new BankClientService();

    public MoneyTransactionServlet() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(PageGenerator.getInstance().getPage("moneyTransactionPage.html", new HashMap<>()));
        resp.setContentType("text/html;charset=utf-8");
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            BankClient sender = new BankClient(
                    req.getParameter("senderName"),
                    req.getParameter("senderPass"),
                    0L);
            if (
                    bankClientService.sendMoneyToClient(
                            sender,
                            req.getParameter("nameTo"),
                            Long.parseLong(req.getParameter("count")))
            ) {
                resp.getWriter().println("The transaction was successful");
            }else {
                resp.getWriter().println("transaction rejected");
            }

        } catch (Exception e) {
            resp.getWriter().println("transaction rejected");
        }
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
