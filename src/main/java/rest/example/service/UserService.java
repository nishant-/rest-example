package rest.example.service;


import rest.example.model.request.UserDetails;
import rest.example.model.response.User;

public interface UserService {

     User createUser(UserDetails userDetails);
}
