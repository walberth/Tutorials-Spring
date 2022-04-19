package com.keysoft.datatranx.service;

import com.keysoft.datatranx.model.Release;

import java.util.Optional;

public interface IReleaseService {
    void addRelease(Release release);
    Optional<Release> getRelease(int releaseId);
    Release scheduleRelease(Release release);
}
