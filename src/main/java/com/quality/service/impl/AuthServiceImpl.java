package com.quality.service.impl;

import com.quality.model.AuthModel;
import com.quality.model.LoginUser;
import com.quality.model.UserModel;
import com.quality.service.AuthService;
import com.quality.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@Service
public class AuthServiceImpl implements AuthService {


    public AuthServiceImpl(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    /**
     * 登录业务方法
     *
     * @param user 用户信息
     * @return token
     */
    @Override
    public Map<String, String> login(AuthModel user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
        //调用认证管理器认证
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (ObjectUtils.isEmpty(authenticate)) {
            throw new RuntimeException("用户名或密码错误");
        }
        //使用userid生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        //TODO 缓存用户信息到redis中
        String userId = loginUser.getUser().getId().toString();
        log.info("登录用户:{}", userId);
        String token = jwtTokenUtil.generateToken(userId);
        //把token响应给前端
        HashMap<String, String> map = new HashMap<>();
        map.put("token", token);
        return map;
    }

    @Override
    public void logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();
        log.info("登出用户:{}", userid);
    }
}
