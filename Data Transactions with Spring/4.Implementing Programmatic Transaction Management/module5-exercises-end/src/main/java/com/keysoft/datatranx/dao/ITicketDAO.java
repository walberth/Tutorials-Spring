package com.keysoft.datatranx.dao;


import com.keysoft.datatranx.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITicketDAO extends JpaRepository<Ticket, Long> {}

