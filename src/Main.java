import controller.HospedeController;
import view.HospedeView;
import java.util.Scanner;

public class   Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HospedeController controller = new HospedeController();
        HospedeView view = new HospedeView(controller, scanner);
        view.exibirMenu();
    }
}