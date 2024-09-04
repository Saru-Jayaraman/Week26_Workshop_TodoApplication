package se.lexicon.dao.impl;

import se.lexicon.dao.TodoItemsDAO;
import se.lexicon.exception.MySQLException;
import se.lexicon.model.Person;
import se.lexicon.model.TodoItem;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class TodoItemDAOImpl implements TodoItemsDAO {

    private Connection connection;

    public TodoItemDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public TodoItem create(TodoItem todoItem) {
        {
            validateInputObject(todoItem, "insertion");
            StringBuilder queryStringBuilder = new StringBuilder("insert into todo_item(title, description, deadline, done");
            //Number of parameters for insertion with assignee id : 5
            //Number of parameters for insertion without assignee id : 4
            if (todoItem.isAssigned()) {
                queryStringBuilder.append(", assignee_id) values(?, ?, ?, ?, ?)");
            } else {
                queryStringBuilder.append(") values(?, ?, ?, ?)");
            }
            System.out.println("Query: " + queryStringBuilder);
            try (
                    PreparedStatement preparedStatement = connection.prepareStatement(queryStringBuilder.toString(), PreparedStatement.RETURN_GENERATED_KEYS)
            ) {
                preparedStatement.setString(1, todoItem.getTitle());
                preparedStatement.setString(2, todoItem.getTaskDescription());
                preparedStatement.setDate(3, Date.valueOf(todoItem.getDeadLine()));
                preparedStatement.setBoolean(4, todoItem.isDone());
                if (todoItem.isAssigned()) {
                    preparedStatement.setInt(5, todoItem.getAssigneeId());
                }
                int resultSet = preparedStatement.executeUpdate();
                if (resultSet == 0)
                    throw new IllegalArgumentException("Creation operation failed...");
                System.out.println("Todo Item added successfully!!!");
                try (
                        ResultSet generatedKeys = preparedStatement.getGeneratedKeys()
                ) {
                    if (generatedKeys.next()) {
                        int generatedId = generatedKeys.getInt(1);
                        todoItem.setId(generatedId);
                        System.out.println("Generated Id: " + todoItem.getId());
                    }
                }
            } catch (SQLException e) {
                throw new MySQLException("Error occurred while creating todo item with id: " + todoItem.getId(), e);
            }
            return todoItem;
        }
    }

    @Override
    public Collection<TodoItem> findAll() {
        {
            List<TodoItem> todoItemList = new ArrayList<>();
            try (
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery("select * from todo_item")
            ) {
                while (resultSet.next()) {
                    int todoItemId = resultSet.getInt(1);
                    String title = resultSet.getString(2);
                    String description = resultSet.getString(3);
                    LocalDate deadLine = resultSet.getDate(4).toLocalDate();
                    boolean done = resultSet.getBoolean(5);
                    int assigneeId = resultSet.getInt(6);

                    TodoItem todoItem = new TodoItem(todoItemId, title, description, deadLine, done, assigneeId);
                    todoItemList.add(todoItem);
                }
            } catch (SQLException e) {
                throw new MySQLException("Error occurred while finding all todo items: ", e);
            }
            return todoItemList;
        }
    }

    @Override
    public TodoItem findById(int id) {
        {
            TodoItem todoItem = null;
            validateInput(id);
            String query = "select * from todo_item where todo_id = ?";
            try (
                    PreparedStatement preparedStatement = connection.prepareStatement(query)
            ) {
                preparedStatement.setInt(1, id);
                try (
                        ResultSet resultSet = preparedStatement.executeQuery()
                ) {
                    if (!resultSet.isBeforeFirst())
                        throw new IllegalArgumentException("TodoItem id not found in the database... Enter a valid TodoItem id...");
                    if (resultSet.next()) {
                        int todoItemId = resultSet.getInt(1);
                        String title = resultSet.getString(2);
                        String description = resultSet.getString(3);
                        LocalDate deadLine = resultSet.getDate(4).toLocalDate();
                        boolean done = resultSet.getBoolean(5);
                        int assigneeId = resultSet.getInt(6);
                        todoItem = new TodoItem(todoItemId, title, description, deadLine, done, assigneeId);
                    }
                }
            } catch (SQLException e) {
                throw new MySQLException("Error occurred while finding todo item with id: " + id, e);
            }
            return todoItem;
        }
    }

    @Override
    public Collection<TodoItem> findAllByDoneStatus(boolean done) {
        {
            List<TodoItem> todoItemList = new ArrayList<>();
            String query = "select * from todo_item where done = ?";
            try (
                    PreparedStatement preparedStatement = connection.prepareStatement(query)
            ) {
                preparedStatement.setBoolean(1, done);
                try (
                        ResultSet resultSet = preparedStatement.executeQuery()
                ) {
                    if (!resultSet.isBeforeFirst())
                        throw new IllegalArgumentException("No records available of done status " + done + "...");
                    while (resultSet.next()) {
                        int todoItemId = resultSet.getInt(1);
                        String title = resultSet.getString(2);
                        String description = resultSet.getString(3);
                        LocalDate deadLine = resultSet.getDate(4).toLocalDate();
                        boolean doneStatus = resultSet.getBoolean(5);
                        int assigneeId = resultSet.getInt(6);

                        TodoItem todoItem = new TodoItem(todoItemId, title, description, deadLine, doneStatus, assigneeId);
                        todoItemList.add(todoItem);
                    }
                }
            } catch (SQLException e) {
                throw new MySQLException("Error occurred while finding todo items with done status: " + done, e);
            }
            return todoItemList;
        }
    }

    @Override
    public Collection<TodoItem> findByAssignee(int id) {
        List<TodoItem> todoItemList = new ArrayList<>();
        validateInput(id);
        String query = "select * from todo_item where assignee_id = ?";
        try (
                PreparedStatement preparedStatement = connection.prepareStatement(query)
        ) {
            preparedStatement.setInt(1, id);
            try (
                    ResultSet resultSet = preparedStatement.executeQuery()
            ) {
                if (!resultSet.isBeforeFirst())
                    throw new IllegalArgumentException("Assignee id not found in the database... Enter a valid Assignee id...");
                while (resultSet.next()) {
                    int todoItemId = resultSet.getInt(1);
                    String title = resultSet.getString(2);
                    String description = resultSet.getString(3);
                    LocalDate deadLine = resultSet.getDate(4).toLocalDate();
                    boolean done = resultSet.getBoolean(5);
                    int assigneeId = resultSet.getInt(6);

                    TodoItem todoItem = new TodoItem(todoItemId, title, description, deadLine, done, assigneeId);
                    todoItemList.add(todoItem);
                }
            }
        } catch (SQLException e) {
            throw new MySQLException("Error occurred while finding todo items with assignee id: " + id, e);
        }
        return todoItemList;
    }

    @Override
    public Collection<TodoItem> findByAssignee(Person person) {
        {
            List<TodoItem> todoItemList = new ArrayList<>();
            validateInput(person.getId());
            String query = "select * from todo_item where assignee_id = ?";
            try (
                    PreparedStatement preparedStatement = connection.prepareStatement(query)
            ) {
                preparedStatement.setInt(1, person.getId());
                try (
                        ResultSet resultSet = preparedStatement.executeQuery()
                ) {
                    if (!resultSet.isBeforeFirst())
                        throw new IllegalArgumentException("Assignee id not found in the database... Enter a valid Assignee id...");
                    while (resultSet.next()) {
                        int todoItemId = resultSet.getInt(1);
                        String title = resultSet.getString(2);
                        String description = resultSet.getString(3);
                        LocalDate deadLine = resultSet.getDate(4).toLocalDate();
                        boolean done = resultSet.getBoolean(5);
                        int assigneeId = resultSet.getInt(6);

                        TodoItem todoItem = new TodoItem(todoItemId, title, description, deadLine, done, assigneeId);
                        todoItemList.add(todoItem);
                    }
                }
            } catch (SQLException e) {
                throw new MySQLException("Error occurred while finding todo items with assignee id: " + person.getId(), e);
            }
            return todoItemList;
        }
    }

    @Override
    public Collection<TodoItem> unAssignedTodoItems() {
        {
            List<TodoItem> todoItemList = new ArrayList<>();
            String query = "select * from todo_item where assignee_id is null";
            try (
                    Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(query)
            ) {
                if (!resultSet.isBeforeFirst())
                    throw new IllegalArgumentException("There are no unassigned tasks...");
                while (resultSet.next()) {
                    int todoItemId = resultSet.getInt(1);
                    String title = resultSet.getString(2);
                    String description = resultSet.getString(3);
                    LocalDate deadLine = resultSet.getDate(4).toLocalDate();
                    boolean done = resultSet.getBoolean(5);
                    int assigneeId = resultSet.getInt(6);

                    TodoItem todoItem = new TodoItem(todoItemId, title, description, deadLine, done, assigneeId);
                    todoItemList.add(todoItem);
                }
            } catch (SQLException e) {
                throw new MySQLException("Error occurred while finding unassigned todo items: ", e);
            }
            return todoItemList;
        }
    }

    @Override
    public TodoItem update(TodoItem todoItem) {
        {
            validateInputObject(todoItem, "updation");
            TodoItem existingTodoItem = findById(todoItem.getId());
            if (existingTodoItem.equals(todoItem))
                throw new IllegalArgumentException("Existing record in the database and Record to be updated are same... Updation is not needed...");
            String query = "update todo_item set title = ?, description = ?, deadLine = ?, done = ?, assignee_id = ? where todo_id = ?";
            try (
                    PreparedStatement preparedStatement = connection.prepareStatement(query)
            ) {
                preparedStatement.setString(1, todoItem.getTitle());
                preparedStatement.setString(2, todoItem.getTaskDescription());
                preparedStatement.setDate(3, Date.valueOf(todoItem.getDeadLine()));
                preparedStatement.setBoolean(4, todoItem.isDone());
                preparedStatement.setInt(5, todoItem.getAssigneeId());
                preparedStatement.setInt(6, todoItem.getId());
                int resultSet = preparedStatement.executeUpdate();
                if (resultSet == 0)
                    throw new IllegalArgumentException("Updation operation failed...");
                System.out.println("TodoItem updated successfully!!!");
                System.out.println("Updated count: " + resultSet);
            } catch (SQLException e) {
                throw new MySQLException("Error occurred while updating todo item with id: " + todoItem.getId(), e);
            }
            return todoItem;
        }
    }

    @Override
    public boolean deleteById(int id) {
        {
            validateInput(id);
            findById(id);
            String query = "delete from todo_item where todo_id = ?";
            try (
                    PreparedStatement preparedStatement = connection.prepareStatement(query)
            ) {
                preparedStatement.setInt(1, id);
                int resultSet = preparedStatement.executeUpdate();
                if (resultSet == 0)
                    throw new IllegalArgumentException("Deletion operation failed...");
                System.out.println("TodoItem deleted successfully!!!");
                return true;
            } catch (SQLException e) {
                throw new MySQLException("Error occurred while deleting todo item with id: " + id, e);
            }
        }
    }

    private void validateInput(int id) {
        if (id <= 0)
            throw new IllegalArgumentException("Id can be neither 0 nor negative...");
    }
    private void validateInputObject(TodoItem todoItem, String operationName) {
        if (Objects.isNull(todoItem))
            throw new NullPointerException("Todo detail is null... Cannot perform " + operationName + " operation...");
    }
}
