package se.lexicon;

import se.lexicon.model.Person;
import se.lexicon.model.TodoItem;
import se.lexicon.model.TodoItemTask;

import java.time.LocalDate;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        Person personObj1 = new Person("Person1", "Person1", "test1@gmail.com");
        String personDetails = personObj1.getSummary();
        System.out.println(personDetails);

        Person personObj2 = new Person("Person2", "Person2", "test2@gmail.com");
        personDetails = personObj2.getSummary();
        System.out.println(personDetails);

        Person personObj3 = new Person("Person3", "Person3", "test3@gmail.com");
        personDetails = personObj3.getSummary();
        System.out.println(personDetails);

        Person personObj4 = new Person("Person4", "Person4", "test4@gmail.com");
        personDetails = personObj4.getSummary();
        System.out.println(personDetails);

        Person personObj5 = new Person("Person5", "Person5", "test5@gmail.com");
        personDetails = personObj5.getSummary();
        System.out.println(personDetails);

        System.out.println();

        System.out.println("========Testcase1========1.TodoItemTask References one person && 2.TodoItem References one person && 3.TodoItemTask contains one TodoItem --> Creator and Assignee are same");
        //TodoItem1 --> Creator and Assignee are same
        TodoItem todoItemObj1 = new TodoItem("Change Tires", "Preferable to use MRF brand",
                LocalDate.of(2024, 6, 30), personObj1, false);

        TodoItemTask todoItemTask1 = new TodoItemTask(personObj1, todoItemObj1);
        String todoItemTaskDetails = todoItemTask1.getSummary();
        System.out.println(todoItemTaskDetails);

        System.out.println();

        System.out.println("========Testcase2========1.TodoItemTask References one person && 2.TodoItem References one person && 3.TodoItemTask contains one TodoItem --> Creator and Assignee are different");
        //TodoItem2 --> Creator and Assignee are different
        TodoItem todoItemObj2 = new TodoItem("Check brightness of light", "Light is dim",
                LocalDate.of(2024, 5, 30), personObj2, false);

        TodoItemTask todoItemTask2 = new TodoItemTask(personObj3, todoItemObj2);
        todoItemTaskDetails = todoItemTask2.getSummary();
        System.out.println(todoItemTaskDetails);

        System.out.println();

        System.out.println("========Testcase3========1.TodoItemTask References zero person && 2.TodoItem References one person && 3.TodoItemTask contains one TodoItem --> Assignee is null");
        //TodoItem3 --> Passing only TodoItem --> Not assigned to any person
        TodoItem todoItemObj3 = new TodoItem("Check brake", "Produces sound when applying brake",
                LocalDate.of(2024, 5, 30), personObj2, false);

        TodoItemTask todoItemTask3 = new TodoItemTask(null, todoItemObj3);
        todoItemTaskDetails = todoItemTask3.getSummary();
        System.out.println(todoItemTaskDetails);

        System.out.println();

        System.out.println("========Testcase5========1.TodoItem References zero person && 2.TodoItemTask contains one TodoItem --> Creator is null");
        //TodoItem5 --> Creator is null
        TodoItem todoItemObj5 = new TodoItem("Check horn", "Produces sound when pressing brake",
                LocalDate.of(2024, 1, 30), null, true);

        TodoItemTask todoItemTask5 = new TodoItemTask(personObj5, todoItemObj5);
        todoItemTaskDetails = todoItemTask5.getSummary();
        System.out.println(todoItemTaskDetails);

        System.out.println();

        System.out.println("========Testcase4========1.TodoItemTask contains zero TodoItem --> TodoItem is null");
        //TodoItem4 --> TodoItem is null
        TodoItemTask todoItemTask4 = new TodoItemTask(personObj4, null);
        todoItemTaskDetails = todoItemTask4.getSummary();
        System.out.println(todoItemTaskDetails);
    }
}