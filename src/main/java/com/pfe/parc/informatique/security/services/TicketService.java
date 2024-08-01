package com.pfe.parc.informatique.security.services;

import com.pfe.parc.informatique.entities.Status;
import com.pfe.parc.informatique.entities.Ticket;
import com.pfe.parc.informatique.entities.User;
import com.pfe.parc.informatique.repository.TicketRepository;
import com.pfe.parc.informatique.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository; // Add user repository

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public Optional<Ticket> findById(Long id) {
        return ticketRepository.findById(id);
    }

    public Ticket save(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public void deleteById(Long id) {
        ticketRepository.deleteById(id);
    }

    public Ticket createTicket(Ticket ticket) {
        // Get the current user from the security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        // Find the user entity
        User user = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Set the user and default status
        ticket.setUser(user);
        ticket.setStatus(Status.NEW);

        return ticketRepository.save(ticket);
    }

    public Ticket updateTicketStatus(Long ticketId, Status status) {
        Ticket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        ticket.setStatus(status);
        return ticketRepository.save(ticket);
    }
}
