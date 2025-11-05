package com.pravas.thinkshare.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pravas.thinkshare.entities.User;

public interface UserRepo extends JpaRepository<User, Integer>{

}
