package com.keysoft.datatranx.dao;

import com.keysoft.datatranx.model.Release;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IReleaseDAO extends JpaRepository<Release, Long> {}