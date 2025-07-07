package util;

import java.util.Scanner;
import java.util.function.Consumer;

public class MenuUtils {
    private static final Scanner scanner = new Scanner(System.in);

    public static int getIntInput(String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            System.out.print("⚠️ Por favor, ingresá un número válido: ");
            scanner.next();
        }
        int input = scanner.nextInt();
        scanner.nextLine(); // Limpia el buffer
        return input;
    }

    //  Metodo para reutilizar sub menues
    public static void handleSubMenu(Runnable printMenu, Consumer<Integer> handleOption, int exitOption) {
        int subOption;
        do {
            printMenu.run();
            subOption = getIntInput("Seleccioná una opción: ");
            handleOption.accept(subOption);
        } while (subOption != exitOption);
    }
}
