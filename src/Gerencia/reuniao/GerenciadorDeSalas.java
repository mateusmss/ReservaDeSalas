package Gerencia.reuniao;
import java.util.*;
import java.time.*;

public class GerenciadorDeSalas
{
    private List<Sala> listaDeSalas;
    private UniversalInput ui;

    //Construtor
    public GerenciadorDeSalas() { listaDeSalas = new ArrayList<>(); }

//---------------------------------------------------------
    public void adicionaSalaChamada(String nome, int capacidadeMaxima, String descricao)
    {
        Sala tmp_sala = new Sala(nome, capacidadeMaxima, descricao);
        listaDeSalas.add(tmp_sala);
    }

//---------------------------------------------------------
    public void removeSalaChamada(String nomeDaSala)
    {
        if(listaDeSalas.isEmpty()) {
            System.out.println("Nenhuma sala para remover");
            return;
        }

        if(listaDeSalas.size() == 1) {
            listaDeSalas.clear();
            return;
        }

        for(Sala s : listaDeSalas)
        {
            if(s.getNome().equals(nomeDaSala))
                listaDeSalas.remove(s);
        }

        System.out.println("Sala removida com sucesso");
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
        if(listaDeSalas.isEmpty())
            System.out.println("Nenhuma sala cadastrada!");

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

    public void cancelaReserva(String identificador)
    { LocalDateTime tmpInicio, tmpFim;

        if(listaDeSalas.isEmpty())
        {
            System.out.println("Não foi possivel cancelar a reserva!\nNenhuma sala cadastrada!");
            return;
        }

        if(listaDeSalas.size() == 1)
        { String nomeSala = listaDeSalas.get(0).getNome();
            ArrayList<Reserva> tmplc = (ArrayList<Reserva>)reservasParaSala(nomeSala);

            if(tmplc.isEmpty())
            {
                System.out.println("Nenhuma reserva encontrada para essa sala!");
                return;
            }
            if(tmplc.size() == 1)
            { if(tmplc.get(0).getUUID().toString().equals(identificador))
            { ui = new UniversalInput();
                Reserva cancelada = tmplc.get(0);
                System.out.println("Reserva encontrada!");
                System.out.println("Deseja mesmo cancela-la?");
                int escolha = ui.escan_escolha();

                if(escolha == 1) {
                    cancelaReserva(cancelada);
                    System.out.println("Ok! Reserva cancelada com sucesso!");
                    return;
                }
                if(escolha == 0) {
                    System.out.println("Ok, não a cancelaremos");
                    return;
                }
                else {System.out.println("Erro ao cancelar a reserva!"); return; }
            } }else{

                for(Reserva r : tmplc)
                {
                    if(r.getUUID().toString().equals(identificador))
                    { ui = new UniversalInput();
                        Reserva cancelada = tmplc.get(0);
                        System.out.println("Reserva encontrada!");
                        System.out.println("Deseja mesmo cancela-la?");
                        int escolha = ui.escan_escolha();

                        if(escolha == 1) {
                            cancelaReserva(cancelada);
                            System.out.println("Ok! Reserva cancelada com sucesso!");
                            return;
                        }
                        if(escolha == 0) {
                            System.out.println("Ok, não a cancelaremos");
                            return;
                        }
                        else { System.out.println("Erro ao cancelar a reserva!"); return; }
                    }

                    System.out.println("Reserva não encontrada");

                } } }
        else {

            for(Sala s : listaDeSalas)
            {
                String nomeSala = s.getNome();
                ArrayList<Reserva> tmplc = (ArrayList<Reserva>)reservasParaSala(nomeSala);

                if(tmplc.isEmpty())
                {
                    System.out.println("Nenhuma reserva encontrada para essa sala!");
                    return;
                }
                if(tmplc.size() == 1)
                { if(tmplc.get(0).getUUID().toString().equals(identificador))
                { ui = new UniversalInput();
                    Reserva cancelada = tmplc.get(0);
                    System.out.println("Reserva encontrada!");
                    System.out.println("Deseja mesmo cancela-la?");
                    int escolha = ui.escan_escolha();

                    if(escolha == 1) {
                        cancelaReserva(cancelada);
                        System.out.println("Ok! Reserva cancelada com sucesso!");
                        return;
                    }
                    if(escolha == 0) {
                        System.out.println("Ok, não a cancelaremos");
                        return;
                    }
                    else {System.out.println("Erro ao cancelar a reserva!"); return; }
                } }else{

                    for(Reserva r : tmplc)
                    {
                        if(r.getUUID().toString().equals(identificador))
                        { ui = new UniversalInput();
                            Reserva cancelada = tmplc.get(0);
                            System.out.println("Reserva encontrada!");
                            System.out.println("Deseja mesmo cancela-la?");
                            int escolha = ui.escan_escolha();

                            if(escolha == 1) {
                                cancelaReserva(cancelada);
                                System.out.println("Ok! Reserva cancelada com sucesso!");
                                return;
                            }
                            if(escolha == 0) {
                                System.out.println("Ok, não a cancelaremos");
                                return;
                            }
                            else { System.out.println("Erro ao cancelar a reserva!"); return; }
                        }

                        System.out.println("Reserva não encontrada");

                    } }

            } }

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
        if(listaDeSalas.isEmpty()) {
            System.out.println("Nenhuma sala cadastrada");
            return;
        }

        if(listaDeSalas.size() == 1 && listaDeSalas.get(0).getNome().equals(nomeSala))
        { List<Reserva> lc = (List<Reserva>) reservasParaSala(nomeSala);

            for(Reserva r : lc)
            {
                r.print();
            }
            return;
        }

        for(Sala s : listaDeSalas)
        {
            if(s.getNome().equals(nomeSala))
            { List<Reserva> lc = (List<Reserva>) reservasParaSala(nomeSala);

                for(Reserva r : lc)
                    r.print();
                return;
            } }

        System.out.println("Sala não encontrada");
        return;
    }

    public void imprimeListaSalas() {
        if(listaDeSalas.isEmpty()) {
            System.out.println("Nenhuma sala cadastrada");
            return;
        }

        for (Sala s : listaDeSalas)
        {
            s.printThisSala();
        }
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