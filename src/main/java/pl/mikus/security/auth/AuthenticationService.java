package pl.mikus.security.auth;

import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.mikus.security.JwtService;
import pl.mikus.user.RoleRepository;
import pl.mikus.user.Token;
import pl.mikus.user.TokenRepository;
import pl.mikus.user.User;
import pl.mikus.user.UserRepository;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    private final PasswordEncoder passwordEncoder;

    private final EmailService emailService;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    @Value("${application.mailing.frontend.activation-url}")
    private String activationUrl;

    public void register(RegistrationRequest request) throws MessagingException {
        var userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new IllegalStateException("Role USER uninitialized."));
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .enabled(false)
                .roles(List.of(userRole))
                .build();
        userRepository.save(user);
        sendValidationEmail(user);
    }

    private void sendValidationEmail(User user) throws MessagingException {
        var activationCode = generateAndSaveActivationCode(user);
        emailService.sendEmail(user.getEmail(),
                user.fullname(),
                EmailTemplate.ACTIVATE_ACCOUNT,
                activationUrl,
                activationCode,
                "Your activation code");
    }

    private String generateAndSaveActivationCode(User user) {
        String activationCode = generateActivationCode(6);
        var token = Token.builder()
                .token(activationCode)
                .createdDate(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();
        tokenRepository.save(token);
        return activationCode;
    }

    private String generateActivationCode(int length) {
        final String characters = "0123456789";
        final StringBuilder codeBuilder = new StringBuilder();
        final SecureRandom random = new SecureRandom();
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }
        return codeBuilder.toString();
    }


    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        var authenticationResult = authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
                  authenticationRequest.getEmail(),
                  authenticationRequest.getPassword()
          )
        );

        var claims = new HashMap<String, Object>();
        var user = (User) authenticationResult.getPrincipal();
        claims.put("fullName", user.fullname());
        var jwtToken = jwtService.generateToken(claims, user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public void activateAccount(String token) throws MessagingException {
        Token tokenFound = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));
        if (LocalDateTime.now().isAfter(tokenFound.getExpiresAt())) {
            sendValidationEmail(tokenFound.getUser());
            throw new RuntimeException("Activation token has expired. A new token has been sent.");
        }

        var user = userRepository.findById(tokenFound.getUser().getId())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        user.setEnabled(true);
        userRepository.save(user);
        tokenFound.setValidatedAt(LocalDateTime.now());
        tokenRepository.save(tokenFound);
    }

}
