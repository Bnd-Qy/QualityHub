package com.quality.service;

import java.util.List;

public interface UserService {
    List<String> findUsernames(List<Long> ids);
}
