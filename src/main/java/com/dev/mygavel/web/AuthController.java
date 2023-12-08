package com.dev.mygavel.web;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import com.dev.mygavel.configEmail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.mygavel.entities.User;
import com.dev.mygavel.services.UserServiceImp;

@RestController
public class AuthController {
    private JwtEncoder jwtEncoder;
    private JwtDecoder jwtDecoder;
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    @Autowired
    private MailService mailService;
    @Autowired
    private UserServiceImp userServ ;
    
    

    public AuthController(JwtEncoder jwtEncoder, JwtDecoder jwtDecoder, AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        this.jwtEncoder = jwtEncoder;
        this.jwtDecoder = jwtDecoder;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/tokens")
    public ResponseEntity<Map<String, String>> jwtToken(           
            String username,
            String password
        ){
            String subject=null;
            String scope=null;
            
            
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            subject=authentication.getName();
            scope=authentication.getAuthorities()
                    .stream().map(aut -> aut.getAuthority())
                    .collect(Collectors.joining(" "));

     
        Map<String, String> idToken=new HashMap<>();
        Instant instant=Instant.now();
       
        
        
            JwtClaimsSet jwtClaimsSetRefresh=JwtClaimsSet.builder()
                    .subject(subject)
                    .issuedAt(instant)
                    .expiresAt(instant.plus(5, ChronoUnit.MINUTES))
                    .issuer("mygavel")
                    .build();
            String jwtRefreshToken=jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSetRefresh)).getTokenValue();
            idToken.put("refreshToken",jwtRefreshToken);
            
            idToken.put("OTP",userServ.createRandomOneTimePassword(username));
            
            
            
        return new ResponseEntity<>(idToken,HttpStatus.OK);
    }
    
    
    
    @PostMapping("/tokenOtp")
    public ResponseEntity<Map<String, String>> jwtTokenOtp(
    		String username,
    		String otp,
            String refreshToken){
            String subject=null;
            String scope=null;

        mailService.sendTextEmail("sahargaiche6@gmail.com","Votre mdp",otp);

        System.out.println(userServ.checkEncodedPassword(username, otp));

            if(refreshToken==null) {
                return new ResponseEntity<>(Map.of("errorMessage","Refresh  Token is required"), HttpStatus.UNAUTHORIZED);
            }
            if(!userServ.checkEncodedPassword(username, otp)) {
                return new ResponseEntity<>(Map.of("errorMessage","Verify your OTP"), HttpStatus.UNAUTHORIZED);
            }
            else {
            Jwt decodeJWT = null;
            try {
                decodeJWT = jwtDecoder.decode(refreshToken);
            } catch (JwtException e) {
                return new ResponseEntity<>(Map.of("errorMessage",e.getMessage()), HttpStatus.UNAUTHORIZED);
            }
            subject=decodeJWT.getSubject();
            UserDetails userDetails = userDetailsService.loadUserByUsername(subject);
            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
            scope=authorities.stream().map(auth->auth.getAuthority()).collect(Collectors.joining(" "));
        
            
        Map<String, String> idToken=new HashMap<>();
        Instant instant=Instant.now();
        JwtClaimsSet jwtClaimsSet=JwtClaimsSet.builder()
                .subject(subject)
                .issuedAt(instant)
                .expiresAt(instant.plus(true?1:5, ChronoUnit.MINUTES))
                .issuer("mygavel")
                .claim("scope",scope)
                .build();
        String jwtAccessToken=jwtEncoder.encode(JwtEncoderParameters.from(jwtClaimsSet)).getTokenValue();
        idToken.put("accessToken",jwtAccessToken);
      
        
        return new ResponseEntity<>(idToken,HttpStatus.OK);
    }
    }
    @PostMapping("/token")
    public void senEmail(){
        mailService.sendTextEmail("sahargaiche6@gmail.com","Test","testtest");

    }
    

}