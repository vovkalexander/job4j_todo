package ru.job4j.todolist.servlet;

import org.json.JSONObject;
import ru.job4j.todolist.model.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DisplayUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = (User) getServletContext().getAttribute("authUser");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        JSONObject json = new JSONObject();
        json.append("showUser", user.getName());
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.println(json);
        writer.flush();
    }
}
