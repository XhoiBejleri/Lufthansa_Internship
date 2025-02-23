package org.example.service.impl;

import org.example.model.entity.UserDetails;
import org.example.repository.UserDetailsRepository;
import org.example.repository.impl.UserDetailsRepositoryImpl;
import org.example.service.UserDetailsService;

public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;

    public UserDetailsServiceImpl() {
        this.userDetailsRepository = new UserDetailsRepositoryImpl();
    }

    @Override
    public void saveUserDetails(UserDetails userDetails) {
        userDetailsRepository.save(userDetails);

    }
}
