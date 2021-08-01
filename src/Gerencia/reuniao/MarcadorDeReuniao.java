package Gerencia.reuniao;
import java.util.*;
import java.time.*;

public class MarcadorDeReuniao
{
    //Construtor

    private List<Participantes> listaParticipantes = new ArrayList<>();
    private List<String> listaParticipantesValidos = new ArrayList<>();
    private List<String> nomes = new ArrayList<>();
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    //--------------------------------------------------------------------------
    public void marcarReuniaoEntre(LocalDate dataInicial, LocalDate dataFinal, Collection<String> listaDeParticipantes)
    {
        nomes.addAll(listaDeParticipantes);
        this.dataInicial = dataInicial;
        this.dataFinal = dataFinal;
        mostraSobreposicao();
    }
//--------------------------------------------------------------------------

    public void indicaDisponibilidadeDe(String participante, LocalDateTime inicio,LocalDateTime fim){
        Participantes p = new Participantes(inicio, fim, participante);
        listaParticipantes.add(p);
        Collections.sort(listaParticipantes);
    }
    public List<String> participantesValidos(LocalDateTime inicioMarcada,LocalDateTime fimMarcada){
        for(int i = 0;i < listaParticipantes.size();i++){
            if(inicioMarcada.isBefore(listaParticipantes.get(i).getInicio()) && fimMarcada.isAfter(listaParticipantes.get(i).getFim())){
                this.listaParticipantesValidos.add(listaParticipantes.get(i).getParticipante());
            }
        }
        if(listaParticipantesValidos == null){
            System.out.println("Nenhum participante consegue participar nesta data");
            return listaParticipantesValidos;
        }
        return listaParticipantesValidos;

    }
//--------------------------------------------------------------------------

    public void mostraSobreposicao(){
        System.out.println("=========== Relatório de Participantes ===========");
        System.out.println("                                                   ");
        System.out.println("--------------- Intervalos da Reunião -----------");
        System.out.print("O intervalo de marcação de possíveis é: " +this.dataInicial.getDayOfMonth()+ "/" +this.dataInicial.getMonthValue() +"/" + this.dataInicial.getYear());
        System.out.println(" e " +this.dataFinal.getDayOfMonth()+ "/" +this.dataFinal.getMonthValue() +"/" + this.dataFinal.getYear() );
        System.out.println("                                                   ");
        System.out.println("--------------- Informações Detalhadas -----------");
        System.out.println("                                                   ");

        for(int i = 0;i <listaParticipantes.size();i++){
            System.out.print("O participante com email: "+listaParticipantes.get(i).getParticipante()+" tem preferencia pelas seguintes datas: ");
            System.out.print(listaParticipantes.get(i).getInicio().getDayOfMonth() +"/");
            System.out.print(listaParticipantes.get(i).getInicio().getMonthValue()+"/");
            System.out.print(listaParticipantes.get(i).getInicio().getYear()+" e ");
            System.out.print(listaParticipantes.get(i).getFim().getDayOfMonth() +"/");
            System.out.print(listaParticipantes.get(i).getFim().getMonthValue()+"/");
            System.out.print(listaParticipantes.get(i).getFim().getYear()+" entre ");
            System.out.print(listaParticipantes.get(i).getInicio().getHour()+":"+ listaParticipantes.get(i).getInicio().getMinute()+" e ");
            System.out.println(listaParticipantes.get(i).getFim().getHour()+":"+ listaParticipantes.get(i).getFim().getMinute()+" ");
            System.out.println("                                                   ");
        }
    }
}