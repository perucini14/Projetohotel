package model;

public class QuartoLuxo extends Quarto {
    private static final double DIARIA_BASE = 150.00;
    private static final double ACRESCIMO = 100.00;

    public QuartoLuxo(int numero, int andar) {
        super(numero, andar);
    }

    @Override
    public double calcularDiaria() {
        return DIARIA_BASE + ACRESCIMO;
    }

    @Override
    public String getTipo() {
        return "Luxo";
    }
}