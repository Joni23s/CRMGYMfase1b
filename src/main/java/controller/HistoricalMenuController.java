package controller;

import service.HistoricalPlanService;

import static util.MenuUtils.getIntInput;
import static util.MenuUtils.handleSubMenu;

public class HistoricalMenuController {
    private static final HistoricalPlanService historicalPlanService = new HistoricalPlanService();

    public void start() {
        int option;
        do {
            printMenuHistorial();
            option = getIntInput("Seleccioná una opción: ");
            handleOptionMenuHistorial(option);
        } while (option != 0); // Ahora se sale con 0
    }

    private static void printMenuHistorial() {
        System.out.println("""
             ╔══════════════════════════════════╗
             ║       📓 MENÚ HISTORIAL          ║
             ╠══════════════════════════════════╣
             ║ 1. 📄 Historial por Estado       ║
             ║ 2. 📑 Historial por Plan         ║
             ║ 3. 👤 Historial por Cliente      ║
             ║ 0. ↩️ Regresar al Menú anterior  ║
             ╚══════════════════════════════════╝
        """);
    }

    private void handleOptionMenuHistorial(int option) {
        switch (option) {
            case 1 -> handleSubMenu(
                    HistoricalMenuController::printSubMenuHistorialByStatus,
                    HistoricalMenuController::handleOptionSubMenuHistorialByStatus,
                    4 // Submenú se sale con 4
            );
            case 2 -> historicalPlanService.listByPlan();
            case 3 -> historicalPlanService.listByClient();
            case 0 -> {} // volver
            default -> System.out.println("❗ Opción inválida.");
        }
    }

    private static void printSubMenuHistorialByStatus() {
        System.out.println("""
             ╔═════════════════════════════════════╗
             ║       📄 LISTAR HISTORIAL           ║
             ╠═════════════════════════════════════╣
             ║ 1. ✅ Historiales Activos           ║
             ║ 2. ❌ Historiales Inactivos         ║
             ║ 3. 📋 Todos los Historiales         ║
             ║ 4. ↩️  Regresar al Menú anterior    ║
             ╚═════════════════════════════════════╝
        """);
    }

    private static void handleOptionSubMenuHistorialByStatus(int option) {
        switch (option) {
            case 1 -> historicalPlanService.listByStatus(true);
            case 2 -> historicalPlanService.listByStatus(false);
            case 3 -> historicalPlanService.listAllHistory();
            case 4 -> {} // volver
            default -> System.out.println("❗ Opción inválida.");
        }
    }
}
