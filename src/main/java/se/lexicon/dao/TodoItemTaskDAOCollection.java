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
        for(TodoItemTask todoItemTask : todoItemTasksSet) {
            if(todoItemTask.getId() == id) {
                return todoItemTask;
            }
        }
        return null;
    }

    @Override
    public Set<TodoItemTask> findAll() {
        HashSet<TodoItemTask> newTodoItemTaskSet = new HashSet<>(todoItemTasksSet);
        return newTodoItemTaskSet;
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
}
