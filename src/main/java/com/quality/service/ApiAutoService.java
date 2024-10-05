package com.quality.service;

import com.quality.model.ApiCaseModel;

import java.util.List;

public interface ApiAutoService {
    Long createCase(ApiCaseModel apiCase);

    Long runCase(Long caseId);


    Long updateCase(ApiCaseModel apiCase);

    Boolean deleteCase(Long caseId);

    List<ApiCaseModel> queryAllApiCase();

    ApiCaseModel queryApiCase(Long caseId);
}
