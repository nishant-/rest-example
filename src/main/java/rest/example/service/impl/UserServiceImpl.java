package rest.example.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rest.example.model.request.UserDetails;
import rest.example.model.response.User;
import rest.example.service.UserService;
import rest.example.util.UserIdGenerator;

import java.util.HashMap;
import java.util.Map;


@Service
public class UserServiceImpl implements UserService {


    Map<String,User> userMap;
    UserIdGenerator userIdGenerator;

   public UserServiceImpl() {

    }

    @Autowired
    public UserServiceImpl(UserIdGenerator userIdGenerator) {
        this.userIdGenerator =userIdGenerator;
    }

    @Override
    public User createUser(UserDetails userDetails) {
        User user = new User();
        user.setEmail(userDetails.getEmail());
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        String userId = userIdGenerator.generateUserId();
        user.setUserId(userId);
        if(userMap==null) {
            userMap = new HashMap<>();
            userMap.put(userId,user);
        }
        return user;
    }
}
