import service.ClientService;
import service.PlanService;

import java.util.Scanner;
import java.util.function.Consumer;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ClientService clientService = new ClientService();
    private static final PlanService planService = new PlanService();

    public static void main(String[] args) {
        go();
    }

    private static void go() {
        int option;
        do {
            printMenu();
            option = getIntInput("Seleccioná una opción: ");
            handleOptionMenu(option);
        } while (option != 0);
        exitMessage();
    }

    //  Metodo para reutilizar sub menues
    private static void handleSubMenu(Runnable printMenu, Consumer<Integer> handleOption, int exitOption) {
        int subOption;
        do {
            printMenu.run();
            subOption = getIntInput("Seleccioná una opción: ");
            handleOption.accept(subOption);
        } while (subOption != exitOption);
    }

    // 📋 Menú principal
    private static void printMenu() {
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

    private static void handleOptionMenu(int option) {
        switch (option) {
            case 1 -> handleSubMenu(Main::printMenuClients, Main::handleOptionMenuClients, 7);
            case 2 -> handleSubMenu(Main::printMenuPlans, Main::handleOptionMenuPlans, 6);
            case 3 -> {
                System.out.println("🕓 Funcionalidad de historial aún no implementada.");
                printSeparator();
            }
            case 0 -> {} // Salida ya gestionada
            default -> System.out.println("❗ Opción inválida.");
        }
    }

    // 👥 CLIENTES
    private static void printMenuClients() {
        System.out.println("""
             ╔═════════════════════════════════╗
             ║      📋 MENÚ CLIENTES           ║
             ╠═════════════════════════════════╣
             ║ 1. 📄 Listar clientes           ║
             ║ 2. 🔍 Buscar cliente            ║
             ║ 3. ➕ Agregar cliente           ║
             ║ 4. ✏️ Modificar cliente         ║
             ║ 5. ❌ Dar de baja cliente       ║
             ║ 6. ✅ Reactivar cliente         ║
             ║ 7. ↩️ Regresar al Menú anterior ║
             ╚═════════════════════════════════╝
        """);
    }

    private static void handleOptionMenuClients(int option) {
        switch (option) {
            case 1 -> handleSubMenu(Main::printSubMenuList, Main::handleOptionSubMenuList, 4);
            case 2 -> handleSubMenu(Main::printSubMenuFind, Main::handleOptionSubMenuFind, 5);
            case 3 -> clientService.addClient();
            case 4 -> clientService.updateClient();
            case 5 -> clientService.deactivateClient();
            case 6 -> clientService.reactivateClient();
            case 7 -> {} // Volver
            default -> System.out.println("❗ Opción inválida.");
        }
    }

    private static void printSubMenuList() {
        System.out.println("""
             ╔═════════════════════════════════════╗
             ║         📄 LISTAR CLIENTES          ║
             ╠═════════════════════════════════════╣
             ║ 1. 🏃‍♂️   Clientes activos            ║
             ║ 2. 🧍‍♂️   Clientes inactivos          ║
             ║ 3. 🧍🏃‍ Todos los Clientes          ║
             ║ 4. ↩️   Regresar al Menú anterior   ║
             ╚═════════════════════════════════════╝
        """);
    }

    private static void handleOptionSubMenuList(int option) {
        switch (option) {
            case 1 -> clientService.listClientsByStatus(true);
            case 2 -> clientService.listClientsByStatus(false);
            case 3 -> clientService.listAllClients();
            case 4 -> {} // Volver
            default -> System.out.println("❗ Opción inválida.");
        }
    }

    private static void printSubMenuFind() {
        System.out.println("""
             ╔═════════════════════════════════════╗
             ║         📄 BUSCAR CLIENTES          ║
             ╠═════════════════════════════════════╣
             ║ 1. 🪪   Por DNI                     ║
             ║ 2. 🧑   Por nombre                  ║
             ║ 3. 👴️   Por apellido                ║
             ║ 4. 📨   Por mail                    ║
             ║ 5. ↩️   Regresar al Menú anterior   ║
             ╚═════════════════════════════════════╝
        """);
    }

    private static void handleOptionSubMenuFind(int option) {
        switch (option) {
            case 1 -> clientService.findClientById();
            case 2 -> clientService.findClientByName();
            case 3 -> clientService.findClientByLastName();
            case 4 -> clientService.findClientByEmail();
            case 5 -> {} // Volver
            default -> System.out.println("❗ Opción inválida.");
        }
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
            case 1 -> handleSubMenu(Main::printSubMenuListPlans, Main::handleOptionSubMenuListPlans, 4);
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
            case 1 -> planService.listPlansbyStatus(true);
            case 2 -> planService.listPlansbyStatus(false);
            case 3 -> planService.listAllPlans();
            case 4 -> {} // Volver
            default -> System.out.println("❗ Opción inválida.");
        }
    }

    // 🧩 Utilidades
    private static int getIntInput(String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            System.out.print("⚠️ Por favor, ingresá un número válido: ");
            scanner.next();
        }
        int input = scanner.nextInt();
        scanner.nextLine(); // Limpia el buffer
        return input;
    }

    private static void exitMessage() {
        System.out.println("""
                    ══════════════════════════════════
                     👋 ¡Gracias por usar CRMGYM!
                    ══════════════════════════════════
                """
        );
    }

    private static void printSeparator() {
        System.out.println();
        System.out.println("═════════════════════════════════════════════════════════");
    }
}
