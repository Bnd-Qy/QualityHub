package com.quality.service.impl;

import com.quality.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<String> findUsernames(List<Long> ids) {
        return null;
    }
}
