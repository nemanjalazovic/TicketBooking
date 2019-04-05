package com.lazovic.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lazovic.demo.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

	public Ticket findOneByTicketId(Long id);

	public Ticket findOneBySourceStation(String id);

}