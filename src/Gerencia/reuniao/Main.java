package Gerencia.reuniao;

import java.time.format.DateTimeFormatter;
import java.util.*;
import java.time.*;

public class Main
{
    public static void main(String args[]){
        UniversalInput ui = new UniversalInput();

        /* Criação de variáveis e lista*/
        List<String> listaParticipantes = new ArrayList<>();
        //private static List<Participantes> listaAux;
        List<String> participantes = new ArrayList<>();
        LocalDate dataInicialMonitor;
        LocalDate dataFinalMonitor;
        LocalDateTime dataInicialParticipantes = null;
        LocalDateTime dataFinalParticipantes = null;
        LocalDate dataInicial;
        LocalDate dataFinal;
        LocalDate agora = LocalDate.now();
        boolean verificacao = true;//variavel de verificação
        Scanner scanner = new Scanner(System.in); //criando scanner
        DateTimeFormatter formatar = DateTimeFormatter.ofPattern("dd/MM/yyyy"); //formatando o tipo de data
        /* ************************************************** */

        /* Parte do scanner da data inicial e final ditas pelo organizador*/
        System.out.println("=========== Sistema de Cadastro de Participantes ===========");
        System.out.println("Bem-vindo ao Sistema de Cadastro, organizador.");
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

        //System.out.println("A data inicial é: " + dataInicialMonitor + ", a data final é: " +dataFinalMonitor);
        MarcadorDeReuniao marcar = new MarcadorDeReuniao();

        /* ****************************************************** */

        /*Organizador insere os emails dos participantes*/
        System.out.println("=========== Cadastro de participantes ===========");
        int count = 0;
        while(verificacao){
            System.out.println("[1] Cadastrar participantes ");
            System.out.println("[2] Finalizar cadastro de participantes");
            System.out.println("[3] Encerrar programa");
            String teste = scanner.next();
            switch (teste){
                case "1":{
                    try{
                        System.out.println("Digite os participantes por endereço de email: ");
                        nome = scanner.next();
                        while (nome.contains("@") != true){
                            System.out.println("--------------- Problema Encontrado -----------");
                            System.out.println("                                                   ");
                            System.out.println("Digite um email válido: ");
                            nome = scanner.next();
                            count++;
                            if(count == 3){
                                throw new Exception();
                            }
                        }
                        listaParticipantes.add(nome);
                        System.out.println("Email cadastrado!");
                        break;
                    }catch(Exception e){
                        System.out.println("--------------- Problema Encontrado -----------");
                        System.out.println("                                                   ");
                        System.out.println("Encerramento do programa ");
                        System.exit(0);
                    }
                }
                case "2":{
                    if(listaParticipantes.size() == 0){
                        System.out.println("--------------- Problema Encontrado -----------");
                        System.out.println("Digite no minimo 1 participante para continuar o programa");
                        System.out.println("                                                   ");
                        break;
                    }
                    verificacao = false;
                    break;
                }
                case "3":{
                    System.out.println("--------------- Encerramento Programa -----------");
                    System.out.println("                                                   ");
                    System.out.println("Encerramento do programa ");
                    System.exit(0);
                }
            }
        }
        /* ******************************************************************************************* */

        /* Looping onde os participantes colocam seu email de verificação e possiveis horários disponíveis dentro de um periodo */
        verificacao = true;
        int count2 = 0;
        for(int k = 0;k < listaParticipantes.size();k++){
            System.out.println("=========== Cadastro dos participantes ===========");
            try{
                while (count2 <= 3){
                    //Nome do participante
                    System.out.println("Olá, " +listaParticipantes.get(k));
                    //Data de disponibilidade
                    System.out.println("Digite a data de inicio que você tem disponibilidade para a reunião. Formato: dia/mês/ano");
                    /*dataInicialScanner = scanner.next();
                    dataInicialteste = LocalDate.parse(dataInicialScanner, formatar);
                    dataInicial = LocalDate.of(dataInicialteste.getYear(),dataInicialteste.getMonth(),dataInicialteste.getDayOfMonth());*/

                    dataInicial = ui.escan_Data(formatar, "dia/mês/ano");

                    System.out.println("Digite a data final que você tem disponibilidade para a reunião. Formato: dia/mês/ano");
                    /*dataFinalScanner = scanner.next();
                    dataFinalteste = LocalDate.parse(dataFinalScanner, formatar);
                    dataFinal = LocalDate.of(dataFinalteste.getYear(),dataFinalteste.getMonth(),dataFinalteste.getDayOfMonth());*/
                    dataFinal = ui.escan_Data(formatar, "dia/mês/ano");

                    //Horário inicial disponivel
                    System.out.println("Digite o horário de inicio que você tem disponível para a reunião. Formato: hora:minuto");
                    /*String horaInicialScanner = scanner.next();*/
                    DateTimeFormatter formatarHora = DateTimeFormatter.ofPattern("HH:mm");
                    LocalTime horaInicialteste = ui.escan_Time();


                    //Horário final disponível
                    System.out.println("Digite o horário de fim que você deseja fazer uma reserva. Formato: hora:minuto");
                    //String horaFinalScanner = scanner.next();
                    LocalTime horaFinalteste = ui.escan_Time();


                    if(dataInicial.isAfter(dataFinal)){
                        System.out.println("--------------- Problema Encontrado -----------");
                        System.out.println("                                                   ");
                        System.out.println("Data final é anterior a data inicial, digite novamente. Data não computada");
                    }
                    if(horaInicialteste.isAfter(horaFinalteste)){
                        System.out.println("--------------- Problema Encontrado -----------");
                        System.out.println("                                                   ");
                        System.out.println("Horário inicial é posterior ao horário final. Horário não computado");
                    }
                    if(dataInicialMonitor.isAfter(dataInicial) || dataFinalMonitor.isBefore(dataFinal)){
                        System.out.println("--------------- Problema Encontrado -----------");
                        System.out.println("                                                   ");
                        System.out.println("Data não condizente com as datas escolhidas pelo administrados. Data não computada");
                        System.out.print("O intervalo de marcação de possíveis é: " +dataInicialMonitor.getDayOfMonth()+ "/" +dataInicialMonitor.getMonthValue() +"/" + dataInicialMonitor.getYear());
                        System.out.println(" e " +dataFinalMonitor.getDayOfMonth()+ "/" +dataFinalMonitor.getMonthValue() +"/" + dataFinalMonitor.getYear() );
                    }
                    if (!dataInicial.isAfter(dataFinal) && !horaInicialteste.isAfter(horaFinalteste) && !dataInicialMonitor.isAfter(dataInicial) && !dataFinalMonitor.isBefore(dataFinal)){
                        dataInicialParticipantes = LocalDateTime.of(dataInicial, horaInicialteste);
                        dataFinalParticipantes = LocalDateTime.of(dataFinal, horaFinalteste);
                        break;
                    }

                    count2++;
                    if(count2 == 3){
                        IllegalArgumentException erro = new IllegalArgumentException();
                        throw erro;
                    }
                }

            }catch(NullPointerException e){
                System.out.println("--------------- Problema Encontrado -----------");
                System.out.println("                                                   ");
                break;
            }
            catch(IllegalArgumentException erro){
                System.out.println("--------------- Problema Encontrado -----------");
                System.out.println("                                                   ");
                System.out.println("Encerramento do programa");
                System.exit(0);
            }

            //Pergunta se quer prosseguir em adicionar participantes

            marcar.indicaDisponibilidadeDe(listaParticipantes.get(k),dataInicialParticipantes, dataFinalParticipantes);
        }
        /* Passando as datas para o MarcadorDeReunião e mostrando resultados */
        marcar.marcarReuniaoEntre(dataInicialMonitor,dataFinalMonitor,participantes);

        /* ************************************************************************************ */

        /*Criação de sala e reserva*/
        boolean verificacao2 = true;
        GerenciadorDeSalas sala = new GerenciadorDeSalas();
        try { Thread.sleep (3000); }
        catch (InterruptedException ex) {
            System.out.println("erro");
        }

        //scanner.close();


        String testee;
        while(verificacao2){
            Scanner escan = new Scanner(System.in).useDelimiter("\n");
            System.out.println("=========== Reservas de Sala ===========");
            System.out.println("                                        ");
            System.out.println("[1] Adicionar chamada de sala");
            System.out.println("[2] Mostrar todas as salas");
            System.out.println("[3] Reservar chamada de sala");
            System.out.println("[4] Remover chamada de sala");
            System.out.println("[5] Cancelar reserva de chamada de sala");
            System.out.println("[6] Ver reservas para sala");
            System.out.println("[7] Sair do programa");
            System.out.println("Digite o número do comando para prosseguir:");
            testee = escan.next();




            switch(testee){
                case "1":{//adicionarSalaChamada
                    System.out.println("--------------- Adicionar sala -----------");
                    System.out.println("                                                   ");
                    System.out.println("Digite o nome da sala:");
                    String nomeSala = escan.next();
                    System.out.println("Digite a capacidade requerida da sala:");
                    int capacidadeSala = escan.nextInt();
                    System.out.println("Digite uma descrição para a sala:");
                    String descricaoSala = escan.next();
                    sala.adicionaSalaChamada(nomeSala, capacidadeSala,descricaoSala);
                    System.out.println("Sala adicionada");
                    break;
                }
                case "2":{//listaDeSalas
                    System.out.println("--------------- Lista de salas -----------");
                    System.out.println("                                                   ");
                    //System.out.println(sala.listaDeSalas().toString());

                    sala.imprimeListaSalas();
                    System.out.println();
                    break;
                }
                case "3":{//reservaSalaChamada
                    System.out.println("--------------- Reserva Sala Chamada-----------");
                    System.out.println("                                                   ");
                    System.out.println("Digite o nome da sala: ");
                    String nomeReserva = escan.next();
                    System.out.println("Digite a data da reserva. Formato: dia/mês/ano ");
                    //Data de disponibilidade
                    //dataInicialScanner = escan.next();
                    dataInicialteste = ui.escan_Data(formatar, "dia/mês/ano");
                    dataInicial = LocalDate.of(dataInicialteste.getYear(),dataInicialteste.getMonth(),dataInicialteste.getDayOfMonth());

                    //Horário inicial disponivel
                    System.out.println("Digite o horário de inicio que você tem disponível para a reunião. Formato: hora:minuto");
                    String horaInicialScanner = escan.next();
                    DateTimeFormatter formatarHora = DateTimeFormatter.ofPattern("HH:mm");
                    LocalTime horaInicialteste = LocalTime.parse(horaInicialScanner, formatarHora);
                    dataInicialParticipantes = LocalDateTime.of(dataInicialteste, horaInicialteste);
                    //Horário final disponível
                    System.out.println("Digite o horário de fim que você deseja fazer uma reserva. Formato: hora:minuto");
                    String horaFinalScanner = escan.next();
                    LocalTime horaFinalteste = LocalTime.parse(horaFinalScanner, formatarHora);
                    dataFinalParticipantes = LocalDateTime.of(dataInicial, horaFinalteste);

                    sala.reservaSalaChamada(nomeReserva,dataInicialParticipantes,dataFinalParticipantes);

                    try { Thread.sleep (3000); }
                    catch (InterruptedException ex) {
                        System.out.println("erro");
                    }

                    break;
                }
                case "4":{
                    //removeSalaChamada
                    System.out.println("--------------- Remover sala -----------");
                    System.out.println("                                                   ");
                    System.out.println("Digite a chamada da sala que quer ser removida");
                    String nomeSalaRemovida = escan.next();
                    sala.removeSalaChamada(nomeSalaRemovida);
                    //System.out.println("Sala removida");
                    break;
                }
                case "5":{//cancelaReserva
                    System.out.println("--------------- Cancelar Reserva -----------");
                    System.out.println("                                                   ");
                    System.out.println("Digite abaixo, o identificador de sua reserva: ");
                    String identificador = escan.next();

                    sala.cancelaReserva(identificador);

                    try { Thread.sleep (3000); }
                    catch (InterruptedException ex) {
                        System.out.println("erro");
                    }

                    //sala.cancelaReserva();
                    break;
                }
                case "6":{//imprimeReservasDaSala
                    System.out.println("--------------- Imprimir Reservas da Sala -----------");
                    System.out.println("                                                   ");
                    System.out.println("Digite o nome da sala que quer ser vistas as reservas: ");
                    String reservasDaSala = escan.next();
                    sala.imprimeReservasDaSala(reservasDaSala);
                    break;
                }
                case "7":{//Finalizar programa
                    System.out.println("--------------- Finalização Programa -----------");
                    System.out.println("                                                   ");
                    System.out.println("Obrigado por utilizar nosso sistema");
                    verificacao2 = false;
                    break;
                }

            }
        }
        }
    }


