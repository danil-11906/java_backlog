package functionals;

import dto.SignUpForm;
import interfaces.SignUpService;
import interfaces.UsersRepository;
import models.User;

public class SignUpServiceImpl implements SignUpService {

    private UsersRepository usersRepository;

    public SignUpServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public void signUp(SignUpForm form) {
        User user = User.builder()
                .name(form.getFirstName())
                .surname(form.getLastName())
                .login(form.getEmail())
                .password(form.getPassword())
                .build();

        usersRepository.save(user);
        System.out.println(user);
    }

}
