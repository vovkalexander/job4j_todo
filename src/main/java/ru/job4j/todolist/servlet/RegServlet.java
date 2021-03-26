package ru.job4j.todolist.servlet;


import ru.job4j.todolist.model.HbmStore;
import ru.job4j.todolist.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegServlet extends HttpServlet {
    @Override
    public void  doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        HbmStore.instOf().saveUser(name, email, password);
        req.getRequestDispatcher("login.jsp").forward(req, resp);
    }
}
