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
        boolean verificacao = true;//variavel de verificação
        Scanner scanner = new Scanner(System.in); //criando scanner
        DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //formatando o tipo de data
        /* ************************************************** */

        /* Parte do scanner da data inicial e final ditas pelo organizador*/
        System.out.println("Digite seu nome:");
        String nome = scanner.next();

        System.out.println("Digite a data de inicio da reunião. Formato: dia/mês/ano");
        String dataInicialScanner = scanner.next();


        LocalDate dataInicialteste = LocalDate.parse(dataInicialScanner, formatar);

        dataInicialMonitor = LocalDate.of(dataInicialteste.getYear(),dataInicialteste.getMonth(),dataInicialteste.getDayOfMonth());

        System.out.println("Digite a data que quer fechar a reunião. Formato: dia/mês/ano");
        String dataFinalScanner = scanner.next();
        LocalDate dataFinalteste = LocalDate.parse(dataFinalScanner, formatar);

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

            //Horário inicial disponivel
            System.out.println("Digite o horário de inicio que você tem disponível para a reunião. Formato: hora:minuto:segundo");
            String horaInicialScanner = scanner.next();
            DateTimeFormatter formatarHora = DateTimeFormatter.ofPattern("HH:mm:ss");
            LocalDateTime horaInicialteste = LocalDateTime.parse(horaInicialScanner, formatarHora);

            //Horário final disponível
            System.out.println("Digite o horário de fim que vocêtem disponível para a reunião. Formato: hora:minuto:segundo");
            String horaFinalScanner = scanner.next();
            LocalDateTime horaFinalteste = LocalDateTime.parse(horaFinalScanner, formatarHora);

            //Pergunta se quer prosseguir em adicionar participantes
            System.out.println("Digite 0 caso queira progressir em adicionar participantes ou 1 caso queira encerrar as marcações e ver resultados");
            marcar.indicaDisponibilidadeDe(listaParticipantes.get(i),horaInicialteste, horaFinalteste);

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
