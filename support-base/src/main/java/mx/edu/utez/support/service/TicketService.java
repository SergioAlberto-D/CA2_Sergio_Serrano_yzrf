package mx.edu.utez.support.service;

import java.util.List;
import java.util.Optional;
import java.util.prefs.PreferenceChangeEvent;

import mx.edu.utez.support.dto.TicketResponseDTO;
import mx.edu.utez.support.exception.ResourceNotFoundException;
import mx.edu.utez.support.model.Ticket;
import mx.edu.utez.support.repository.TicketRepository;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    private final TicketRepository repository;

    public TicketService(TicketRepository repository) {
        this.repository = repository;
    }

    public List<TicketResponseDTO> findAll() {
        return repository.findAll().stream()
                .map(TicketResponseDTO::fromEntity)
                .toList();
    }

    // Las operaciones solicitadas durante la evaluacion se implementan aqui.

    ///public Optional < Ticket > findById (Long Id)

      public Optional <Ticket> findById(Long id){
        Ticket ticketBd= repository.findById(id).orElseThrow(
          ()->
                  new ResourceNotFoundException("Ticket not found whit id: " + id)
        );
          ticketBd.setTitle();
          ticketBd.setDescription();
          ticketBd.setOpen();
          return Ticket.of(repository.save(ticketBd));
      }

      public Ticket save(Ticket ticket){
        ticket.setTitle();
        ticket.setDescription();
        ticket.setOpen();
        return repository.save(ticket);
      }


}
