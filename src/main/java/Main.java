
import service.ClientService;

import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ClientService clientService = new ClientService();

    public static void main(String[] args) {
        int option;
        do {
            printMenu();
            option = getIntInput("Seleccioná una opción: ");
            handleOption(option);
        } while (option != 0);
    }

    private static void printMenu() {
        System.out.println("\n╔═══════════════════════════════╗");
        System.out.println("║      📋 MENÚ CLIENTES         ║");
        System.out.println("╠═══════════════════════════════╣");
        System.out.println("║ 1. 📄 Listar clientes         ║");
        System.out.println("║ 2. 🔍 Buscar cliente          ║");
        System.out.println("║ 3. ➕ Agregar cliente          ║");
        System.out.println("║ 4. ✏️  Modificar cliente       ║");
        System.out.println("║ 5. ❌ Eliminar cliente         ║");
        System.out.println("║ 0. 🚪 Salir                   ║");
        System.out.println("╚═══════════════════════════════╝");
    }

    private static int getIntInput(String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            System.out.print("⚠️ Por favor, ingresá un número válido: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static void handleOption(int option) {
        switch (option) {
            case 1 -> clientService.listClients();
            case 2 -> clientService.findClient();
            case 3 -> clientService.addClient();
            case 4 -> clientService.updateClient();
            case 5 -> clientService.deleteClient();
            case 0 -> System.out.println("👋 ¡Hasta luego!");
            default -> System.out.println("❗ Opción inválida.");
        }
    }
}
