package controller;

import service.ClientService;

import java.util.Scanner;
import static util.MenuUtils.getIntInput;
import static util.MenuUtils.handleSubMenu;

public class ClientMenuController {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ClientService clientService = new ClientService();

    public void start() {
        int option;
        do {
            printMenuClients();
            option = getIntInput("Seleccioná una opción: ");
            handleOptionMenuClients(option);
        } while (option != 7);
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
            case 1 -> handleSubMenu(ClientMenuController::printSubMenuList, ClientMenuController::handleOptionSubMenuList, 4);
            case 2 -> handleSubMenu(ClientMenuController::printSubMenuFind, ClientMenuController::handleOptionSubMenuFind, 5);
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
}
