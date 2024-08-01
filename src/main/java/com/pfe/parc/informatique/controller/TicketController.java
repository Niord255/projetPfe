package com.pfe.parc.informatique.controller;

import com.pfe.parc.informatique.entities.Ticket;
import com.pfe.parc.informatique.entities.Material;
import com.pfe.parc.informatique.security.services.TicketService;
import com.pfe.parc.informatique.security.services.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private MaterialService materialService;

    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Long id) {
        Optional<Ticket> ticket = ticketService.findById(id);
        return ticket.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public List<Ticket> getTicketsByUserId(@PathVariable Long userId) {
        return ticketService.findByUserId(userId);
    }

    @GetMapping("/material/{materialId}")
    public List<Ticket> getTicketsByMaterialId(@PathVariable Long materialId) {
        return ticketService.findByMaterialId(materialId);
    }

    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
        Optional<Material> material = materialService.findById(ticket.getMaterial().getId());
        if (material.isPresent()) {
            ticket.setMaterial(material.get());
            return ResponseEntity.ok(ticketService.save(ticket));
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Ticket> updateTicket(@PathVariable Long id, @RequestBody Ticket updatedTicket) {
        Optional<Ticket> ticket = ticketService.findById(id);
        if (ticket.isPresent()) {
            Optional<Material> material = materialService.findById(updatedTicket.getMaterial().getId());
            if (material.isPresent()) {
                updatedTicket.setMaterial(material.get());
                updatedTicket.setId(id);
                return ResponseEntity.ok(ticketService.save(updatedTicket));
            } else {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        Optional<Ticket> ticket = ticketService.findById(id);
        if (ticket.isPresent()) {
            ticketService.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
