package com.keysoft.datatranx.dao;

import com.keysoft.datatranx.model.Release;
import com.keysoft.datatranx.model.Ticket;

import java.util.List;

public interface IReleaseDAO
{
    void addRelease(Release release);
    Release getReleaseId(int releaseId);
}
