package controller;

import service.PlanService;

import static util.MenuUtils.getIntInput;
import static util.MenuUtils.handleSubMenu;

public class PlanMenuController {
    private static final PlanService planService = new PlanService();

    public void start() {
        int option;
        do {
            printMenuPlans();
            option = getIntInput("Seleccioná una opción: ");
            handleOptionMenuPlans(option);
        } while (option != 6);
    }

    // 📦 PLANES
    private static void printMenuPlans() {
        System.out.println("""
             ╔══════════════════════════════════╗
             ║        📋 MENÚ DE PLANES         ║
             ╠══════════════════════════════════╣
             ║ 1. 📄 Listar Planes              ║
             ║ 2. ✏️ Modificar Plan             ║
             ║ 3. ➕ Crear Plan                 ║
             ║ 4. ❌ Dar de baja un Plan        ║
             ║ 5. ✅ Reactivar un Plan          ║
             ║ 6. ↩️ Regresar al Menú anterior  ║
             ╚══════════════════════════════════╝
        """);
    }

    private static void handleOptionMenuPlans(int option) {
        switch (option) {
            case 1 -> handleSubMenu(PlanMenuController::printSubMenuListPlans, PlanMenuController::handleOptionSubMenuListPlans, 4);
            case 2 -> planService.updatePlan();
            case 3 -> planService.addPlan();
            case 4 -> planService.disablePlan();
            case 5 -> planService.reactivatePlan();
            case 6 -> {} // Volver
            default -> System.out.println("❗ Opción inválida.");
        }
    }

    private static void printSubMenuListPlans() {
        System.out.println("""
             ╔══════════════════════════════════╗
             ║        📋 VER PLANES             ║
             ╠══════════════════════════════════╣
             ║ 1. 📄 Planes Activos             ║
             ║ 2. 🔍 Planes Inactivos           ║
             ║ 3. ➕ Todos los planes           ║
             ║ 4. ↩️ Regresar al Menú anterior  ║
             ╚══════════════════════════════════╝
        """);
    }

    private static void handleOptionSubMenuListPlans(int option) {
        switch (option) {
            case 1 -> planService.listPlansByStatus(true);
            case 2 -> planService.listPlansByStatus(false);
            case 3 -> planService.listAllPlans();
            case 4 -> {} // Volver
            default -> System.out.println("❗ Opción inválida.");
        }
    }
}
