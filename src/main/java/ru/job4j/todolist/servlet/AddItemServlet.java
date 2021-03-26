package ru.job4j.todolist.servlet;

import org.json.JSONException;
import org.json.JSONObject;
import ru.job4j.todolist.model.HbmStore;
import ru.job4j.todolist.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;

public class AddItemServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        StringBuffer sb = new StringBuffer();
        String line = null;
        String userName = null;
        BufferedReader reader = req.getReader();
        while ((line = reader.readLine()) != null) {
            sb.append(line);
        }
        try {
            JSONObject jsonObject =  new JSONObject(sb.toString());
            userName = jsonObject.getString("desc");
        } catch (JSONException e) {
            throw new IOException("Error parsing JSON request string");
        }
        User user = (User) getServletContext().getAttribute("authUser");
        HbmStore.instOf().save(userName, user);
    }
}


