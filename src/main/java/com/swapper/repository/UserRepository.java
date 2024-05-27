package com.swapper.repository;

import com.swapper.entities.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.username = ?1 AND enabled=?2")
    User findBy(String username, boolean enabled);

    @Query("SELECT u FROM User u WHERE u.id = ?1 AND enabled=?2")
    Optional<User> findBy(Long id, boolean enabled);

}