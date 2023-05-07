package com.centimeapp.database.repository;

import com.centimeapp.database.entity.User;
import com.centimeapp.database.model.NestedOutputWithSubclasses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserServiceRepository extends JpaRepository<User,Integer> {

}
