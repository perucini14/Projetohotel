package model;

public class QuartoStandard extends Quarto {
    private static final double DIARIA_FIXA = 150.00;

    public QuartoStandard(int numero, int andar) {
        super(numero, andar);
    }

    @Override
    public double calcularDiaria() {
        return DIARIA_FIXA;
    }

    @Override
    public String getTipo() {
        return "Standard";
    }
}