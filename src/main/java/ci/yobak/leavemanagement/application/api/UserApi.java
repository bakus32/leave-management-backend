package ci.yobak.leavemanagement.application.api;

import ci.yobak.leavemanagement.application.adapter.UserApplicationAdapter;
import ci.yobak.leavemanagement.application.dto.PostUserDto;
import ci.yobak.leavemanagement.application.dto.UserResponseDto;
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
import java.util.stream.Collectors;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/api/{version}/users")
@Tag(name = "Users", description = "Employee user management")
public class UserApi {
    private final UserApplicationAdapter userApplicationAdapter;

    public UserApi(UserApplicationAdapter userApplicationAdapter) {
        this.userApplicationAdapter = userApplicationAdapter;
    }

    @Operation(summary = "Create a user", description = "Create a new employee user and associate them with a profile.")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "User created successfully",
            content = @Content(schema = @Schema(implementation = UserResponseDto.class))),
        @ApiResponse(responseCode = "400", description = "Invalid request payload")
    })
    @PostMapping(version = "v1")
    public ResponseEntity<UserResponseDto> create(@Valid @RequestBody PostUserDto request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userApplicationAdapter.create(request));
    }

    @Operation(summary = "List users", description = "Fetch all users and their profile assignments.")
    @ApiResponse(responseCode = "200", description = "Users retrieved successfully",
        content = @Content(schema = @Schema(implementation = UserResponseDto.class)))
    @GetMapping(version = "v1")
    public ResponseEntity<CollectionModel<EntityModel<UserResponseDto>>> getAll() {
        List<UserResponseDto> users = userApplicationAdapter.getAll();
        List<EntityModel<UserResponseDto>> userModels = users.stream()
            .map(u -> EntityModel.of(u,
                    linkTo(methodOn(UserApi.class).getById(u.getId())).withSelfRel(),
                    linkTo(methodOn(UserApi.class).getAll()).withRel("users")))
            .collect(Collectors.toList());
        return ResponseEntity.ok(CollectionModel.of(userModels,
                linkTo(methodOn(UserApi.class).getAll()).withSelfRel()));
    }

    @Operation(summary = "Get user by id", description = "Fetch a single user by identifier.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "User retrieved successfully",
            content = @Content(schema = @Schema(implementation = UserResponseDto.class))),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping(path = "/{id}", version = "v1")
    public ResponseEntity<EntityModel<UserResponseDto>> getById(@PathVariable Long id) {
        UserResponseDto user = userApplicationAdapter.getById(id);
        return ResponseEntity.ok(EntityModel.of(user,
                linkTo(methodOn(UserApi.class).getById(id)).withSelfRel(),
                linkTo(methodOn(UserApi.class).getAll()).withRel("users")));
    }

    @Operation(summary = "Update a user", description = "Update an existing user record and reassociate the profile if needed.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "User updated successfully",
            content = @Content(schema = @Schema(implementation = UserResponseDto.class))),
        @ApiResponse(responseCode = "404", description = "User not found"),
        @ApiResponse(responseCode = "400", description = "Invalid request payload")
    })
    @PutMapping(path = "/{id}", version = "v1")
    public ResponseEntity<UserResponseDto> update(@PathVariable Long id, @Valid @RequestBody PostUserDto request) {
        return ResponseEntity.ok(userApplicationAdapter.update(id, request));
    }

    @Operation(summary = "Delete a user", description = "Delete a user by their identifier.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "User deleted successfully"),
        @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping(path = "/{id}", version = "v1")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userApplicationAdapter.delete(id);
        return ResponseEntity.noContent().build();
    }
}
