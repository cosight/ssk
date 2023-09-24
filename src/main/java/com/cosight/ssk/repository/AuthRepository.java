package com.cosight.ssk.repository;

import com.cosight.ssk.entity.Account;
import org.springframework.data.repository.CrudRepository;

public interface AuthRepository extends CrudRepository<Account, Long> {
}
