package com.keysoft.datatranx.dao;


import com.keysoft.datatranx.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IApplicationDAO extends JpaRepository<Application, Long> {}
