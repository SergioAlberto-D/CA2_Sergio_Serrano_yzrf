package mx.edu.utez.support.controller;

import java.util.List;

import jakarta.validation.Valid;
import mx.edu.utez.support.dto.TicketResponseDTO;
import mx.edu.utez.support.exception.ResourceNotFoundException;
import mx.edu.utez.support.model.Ticket;
import mx.edu.utez.support.service.TicketService;
import org.apache.coyote.Request;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService service;

    public TicketController(TicketService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<TicketResponseDTO>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    // Las operaciones solicitadas durante la evaluacion se implementan aqui.
    @PostMapping
    public ResponseEntity<TicketResponseDTO> save(@Valid @RequestBody TicketResponseDTO ticket) {
        Ticket newTicket = service.save(toEntity(ticket), ticket.id());
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponse(newTicket));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<TicketResponseDTO> updatePrice(@PathVariable Long id,
                                                         @RequestBody Request request){
        Ticket ticket = service.findById(id).orElseThrow(
                ()->
                        new ResourceNotFoundException("Ticket not found with id: " + id)
        );

        return ResponseEntity.ok(toResponse(ticket));
    }
}


