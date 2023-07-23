package com.geekster.expenseTrackerAPI.repository;

import com.geekster.expenseTrackerAPI.model.AuthenticationToken;
import com.geekster.expenseTrackerAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepo extends JpaRepository<AuthenticationToken,Long> {

    AuthenticationToken findFirstByToken(String token);
    AuthenticationToken findFirstByUser(User user);

}
