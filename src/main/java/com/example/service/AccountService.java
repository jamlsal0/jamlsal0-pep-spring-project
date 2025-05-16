package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Account makeAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account authenticate(Account account) {
        Account theAccount = accountRepository.findByUsername(account.getUsername());
        
        if(theAccount == null) return null;

        if (theAccount.getPassword().equals(account.getPassword())) {
            return theAccount;
        }
        return null;
    }


    public boolean isUsernameBlank(Account account) {
        if (account.getUsername().isBlank()) {
            return true;
        }
        return false;
    }

    public boolean isPasswordShort(Account account) {
        if (account.getPassword().length() < 4) {
            return true;
        }
        return false;
    }

    public boolean accountExists(Account account) {
        if (accountRepository.findByUsername(account.getUsername()) != null) {
            return true;
        }
        return false;
    }
}
