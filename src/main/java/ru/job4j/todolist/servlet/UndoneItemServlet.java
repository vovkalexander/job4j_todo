package ru.job4j.todolist.servlet;

import org.json.JSONArray;
import ru.job4j.todolist.model.HbmStore;
import ru.job4j.todolist.model.Item;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UndoneItemServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/json");
        resp.setCharacterEncoding("UTF-8");
        Collection<Item> items = new HbmStore().findAllItems();
        resp.setHeader("Access-Control-Allow-Origin", "*");
        List<Item> undone = new ArrayList<>();
        for (Item item: items) {
            if (!item.isDone()) {
                undone.add(item);
            }
        }
        JSONArray jsonArray = new JSONArray(undone);
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        writer.println(jsonArray);
        writer.flush();
    }
}

