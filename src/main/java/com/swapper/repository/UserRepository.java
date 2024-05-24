package com.swapper.repository;

import com.swapper.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {}