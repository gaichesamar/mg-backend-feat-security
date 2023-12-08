package com.dev.mygavel.services;



import java.util.Arrays;
import java.util.Random;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dev.mygavel.dto.UserDto;
import com.dev.mygavel.entities.Role;
import com.dev.mygavel.entities.User;
import com.dev.mygavel.repo.RoleRepository;
import com.dev.mygavel.repo.UserRepository;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User register(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new IllegalArgumentException("Cet email est déjà utilisé.");
        } else {
            User user = new User();
            user.setNom(userDto.getNom());
            user.setPrenom(userDto.getPrenom());
            user.setEmail(userDto.getEmail());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setDateNaissance(userDto.getDateNaissance());
            user.setTelephone(userDto.getTelephone());

            Role role = roleRepository.findByLibelle("admin");
            if (role == null) {
                role = new Role();
                role.setLibelle("admin");
                role = roleRepository.save(role);
            }
            user.setRoles(Arrays.asList(role));

            System.out.println(user.getRoles());

            return userRepository.save(user);
        }
    }
    
    public String createRandomOneTimePassword(String username)
    {
                Random random = new Random();
                StringBuilder oneTimePassword = new StringBuilder();
                for (int i = 0; i < 6; i++) {
                    int randomNumber = random.nextInt(10);
                    oneTimePassword.append(randomNumber);
                }
          User user = userRepository.findByEmail(username) ; 
          user.setOtp(passwordEncoder.encode(oneTimePassword.toString().trim()));    
          userRepository.save(user);
          
          return oneTimePassword.toString().trim();
     
   }
    
    public Boolean checkEncodedPassword(String username,String OtpEntred)
    {
        User user = userRepository.findByEmail(username) ; 
        System.out.println(user.getOtp()+"--------" + passwordEncoder.matches(OtpEntred.trim(), user.getOtp()));
        
    	return passwordEncoder.matches(OtpEntred.trim(), user.getOtp());
    }
    
    
}
