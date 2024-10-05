package com.quality.mapper;

import com.quality.model.ApiCaseModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApiAutoMapper {
    List<ApiCaseModel> queryApiCase(List<Long> caseIds);

    ApiCaseModel queryApiCaseById(Long caseId);

    Long insertApiCase(ApiCaseModel apiCase);
}
