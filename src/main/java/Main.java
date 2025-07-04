import service.ClientService;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ClientService clientService = new ClientService();

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
    }

    private static void printMenu() {
        System.out.println("""
             ╔═══════════════════════════════╗
             ║      📋 MENÚ CLIENTES         ║
             ╠═══════════════════════════════╣
             ║ 1. 📄 Listar clientes         ║
             ║ 2. 🔍 Buscar cliente          ║
             ║ 3. ➕ Agregar cliente         ║
             ║ 4. ✏️ Modificar cliente       ║
             ║ 5. ❌ Dar de baja cliente     ║
             ║ 6. ✅ Reactivar cliente       ║
             ║ 0. 🚪 Salir                   ║
             ╚═══════════════════════════════╝
        """);
    }

    private static void printSubMenuList() {
        System.out.println("""
             ╔═════════════════════════════════════╗
             ║         📄 LISTAR CLIENTES          ║
             ╠═════════════════════════════════════╣
             ║ 1. 🏃‍♂️   Clientes activos            ║
             ║ 2. 🧍‍♂️   Clientes inactivos          ║
             ║ 3. 🧍🏃‍➡️ Todos los Clientes          ║
             ║ 4. ↩️   Regresar al Menú anterior   ║
             ╚═════════════════════════════════════╝
        """);
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


    private static int getIntInput(String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            System.out.print("⚠️ Por favor, ingresá un número válido: ");
            scanner.next();
        }
        int input = scanner.nextInt();
        scanner.nextLine(); // limpia el \n que queda en el buffer
        return input;
    }

    private static void handleOptionMenu(int option) {
        switch (option) {
            case 1 ->{
                int subOption = 0;
                do{
                    printSubMenuList();
                    subOption = getIntInput("Seleccioná una opción: ");
                    handleOptionSubMenuList(subOption);
                } while(subOption != 4);
            }
            case 2 -> {
                int subOption = 0;
                do{
                printSubMenuFind();
                    subOption = getIntInput("Seleccioná una opción: ");
                    handleOptionSubMenuFind(subOption);
                } while(subOption != 5);
            }
            case 3 -> clientService.addClient();
            case 4 -> clientService.updateClient();
            case 5 -> clientService.disableClient();
            case 6 -> clientService.reactivateClient();
            case 0 -> exitMessage(); // Salida gestionada por exitMessage()
            default -> System.out.println("❗ Opción inválida.");
        }
    }

    private static void handleOptionSubMenuList(int option) {
        switch (option) {
            case 1 -> clientService.listClientsbyStatus(true);
            case 2 -> clientService.listClientsbyStatus(false);
            case 3 -> clientService.listAllClients();
            case 4 -> go();
            default -> System.out.println("❗ Opción inválida.");
        }
    }

    private static void handleOptionSubMenuFind(int option) {
        switch (option) {
            case 1 -> clientService.findClientById();
            case 2 -> clientService.findClientByName();
            case 3 -> clientService.findClientByLastName();
            case 4 -> clientService.findClientByEmail();
            case 5 -> go();
            default -> System.out.println("❗ Opción inválida.");
        }
    }

    private static void exitMessage() {
        System.out.println("""
                    ══════════════════════════════════
                     👋 ¡Gracias por usar CRMGYM!
                    ══════════════════════════════════
                """
        );
    }
}
