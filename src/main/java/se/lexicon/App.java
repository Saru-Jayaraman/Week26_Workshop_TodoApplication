package se.lexicon;

import se.lexicon.dao.db.MySQLConnection;
import se.lexicon.dao.impl.PeopleDAOImpl;
import se.lexicon.dao.impl.TodoItemDAOImpl;
import se.lexicon.dao.PeopleDAO;
import se.lexicon.dao.TodoItemsDAO;
import se.lexicon.model.Person;
import se.lexicon.model.TodoItem;

import java.time.LocalDate;
import java.util.Collection;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("=====================================PERSON==========================================");
        PeopleDAO peopleObj = new PeopleDAOImpl(MySQLConnection.getConnection());

        System.out.println("====================================CREATE()=========================================");
        System.out.println("Inserted person: " + peopleObj.create(new Person("Firstname14", "Lastname14")));
//        System.out.println("Inserted person: " + peopleObj.create(null)); // Throws error
        System.out.println();

        System.out.println("====================================FIND ALL()=======================================");
        System.out.println("Person List:");
        Collection<Person> personList = peopleObj.findAll();
        if (personList.isEmpty())
            System.out.println("No elements found...");
        else
            personList.forEach(System.out::println);
        System.out.println();

        System.out.println("====================================FIND BY ID()=====================================");
        int findId = 10;
        System.out.println("Person Details of id " + findId + " : " + peopleObj.findById(findId));
        System.out.println();

        System.out.println("=================================FIND BY USERNAME()==================================");
        String findName = "Firstname11 Lastname11";
        System.out.println("Person Details of username " + findName + " : ");
        peopleObj.findByUsername(findName).forEach(System.out::println);
        System.out.println();

        System.out.println("======================================UPDATE()=======================================");
        Person updatePerson = new Person(33, "Firstname33", "Lastname33");
//        System.out.println("Updated Person Details: " + peopleObj.update(null)); // Throws error
        System.out.println("Updated Person Details: " + peopleObj.update(updatePerson));
        System.out.println();

        System.out.println("====================================DELETE()=========================================");
        int deleteId = 3;
        System.out.println("Is Person " + deleteId + " deleted: " + peopleObj.deleteById(deleteId));
        System.out.println();

        System.out.println("====================================TODOITEM=========================================");
        TodoItemsDAO todoItemsObj = new TodoItemDAOImpl(MySQLConnection.getConnection());

        System.out.println("====================================CREATE()=========================================");
        System.out.println("Inserted Todoitem: " + todoItemsObj.create(new TodoItem("Task3", "Description3",
                LocalDate.of(2024, 11, 30), 10, true)));
        System.out.println("Inserted Todoitem: " + todoItemsObj.create(new TodoItem("Task7", "Description7",
                LocalDate.of(2024, 8, 30), false)));
        System.out.println();

        System.out.println("====================================FIND ALL()=======================================");
        System.out.println("TodoItem List:");
        Collection<TodoItem> todoItemsList = todoItemsObj.findAll();
        if (todoItemsList.isEmpty())
            System.out.println("No elements found...");
        else
            todoItemsList.forEach(System.out::println);
        System.out.println();

        System.out.println("====================================FIND BY ID()=====================================");
        int findTodoItemId = 10;
        System.out.println("TodoItem Details of id " + findTodoItemId + " : " + todoItemsObj.findById(findTodoItemId));
        System.out.println();

        System.out.println("================================FIND BY ASSIGNEE ID()================================");
        int findAssigneeId = 11;
        System.out.println("TodoItem Details of id " + findAssigneeId + " :");
        todoItemsObj.findByAssignee(findAssigneeId).forEach(System.out::println);
        System.out.println();

        System.out.println("================================FIND BY DONE STATUS()================================");
        boolean done = false;
        System.out.println("TodoItem Details of DONE status: " + done);
        todoItemsObj.findAllByDoneStatus(done).forEach(System.out::println);
        System.out.println();

        System.out.println("==============================FIND BY UNASSIGNED TASK()==============================");
        System.out.println("TodoItem Details of UNASSIGNED TASKS:");
        todoItemsObj.unAssignedTodoItems().forEach(System.out::println);
        System.out.println();

        System.out.println("====================================UPDATE()=========================================");
        TodoItem updateTodoItem = new TodoItem(9, "Task9", "Description9",
                LocalDate.of(2024, 11, 30), true, 10);
        System.out.println("Updated TodoItem Details: " + todoItemsObj.update(updateTodoItem));
        System.out.println();

        System.out.println("====================================DELETE()=========================================");
        int deleteTodoItemId = 2;
        System.out.println("Is Todoitem id " + deleteTodoItemId + " deleted: " + todoItemsObj.deleteById(deleteTodoItemId));
        System.out.println();

        System.out.println("=================================FIND BY ASSIGNEE()==================================");
        Person findByPerson = new Person(11, "Firstname11", "Lastname11");
        System.out.println("TodoItem Details of person " + findByPerson + " :");
        todoItemsObj.findByAssignee(findByPerson).forEach(System.out::println);
        System.out.println();
    }
}