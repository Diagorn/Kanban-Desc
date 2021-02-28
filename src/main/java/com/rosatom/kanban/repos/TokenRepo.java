package com.rosatom.kanban.repos;

import com.rosatom.kanban.domain.Account;
import com.rosatom.kanban.domain.Token;
import org.springframework.data.repository.CrudRepository;

public interface TokenRepo extends CrudRepository<Token, Long> {
    Token findTokenByToken(String token);
    Token findTokenByAccount(Account account);
}
