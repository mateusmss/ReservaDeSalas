package Gerencia.reuniao;
import java.util.*;
import java.time.*;

public class MarcadorDeReuniao
{
    //Construtor

    List<Participantes> listaParticipantes = new ArrayList<>();
    List<Participantes> listaNaoDisponiveis = new ArrayList<>();
    List<Participantes> listaDisponiveis = new ArrayList<>();

    //--------------------------------------------------------------------------
    public void marcarReuniaoEntre(LocalDate dataInicial, LocalDate dataFinal, Collection<String> listaDeParticipantes)
    {
        System.out.println("A reunião pode ser marcada entre" +dataInicial+ " e " +dataFinal);
        System.out.println("Os possíveis participantes são: " +listaDeParticipantes);
        mostraSobreposicao();
    }
//--------------------------------------------------------------------------

    public void indicaDisponibilidadeDe(String participante, LocalDateTime inicio,LocalDateTime fim){
        Participantes p = new Participantes(inicio, fim, participante);
        listaParticipantes.add(p);
        Collections.sort(listaParticipantes);
    }

//--------------------------------------------------------------------------

    public void mostraSobreposicao(){
        System.out.println("Os participantes que poderão participar com seus respectivos horários: ");
        for(int i = 0;i <listaParticipantes.size();i++){
            System.out.println("O participante"+listaParticipantes.get(i).getParticipante()+"pode se reunir entre os dias: ");
            System.out.println(listaParticipantes.get(i).getInicio());
            System.out.println(listaParticipantes.get(i).getFim());
        }
    }
}