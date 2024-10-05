package com.quality.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiCaseModel {
    /**
     * 用例ID
     */
    private Long id;
    /**
     * 用例名称
     */
    private String name;
    /**
     * 用例描述信息
     */
    private String desc;
    /**
     * 请求地址
     */
    private String url;
    /**
     * 请求方式
     */
    private String method;
    /**
     * 请求头
     */
    private Map<String, List<String>> header;
    /**
     * 请求体
     */
    private Map<String, Object> body;
    /**
     * 依赖的case
     */
    private List<Long> depends;

    /**
     * 断言列表  assert_code:200  assert_body:var_name=value  assert_body_not_null:断言结果不为空  assert_body_type:json 断言响应类型为json
     */
    private Map<String, Object> asserts;
}
