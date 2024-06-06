package com.pfe.parc.informatique.controller;

import com.pfe.parc.informatique.entities.Status;
import com.pfe.parc.informatique.entities.Ticket;
import com.pfe.parc.informatique.repository.TicketRepository;
import com.pfe.parc.informatique.security.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tickets")
@CrossOrigin("*")
public class TicketController {
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private TicketService ticketService;

    @PostMapping("/tickets")
    public Ticket createTicket(@RequestBody Ticket ticket) {
        ticket.setStatus(Status.PENDING);
        ticket.setCreatedDate(new Date());
        return ticketService.save(ticket);
    }

    @GetMapping("/tickets")
    public List<Ticket> getAllTickets() {
        return ticketService.findAll();
    }

    @GetMapping("/tickets/{id}")
    public Optional<Ticket> getTicketById(@PathVariable Long id) {
        return ticketService.findById(id);
    }

    @PutMapping("/tickets/{id}")
    public Ticket updateTicket(@PathVariable Long id, @RequestBody Ticket ticketDetails) {
        Ticket ticket = ticketService.findById(id).orElseThrow(() -> new RuntimeException("Ticket not found"));
        ticket.setTitle(ticketDetails.getTitle());
        ticket.setDescription(ticketDetails.getDescription());
        ticket.setStatus(ticketDetails.getStatus());
        ticket.setUpdatedDate(new Date());
        return ticketService.save(ticket);
    }

    @DeleteMapping("/tickets/{id}")
    public void deleteTicket(@PathVariable Long id) {
        ticketService.deleteById(id);
    }
}
