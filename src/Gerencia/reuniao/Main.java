package Gerencia.reuniao;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.*;

public class Main
{
    public static void main(String args[]){
        //private static List<Participantes> listaAux;
        List<String> participantes = new ArrayList<>();
        LocalDate dataInicial;
        LocalDate dataFinal;
        LocalDateTime dataInicialParticipantes;
        LocalDateTime dataFinalParticipantes;
        LocalTime horarioInicial;
        LocalTime horarioFinal;
        boolean verificacao = true;
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Digite seu nome:");
        String nome = scanner.next();

        System.out.println("Digite a data de inicio da reunião. Formato: dia/mês/ano");
        String dataInicialScanner = scanner.next();


        LocalDate dataInicialteste = LocalDate.parse(dataInicialScanner, formatar);

        dataInicial = LocalDate.of(dataInicialteste.getYear(),dataInicialteste.getMonth(),dataInicialteste.getDayOfMonth());

        System.out.println("Digite a data que quer fechar a reunião. Formato: dia/mês/ano");
        String dataFinalScanner = scanner.next();
        LocalDate dataFinalteste = LocalDate.parse(dataFinalScanner, formatar);

        dataFinal = LocalDate.of(dataFinalteste.getYear(),dataFinalteste.getMonth(),dataFinalteste.getDayOfMonth());

        System.out.println("A data inicial é: " + dataInicial + ", a data final é: " +dataFinal);
        MarcadorDeReuniao marcar = new MarcadorDeReuniao();


        while(verificacao){
            System.out.println("Digite seu email: ");
            nome = scanner.next();

            System.out.println("Digite a data de inicio que você deseja fazer uma reserva. Formato: dia/mês/ano");
            dataInicialScanner = scanner.next();
            dataInicialteste = LocalDate.parse(dataInicialScanner, formatar);
            dataInicial = LocalDate.of(dataInicialteste.getYear(),dataInicialteste.getMonth(),dataInicialteste.getDayOfMonth());


            System.out.println("Digite o horário de inicio que você deseja fazer uma reserva. Formato: hora:minuto");
            String horaInicialScanner = scanner.next();
            DateTimeFormatter formatarHora = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime horaInicialteste = LocalTime.parse(horaInicialScanner, formatarHora);
            dataInicialParticipantes = LocalDateTime.of(dataInicial, horaInicialteste);
            System.out.println(dataInicialParticipantes);


            System.out.println("Digite a data final que você deseja fazer uma reserva. Formato: dia/mês/ano hora:minuto");
            dataFinalScanner = scanner.next();
            dataFinalteste = LocalDate.parse(dataFinalScanner, formatar);
            dataFinal = LocalDate.of(dataFinalteste.getYear(),dataFinalteste.getMonth(),dataFinalteste.getDayOfMonth());

            System.out.println("Digite o horário de fim que você deseja fazer uma reserva. Formato: hora:minuto:segundo");
            String horaFinalScanner = scanner.next();
            LocalTime horaFinalteste = LocalTime.parse(horaFinalScanner, formatarHora);
            dataFinalParticipantes = LocalDateTime.of(dataFinal, horaFinalteste);
            System.out.println(dataFinalParticipantes);

            System.out.println("Digite 0 caso queira progressir em adicionar participantes ou 1 caso queira encerrar as marcações e ver resultados");
            marcar.indicaDisponibilidadeDe(nome,dataInicialParticipantes, dataFinalParticipantes);

            int teste = scanner.nextInt();
            if(teste == 1){
                verificacao = false;
            }

        }
        marcar.marcarReuniaoEntre(dataInicial,dataFinal,participantes);
        System.out.println("Resultados: ");


    }

}
