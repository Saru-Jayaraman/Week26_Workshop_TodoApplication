package se.lexicon;

import se.lexicon.dao.*;
import se.lexicon.model.*;
import java.time.LocalDate;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( String[] args )
    {
        AppUser appUserObj1 = new AppUser("person1", "person1", AppRole.ROLE_APP_USER);
        AppUser appUserObj2 = new AppUser("person2", "person2", AppRole.ROLE_APP_ADMIN);
        AppUser appUserObj3 = new AppUser("person3", "person3", AppRole.ROLE_APP_USER);
        AppUser appUserObj4 = new AppUser("person4", "person4", AppRole.ROLE_APP_ADMIN);
        AppUser appUserObj5 = new AppUser("person5", "person5", AppRole.ROLE_APP_USER);

        AppUserDAO appUserDAOCollection = AppUserDAOCollection.getInstance();
        appUserDAOCollection.persist(appUserObj1);
        appUserDAOCollection.persist(appUserObj2);
        appUserDAOCollection.persist(appUserObj3);
        appUserDAOCollection.persist(appUserObj4);
        appUserDAOCollection.persist(appUserObj5);

        System.out.println("-------------------------------" + "APP USER class - Find All:" + "-------------------------------");
        Set<AppUser> appUserSet = appUserDAOCollection.findAll();
        for(AppUser appUser : appUserSet) {
            System.out.println(appUser.toString());
        }

        System.out.println("-------------------------------" + "AppUser class - Find by Username: " + appUserObj2.getUsername());
        AppUser appUser = appUserDAOCollection.findByUsername(appUserObj2.getUsername());
        System.out.println(appUser);

        System.out.println("-------------------------------" + "AppUser class - Remove the id: " + appUserObj3.getUsername());
        appUserDAOCollection.remove(appUserObj3.getUsername());
        System.out.println("-------------------------------" + "After Removing:");
        appUserSet = appUserDAOCollection.findAll();
        for(AppUser appUser1 : appUserSet) {
            System.out.println(appUser1.toString());
        }

        System.out.println();

        Person personObj1 = new Person("Person1", "Person1", "test1@gmail.com", appUserObj1);
        Person personObj2 = new Person("Person2", "Person2", "test2@gmail.com", appUserObj2);
        Person personObj3 = new Person("Person3", "Person3", "test3@gmail.com", appUserObj3);
        Person personObj4 = new Person("Person4", "Person4", "test4@gmail.com", appUserObj4);
        Person personObj5 = new Person("Person5", "Person5", "test5@gmail.com", appUserObj5);

        PersonDAO personDaoObj = PersonDAOCollection.getInstance();
        personDaoObj.persist(personObj1);
        personDaoObj.persist(personObj2);
        personDaoObj.persist(personObj3);

        PersonDAO personDaoObj1 = PersonDAOCollection.getInstance();
        personDaoObj.persist(personObj4);
        personDaoObj.persist(personObj5);

        System.out.println("-------------------------------" + "PERSON class - Find All:" + "-------------------------------");
        Set<Person> personSet = personDaoObj1.findAll();
        for(Person person : personSet) {
            System.out.println(person.toString());
        }

        System.out.println("-------------------------------" + "Person class - Find by ID:" + personObj1.getId());
        Person person = personDaoObj.findById(personObj1.getId());
        System.out.println(person);

        System.out.println("-------------------------------" + "Person class - Find by Email: " + personObj4.getEmail());
        person = personDaoObj.findByEmail(personObj4.getEmail());
        System.out.println(person);

        System.out.println("-------------------------------" + "Person class - Find by Username: " + personObj4.getCredentials().getUsername());
        person = personDaoObj.findByUsername(personObj4.getCredentials().getUsername());
        System.out.println(person);

        System.out.println("-------------------------------" + "Person class - Remove the id: " + personObj1.getId());
        personDaoObj.remove(personObj1.getId());
        System.out.println("-------------------------------" + "After Removing:");
        personSet = personDaoObj.findAll();
        for(Person person1 : personSet) {
            System.out.println(person1.toString());
        }

        System.out.println();

        TodoItem todoItemObj1 = new TodoItem("Change Tires", "Preferable to use MRF brand",
                LocalDate.of(2024, 6, 30), personObj1, true);
        TodoItem todoItemObj2 = new TodoItem("Check brightness of light", "Light is dim",
                LocalDate.of(2024, 5, 30), personObj2, false);
        TodoItem todoItemObj3 = new TodoItem("Check brake", "Produces sound when applying brake",
                LocalDate.of(2024, 5, 30), personObj2, true);
        TodoItem todoItemObj4 = new TodoItem("Check horn", "Produces sound when pressing brake",
                LocalDate.of(2024, 7, 30), personObj4, false);
        TodoItem todoItemObj5 = new TodoItem("Check light", "Light is blinking",
                LocalDate.of(2024, 7, 15), personObj4, true);

        TodoItemDAO todoItemDAOCollection = TodoItemDAOCollection.getInstance();
        todoItemDAOCollection.persist(todoItemObj1);
        todoItemDAOCollection.persist(todoItemObj2);
        todoItemDAOCollection.persist(todoItemObj3);
        todoItemDAOCollection.persist(todoItemObj4);
        todoItemDAOCollection.persist(todoItemObj5);

        System.out.println("-------------------------------" + "TODO ITEM class - Find All:" + "-------------------------------");
        Set<TodoItem> todoItemSet = todoItemDAOCollection.findAll();
        for(TodoItem todoItem : todoItemSet) {
            System.out.println(todoItem.toString());
        }

        System.out.println("-------------------------------" + "TodoItem class - Find by ID:" + todoItemObj2.getId());
        TodoItem todoItem = todoItemDAOCollection.findById(todoItemObj2.getId());
        System.out.println(todoItem);

        System.out.println("-------------------------------" + "TodoItem class - Find by Done Status: TRUE");
        todoItemSet = todoItemDAOCollection.findAllByDoneStatus(true);
        for(TodoItem todoItem1 : todoItemSet) {
            System.out.println(todoItem1.toString());
        }

        System.out.println("-------------------------------" + "TodoItem class - Find by Title: " + todoItemObj4.getTitle());
        todoItemSet = todoItemDAOCollection.findByTitleContains("check horn");
        for(TodoItem todoItem1 : todoItemSet) {
            System.out.println(todoItem1.toString());
        }

        System.out.println("-------------------------------" + "TodoItem class - Find by Person Id: " + todoItemObj2.getCreator().getId());
        todoItemSet = todoItemDAOCollection.findByPersonID(todoItemObj2.getCreator().getId());
        for(TodoItem todoItem1 : todoItemSet) {
            System.out.println(todoItem1.toString());
        }

        System.out.println("-------------------------------" + "TodoItem class - Find by Deadline Before: " + LocalDate.of(2024,6,30));
        todoItemSet = todoItemDAOCollection.findByDeadlineBefore(LocalDate.of(2024,6,30));
        for(TodoItem todoItem1 : todoItemSet) {
            System.out.println(todoItem1.toString());
        }

        System.out.println("-------------------------------" + "TodoItem class - Find by Deadline After: " + LocalDate.of(2024,6,29));
        todoItemSet = todoItemDAOCollection.findByDeadlineAfter(LocalDate.of(2024,6,29));
        for(TodoItem todoItem1 : todoItemSet) {
            System.out.println(todoItem1.toString());
        }

        System.out.println("-------------------------------" + "TodoItem class - Remove the id: " + todoItemObj2.getId());
        todoItemDAOCollection.remove(todoItemObj2.getId());
        System.out.println("-------------------------------" + "After Removing:");
        todoItemSet = todoItemDAOCollection.findAll();
        for(TodoItem todoItem1 : todoItemSet) {
            System.out.println(todoItem1.toString());
        }

        System.out.println();

        TodoItemTask todoItemTaskObj1 = new TodoItemTask(personObj1, todoItemObj1);
        TodoItemTask todoItemTaskObj2 = new TodoItemTask(personObj1, todoItemObj2);
        TodoItemTask todoItemTaskObj3 = new TodoItemTask(personObj2, todoItemObj3);
        TodoItemTask todoItemTaskObj4 = new TodoItemTask(personObj4, todoItemObj4);
        TodoItemTask todoItemTaskObj5 = new TodoItemTask(personObj5, todoItemObj5);

        TodoItemTaskDAO todoItemTaskDAOCollection = TodoItemTaskDAOCollection.getInstance();

        todoItemTaskDAOCollection.persist(todoItemTaskObj1);
        todoItemTaskDAOCollection.persist(todoItemTaskObj2);
        todoItemTaskDAOCollection.persist(todoItemTaskObj3);
        todoItemTaskDAOCollection.persist(todoItemTaskObj4);
        todoItemTaskDAOCollection.persist(todoItemTaskObj5);

        System.out.println("-------------------------------" + "TODO ITEM TASK class - Find All:" + "-------------------------------");
        Set<TodoItemTask> todoItemTaskSet = todoItemTaskDAOCollection.findAll();
        for(TodoItemTask todoItemTask : todoItemTaskSet) {
            System.out.println(todoItemTask.toString());
        }

        System.out.println("-------------------------------" + "TodoItemTask class - Find by ID: " + todoItemTaskObj3.getId());
        TodoItemTask todoItemTask = todoItemTaskDAOCollection.findById(todoItemTaskObj3.getId());
        System.out.println(todoItemTask);

        System.out.println("-------------------------------" + "TodoItemTask class - Find by Assigned Status: FALSE");
        todoItemTaskSet = todoItemTaskDAOCollection.findByAssignedStatus(false);
        System.out.println(todoItemTaskSet);

        System.out.println("-------------------------------" + "TodoItemTask class - Find by Person Id: " + todoItemTaskObj1.getAssignee().getId());
        todoItemTaskSet = todoItemTaskDAOCollection.findByPersonId(todoItemTaskObj1.getAssignee().getId());
        System.out.println(todoItemTaskSet);

        System.out.println("-------------------------------" + "TodoItemTask class - Remove the id: " + todoItemTaskObj5.getId());
        todoItemTaskDAOCollection.remove(todoItemTaskObj5.getId());
        System.out.println("-------------------------------" + "After Removing:");
        todoItemTaskSet = todoItemTaskDAOCollection.findAll();
        for(TodoItemTask todoItemTask1 : todoItemTaskSet) {
            System.out.println(todoItemTask1.toString());
        }

        System.out.println();
    }
}