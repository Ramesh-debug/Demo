package com.udemy.securitycourse1.DetailsService;
import com.udemy.securitycourse1.entity.Details;
import com.udemy.securitycourse1.repository.DetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DetailsService {

    @Autowired
    public DetailsRepository detailsRepo;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    public Details addDetails(Details details)
    {
        String pass=bCryptPasswordEncoder().encode(details.getPassword());
        details.setPassword(pass);
        detailsRepo.save(details);
        return details;
    }

}
