package com.quality.controller;

import com.quality.model.AuthModel;
import com.quality.model.Response;
import com.quality.model.UserModel;
import com.quality.service.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@CrossOrigin
@Slf4j
@RestController
@RequestMapping("/auth")
@Api(tags = "用户认证")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "登录",description = "用户登录接口")
    @PostMapping("/login")
    public Response<?> login(@RequestBody AuthModel user) {
        Map<String, String> data = authService.login(user);
        return Response.builder().code(200).message("登录成功").data(data).build();
    }

    @Operation(summary = "登出",description = "用户登出接口")
    @PostMapping("/logout")
    public Response<?> logout() {
        authService.logout();
        return Response.builder().code(200).message("退出登录成功").build();
    }


    @GetMapping("/code")
    public Response<?> generateImageCheckCode(){
        return null;
    }
}
