package edu.ifba.internet_banking_main_api.controllers;

import edu.ifba.internet_banking_main_api.dtos.request.CreateUserRequestDTO;
import edu.ifba.internet_banking_main_api.dtos.response.SuccessResponse;
import edu.ifba.internet_banking_main_api.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<SuccessResponse> createUser(@Valid @RequestBody CreateUserRequestDTO request) {
        userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new SuccessResponse("User successfully created"));
    }
}
