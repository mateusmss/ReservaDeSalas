package Gerencia.reuniao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Random;
import java.util.UUID;
public class Reserva
{

    //Parametros-----------------------------------------------
    private UUID id;
    private Collection<String> participantes;
    private Sala sala;
    private LocalDateTime inicio, fim;
    //Construtor-----------------------------------------------
    public Reserva(Sala sala, Collection<String> participantes)
    {
        this.sala = sala;
        this.participantes = participantes;

        this.id = UUID.randomUUID();

    }

//---------------------------------------------------------------
    public void setSala(Sala sala){ this.sala = sala; }

//---------------------------------------------------------------
    public void setParticipantes(Collection<String> participantes)
    { this.participantes = participantes; }

//---------------------------------------------------------------
    public Sala getSala(){ return sala; }

//---------------------------------------------------------------
    public Collection<String> getParticipantes(){ return participantes; }
    public void setInicio(LocalDateTime pInicio){ inicio = pInicio; }
    public void setFim(LocalDateTime pFim){ fim = pFim; }
    
    public LocalDateTime getInicio(){ return inicio; }
    public LocalDateTime getFim(){ return fim; }
    public UUID getUUID(){ return this.id; }

    public void print()
    {
        System.out.println("Sala reservada entre os dias "+inicio.getDayOfMonth()+"/"+inicio.getMonth());
        System.out.print(" e "+fim.getDayOfMonth()+"/"+fim.getMonth());

        System.out.println("entre os horarios: "+inicio.getHour()+":"+inicio.getMinute());
        System.out.print(" e "+fim.getHour()+":"+fim.getMinute());
    }


}
