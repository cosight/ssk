package com.cosight.ssk.repository;

import com.cosight.ssk.entity.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long>/*, AuthRepositoryCustom */ {

    // Optional<Account> findByUsername();

}
