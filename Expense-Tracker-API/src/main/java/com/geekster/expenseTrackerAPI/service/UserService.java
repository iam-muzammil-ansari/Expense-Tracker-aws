package com.geekster.expenseTrackerAPI.service;

import com.geekster.expenseTrackerAPI.model.AuthenticationToken;
import com.geekster.expenseTrackerAPI.model.DataToObject.SignInInput;
import com.geekster.expenseTrackerAPI.model.DataToObject.SignUpOutput;
import com.geekster.expenseTrackerAPI.model.User;
import com.geekster.expenseTrackerAPI.repository.AuthRepo;
import com.geekster.expenseTrackerAPI.repository.UserRepo;
import com.geekster.expenseTrackerAPI.service.emailUtility.EmailHandler;
import com.geekster.expenseTrackerAPI.service.hashingUtility.PasswordEncrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    AuthRepo authRepo;

    public SignUpOutput SignUpUser(User user) {
        boolean signUpStatus = true;
        String signUpStatusMessage = null;

        String newEmail = user.getEmail();

        if(newEmail == null)
        {
            signUpStatusMessage = "Invalid email";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }

        //check if this patient email already exists ??
        User existingUser = userRepo.findFirstByEmail(newEmail);

        if(existingUser != null)
        {
            signUpStatusMessage = "Email already registered!!!";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }

        //hash the password: encrypt the password
        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(user.getPassword());

            //saveAppointment the patient with the new encrypted password

            user.setPassword(encryptedPassword);
            userRepo.save(user);

            return new SignUpOutput(signUpStatus, "User registered successfully!!!");
        }
        catch(Exception e)
        {
            signUpStatusMessage = "Internal error occurred during sign up";
            signUpStatus = false;
            return new SignUpOutput(signUpStatus,signUpStatusMessage);
        }
    }

    public String signInUser(SignInInput signInInput) {
        String signInStatusMessage = null;

        String signInEmail = signInInput.getEmail();

        if(signInEmail == null)
        {
            signInStatusMessage = "Invalid email";
            return signInStatusMessage;


        }

        //check if this patient email already exists ??
        User existingUser = userRepo.findFirstByEmail(signInEmail);

        if(existingUser == null)
        {
            signInStatusMessage = "Email not registered!!!";
            return signInStatusMessage;

        }

        //match passwords :

        //hash the password: encrypt the password
        try {
            String encryptedPassword = PasswordEncrypter.encryptPassword(signInInput.getPassword());
            if(existingUser.getPassword().equals(encryptedPassword))
            {
                //session should be created since password matched and user id is valid
                AuthenticationToken authToken  = new AuthenticationToken(existingUser);
                authRepo.save(authToken);

                EmailHandler.sendEmail(signInEmail,"email testing",authToken.getToken());
                return "Token sent to your email";
            }
            else {
                signInStatusMessage = "Invalid credentials!!!";
                return signInStatusMessage;
            }
        }
        catch(Exception e)
        {
            signInStatusMessage = "Internal error occurred during sign in";
            return signInStatusMessage;
        }
    }

    public User getUserByEmail(String email) {
        return userRepo.findFirstByEmail(email);
    }
    public String signOutUser(String email) {
        User user = userRepo.findFirstByEmail(email);
        if (user != null) {
            authRepo.delete(authRepo.findFirstByUser(user));
            return "User Signed out Successfully";
        } else {
            return "User not found!";
        }
    }
}
