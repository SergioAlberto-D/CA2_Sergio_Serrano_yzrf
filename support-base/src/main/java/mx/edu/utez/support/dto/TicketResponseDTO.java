package mx.edu.utez.support.dto;

import mx.edu.utez.support.model.Ticket;

public record TicketResponseDTO(
        Long id,
        String title,
        String description,
        Boolean open
) {
    public static TicketResponseDTO fromEntity(Ticket ticket) {
        return new TicketResponseDTO(
                ticket.getId(),
                ticket.getTitle(),
                ticket.getDescription(),
                ticket.getOpen()
        );
    }
}
