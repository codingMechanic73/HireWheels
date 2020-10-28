package com.upgrad.hirewheels;

import com.upgrad.hirewheels.dao.UsersDao;
import com.upgrad.hirewheels.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class HireWheelsApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(HireWheelsApplication.class, args);
        UsersDao usersDao = ctx.getBean(UsersDao.class);

        System.out.println("*********inserting 5 users**********");
        Users user1 = new Users();
        user1.setEmail("user1@example.com");
        user1.setFirstName("u1fn");
        user1.setLastName("u1ln");
        user1.setMobileNo("+91******1");
        user1.setPassword("pass1");

        Users user2 = new Users();
        user2.setEmail("user2@example.com");
        user2.setFirstName("u2fn");
        user2.setLastName("u2ln");
        user2.setMobileNo("+91******2");
        user2.setPassword("pass2");


        Users user3 = new Users();
        user3.setEmail("user3@example.com");
        user3.setFirstName("u3fn");
        user3.setLastName("u3ln");
        user3.setMobileNo("+91******3");
        user3.setPassword("pass3");


        Users user4 = new Users();
        user4.setEmail("user4@example.com");
        user4.setFirstName("u4fn");
        user4.setLastName("u4ln");
        user4.setMobileNo("+91******4");
        user4.setPassword("pass4");


        Users user5 = new Users();
        user5.setEmail("user5@example.com");
        user5.setFirstName("u5fn");
        user5.setLastName("u5ln");
        user5.setMobileNo("+91******5");
        user5.setPassword("pass5");


        usersDao.save(user1);
        usersDao.save(user2);
        usersDao.save(user3);
        usersDao.save(user4);
        usersDao.save(user5);

        System.out.println("\n");
        System.out.println("*********Find all users**********");
        List<Users> users = usersDao.findAll();
        users.forEach(user -> System.out.println(user));

        System.out.println("\n");
        System.out.println("*********Page 1 users**********");
        Page<Users> page0 = usersDao.findAll(PageRequest.of(0, 2));
        page0.stream().forEach(user -> System.out.println(user));

        System.out.println("\n");
        System.out.println("*********Page 2 users**********");
        Page<Users> page1 = usersDao.findAll(PageRequest.of(1, 2));
        page1.stream().forEach(user -> System.out.println(user));

        System.out.println("\n");
        System.out.println("*********Custom queries**********");
        System.out.println("*********All users by firstName**********");
        List<Users> usersByFirstName = usersDao.findByFirstName("u1fn");
        usersByFirstName.forEach(user -> System.out.println(user));

        System.out.println("\n");
        System.out.println("*********All users by first or last name**********");
        List<Users> usersByLastName = usersDao.findByFirstNameOrLastName("u1fn", "u2ln");
        usersByLastName.forEach(user -> System.out.println(user));

        System.out.println("\n");
        System.out.println("*********User with email**********");
        Optional<Users> userWithEmail = usersDao.findByEmail("user1@example.com");
        System.out.println(userWithEmail.orElse(null));

        System.out.println("\n");
        System.out.println("*********User with mobile**********");
        Optional<Users> userWithMobile = usersDao.findByMobileNo("+91******5");
        System.out.println(userWithMobile.orElse(null));
    }

}
