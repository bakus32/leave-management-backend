package ci.yobak.leavemanagement.application.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

public record PostProfileDto (

    @NotBlank(message = "Profile code is required")
    @Size(max = 50, message = "Profile code must be at most 50 characters")
    String code,

    @NotBlank(message = "Profile name is required")
    @Size(max = 100, message = "Profile name must be at most 100 characters")
    String name){
}
