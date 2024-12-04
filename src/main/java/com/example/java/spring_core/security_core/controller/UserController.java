package com.example.java.spring_core.security_core.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "User")
public class UserController {


    @Operation(
            description = "Get endpoint for manager",
            summary = "This is a summary for user get endpoint",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Unauthorized / Invalid Token",
                            responseCode = "403"
                    )
            }

    )
    @GetMapping
    public String get() {
        return "GET:: user controller";
    }

    @PostMapping
    public String post() {
        return "POST:: user controller";
    }

    @PutMapping
    public String put() {
        return "PUT:: user controller";
    }

    @DeleteMapping
    public String delete() {
        return "DELETE:: user controller";
    }
}








