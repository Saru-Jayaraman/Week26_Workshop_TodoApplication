package se.lexicon.dao;

import se.lexicon.model.TodoItem;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class TodoItemDAOCollection implements TodoItemDAO {

    Set<TodoItem> todoItemsSet = new HashSet<>();

    @Override
    public TodoItem persist(TodoItem todoItem) {
        todoItemsSet.add(todoItem);
        return todoItem;
    }

    @Override
    public TodoItem findById(int id) {
        for(TodoItem todoItem : todoItemsSet) {
            if(todoItem.getId() == id) {
                return todoItem;
            }
        }
        return null;
    }

    @Override
    public Set<TodoItem> findAll() {
        HashSet<TodoItem> newTodoItemSet = new HashSet<>(todoItemsSet);
        return newTodoItemSet;
    }

    @Override
    public Set<TodoItem> findAllByDoneStatus(boolean done) {
        Set<TodoItem> doneTodoItemSet = new HashSet<>();
        for(TodoItem todoItem : todoItemsSet) {
            if(todoItem.isDone() == done) {
                doneTodoItemSet.add(todoItem);
            }
        }
        return doneTodoItemSet;
    }

    @Override
    public Set<TodoItem> findByTitleContains(String title) {
        Set<TodoItem> titleTodoItemSet = new HashSet<>();
        for(TodoItem todoItem : todoItemsSet) {
            if(todoItem.getTitle().equalsIgnoreCase(title)) {
                titleTodoItemSet.add(todoItem);
            }
        }
        return titleTodoItemSet;
    }

    @Override
    public Set<TodoItem> findByPersonID(int personId) {
        Set<TodoItem> idTodoItemSet = new HashSet<>();
        for(TodoItem todoItem : todoItemsSet) {
            if(todoItem.getCreator().getId() == personId) {
                idTodoItemSet.add(todoItem);
            }
        }
        return idTodoItemSet;
    }

    @Override
    public Set<TodoItem> findByDeadlineBefore(LocalDate deadLine) {
        Set<TodoItem> deadLineBeforeTodoItemSet = new HashSet<>();
        for(TodoItem todoItem : todoItemsSet) {
            if(todoItem.getDeadLine().isBefore(deadLine)) {
                deadLineBeforeTodoItemSet.add(todoItem);
            }
        }
        return deadLineBeforeTodoItemSet;
    }

    @Override
    public Set<TodoItem> findByDeadlineAfter(LocalDate deadLine) {
        Set<TodoItem> deadLineAfterTodoItemSet = new HashSet<>();
        for(TodoItem todoItem : todoItemsSet) {
            if(todoItem.getDeadLine().isAfter(deadLine)) {
                deadLineAfterTodoItemSet.add(todoItem);
            }
        }
        return deadLineAfterTodoItemSet;
    }

    @Override
    public void remove(int id) {
        Iterator<TodoItem> iterator = todoItemsSet.iterator();
        TodoItem removeTodoItem;
        while (iterator.hasNext()) {
            removeTodoItem = iterator.next();
            if (removeTodoItem.getId() == id) {
                iterator.remove();
                break;
            }
        }
    }
}