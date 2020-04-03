package servlet;

import com.google.gson.Gson;
import exception.DBException;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ApiServlet extends HttpServlet {

    @Override
 /*   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = null;
        try {
            userService = new UserService();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();
        String json;
        if (req.getPathInfo().contains("all")) {
            json = gson.toJson(userService.getAllClient());
        } else {
            json = gson.toJson(userService.getClientByName(req.getParameter("name")));
        }
        resp.getWriter().write(json);
        resp.setStatus(200);
    }*/
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

 /*   @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = null;
        try {
            userService = new UserService();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
      //      userService.createTable();
            resp.setStatus(200);
        } catch (DBException e) {
            resp.setStatus(400);
        }
    }*/
/*
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = null;
        try {
            userService = new UserService();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        if (req.getPathInfo().contains("all")){
            try {
                userService.cleanUp();
            } catch (DBException e) {
                resp.setStatus(400);
            }
        }
    }*/
}
