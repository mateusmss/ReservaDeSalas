package Gerencia.reuniao;
import java.util.*;
import java.time.*;

public class GerenciadorDeSalas
{
    private List<Sala> listaDeSalas = new ArrayList<>();

    //Construtor
    public GerenciadorDeSalas()
    {

    }

//---------------------------------------------------------
    public void adicionaSalaChamada(String nome, int capacidadeMaxima, String descricao)
    {
        Sala tmp_sala = new Sala(nome, capacidadeMaxima, descricao);
        listaDeSalas.add(tmp_sala);
    }

//---------------------------------------------------------
    public void removeSalaChamada(String nomeDaSala)
    {
        for(Sala s : listaDeSalas)
        {
            if(s.getNome().equals(nomeDaSala))
                listaDeSalas.remove(s);
        }
    }

//---------------------------------------------------------
    public List<Sala> listaDeSalas()
    {
        return listaDeSalas;
    }

//---------------------------------------------------------
    public void adicionaSala(Sala novaSala)
    {
        listaDeSalas.add(novaSala);
    }

//---------------------------------------------------------
    public Reserva reservaSalaChamada(String nomeDaSala, LocalDateTime
                                      dataInicial, LocalDateTime dataFinal)
    {
        for(Sala s : listaDeSalas)
        {
            if(s.getNome().equals(nomeDaSala))
            {
                Reserva reserva = new Reserva(s, null);
                reserva.setInicio(dataInicial);;
                reserva.setFim(dataFinal);
                return reserva;
            }

        }

        return null;
    }

//---------------------------------------------------------
    public void cancelaReserva(Reserva cancelada)
    {

    }
}