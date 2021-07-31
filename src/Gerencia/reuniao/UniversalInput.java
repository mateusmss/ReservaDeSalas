package Gerencia.reuniao;

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

    public String escan_Nome()
    {
        String nome;
        nome = escan.next();

        return nome;
    }

    public String escan_Email()
    { String nome = "";

        try {

            nome = escan.next();
            while (!nome.contains("@") || nome.contains(" ")) {
                System.out.println("Por favor, escreva um email valido!");
                System.out.println("\ttente algo começando com @ e que não contenha espaços");

                nome = escan.next();
                return nome;
            }
        }catch (Exception e)
        {
            System.out.println("==================ERRO==================\n");
            System.out.println("Problemas encontrados, terminando a execução do programa!");
            System.exit(1);
        }

        return null;
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



    }
}
