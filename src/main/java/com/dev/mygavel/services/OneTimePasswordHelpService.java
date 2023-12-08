package com.dev.mygavel.services;

import com.dev.mygavel.configEmail.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.function.Supplier;

//@Service
public class OneTimePasswordHelpService {
@Autowired
private MailService mailService;
    private final static Integer LENGTH = 6;

    public static Supplier<Integer> createRandomOneTimePassword() {
        return () -> {
            Random random = new Random();
            StringBuilder oneTimePassword = new StringBuilder();
            for (int i = 0; i < LENGTH; i++) {
                int randomNumber = random.nextInt(10);
                oneTimePassword.append(randomNumber);
            }
            return Integer.parseInt(oneTimePassword.toString().trim());
        };
    }
    }