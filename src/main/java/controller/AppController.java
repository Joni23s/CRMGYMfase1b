package controller;

import java.util.Scanner;
import static util.MenuUtils.getIntInput;

public class AppController {
    private static final Scanner scanner = new Scanner(System.in);
    private final ClientMenuController clientMenu = new ClientMenuController();
    private final PlanMenuController planMenu = new PlanMenuController();

    public void start() {
        int option;
        do {
            printMainMenu();
            option = getIntInput("Seleccioná una opción: ");
            handleOption(option);
        } while (option != 0);
        exitMessage();
    }

    private void printMainMenu() {
        System.out.println("""
             ╔═══════════════════════════════╗
             ║           📋 MENÚ             ║
             ╠═══════════════════════════════╣
             ║ 1. 📄 Clientes                ║
             ║ 2. 🔍 Planes                  ║
             ║ 3. 🕓 Historial               ║
             ║ 0. 🚪 Salir                   ║
             ╚═══════════════════════════════╝
        """);
    }

    private void handleOption(int option) {
        switch (option) {
            case 1 -> clientMenu.start();
            case 2 -> planMenu.start();
            case 3 -> System.out.println("🚧 Módulo Historial aún no implementado");
            case 0 -> {}
            default -> System.out.println("❗ Opción inválida.");
        }
    }

    private void exitMessage() {
        System.out.println("""
            ══════════════════════════════════
             👋 ¡Gracias por usar CRMGYM!
            ══════════════════════════════════
        """);
    }


}
