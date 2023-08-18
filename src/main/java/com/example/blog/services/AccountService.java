package com.example.blog.services;

import com.example.blog.models.Account;
import com.example.blog.models.Authority;
import com.example.blog.repositories.AccountRepository;
import com.example.blog.repositories.AuthorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AccountService {

    @Autowired
    private  PasswordEncoder passwordEncoder;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    public Account save(Account account){
        if (account.getAuthorities().isEmpty()){
            Set<Authority> authorities = new HashSet<>();
            authorityRepository.findById("ROLE_USER").ifPresent(authorities::add);
            account.setAuthorities(authorities);
        }
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return accountRepository.save(account);
    }

    public Optional <Account> findByEmail(String email){
        return accountRepository.findOneByEmail(email);
    }
}
