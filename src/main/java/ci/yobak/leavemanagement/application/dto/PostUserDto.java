package ci.yobak.leavemanagement.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PostUserDto {
    @NotBlank(message = "User name is required")
    @Size(max = 100, message = "User name must be at most 100 characters")
    private String name;

    @NotBlank(message = "User email is required")
    @Email(message = "User email must be valid")
    @Size(max = 150, message = "User email must be at most 150 characters")
    private String email;

    @NotBlank(message = "User phone is required")
    @Size(max = 30, message = "User phone must be at most 30 characters")
    private String phone;

    @NotNull(message = "Profile id is required")
    private Long profileId;
}
