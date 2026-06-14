package model;

import interfaces.Cadastravel;

public abstract class Quarto implements Cadastravel {
    protected int numero;
    protected int andar;
    protected boolean disponivel;

    public Quarto(int numero, int andar) {
        this.numero = numero;
        this.andar = andar;
        this.disponivel = true; 
    }

    
    public abstract double calcularDiaria();
    public abstract String getTipo();

    
    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    public int getAndar() { return andar; }
    public void setAndar(int andar) { this.andar = andar; }

    public boolean isDisponivel() { return disponivel; }
    public void setDisponivel(boolean disponivel) { this.disponivel = disponivel; }

    @Override
    public String getId() {
        return String.valueOf(this.numero);
    }


    public String toJson() {
        return "{\"numero\":" + numero + ",\"andar\":" + andar + ",\"disponivel\":" + disponivel + ",\"tipo\":\"" + getTipo() + "\"}";
    }

    @Override
    public void exibirDados() {
        System.out.println("Quarto " + numero + " (Andar: " + andar + ") - Tipo: " + getTipo() + " - Diária: R$" + calcularDiaria() + " - Disponível: " + (disponivel ? "Sim" : "Não"));
    }
}
