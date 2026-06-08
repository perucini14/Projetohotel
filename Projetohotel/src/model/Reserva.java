package model;

import interfaces.Cadastravel;

public class Reserva implements Cadastravel {
    private String id;
    private Hospede hospede;
    private int numeroQuarto;
    private String dataEntrada;
    private String dataSaida;
    private StatusReserva status;

    public Reserva(String id, Hospede hospede, int numeroQuarto, String dataEntrada, String dataSaida) {
        this.id = id;
        this.hospede = hospede;
        this.numeroQuarto = numeroQuarto;
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.status = StatusReserva.ATIVA;
    }

    @Override
    public String getId() { return id; }
    public Hospede getHospede() { return hospede; }
    public int getNumeroQuarto() { return numeroQuarto; }
    public String getDataEntrada() { return dataEntrada; }
    public String getDataSaida() { return dataSaida; }
    public StatusReserva getStatus() { return status; }
    public void setStatus(StatusReserva status) { this.status = status; }

    @Override
    public String toJson() {
        return "{" +
                "\"id\":\"" + id + "\"," +
                "\"idHospede\":\"" + hospede.getId() + "\"," +
                "\"numeroQuarto\":" + numeroQuarto + "," +
                "\"dataEntrada\":\"" + dataEntrada + "\"," +
                "\"dataSaida\":\"" + dataSaida + "\"," +
                "\"status\":\"" + status + "\"" +
                "}";
    }

    @Override
    public void exibirDados() {
        System.out.println("----------------------------------------");
        System.out.println("Reserva ID: " + id);
        System.out.println("Hóspede: " + hospede.getNome() + " (CPF: " + hospede.getCpf() + ")");
        System.out.println("Quarto: " + numeroQuarto);
        System.out.println("Período: " + dataEntrada + " até " + dataSaida);
        System.out.println("Status: " + status);
        System.out.println("----------------------------------------");
    }
}