package ru.itis.springbootdemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.itis.springbootdemo.dto.UserForm;
import ru.itis.springbootdemo.models.StateActive;
import ru.itis.springbootdemo.models.StateConfirmed;
import ru.itis.springbootdemo.models.User;
import ru.itis.springbootdemo.repositories.UsersRepository;

import java.util.UUID;

@Component
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailsService mailsService;

    @Autowired
    private SmsService smsService;

    @Override
    public void signUp(UserForm form) {
        User newUser = User.builder()
                .email(form.getEmail())
                .name(form.getName())
                .surname(form.getSurname())
                .password(passwordEncoder.encode(form.getPassword()))
                .phone(form.getPhone())
                .role(User.Role.USER)
                .stateActive(StateActive.ACTIVE)
                .stateConfirmed(StateConfirmed.NOT_CONFIRMED)
                .confirmCode(UUID.randomUUID().toString())
                .build();

        usersRepository.save(newUser);
        smsService.sendSms(form.getPhone(), "Вы зарегестированы");
        mailsService.sendEmailForConfirm(newUser.getEmail(), newUser.getConfirmCode());
    }
}
