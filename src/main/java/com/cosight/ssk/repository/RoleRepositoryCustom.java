package com.cosight.ssk.repository;

import com.cosight.ssk.entity.Authority;
import java.util.List;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface RoleRepositoryCustom {

    List<Authority> findRolesByAuthId(Long id);

}
