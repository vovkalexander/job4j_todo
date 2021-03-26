package ru.job4j.todolist.servlet;

import ru.job4j.todolist.model.HbmStore;
import ru.job4j.todolist.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AuthServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = HbmStore.instOf().findUserByDate(email, password);
        if (user != null) {
            HttpSession sc = req.getSession();
             sc.setAttribute("user", user);
             getServletContext().setAttribute("authUser", user);
            resp.sendRedirect(req.getContextPath() + "/index.html");
        } else {
            req.setAttribute("error", "Не верный email или пароль");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }
}
