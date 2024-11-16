package com.aplication.maxtrix2_0.repository;

import com.aplication.maxtrix2_0.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByLogin(String login);

    User findByLogin(String login);
}
