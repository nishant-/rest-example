package rest.example.controller;


import javafx.beans.value.ObservableBooleanValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.example.exceptions.UserServiceException;
import rest.example.model.request.UpdateUserDetails;
import rest.example.model.request.UserDetails;
import rest.example.model.response.User;
import rest.example.service.UserService;

import javax.validation.Valid;
import java.util.Map;

@RestController //-> registers the class as REST Controller
@RequestMapping("users")
public class UserController {


    @Autowired
    UserService userService;
    private Map<String,User> userMap;


    @GetMapping //passing @RequestParam annotation
    public String getUsers(@RequestParam(value="page",defaultValue = "1") int page,
                           @RequestParam(value="limit" , defaultValue = "50") int limit,
                           @RequestParam(value="sort", defaultValue =  "desc", required= false) String sort) //required doesn't work with primitives,
            //will work with String
    {

        return "get user was called, with page = " + page + " limit = " +limit + ", sort = " + sort;
    }

    @GetMapping(path="/{userId}",produces= {MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE })
    //HTTP 406 - Not acceptable
    public ResponseEntity<User> getUser(@PathVariable String userId) {

       /* User user = User.builder().
                email("test@mytest.com").
                lastName("Bhadani").
                firstName("Nishant").
                build();
        return new ResponseEntity<User>(user,HttpStatus.OK);*/

      /* String firstName = null;
       firstName.length();*/
      //this exception will be handled by custom exception handler

        if(true) {
            throw new UserServiceException("user service exception"); //throw a user defined exception
        }

       if(userMap.containsKey(userId)) {
           return new ResponseEntity<>(userMap.get(userId), HttpStatus.OK);
       }
       else {
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       }

    }


    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDetails userDetails) {
        //adding @Valid kicks in validation for the fields defined in UserDetails

        User user = userService.createUser(userDetails);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @PutMapping(path = "/{userId}",consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public User updateUser(@PathVariable String userId, @Valid @RequestBody UpdateUserDetails updateUserDetails) {

        User storedUser = userMap.get(userId);
        storedUser.setFirstName(updateUserDetails.getFirstName());
        storedUser.setLastName(updateUserDetails.getLastName());
        userMap.put(userId,storedUser);
        return storedUser;
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<Void> deleteUser (@PathVariable String id) {

        userMap.remove(id);
        return ResponseEntity.noContent().build();
    }

}
