package com.lazovic.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lazovic.demo.model.Ticket;
import com.lazovic.demo.repository.TicketRepository;

@Service
public class TicketService {

	private Logger LOG = LoggerFactory.getLogger(TicketService.class);

	@Autowired
	TicketRepository iTicketRepository;

	public Ticket save(Ticket ticket) {
		return iTicketRepository.save(ticket);
	}

	public Ticket getId(Long id) {
		LOG.info("Getting the product with given id:" + id);
		return iTicketRepository.findOneByTicketId(id);

	}

	public Iterable<Ticket> getAllBookedTickets() {
		LOG.info("Getting the all products");
		return iTicketRepository.findAll();

	}

	public Ticket saveTicket(Ticket ticket) {
		Ticket ticketToSave;
		try {
			LOG.info("Saving ticket...");
			ticketToSave = iTicketRepository.save(ticket);
			return ticketToSave;
		} catch (Exception e) {
			LOG.error("An error occurred during product saving:" + e.getMessage());
		}
		return new Ticket();

	}

	public Ticket updateTicket(Ticket ticketToUpdate, Long id) {
		Ticket foundTicket = iTicketRepository.findOneByTicketId(id);
		try {
			foundTicket.setPassengerName(ticketToUpdate.getPassengerName());
			foundTicket.setBookingDate(ticketToUpdate.getBookingDate());
			foundTicket.setSourceStation(ticketToUpdate.getSourceStation());
			foundTicket.setDestStation(ticketToUpdate.getDestStation());
			foundTicket.setEmail(ticketToUpdate.getEmail());
			LOG.info("Succeed update ticket!");

			return iTicketRepository.save(foundTicket);

		} catch (Exception e) {
			LOG.error("An error pccurred during update of ticket" + e.getMessage());
		}
		return ticketToUpdate;
	}

	public void deleteTicket(Long id) {
		try {
			iTicketRepository.deleteById(id);
		} catch (Exception e) {
			LOG.error("An error occurred during deleting of ticket:" + e.getMessage());
		}
	}
}