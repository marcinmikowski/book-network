package pl.mikus.security.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthenticationRequest {
    @Email(message = "Email is not well formatted")
    @NotEmpty(message = "Email is mandatory")
    @NotBlank(message = "Email is mandatory")
    private String email;
    @Size(min = 8, message = "Password must be at least 8 long minimum")
    @NotEmpty(message = "Password is mandatory")
    @NotBlank(message = "Password is mandatory")
    private String password;

}
