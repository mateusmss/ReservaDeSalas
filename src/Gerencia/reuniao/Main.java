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
        LocalDate dataInicial;
        LocalDate agora = LocalDate.now();
        boolean verificacao = true;//variavel de verificação
        Scanner scanner = new Scanner(System.in); //criando scanner
        DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //formatando o tipo de data
        /* ************************************************** */

        /* Parte do scanner da data inicial e final ditas pelo organizador*/
        System.out.println("Digite seu nome:");
        String nome = scanner.next();

        System.out.println("Digite a data de inicio da reunião. Formato: dia/mês/ano");
        String dataInicialScanner = scanner.next();

        /*Verificador de datas iniciais*/
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


        /*Verificador de datas finais*/


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
            System.out.println("Digite 0 caso queira prosseguir em adicionar participantes ou 1 caso queira encerrar");
            int teste = scanner.nextInt();
            if(teste == 1){
                verificacao = false;
            }
        }
        /* ******************************************************************************************* */

        /* Looping onde os participantes colocam seu email de verificação e possiveis horários disponíveis dentro de um periodo */
        verificacao = true;
        int i = 0;
        for(int k = 0;k < listaParticipantes.size();k++){
            //Nome do participante

            //Data de disponibilidade
            System.out.println("Digite a data que você tem disponibilidade para a reunião. Formato: dia/mês/ano");
            dataInicialScanner = scanner.next();
            dataInicialteste = LocalDate.parse(dataInicialScanner, formatar);
            dataInicial = LocalDate.of(dataInicialteste.getYear(),dataInicialteste.getMonth(),dataInicialteste.getDayOfMonth());

            //Horário inicial disponivel
            System.out.println("Digite o horário de inicio que você tem disponível para a reunião. Formato: hora:minuto:segundo");
            String horaInicialScanner = scanner.next();
            DateTimeFormatter formatarHora = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime horaInicialteste = LocalTime.parse(horaInicialScanner, formatarHora);
            dataInicialParticipantes = LocalDateTime.of(dataInicialteste, horaInicialteste);


            //Horário final disponível
            System.out.println("Digite o horário de fim que você deseja fazer uma reserva. Formato: hora:minuto:segundo");
            String horaFinalScanner = scanner.next();
            LocalTime horaFinalteste = LocalTime.parse(horaFinalScanner, formatarHora);
            dataFinalParticipantes = LocalDateTime.of(dataInicial, horaFinalteste);


            //Pergunta se quer prosseguir em adicionar participantes
            i++;
            marcar.indicaDisponibilidadeDe(listaParticipantes.get(i),dataInicialParticipantes, dataFinalParticipantes);
        }
        /* Passando as datas para o MarcadorDeReunião e mostrando resultados */
        marcar.marcarReuniaoEntre(dataInicialMonitor,dataFinalMonitor,participantes);

        /* ************************************************************************************ */

        /*Criação de sala e reserva*/
        while(verificacao){
            System.out.println("Criação de sala");
            int testee = scanner.nextInt();
            GerenciadorDeSalas sala = new GerenciadorDeSalas();

            switch(testee){
                case 1:{//adicionarSalaChamada
                    System.out.println("Digite o nome da sala:");
                    String nomeSala = scanner.next();
                    System.out.println("Digite a capacidade requerida da sala:");
                    int capacidadeSala = scanner.nextInt();
                    System.out.println("Digite uma descrição para a sala:");
                    String descricaoSala = scanner.next();
                    sala.adicionaSalaChamada(nomeSala, capacidadeSala,descricaoSala);
                    break;
                }
                case 2:{//removeSalaChamada
                    System.out.println("Digite a chamada da sala que quer ser removida");
                    String nomeSalaRemovida = scanner.next();
                    sala.removeSalaChamada(nomeSalaRemovida);
                    break;
                }
                case 3:{//listaDeSalas
                    sala.listaDeSalas();
                    break;
                }
                case 4:{//reservaSalaChamada
                    System.out.println("Digite o nome da sala: ");
                    String nomeReserva = scanner.next();
                    System.out.println("Digite a data inicial da reserva: ");
                    //Data de disponibilidade
                    System.out.println("Digite a data que você tem disponibilidade para a reunião. Formato: dia/mês/ano");
                    dataInicialScanner = scanner.next();
                    dataInicialteste = LocalDate.parse(dataInicialScanner, formatar);
                    dataInicial = LocalDate.of(dataInicialteste.getYear(),dataInicialteste.getMonth(),dataInicialteste.getDayOfMonth());

                    //Horário inicial disponivel
                    System.out.println("Digite o horário de inicio que você tem disponível para a reunião. Formato: hora:minuto:segundo");
                    String horaInicialScanner = scanner.next();
                    DateTimeFormatter formatarHora = DateTimeFormatter.ofPattern("HH:mm");
                    LocalTime horaInicialteste = LocalTime.parse(horaInicialScanner, formatarHora);
                    dataInicialParticipantes = LocalDateTime.of(dataInicialteste, horaInicialteste);
                    System.out.println("Digite a data final da reserva: ");
                    //Horário final disponível
                    System.out.println("Digite o horário de fim que você deseja fazer uma reserva. Formato: hora:minuto:segundo");
                    String horaFinalScanner = scanner.next();
                    LocalTime horaFinalteste = LocalTime.parse(horaFinalScanner, formatarHora);
                    dataFinalParticipantes = LocalDateTime.of(dataInicial, horaFinalteste);

                    sala.reservaSalaChamada(nomeReserva,dataInicialParticipantes,dataFinalParticipantes);
                    break;
                }
                case 5:{//cancelaReserva
                    System.out.println("Digite o nome da reserva: ");
                    String reservasSala = scanner.next();

                    //sala.cancelaReserva();
                    break;
                }
                case 6:{//reservasParaSala
                    System.out.println("Digite o nome da reserva: ");
                    String reservasSala = scanner.next();
                    sala.reservasParaSala(reservasSala);
                    break;
                }
                case 7:{//imprimeReservasDaSala
                    System.out.println("Digite o nome da sala que quer ser vistas as reservas: ");
                    String reservasDaSala = scanner.next();
                    sala.imprimeReservasDaSala(reservasDaSala);
                    break;
                }
            }
        }

            int teste = scanner.nextInt();
            if(teste == 1){
                verificacao = false;
            }
        }


    }


