package se.lexicon.dao;

import se.lexicon.model.TodoItem;
import java.time.LocalDate;
import java.util.Set;

public interface TodoItemDAO {
    TodoItem persist(TodoItem todoItem);
    TodoItem findById(int id);
    Set<TodoItem> findAll();
    Set<TodoItem> findAllByDoneStatus(boolean done);
    Set<TodoItem> findByTitleContains(String title);
    Set<TodoItem> findByPersonID(int personId);
    Set<TodoItem> findByDeadlineBefore(LocalDate deadLine);
    Set<TodoItem> findByDeadlineAfter(LocalDate deadLine);
    void remove(int id);
}