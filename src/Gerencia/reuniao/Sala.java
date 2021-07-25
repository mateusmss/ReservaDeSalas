package Gerencia.reuniao;
import java.time.*;

public class Sala
{
    private String nome;
    private int capacidade;
    private String observacoes;

    private LocalDateTime rInicio;
    private LocalDateTime rFim;

    public Sala(String nome, int capacidade, String observacoes){
        this.nome = nome;
        this.capacidade = capacidade;
        this.observacoes = observacoes;
    }
    public String getNome(){
        return this.nome;
    }
    public String getLocal(){
        return this.nome;
    }
    public int getCapacidade(){
        return this.capacidade;
    }
    public String getObservacoes(){
        return this.nome;
    }
    public void setDateTime(LocalDateTime inicio, LocalDateTime fim)
    {
        rInicio = inicio;
        rFim = fim;
    }
}
