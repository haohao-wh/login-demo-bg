package com.dao;

import com.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserDao extends JpaRepository<User, String> {
    @Query(" from User where id = :id ")
    User findUserById(@Param("id") String id);

    List<User> findAll();

    @Query(" from User where name = :name ")
    User findUserByName(@Param("name") String name);



}
