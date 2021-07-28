package Gerencia.reuniao;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

public class Sala
{
    private String nome;
    private int capacidade;
    private String observacoes;
    private List<Reserva> reservada;

    private LocalDateTime rInicio;
    private LocalDateTime rFim;

    public Sala(String nome, int capacidade, String observacoes){
        this.nome = nome;
        this.capacidade = capacidade;
        this.observacoes = observacoes;
        reservada = new ArrayList<>();
    }
    public String getNome(){
        return this.nome;
    }
    public String getLocal(){
        return this.nome;
    }
    public int getCapacidade(){
        return this.capacidade;
    }
    public String getObservacoes(){
        return this.nome;
    }
    public void liberar(Reserva r)
    {
        for(Reserva s : reservada)
        {
            if(s.getUUID().hashCode() == r.getUUID().hashCode())
            {
                reservada.remove(r);
                return;
            } }

        System.out.println("RESERVA NÃO PODE SER LIBERADA: ela não existe");

    }


    public Reserva getReservada(LocalDateTime inicio, LocalDateTime fim)
    {
        for(Reserva r : reservada)
        {
            if(r.getInicio().isEqual(inicio) && r.getFim().isEqual(fim))
                return r;
        }
        return null;
    }
    
    public boolean isReservada(LocalDateTime inicio, LocalDateTime fim)
    { Reserva tmp = new Reserva(this, null);

        for(Reserva r : reservada)
            if(checkReserva(r, tmp))
            { return true; }

        return false;

    }

    public void setReservada(Reserva reserva){
        boolean ocupado = false;
        for(Reserva r : reservada)
        {
            if(checkReserva(r, reserva))
            {
                System.out.println("Horario já ocupado, não foi possivel reserva-lo");
                return;
            }
            reservada.add(reserva);
        } }


//----------------metodos-privados----------------

    private boolean checkReserva(Reserva r, Reserva s)
    {
        if(r.getInicio().isBefore(s.getFim()) && s.getInicio().isBefore(r.getInicio()))
            return true;
        else if(s.getInicio().isAfter(r.getInicio()) && r.getFim().isBefore(s.getFim()))
            return true;
        else if(s.getInicio().isAfter(r.getInicio()) && s.getFim().isBefore(r.getFim()))
            return true;
        else if(r.getInicio().isAfter(s.getInicio()) && r.getFim().isBefore(s.getFim()))
            return true;
        else return false;
    }

}
