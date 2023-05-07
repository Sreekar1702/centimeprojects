package com.centimeapp.database.servicedao;

import com.centimeapp.database.entity.User;
import com.centimeapp.database.model.NestedOutputWithSubclasses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IUserService  {

    User findById(Integer id);

    List<NestedOutputWithSubclasses> findNestedUserDetails();
}
