package com.quality.service;

import com.quality.model.AuthModel;
import com.quality.model.UserModel;

import java.util.Map;

public interface AuthService {
    Map<String, String> login(AuthModel user);

    void logout();
}
