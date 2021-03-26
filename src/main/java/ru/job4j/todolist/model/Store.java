package ru.job4j.todolist.model;

import ru.job4j.todolist.model.Item;

import java.util.Collection;

public interface Store {
    Collection<Item> findAllItems();

    Item save(String description, User user);

    Item findItemById(int id);

    void replace(int id);

    User saveUser(String name, String email, String password);

    User findUserByDate(String email, String password);
}
