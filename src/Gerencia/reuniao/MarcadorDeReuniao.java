package Gerencia.reuniao;
import java.util.*;
import java.time.*;


public class MarcadorDeReuniao
{
    //Construtor
    List<String> listaDeParticipantes;
    static List<Participantes> listaAux;

    public MarcadorDeReuniao(List<Participantes> listaAux)
    {
        listaDeParticipantes = new ArrayList<>();
        this.listaAux = listaAux;
    }

//--------------------------------------------------------------------------
    public void marcarReuniaoEntre(LocalDate dataInicial, LocalDate dataFinal, Collection<String> listaDeParticipantes)
    {
        
    }
//--------------------------------------------------------------------------

    public void indicaDisponibilidadeDe(String participante, LocalDateTime inicio,LocalDateTime fim){
        listaDeParticipantes.add(participante);
        Participantes p = new Participantes(inicio, fim, participante);

    }
//--------------------------------------------------------------------------

    public void mostraSobreposicao(){

    }
}