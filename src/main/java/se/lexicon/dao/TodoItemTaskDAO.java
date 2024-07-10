package se.lexicon.dao;

import se.lexicon.model.TodoItemTask;
import java.util.Set;

public interface TodoItemTaskDAO {
    TodoItemTask persist(TodoItemTask todoItemTask);
    TodoItemTask findById(int id);
    Set<TodoItemTask> findAll();
    Set<TodoItemTask> findByAssignedStatus(boolean assigned);
    Set<TodoItemTask> findByPersonId(int personId);
    void remove(int id);
}