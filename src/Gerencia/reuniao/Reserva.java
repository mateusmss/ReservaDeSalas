package Gerencia.reuniao;

import java.time.LocalDateTime;
import java.util.Collection;
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
    public void setInicio(LocalDateTime pInicio){ inicio = pInicio; }
    public void setFim(LocalDateTime pFim){ fim = pFim; }
    
    public LocalDateTime getInicio(){ return inicio; }
    public LocalDateTime getFim(){ return fim; }
    public UUID getUUID(){ return this.id; }

    public void print()
    {
        System.out.print("Sala reservada entre os dias "+inicio.getDayOfMonth()+"/"+inicio.getMonth());
        System.out.println(" e "+fim.getDayOfMonth()+"/"+fim.getMonth());

        System.out.print("entre os horarios: "+inicio.getHour()+":"+inicio.getMinute());
        System.out.println(" e "+fim.getHour()+":"+fim.getMinute());
    }


}
