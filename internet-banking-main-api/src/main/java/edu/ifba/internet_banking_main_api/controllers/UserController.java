package edu.ifba.internet_banking_main_api.controllers;

import edu.ifba.internet_banking_main_api.dtos.request.CreateUserRequestDTO;
import edu.ifba.internet_banking_main_api.dtos.response.SuccessResponse;
import edu.ifba.internet_banking_main_api.dtos.response.ErrorResponse;
import edu.ifba.internet_banking_main_api.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "User Management", description = "Endpoints for user registration and management")
public class UserController {

    private final UserService userService;

    @PostMapping
    @Operation(
        summary = "Create new user",
        description = "Register a new user with CPF, personal information, and creates a bank account. This endpoint is publicly accessible and doesn't require authentication."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "User successfully created",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = SuccessResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid request data or validation errors",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponse.class)
            )
        ),
        @ApiResponse(
            responseCode = "409",
            description = "User with this CPF already exists",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = ErrorResponse.class)
            )
        )
    })
    public ResponseEntity<SuccessResponse> createUser(@Valid @RequestBody CreateUserRequestDTO request) {
        userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new SuccessResponse("User successfully created"));
    }
}
