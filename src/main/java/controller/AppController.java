package controller;

import static util.MenuUtils.getIntInput;

public class AppController {
    private final ClientMenuController clientMenu = new ClientMenuController();
    private final PlanMenuController planMenu = new PlanMenuController();
    private final HistoricalMenuController historicalMenu = new HistoricalMenuController();

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
             ║ 🏋️‍♀️ CRM GYM App 🏋️‍♂️(Fase 1-b)  ║
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
            case 3 -> historicalMenu.start();
            case 0 -> System.out.println("🔚 Saliendo del sistema...");
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
