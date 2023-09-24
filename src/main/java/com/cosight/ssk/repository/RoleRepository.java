package com.cosight.ssk.repository;

import com.cosight.ssk.entity.Authority;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Authority, Integer> /*, RoleRepositoryCustom*/ {
}
