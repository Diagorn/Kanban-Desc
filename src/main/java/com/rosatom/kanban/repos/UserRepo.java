package com.rosatom.kanban.repos;

import com.rosatom.kanban.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Account, String> {
    Account findByUsername(String username);
}
