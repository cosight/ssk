package com.cosight.ssk.repository;

import com.cosight.ssk.entity.Account;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

public class AuthRepositoryImpl extends QuerydslRepositorySupport implements AuthRepositoryCustom {

    public AuthRepositoryImpl() {
        super(Account.class);
    }

}
