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
        System.out.println(periodo);
        LocalDateTime localDateInicial = dataInicial.atTime(00,00,00);
        LocalDateTime localDateFinal = dataFinal.atTime(23,59, 59);

        /* Verificação se as datas requeridas estão no intervalo de datas pré definidas*/
        for(int i = 0; i < listaParticipantes.size();i++){
            if(localDateInicial.isBefore(listaParticipantes.get(i).getInicio())){
                System.out.println("A data da reserva requerida é anterior a marcada");
                listaNaoDisponiveis.add(listaParticipantes.get(i));
                System.out.println(localDateInicial);
                System.out.println(localDateFinal);
            }
        /*Verificação se a data do candidato passa a pré escolhida*/
            System.out.println(localDateInicial.isBefore(listaParticipantes.get(i).getInicio()));
        }
        for(int i = 0;i < listaParticipantes.size();i++){
            if(localDateFinal.isBefore(listaParticipantes.get(i).getFim())){
                System.out.println("A data da reserva requerida é posterior a marcada");
                listaNaoDisponiveis.add(listaParticipantes.get(i));
            }
        }
        listaParticipantes.removeAll(listaNaoDisponiveis);

        /*Verificação se as datas de inicio e fim dos participantes são comitentes ---> falta finalizar pra acabar essa parte*/
        for(int i = 0;i <= listaParticipantes.size();i++){
            /*if(){

            }*/
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