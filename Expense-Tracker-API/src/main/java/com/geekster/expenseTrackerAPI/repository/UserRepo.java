package com.geekster.expenseTrackerAPI.repository;

import com.geekster.expenseTrackerAPI.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    User findFirstByEmail(String newEmail);
}
