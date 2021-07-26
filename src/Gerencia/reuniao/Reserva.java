package Gerencia.reuniao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;

public class Reserva
{

    //Parametros-----------------------------------------------
    Collection<String> participantes;
    Sala sala;
    LocalDateTime inicio, fim;
    //Construtor-----------------------------------------------
    public Reserva(Sala sala, Collection<String> participantes)
    {
        this.sala = sala;
        this.participantes = participantes;
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


}
