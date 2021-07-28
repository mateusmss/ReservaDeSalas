package Gerencia.reuniao;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.*;

public class Main
{
    public static void main(String args[]){
        /* Criação de variáveis e lista*/
        List<String> listaParticipantes = new ArrayList<>();
        //private static List<Participantes> listaAux;
        List<String> participantes = new ArrayList<>();
        LocalDate dataInicialMonitor;
        LocalDate dataFinalMonitor;
        LocalDateTime dataInicialParticipantes;
        LocalDateTime dataFinalParticipantes;
        LocalTime horarioInicial;
        LocalTime horarioFinal;
        LocalDate dataInicial;
        LocalDate dataFinal;
        LocalDate agora = LocalDate.now();
        boolean verificacao = true;//variavel de verificação
        Scanner scanner = new Scanner(System.in); //criando scanner
        DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //formatando o tipo de data
        /* *************************************************** */

        /* Parte do scanner da data inicial e final ditas pelo organizador*/
        System.out.println("Digite seu nome:");
        String nome = scanner.next();

        System.out.println("Digite a data de inicio da reunião. Formato: dia/mês/ano");
        String dataInicialScanner = scanner.next();

        /*
        Verificador de datas iniciais
         */
        LocalDate dataInicialteste = null;
        if(LocalDate.parse(dataInicialScanner, formatar).isBefore(agora)) {
            int tmp = 0;
            System.out.println("Não é possivel marcar eventos no passado" +
                    " digite uma data valida!");
            dataInicialScanner = scanner.next();
            while (LocalDate.parse(dataInicialScanner, formatar).isBefore(agora)){
                System.out.println("Não é possivel marcar eventos no passado" +
                        " digite uma data valida!");
                dataInicialScanner = scanner.next();
                tmp++;

                if(tmp > 10){ throw new IllegalArgumentException("Valor irresolvivel, fim de execução"); }
            }
            dataInicialteste   = LocalDate.parse(dataInicialScanner, formatar);
        }
        else dataInicialteste   = LocalDate.parse(dataInicialScanner, formatar);

        dataInicial = LocalDate.of(dataInicialteste.getYear(),dataInicialteste.getMonth(),dataInicialteste.getDayOfMonth());
        dataInicialMonitor = LocalDate.of(dataInicialteste.getYear(),dataInicialteste.getMonth(),dataInicialteste.getDayOfMonth());
        // -------------------------------------------------------------

        /*
        Verificador de datas finais
         */

        System.out.println("Digite a data que quer fechar a reunião. Formato: dia/mês/ano");
        LocalDate dataFinalteste = null;
        String dataFinalScanner = scanner.next();
        if(LocalDate.parse(dataFinalScanner, formatar).isBefore(agora)) {
            int tmp = 0;
            System.out.println("Não é possivel marcar eventos no passado" +
                    " digite uma data valida!");
            dataFinalScanner = scanner.next();
            while (LocalDate.parse(dataFinalScanner, formatar).isBefore(agora)){
                System.out.println("Não é possivel marcar eventos no passado" +
                        " digite uma data valida novamente!");
                dataFinalScanner = scanner.next();
                tmp++;

                if(tmp > 10){ throw new IllegalArgumentException("Valor irresolvivel, fim de execução"); }
            }
            dataFinalteste = LocalDate.parse(dataFinalScanner, formatar);
        }
        else dataFinalteste = LocalDate.parse(dataFinalScanner, formatar);


        dataFinalMonitor = LocalDate.of(dataFinalteste.getYear(),dataFinalteste.getMonth(),dataFinalteste.getDayOfMonth());

        System.out.println("A data inicial é: " + dataInicialMonitor + ", a data final é: " +dataFinalMonitor);
        MarcadorDeReuniao marcar = new MarcadorDeReuniao();

        /* ****************************************************** */

        /*Organizador insere os emails dos participantes*/
        while(verificacao){
            System.out.println("Digite os participantes: ");
            nome = scanner.next();
            listaParticipantes.add(nome);
            System.out.println("Digite 0 caso queira progressir em adicionar participantes ou 1 caso queira encerrar");
            int teste = scanner.nextInt();
            if(teste == 1){
                verificacao = false;
            }
        }
        /* ******************************************************************************************* */

        /* Looping onde os participantes colocam seu email de verificação e possiveis horários disponíveis dentro de um periodo */
        verificacao = true;
        while(verificacao){
            int i = 0;
            //Nome do participante
            System.out.println("Olá: " + listaParticipantes.get(i));
            //Data Inicial
            System.out.println("Digite a data de inicio que você deseja fazer uma reserva. Formato: dia/mês/ano");
            dataInicialScanner = scanner.next();
            dataInicialteste = LocalDate.parse(dataInicialScanner, formatar);
            dataInicial = LocalDate.of(dataInicialteste.getYear(),dataInicialteste.getMonth(),dataInicialteste.getDayOfMonth());

            //Horário inicial disponivel
            System.out.println("Digite o horário de inicio que você tem disponível para a reunião. Formato: hora:minuto:segundo");
            String horaInicialScanner = scanner.next();
            DateTimeFormatter formatarHora = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalTime horaInicialteste = LocalTime.parse(horaInicialScanner, formatarHora);
            dataInicialParticipantes = LocalDateTime.of(dataInicialteste, horaInicialteste);
            System.out.println(dataInicialParticipantes);

            //data final disponivel
            System.out.println("Digite a data final que você deseja fazer uma reserva. Formato: dia/mês/ano");
            dataFinalScanner = scanner.next();
            dataFinalteste = LocalDate.parse(dataFinalScanner, formatar);
            dataFinal = LocalDate.of(dataFinalteste.getYear(),dataFinalteste.getMonth(),dataFinalteste.getDayOfMonth());


            //Horário final disponível
            System.out.println("Digite o horário de fim que você deseja fazer uma reserva. Formato: hora:minuto:segundo");
            String horaFinalScanner = scanner.next();
            LocalTime horaFinalteste = LocalTime.parse(horaFinalScanner, formatarHora);
            dataFinalParticipantes = LocalDateTime.of(dataFinal, horaFinalteste);
            System.out.println(dataFinalParticipantes);

            //Pergunta se quer prosseguir em adicionar participantes
            System.out.println("Digite 0 caso queira progressir em adicionar participantes ou 1 caso queira encerrar as marcações e ver resultados");
            marcar.indicaDisponibilidadeDe(listaParticipantes.get(i),dataInicialParticipantes, dataFinalParticipantes);

            //Teste de verificação
            System.out.println("Digite 0 caso queira progressir em adicionar datas ou 1 caso queira encerrar");
            int teste = scanner.nextInt();
            if(teste == 1){
                verificacao = false;
            }

        }
        /* ************************************************************************************ */

        /* Passando as datas para o MarcadorDeReunião e mostrando resultados */
        marcar.marcarReuniaoEntre(dataInicialMonitor,dataFinalMonitor,participantes);
        System.out.println("Resultados: ");

        /* ************************************************************************************ */

    }

}
