package model;

import interfaces.Cadastravel;

public class Servico implements Cadastravel {
    private String id;
    private String idReserva;
    private TipoServico tipo;
    private double valor;

    public Servico(String id, String idReserva, TipoServico tipo, double valor) {
        this.id = id;
        this.idReserva = idReserva;
        this.tipo = tipo;
        this.valor = valor;
    }

    @Override
    public String getId() { return id; }
    public String getIdReserva() { return idReserva; }
    public TipoServico getTipo() { return tipo; }
    public double getValor() { return valor; }


    public String toJson() {
        return "{" +
                "\"id\":\"" + id + "\"," +
                "\"idReserva\":\"" + idReserva + "\"," +
                "\"tipo\":\"" + tipo + "\"," +
                "\"valor\":" + valor +
                "}";
    }

    @Override
    public void exibirDados() {
        System.out.println("Serviço ID: " + id + " | Tipo: " + tipo + " | Valor: R$ " + valor);
    }
}