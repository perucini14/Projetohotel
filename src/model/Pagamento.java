package model;

import interfaces.Cadastravel;

public class Pagamento implements Cadastravel {

    public enum FormaPagamento {
        DINHEIRO,
        CARTAO,
        PIX
    }

    public enum StatusPagamento {
        PENDENTE,
        PAGO,
        CANCELADO
    }

    private String id;
    private String idReserva;
    private double valorTotal;
    private FormaPagamento formaPagamento;
    private StatusPagamento status;

    public Pagamento(String id, String idReserva, double valorTotal,
                     FormaPagamento formaPagamento,
                     StatusPagamento status) {

        this.id = id;
        this.idReserva = idReserva;
        this.valorTotal = valorTotal;
        this.formaPagamento = formaPagamento;
        this.status = status;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getIdReserva() {
        return idReserva;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public StatusPagamento getStatus() {
        return status;
    }

    public void setStatus(StatusPagamento status) {
        this.status = status;
    }

    @Override
    public void exibirDados() {

        System.out.println("ID: " + id);
        System.out.println("Reserva: " + idReserva);
        System.out.println("Valor: R$ " + valorTotal);
        System.out.println("Forma: " + formaPagamento);
        System.out.println("Status: " + status);
    }

    @Override
    public String toJson() {

        return "{"
                + "\"id\":\"" + id + "\","
                + "\"idReserva\":\"" + idReserva + "\","
                + "\"valorTotal\":" + valorTotal + ","
                + "\"formaPagamento\":\"" + formaPagamento + "\","
                + "\"status\":\"" + status + "\""
                + "}";
    }
}