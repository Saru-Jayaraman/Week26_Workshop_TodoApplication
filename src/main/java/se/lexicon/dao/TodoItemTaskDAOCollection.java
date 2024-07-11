package se.lexicon.dao;

import se.lexicon.model.TodoItemTask;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class TodoItemTaskDAOCollection implements TodoItemTaskDAO {

    Set<TodoItemTask> todoItemTasksSet = new HashSet<>();

    @Override
    public TodoItemTask persist(TodoItemTask todoItemTask) {
        todoItemTasksSet.add(todoItemTask);
        return todoItemTask;
    }

    @Override
    public TodoItemTask findById(int id) {
        validateInput(id);
        for(TodoItemTask todoItemTask : todoItemTasksSet) {
            if(todoItemTask.getId() == id) {
                return todoItemTask;
            }
        }
        return null;
    }

    @Override
    public Set<TodoItemTask> findAll() {
        return new HashSet<>(todoItemTasksSet);
    }

    @Override
    public Set<TodoItemTask> findByAssignedStatus(boolean assigned) {
        Set<TodoItemTask> assignedTodoItemSet = new HashSet<>();
        for(TodoItemTask todoItemTask : todoItemTasksSet) {
            if(todoItemTask.isAssigned() == assigned) {
                assignedTodoItemSet.add(todoItemTask);
            }
        }
        return assignedTodoItemSet;
    }

    @Override
    public Set<TodoItemTask> findByPersonId(int personId) {
        validateInput(personId);
        Set<TodoItemTask> idTodoItemTaskSet = new HashSet<>();
        for(TodoItemTask todoItemTask : todoItemTasksSet) {
            if(todoItemTask.getAssignee().getId() == personId) {
                idTodoItemTaskSet.add(todoItemTask);
            }
        }
        return idTodoItemTaskSet;
    }

    @Override
    public void remove(int id) {
        validateInput(id);
        Iterator<TodoItemTask> iterator = todoItemTasksSet.iterator();
        TodoItemTask removeTodoItemTask;
        while (iterator.hasNext()) {
            removeTodoItemTask = iterator.next();
            if (removeTodoItemTask.getId() == id) {
                iterator.remove();
                break;
            }
        }
    }

    private void validateInput(int id) {
        if(id <= 0) {
            throw new IllegalArgumentException("ID cannot be zero or negative number...");
        }
    }
}
