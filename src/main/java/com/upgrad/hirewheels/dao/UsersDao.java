package com.upgrad.hirewheels.dao;

import com.upgrad.hirewheels.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface UsersDao extends JpaRepository<Users, Integer> {
    public List<Users> findByFirstName(String firstName);

    public List<Users> findByFirstNameOrLastName(String firstName, String lastName);

    public Optional<Users> findByEmail(String email);

    public Optional<Users> findByMobileNo(String mobileNo);
}
