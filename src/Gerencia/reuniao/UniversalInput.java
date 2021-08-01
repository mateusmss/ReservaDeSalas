package Gerencia.reuniao;


import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class UniversalInput
{
    private Scanner escan;
    private tratamento_Excesao tratar;
    public UniversalInput()
    {
        escan = new Scanner(System.in).useDelimiter("\n");
        tratar = new tratamento_Excesao();
    }
    /*
    A FAZER: Metodos adicionais para leituras que não se encaixe em nenhum dos metodos abaixo
             Implementar os manejos de erros adequados na subclasse tratamento_Excesao
     */

    public int escan_escolha()
    { int i, j = 0;
        do {
            try {
                i = escan.nextInt();
                return i;
            } catch (Exception e) { ;
                System.out.println("Entrada invalida, coloque 1 para sim e 0 para não!");
                j++;
            } }while (j < 3);
        if (j > 2) {
            System.out.println("ERRO: demasiadas entradas erroneas!\nO programa será finalizado!");
            System.exit(1);
        }
        return -1;
    }

    public LocalDate escan_Data(DateTimeFormatter formatter, String complemento_ErrorMessage)
    { String data = "";
        LocalDate dataTeste;
        LocalDate dataReturn;

        try {
            data = escan.next();
            dataTeste = LocalDate.parse(data, formatter);
            dataReturn = LocalDate.of(dataTeste.getYear(), dataTeste.getMonth(), dataTeste.getDayOfMonth());
            return dataReturn;

        }catch (Exception e){
            int j = 0;

            do{
                dataReturn = tratar.EscanDataException(formatter, complemento_ErrorMessage);
                j++;
            }while (j < 3 && Objects.isNull(dataReturn));
            if(j > 2)
            {
                System.out.println("O programa está finalizando devido à demasiadas entradas erroneas!");
                System.exit(1);
            } else return dataReturn; }

        return null;
    }

    public LocalTime escan_Time()
    {
        String time;
        DateTimeFormatter formatarHora;
        LocalTime horaInicialteste;

        try{
            time = escan.next();
            formatarHora = DateTimeFormatter.ofPattern("HH:mm");
            horaInicialteste = LocalTime.parse(time, formatarHora);
            return horaInicialteste;
        }catch (Exception e)
        { int j = 0;

            do{
                horaInicialteste = tratar.EscanHoraException();
                j++;
            }while (j < 3 && Objects.isNull(horaInicialteste));
            if(j > 2)
            {
                System.out.println("O programa está finalizando devido à demasiadas entradas erroneas!");
                System.exit(1);
            }else return horaInicialteste; }

        return null;
    }

    private class tratamento_Excesao
    {


        public LocalDate EscanDataException(DateTimeFormatter formatter, String complemento)
        { String data = "";
            LocalDate dataTeste;
            LocalDate dataReturn;

            System.out.println("============ERRO=============\n");
            System.out.println("A data disponibilizada está formatada de maneira erronea!");
            System.out.println("Tente utilizar o formato "+complemento);

            try {
                data = escan.next();
                dataTeste = LocalDate.parse(data, formatter);
                dataReturn = LocalDate.of(dataTeste.getYear(), dataTeste.getMonth(), dataTeste.getDayOfMonth());
                return dataReturn;
            }catch (Exception e)
            {
                return null;
            } }

        public LocalTime EscanHoraException()
        {
            String time;
            DateTimeFormatter formatarHora;
            LocalTime horaInicialteste;

            System.out.println("============ERRO=============\n");
            System.out.println("A hora disponibilizada está formatada de maneira erronea!");
            System.out.println("Tente utilizar o formato Hora:Minuto");

            try {
                time = escan.next();
                formatarHora = DateTimeFormatter.ofPattern("HH:mm");
                horaInicialteste = LocalTime.parse(time, formatarHora);
                return horaInicialteste;
            }catch (Exception e)
            {
                return null;
            } }

    }
}
