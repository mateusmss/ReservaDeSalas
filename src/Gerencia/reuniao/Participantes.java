package Gerencia.reuniao;

import java.time.LocalDateTime;


public class Participantes implements Comparable<Participantes>{
    private String idParticipante;
    private LocalDateTime inicio, fim;

    public Participantes(LocalDateTime inicio, LocalDateTime fim, String participante)
    {
        this.idParticipante = participante;
        this.inicio = inicio;
        this.fim = fim;
    }
    
    public LocalDateTime getInicio(){
        return this.inicio;
    }
    public LocalDateTime getFim(){

        return this.fim;
    }
    public String getParticipante(){

        return this.idParticipante;
    }

    public int compareTo(Participantes o){
        int valor = inicio.compareTo(o.inicio);
        return (valor != 0 ? valor : 1);
    }

}
