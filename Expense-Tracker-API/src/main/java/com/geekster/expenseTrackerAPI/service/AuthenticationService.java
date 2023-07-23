package com.geekster.expenseTrackerAPI.service;

import com.geekster.expenseTrackerAPI.model.AuthenticationToken;
import com.geekster.expenseTrackerAPI.repository.AuthRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    @Autowired
    AuthRepo authRepo;

    public boolean authenticate(String email, String token) {
        AuthenticationToken authenticationToken = authRepo.findFirstByToken(token);

        if(authenticationToken == null) {
            return  false;
        }
        String tokenConnectedEmail = authenticationToken.getUser().getEmail();

        return tokenConnectedEmail.equals(email);
    }
}
