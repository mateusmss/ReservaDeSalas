package Gerencia.reuniao;

import java.util.List;

public class Main
{
    private static List<Participantes> listaAux;
    public static void main(String[] args)
    {
        MarcadorDeReuniao mr = new MarcadorDeReuniao(listaAux);
    }
}
