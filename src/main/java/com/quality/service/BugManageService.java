package com.quality.service;

import com.quality.model.BugModel;

public interface BugManageService {
    Long createBug(BugModel bug);

    void updateBug(BugModel bug);

    void deleteBug(Long bugId);

    BugModel queryBug();
}
