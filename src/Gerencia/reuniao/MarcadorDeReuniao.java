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
        Period periodo = Period.between(dataInicial,dataFinal);
        LocalDateTime localDateInicial = dataInicial.atTime(00,00,00);
        LocalDateTime localDateFinal = dataFinal.atTime(23,59, 59);


        for(int j = 0;j < listaParticipantes.size();j++){
            int frequency = Collections.frequency(listaParticipantes, listaParticipantes.get(j).getInicio());
            System.out.println(listaParticipantes.get(j).getInicio());
            System.out.println(frequency);
        }

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
            System.out.println(listaParticipantes.get(i).getParticipante());
            System.out.println(listaParticipantes.get(i).getInicio());
            System.out.println(listaParticipantes.get(i).getFim());
        }
    }
}