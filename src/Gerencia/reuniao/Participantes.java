package Gerencia.reuniao;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Participantes {
    String participante;
    LocalDateTime inicio, fim;

    public Participantes(LocalDateTime inicio, LocalDateTime fim, String participante)
    {
        this.participante = participante;
        this.inicio = inicio;
        this.fim = fim;
    }
    
    public LocalDateTime getInicio(){ return inicio; }
    public LocalDateTime getFim(){ return fim; }
    public String getParticipante(){ return participante; }
    
    


}
