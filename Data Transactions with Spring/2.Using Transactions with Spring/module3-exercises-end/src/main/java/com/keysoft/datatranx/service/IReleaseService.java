package com.keysoft.datatranx.service;

import com.keysoft.datatranx.model.Release;
import com.keysoft.datatranx.model.Ticket;

import java.util.List;

public interface IReleaseService {
    void addRelease(Release release);
    Release getRelease(int releaseId);
    Release scheduleRelease(Release release);
}
