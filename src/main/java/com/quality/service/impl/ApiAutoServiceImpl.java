package com.quality.service.impl;

import com.quality.asserts.Assert;
import com.quality.asserts.HttpBodyTypeAssert;
import com.quality.asserts.HttpBodyVariableAssert;
import com.quality.asserts.HttpStatusAssert;
import com.quality.mapper.ApiAutoMapper;
import com.quality.model.ApiCaseModel;
import com.quality.service.ApiAutoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ApiAutoServiceImpl implements ApiAutoService {
    private final ApiAutoMapper apiAutoMapper;
    private final RestTemplate restTemplate;
    public static Map<String, HttpMethod> REQUEST_METHOD_MAPPING;

    private List<Assert> assertsType;

    static {
        REQUEST_METHOD_MAPPING = new HashMap<>();
        REQUEST_METHOD_MAPPING.put("GET", HttpMethod.GET);
        REQUEST_METHOD_MAPPING.put("POST", HttpMethod.POST);
        REQUEST_METHOD_MAPPING.put("PUT", HttpMethod.PUT);
        REQUEST_METHOD_MAPPING.put("DELETE", HttpMethod.DELETE);
        //TODO add other request methods
    }

    @PostConstruct
    private void loadAsserts() {
        //懒加载
        assertsType = new ArrayList<>();
        assertsType.add(new HttpStatusAssert());
        assertsType.add(new HttpBodyTypeAssert());
        assertsType.add(new HttpBodyVariableAssert());
    }

    public ApiAutoServiceImpl(ApiAutoMapper apiAutoMapper, RestTemplate restTemplate) {
        this.apiAutoMapper = apiAutoMapper;
        this.restTemplate = restTemplate;
    }

    @Override
    public Long createCase(ApiCaseModel apiCase) {
        return apiAutoMapper.insertApiCase(apiCase);
    }

    @Override
    public Long runCase(Long caseId) {
        //查询case
        ApiCaseModel apiCaseModel = apiAutoMapper.queryApiCaseById(caseId);
        //判断是否有依赖
        if (!ObjectUtils.isEmpty(apiCaseModel) && !CollectionUtils.isEmpty(apiCaseModel.getDepends())) {
            List<Long> depends = apiCaseModel.getDepends();
            //批量查询依赖的测试用例
            for (Long depend : depends) {
                //执行依赖
                runCase(depend);
            }
        }
        HttpMethod httpMethod = REQUEST_METHOD_MAPPING.get(apiCaseModel.getMethod());
        String url = apiCaseModel.getUrl();
        Map<String, List<String>> headers = apiCaseModel.getHeader();
        Map<String, Object> body = apiCaseModel.getBody();

        //利用正则表达式解析变量${var_name}

        //从缓存中加载变量并替换
        //new HttpEntity<>(body,header);
        MultiValueMap<String, String> headerArgs = CollectionUtils.toMultiValueMap(headers);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headerArgs);
        ResponseEntity<Map> response = restTemplate.exchange(url, httpMethod, entity, Map.class);
        //从response中解析状态码和body

        //对body中的变量进行缓存

        //进行断言操作
        Map<String, Object> asserts = apiCaseModel.getAsserts();
        for (String assertType : asserts.keySet()) {
            for (Assert ass : assertsType) {
                if (ass.support(assertType)) {
                    boolean assertResult = ass.doAssert(asserts.get(assertType), response);
                    //回填测试结果到数据库中
                }
            }
        }
        return null;
    }

    @Override
    public Long updateCase(ApiCaseModel apiCase) {
        return null;
    }

    @Override
    public Boolean deleteCase(Long caseId) {
        return null;
    }

    @Override
    public List<ApiCaseModel> queryAllApiCase() {
        return null;
    }

    @Override
    public ApiCaseModel queryApiCase(Long caseId) {
        return null;
    }
}
