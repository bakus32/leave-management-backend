package ci.yobak.leavemanagement.application.api;

import ci.yobak.leavemanagement.application.adapter.ProfileApplicationAdapter;
import ci.yobak.leavemanagement.application.dto.PostProfileDto;
import ci.yobak.leavemanagement.application.dto.ProfileResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("/api/{version}/profiles")
@Tag(name = "Profiles", description = "Profile master data management")
public class ProfileApi {
    private final ProfileApplicationAdapter profileApplicationAdapter;

    public ProfileApi(ProfileApplicationAdapter profileApplicationAdapter) {
        this.profileApplicationAdapter = profileApplicationAdapter;
    }

    @Operation(summary = "Create a profile", description = "Create a new user profile such as EMPLOYEE, MANAGER or ADMIN.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Profile created successfully",
            content = @Content(schema = @Schema(implementation = ProfileResponseDto.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request payload")
    })
    @PostMapping(version = "v1")
    public ResponseEntity<ProfileResponseDto> create(@Valid @RequestBody PostProfileDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(profileApplicationAdapter.create(request));
    }

    @Operation(summary = "List profiles", description = "Fetch all available profiles.")
    @ApiResponse(responseCode = "200", description = "Profiles retrieved successfully",
        content = @Content(schema = @Schema(implementation = ProfileResponseDto.class)))
    @GetMapping(version = "v1")
    public ResponseEntity<List<ProfileResponseDto>> getAll() {
        return ResponseEntity.ok(profileApplicationAdapter.getAll());
    }

    @Operation(summary = "Get profile by id", description = "Fetch a single profile using its identifier.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Profile retrieved successfully",
            content = @Content(schema = @Schema(implementation = ProfileResponseDto.class))),
        @ApiResponse(responseCode = "404", description = "Profile not found")
    })
    @GetMapping(path = "/{id}", version = "v1")
    public ResponseEntity<ProfileResponseDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(profileApplicationAdapter.getById(id));
    }

    @Operation(summary = "Update a profile", description = "Update the code or name of an existing profile.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Profile updated successfully",
            content = @Content(schema = @Schema(implementation = ProfileResponseDto.class))),
        @ApiResponse(responseCode = "404", description = "Profile not found"),
        @ApiResponse(responseCode = "400", description = "Invalid request payload")
    })
    @PutMapping(path = "/{id}", version = "v1")
    public ResponseEntity<ProfileResponseDto> update(@PathVariable Long id, @Valid @RequestBody PostProfileDto request) {
        return ResponseEntity.ok(profileApplicationAdapter.update(id, request));
    }

    @Operation(summary = "Delete a profile", description = "Delete the profile matching the supplied identifier.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Profile deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Profile not found")
    })
    @DeleteMapping(path = "/{id}", version = "v1")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        profileApplicationAdapter.delete(id);
        return ResponseEntity.noContent().build();
    }
}
