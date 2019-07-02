package rest.example.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.example.model.request.UserDetails;
import rest.example.model.response.User;

import javax.validation.Valid;

@RestController //-> registers the class as REST Controller
@RequestMapping("users")
public class UserController {


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

        User user = User.builder().
                email("test@mytest.com").
                lastName("Bhadani").
                firstName("Nishant").
                build();
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }


    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<User> createUser(@Valid @RequestBody UserDetails userDetails) {
        //adding @Valid kicks in validation for the fields defined in UserDetails
        User user = new User();
        user.setEmail(userDetails.getEmail());
        user.setFirstName(userDetails.getFirstName());
        user.setLastName(userDetails.getLastName());
        return new ResponseEntity<User>(user,HttpStatus.OK);
    }

    @PutMapping
    public String updateUser() {
        return "update user was called";
    }

    @DeleteMapping
    public String deleteUser () {
        return "delete user was called";
    }

}
