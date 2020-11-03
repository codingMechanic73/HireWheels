package com.upgrad.hirewheels.dao;

import com.upgrad.hirewheels.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {
    public List<User> findByFirstName(String firstName);

    public List<User> findByFirstNameOrLastName(String firstName, String lastName);

    public Optional<User> findByEmail(String email);

    public Optional<User> findByMobileNo(String mobileNo);

    Optional<User> findByEmailAndPassword(String email, String password);
}
