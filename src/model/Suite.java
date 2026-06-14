package model;

public class Suite extends Quarto {
    private static final double DIARIA_PREMIUM = 500.00;

    public Suite(int numero, int andar) {
        super(numero, andar);
    }

    @Override
    public double calcularDiaria() {
        return DIARIA_PREMIUM;
    }

    @Override
    public String getTipo() {
        return "Suite";
    }
}