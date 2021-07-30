package Gerencia.reuniao;
import java.util.*;
import java.time.*;

public class GerenciadorDeSalas
{
    private List<Sala> listaDeSalas;

    //Construtor
    public GerenciadorDeSalas()
    {
        listaDeSalas = new ArrayList<>();
    }

//---------------------------------------------------------
    public void adicionaSalaChamada(String nome, int capacidadeMaxima, String descricao)
    {
        Sala tmp_sala = new Sala(nome, capacidadeMaxima, descricao);
        listaDeSalas.add(tmp_sala);
    }

//---------------------------------------------------------
    public void removeSalaChamada(String nomeDaSala)
    {
        for(Sala s : listaDeSalas)
        {
            if(s.getNome().equals(nomeDaSala))
                listaDeSalas.remove(s);
        }
    }

//---------------------------------------------------------
    public List<Sala> listaDeSalas()
    {
        return listaDeSalas;
    }

//---------------------------------------------------------
    public void adicionaSala(Sala novaSala)
    {
        if(listaDeSalas.contains(novaSala))
            System.out.println("Sala já está na coleção");
        else if(checarPresense(novaSala))
            System.out.println("ERRO em GerenciadorDeSalas: sala com mesmo nome já está na coleção");
        else listaDeSalas.add(novaSala);
    }

//---------------------------------------------------------
    public Reserva reservaSalaChamada(String nomeDaSala, LocalDateTime
                                      dataInicial, LocalDateTime dataFinal)
    {
        for(Sala s : listaDeSalas)
        {
            if(s.getNome().equals(nomeDaSala))
            {
                if(s.isReservada(dataInicial, dataFinal))
                    System.out.println("A sala já está reservada!");
                else {
                    Reserva reserva = new Reserva(s, null);
                    reserva.setInicio(dataInicial);
                    reserva.setFim(dataFinal);
                    s.setReservada(reserva);
                    return reserva;
        } } }

        return null;
    }

//---------------------------------------------------------
    public void cancelaReserva(Reserva cancelada)
    {
        for (Sala s : listaDeSalas)
            if(s.getReservada(cancelada.getInicio(), cancelada.getFim()).getUUID().hashCode()
                == cancelada.getUUID().hashCode())
                { s.liberar(cancelada);
                    return; }

        System.out.println("RESERVA NÃO PODE SER LIBERADA: ela não existe");


    }


    Collection<Reserva> reservasParaSala(String nomeSala)
    {
        for(Sala s : listaDeSalas)
            if(s.getNome().equals(nomeSala))
            { return s.getListaReservada(); }

        return null;
    }

    public void imprimeReservasDaSala(String nomeSala)
    {
        List<Reserva> lc;
        try {
            lc = (List<Reserva>) reservasParaSala(nomeSala);
            Iterator<Reserva> i = lc.iterator();
        }catch (RuntimeException e)
        { Scanner s = new Scanner(System.in);

            System.out.println("A sala selecionada não existe!");
            System.out.println("Gostaria de adicionala? 1 para adiciona-la, 0 para voltar às opções");

            int capacidade, op = s.nextInt();
            String descricao;

            System.out.println("Digite a capacidade requerida da sala:");
            capacidade = s.nextInt();

            System.out.println("Digite uma descrição para a sala:");

            descricao = s.next();

            if(op == 0)
                return;
            else if(op == 1)
                adicionaSalaChamada(nomeSala, capacidade, descricao);
            else{ s.close(); throw new IllegalArgumentException("Nem 1 nem 0, assim não dá!"); }

            s.close();



        }finally {
            lc = (List<Reserva>) reservasParaSala(nomeSala);
            int iInt = 0;
        }


        int iInt = 0;
        Iterator<Reserva> i = lc.iterator();
        System.out.println("A sala selecionada tem ");
        do{
            System.out.print("reserva de indice i = "+ i);
            System.out.println("dia: \t hora:");
            Reserva tmp = lc.get(iInt);
            System.out.println(tmp.getInicio().getDayOfMonth()+"\t"+tmp.getInicio().getHour());
            System.out.println("Reservada até: ");
            System.out.println("dia: \t hora:");
            System.out.println(tmp.getFim().getDayOfMonth()+"\t"+tmp.getFim().getHour());
            iInt++;
        }while (i.hasNext());
    }
//----------metodos-privados-------------------------------
    private boolean checarPresense(Sala sala)
    { boolean truth = false;

        for(Sala s : listaDeSalas)
            if (s.getNome().equals(sala.getNome())) {
                truth = true;
                break;
            }

        return truth;
    }
}