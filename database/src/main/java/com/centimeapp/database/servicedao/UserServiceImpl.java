package com.centimeapp.database.servicedao;

import com.centimeapp.database.annotations.LogMethodParam;
import com.centimeapp.database.entity.User;
import com.centimeapp.database.exception.CustomUserException;
import com.centimeapp.database.utils.DFSGetNestedOutputHelper;
import com.centimeapp.database.model.NestedOutput;
import com.centimeapp.database.model.NestedOutputWithSubclasses;
import com.centimeapp.database.repository.UserServiceRepository;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import static org.springframework.beans.BeanUtils.copyProperties;
@Service
@Log4j2
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserServiceRepository userServiceRepository;
    @LogMethodParam
    public User findById(Integer id){
        log.info("Id is" + id);
        return userServiceRepository.findById(id)
                .orElseThrow(()-> new CustomUserException("User with id does not exist","User_Not_Found"));
    }

   @LogMethodParam
   @ApiOperation("Fetch list of all users from database and return nested output")
    public List<NestedOutputWithSubclasses> findNestedUserDetails() {
        log.info("Fetching data from Database");
        List<NestedOutputWithSubclasses> nestedOutputList = null;
        try{
        List<User> allUsers = userServiceRepository.findAll();
        if(CollectionUtils.isEmpty(allUsers)) {
            throw new CustomUserException("No Entries present in database","No_Enties_Found_In_Database");
        }
        List<NestedOutput> nestedOutputs = new ArrayList<>();
        for(User user: allUsers) {
            NestedOutput nestedOutput = new NestedOutput();
            copyProperties(user, nestedOutput);
            nestedOutputs.add(nestedOutput);
        }
        DFSGetNestedOutputHelper hierarchy = new DFSGetNestedOutputHelper(nestedOutputs);
            nestedOutputList = hierarchy.updateHierarchy();
        } catch (Exception e) {
            throw new CustomUserException("Exception while fetching details from database","Exception_While_Fetching_Details");
        }
        return nestedOutputList;
    }

}
