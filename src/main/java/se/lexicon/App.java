package se.lexicon;

import se.lexicon.model.*;

import java.time.LocalDate;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        AppUser appUser1 = new AppUser("person1", "person1", AppRole.ROLE_APP_USER);
        AppUser appUser2 = new AppUser("person2", "person2", AppRole.ROLE_APP_ADMIN);
        AppUser appUser3 = new AppUser("person3", "person3", AppRole.ROLE_APP_USER);
        AppUser appUser4 = new AppUser("person4", "person4", AppRole.ROLE_APP_ADMIN);
        AppUser appUser5 = new AppUser("person5", "person5", AppRole.ROLE_APP_USER);

        Person personObj1 = new Person("Person1", "Person1", "test1@gmail.com", appUser1);
        String personDetails = personObj1.toString();
        System.out.println(personDetails);

        Person personObj2 = new Person("Person2", "Person2", "test2@gmail.com", appUser2);
        personDetails = personObj2.toString();
        System.out.println(personDetails);

        Person personObj3 = new Person("Person3", "Person3", "test3@gmail.com", appUser3);
        personDetails = personObj3.toString();
        System.out.println(personDetails);

        Person personObj4 = new Person("Person4", "Person4", "test4@gmail.com", appUser4);
        personDetails = personObj4.toString();
        System.out.println(personDetails);

        Person personObj5 = new Person("Person5", "Person5", "test5@gmail.com", appUser5);
        personDetails = personObj5.toString();
        System.out.println(personDetails);

        System.out.println();

        System.out.println("========Testcase1========1.TodoItemTask References one person && 2.TodoItem References one person && 3.TodoItemTask contains one TodoItem --> Creator and Assignee are same");
        //TodoItem1 --> Creator and Assignee are same
        TodoItem todoItemObj1 = new TodoItem("Change Tires", "Preferable to use MRF brand",
                LocalDate.of(2024, 6, 30), personObj1, false);

        TodoItemTask todoItemTask1 = new TodoItemTask(personObj1, todoItemObj1);
        String todoItemTaskDetails = todoItemTask1.toString();
        System.out.println(todoItemTaskDetails);

        System.out.println();

        System.out.println("========Testcase2========1.TodoItemTask References one person && 2.TodoItem References one person && 3.TodoItemTask contains one TodoItem --> Creator and Assignee are different");
        //TodoItem2 --> Creator and Assignee are different
        TodoItem todoItemObj2 = new TodoItem("Check brightness of light", "Light is dim",
                LocalDate.of(2024, 5, 30), personObj2, false);

        TodoItemTask todoItemTask2 = new TodoItemTask(personObj3, todoItemObj2);
        todoItemTaskDetails = todoItemTask2.toString();
        System.out.println(todoItemTaskDetails);

        System.out.println();

        System.out.println("========Testcase3========1.TodoItemTask References zero person && 2.TodoItem References one person && 3.TodoItemTask contains one TodoItem --> Assignee is null");
        //TodoItem3 --> Passing only TodoItem --> Not assigned to any person
        TodoItem todoItemObj3 = new TodoItem("Check brake", "Produces sound when applying brake",
                LocalDate.of(2024, 5, 30), personObj2, false);

        TodoItemTask todoItemTask3 = new TodoItemTask(null, todoItemObj3);
        todoItemTaskDetails = todoItemTask3.toString();
        System.out.println(todoItemTaskDetails);

        System.out.println();

        System.out.println("========Testcase5========1.TodoItem References zero person && 2.TodoItemTask contains one TodoItem --> Creator is null");
        //TodoItem5 --> Creator is null
        TodoItem todoItemObj5 = new TodoItem("Check horn", "Produces sound when pressing brake",
                LocalDate.of(2024, 1, 30), null, true);

        TodoItemTask todoItemTask5 = new TodoItemTask(personObj5, todoItemObj5);
        todoItemTaskDetails = todoItemTask5.toString();
        System.out.println(todoItemTaskDetails);

        System.out.println();

        System.out.println("========Testcase4========1.TodoItemTask contains zero TodoItem --> TodoItem is null");
        //TodoItem4 --> TodoItem is null
        TodoItemTask todoItemTask4 = new TodoItemTask(personObj4, null);
        todoItemTaskDetails = todoItemTask4.toString();
        System.out.println(todoItemTaskDetails);

        System.out.println("========================================================================================");

        System.out.println("AppUser:");
        System.out.println("========");
        System.out.println("APPUSER1 TOSTRING():" + appUser1 + "----" + "APPUSER1 HASHCODE():" + appUser1.hashCode());
        System.out.println("APPUSER2 TOSTRING():" + appUser2 + "----" + "APPUSER2 HASHCODE():" + appUser2.hashCode());
        System.out.println("APPUSER1 & APPUSER2 EQUALS():" + appUser1.equals(appUser2));

        System.out.println("Person:");
        System.out.println("=======");
        personDetails = personObj1.toString();
        System.out.println("PERSON1 TOSTRING():" + personDetails + "----" + "PERSON1 HASHCODE():" + personObj1.hashCode());
        personDetails = personObj2.toString();
        System.out.println("PERSON2 TOSTRING():" + personDetails + "----" + "PERSON2 HASHCODE():" + personObj2.hashCode());
        System.out.println("PERSON1 & PERSON2 EQUALS():" + personObj1.equals(personObj2));

        System.out.println("TodoItem:");
        System.out.println("=========");
        todoItemTaskDetails = todoItemObj1.toString();
        System.out.println("TODOITEM1 TOSTRING():" + todoItemTaskDetails + "----" + "TODOITEM1 HASHCODE():" + todoItemObj1.hashCode());
        todoItemTaskDetails = todoItemObj2.toString();
        System.out.println("TODOITEM2 TOSTRING():" + todoItemTaskDetails + "----" + "TODOITEM2 HASHCODE():" + todoItemObj2.hashCode());
        System.out.println("TODOITEM1 & TODOITEM2 EQUALS():" + todoItemObj1.equals(todoItemObj2));

        System.out.println("TodoItemTask:");
        System.out.println("=============");
        todoItemTaskDetails = todoItemTask1.toString();
        System.out.println("TODOITEMTASK1 TOSTRING():" + todoItemTaskDetails + "----" + "TODOITEMTASK1 HASHCODE():" + todoItemTask1.hashCode());
        todoItemTaskDetails = todoItemTask2.toString();
        System.out.println("TODOITEMTASK2 TOSTRING():" + todoItemTaskDetails + "----" + "TODOITEMTASK2 HASHCODE():" + todoItemTask2.hashCode());
        System.out.println("TODOITEMTASK1 & TODOITEMTASK2 EQUALS():" + todoItemTask1.equals(todoItemTask2));
    }
}