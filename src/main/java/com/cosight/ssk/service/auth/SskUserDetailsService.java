package com.cosight.ssk.service.auth;

import com.cosight.ssk.repository.AccountRepository;
import com.cosight.ssk.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SskUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Account account = accountRepository.findByUsername()
        //                                    .orElseThrow(IllegalAccessError::new);

        // List<Authority> roles = roleRepository.findRolesByAuthId(account.getId());

        // return new SskUserDetails(account, null);
        return null;
    }

}
