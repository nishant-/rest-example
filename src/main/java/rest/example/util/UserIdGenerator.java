package rest.example.util;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserIdGenerator  {

    public String generateUserId() {
        String userId = UUID.randomUUID().toString();
        return userId;
    }
}
