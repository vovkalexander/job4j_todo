package ru.job4j.todolist.servlet;

import org.json.JSONException;
import org.json.JSONObject;
import ru.job4j.todolist.model.HbmStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class ReplaceDoneServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setHeader("Access-Control-Allow-Origin", "*");
        int id = Integer.parseInt(req.getParameter("id"));
        HbmStore.instOf().replace(id);
    }
}

