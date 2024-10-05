package com.quality.controller;

import com.quality.model.Response;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@Api(tags = "欢迎页")
public class WelcomeController {
    @GetMapping
    public Response<?> welcome() {
        return Response.builder().message("Welcome quality hub!").code(HttpStatus.OK.value())
                .build();
    }
}
