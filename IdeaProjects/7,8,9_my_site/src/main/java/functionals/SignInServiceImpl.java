package functionals;

import dto.SignInForm;
import interfaces.SignInService;
import interfaces.UsersRepository;
import models.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

public class SignInServiceImpl implements SignInService {

    private UsersRepository usersRepository;
    private PasswordEncoder passwordEncoder;

    public SignInServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public int signIn(SignInForm form) {
        String login = form.getEmail();
        String password = form.getPassword();
        User log = User.builder()
                .login(login)
                .build();
        Optional<User> result = usersRepository.findByLogin(log);
        if (result.isPresent()) {
            if (passwordEncoder.matches(password, result.get().getPassword())) {
                return 1;
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }
}
