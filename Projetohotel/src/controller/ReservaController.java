package controller;

import model.Reserva;
import model.StatusReserva;
import util.ArquivoUtil;

import java.util.ArrayList;

public class ReservaController {
    private ArrayList<Reserva> listaReservas;
    private final String CAMINHO_ARQUIVO = "dados/reservas.json";

    public ReservaController() {
        this.listaReservas = new ArrayList<>();
    }

    public void abrirReserva(Reserva novaReserva) throws Exception {
        for (Reserva r : listaReservas) {
            if (r.getNumeroQuarto() == novaReserva.getNumeroQuarto() && r.getStatus() == StatusReserva.ATIVA) {
                throw new Exception("Erro: O quarto " + novaReserva.getNumeroQuarto() + " já possui uma reserva ativa!");
            }
        }
        listaReservas.add(novaReserva);
        salvarEmArquivo();
    }

    public Reserva buscarPorId(String id) throws Exception {
        for (Reserva r : listaReservas) {
            if (r.getId().equalsIgnoreCase(id)) {
                return r;
            }
        }
        throw new Exception("Erro: Reserva com o ID " + id + " não foi encontrada.");
    }

    public void fazerCheckin(String id) throws Exception {
        Reserva r = buscarPorId(id);
        if (r.getStatus() != StatusReserva.ATIVA) {
            throw new Exception("Erro: Não é possível fazer check-in. Status atual: " + r.getStatus());
        }
        r.setStatus(StatusReserva.CHECKIN);
        salvarEmArquivo();
    }

    public void fazerCheckout(String id) throws Exception {
        Reserva r = buscarPorId(id);
        if (r.getStatus() != StatusReserva.CHECKIN) {
            throw new Exception("Erro: Não é possível fazer check-out sem ter feito check-in primeiro.");
        }
        r.setStatus(StatusReserva.CHECKOUT);
        salvarEmArquivo();
    }

    public void cancelarReserva(String id) throws Exception {
        Reserva r = buscarPorId(id);
        if (r.getStatus() == StatusReserva.CHECKOUT) {
            throw new Exception("Erro: Não é possível cancelar uma reserva que já foi finalizada (Checkout).");
        }
        r.setStatus(StatusReserva.CANCELADA);
        salvarEmArquivo();
    }

    public ArrayList<Reserva> getListaReservas() {
        return listaReservas;
    }

    public void salvarEmArquivo() {
        StringBuilder jsonBuilder = new StringBuilder("[\n");
        for (int i = 0; i < listaReservas.size(); i++) {
            jsonBuilder.append("  ").append(listaReservas.get(i).toJson());
            if (i < listaReservas.size() - 1) jsonBuilder.append(",\n");
        }
        jsonBuilder.append("\n]");

        ArquivoUtil.salvar(CAMINHO_ARQUIVO, jsonBuilder.toString());
    }

    public void carregarDeArquivo() {
        try {
            String conteudo = ArquivoUtil.ler(CAMINHO_ARQUIVO);
            System.out.println("Dados de reservas carregados com sucesso!");
        } catch (Exception e) {
            System.out.println("Arquivo de reservas não encontrado. Criando novo banco de dados...");
        }
    }
}