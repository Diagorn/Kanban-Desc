package com.rosatom.kanban.service;

import com.rosatom.kanban.domain.Account;
import com.rosatom.kanban.domain.Token;
import com.rosatom.kanban.repos.TokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class TokenService {
    @Autowired
    private TokenRepo tokenRepo;

    public String generateToken() {
        String resultToken = "";
        boolean isInDB = true;
        while (isInDB) {
            resultToken = UUID.randomUUID().toString().substring(0, 8);
            if (tokenRepo.findTokenByToken(resultToken) == null)
                isInDB = false;
        }
        return resultToken;
    }

    public void createToken(Account account) {
        Token token = new Token();
        token.setAccount(account);
        token.setToken(generateToken());
        tokenRepo.save(token);
    }
}
