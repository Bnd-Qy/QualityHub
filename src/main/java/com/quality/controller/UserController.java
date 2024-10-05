package com.quality.controller;

import com.quality.model.Response;
import com.quality.model.UserModel;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/user")
@Api(tags = "用户中心")
public class UserController {
    @GetMapping("/info")
    public Response<?> info() {
        Map<String, Object> info = new HashMap<>();
        info.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        info.put("name", "冰点契约");
        info.put("introduction", "I am a super administrator");
        List<String> roles = new ArrayList<>();
        roles.add("admin");
        info.put("roles", roles);
        return Response.builder().code(200).data(info).build();
    }


    @PostMapping("/register")
    public Response<?> register(@RequestBody UserModel user){
        return null;
    }
}
