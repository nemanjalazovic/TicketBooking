package com.lazovic.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lazovic.demo.model.Ticket;
import com.lazovic.demo.service.TicketService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/ticket")
public class TicketController {

	@Autowired
	TicketService ticketService;

	@ApiOperation(value = "View a list of available employees")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Ticket> getTicket(@PathVariable("id") Long id) {
		Ticket ticket = ticketService.getId(id);
		if (ticket == null) {
			return new ResponseEntity<Ticket>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Ticket>(ticket, HttpStatus.OK);

	}

	@RequestMapping(value = "", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<Ticket> getAllBookedTickets() {
		return ticketService.getAllBookedTickets();
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Ticket saveTicket(@RequestBody Ticket ticket) {
		return ticketService.saveTicket(ticket);
	}

	@RequestMapping(path = "{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Ticket updateTicket(@RequestBody Ticket ticketToUpdate, @PathVariable(name = "id") Long id) {
		return ticketService.updateTicket(ticketToUpdate, id);
	}

	@RequestMapping(path = "{id}", method = RequestMethod.DELETE)
	public void deleteTicket(@PathVariable(name = "id") Long id) {
		ticketService.deleteTicket(id);
	}

	/*
	 * @RequestMapping(value = "/{sourceStation}", method = RequestMethod.GET)
	 * public ResponseEntity<Ticket> search(@PathVariable("sourceStation") String
	 * sourceStation) { Ticket ticket =
	 * ticketService.findBySourceStation(sourceStation); if (ticket == null) {
	 * return new ResponseEntity<Ticket>(HttpStatus.NOT_FOUND); } return new
	 * ResponseEntity<Ticket>(ticket, HttpStatus.OK);
	 * 
	 * }
	 */

}
