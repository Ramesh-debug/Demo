package com.udemy.securitycourse1.DetailsService;

import com.udemy.securitycourse1.entity.Details;
import com.udemy.securitycourse1.repository.DetailsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetails implements UserDetailsService {

    @Autowired
    public DetailsRepository detailsRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Details> user1 = detailsRepo.findByUserName(username);
        if (user1.isPresent()) {
            Details user = user1.get();
            return User.builder()
                    .username(user.getUserName())
                    .password(user.getPassword())
                    .roles(getAllRoles(user)).build();
        }
        else
        {
            throw new UsernameNotFoundException(username);
        }
    }

    private String[] getAllRoles(Details user)
    {
        if(user.getRoles()==null)
        {
            return new String[]{"USER"};
        }
        return user.getRoles().split(",");
    }
}
