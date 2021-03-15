package ru.job4j.todolist.model;

import ru.job4j.todolist.model.Item;

import java.util.Collection;

public interface Store {
    Collection<Item> findAllItems();

    Item save(String description);

    Item findItemById(int id);

    void replace(int id);
}
