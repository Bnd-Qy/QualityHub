package com.quailty.test;

import com.quality.QualityHubApplication;
import com.quality.mapper.UserMapper;
import com.quality.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
@SpringBootTest(classes = QualityHubApplication.class)
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @DisplayName(value = "测试查询")
    @Test
    void queryTest() {
        final String username = "zss";
        UserModel userModel = userMapper.queryUser(username);
        log.info("user:{}", userModel);
        Assertions.assertNotNull(userModel);
    }

    @Test
    void encodeTest(){
        String encode = new BCryptPasswordEncoder().encode("12345678");
        log.info("encode:{}",encode);
    }
}
