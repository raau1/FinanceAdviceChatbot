package com.financeadvicechatbot.repository;

import com.financeadvicechatbot.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {
    // Finding users by their email
    User findByEmail(String email);
}
