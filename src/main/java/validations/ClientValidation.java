package validations;

import model.Client;
import repository.ClientRepository;
import repository.ClientRepositoryImpl;

import java.util.Scanner;
import java.util.regex.Pattern;

public class ClientValidation {
    private final ClientRepository clientRepository = new ClientRepositoryImpl();
    private final Scanner console = new Scanner(System.in);
    private final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    public int isDocumentIdDuplicated(String message) {
        while (true) {
            int checkedId = getIntInput(message);

            Client clientExists = clientRepository.findById(checkedId);
            if (clientExists == null) {
                return checkedId;
            }
            System.out.println("❌ Ya existe un cliente con DNI: " + checkedId);
            System.out.println("Intente con un DNI diferente.");
        }
    }

    public int isDocumentIdDuplicated(String message, Client client) {
        while (true) {
            int checkedId = getIntInput(message, client.getDocumentId());

            Client clientExists = clientRepository.findById(checkedId);
            if (clientExists == null || (clientExists.getDocumentId() == client.getDocumentId())) {
                return checkedId;
            }
            System.out.println("❌ Ya existe un cliente con DNI: " + checkedId);
            System.out.println("Intente con un DNI diferente.");
        }
    }

    public int getIntInput(String message) {
        while (true) {
            System.out.print(message);

            try {
                return Integer.parseInt(console.nextLine().trim());

            } catch (NumberFormatException e) {
                System.out.println("Error: Ingresa un número válido.");
            }
        }
    }

    public int getIntInput(String message, int currentValue) {
        while (true) {
            System.out.print(message);
            String input = console.nextLine().trim();
            if (input.equals("-")){
                return currentValue;
            }

            try {
                return Integer.parseInt(input);

            } catch (NumberFormatException e) {
                System.out.println("Error: Ingresa un número válido.");
            }
        }
    }

    public String getStringInput(String message) {
        String input;
        while (true) {
            System.out.print(message);
            input = console.nextLine().trim();

            if (!input.isBlank()) {
                return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
            } else {
                System.out.println("Error: el campo no puede estar en blanco o vació.");
            }

        }
    }

    public String getStringInput(String message, String currentValue) {
        String input;
        while (true) {
            System.out.print(message);
            input = console.nextLine().trim();

            if (input.equals("-")){
                return currentValue;
            }

            if (!input.isBlank()) {
                return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
            } else {
                System.out.println("Error: el campo no puede estar en blanco o vació.");
            }

        }
    }

    public String getEmailInput(String message) {
        String input;

        do {
            System.out.print(message);
            input = console.nextLine();

            if (EMAIL_PATTERN.matcher(input).matches()) {
                return input.toLowerCase();
            } else {
                System.out.println("Error: el formato del correo electrónico no es válido");
            }

        }while (true);
    }

    public String getEmailInput(String message, String currentValue) {
        String input;

        do {
            System.out.print(message);
            input = console.nextLine();

            if (input.equals("-")){
                return currentValue;
            }

            if (EMAIL_PATTERN.matcher(input).matches()) {
                return input.toLowerCase();
            } else {
                System.out.println("Error: el formato del correo electrónico no es válido");
            }

        }while (true);
    }

    public long getLongInput(String message) {
        while (true) {
            System.out.print(message);
            try {
                return Long.parseLong(console.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingresa un número válido.");
            }
        }
    }

    public long getLongInput(String message, Long currentValue) {
        while (true) {
            System.out.print(message);

            String input = console.nextLine().trim();
            if (input.equals("-")){
                return currentValue;
            }
            try {
                return Long.parseLong(input);
            } catch (NumberFormatException e) {
                System.out.println("Error: Ingresa un número válido.");
            }
        }
    }

    public boolean getStateInput(String message, boolean currentValue) {
        while (true) {
            System.out.print(message);
            String input = console.nextLine().trim().toLowerCase();

            if (input.equals("-")) {
                return currentValue;
            } else if (input.equalsIgnoreCase("activo") || input.equalsIgnoreCase("inactivo")) {

                return input.equals("activo");
            } else {
                System.out.println("Error: ingresa 'activo', 'inactivo' o '-' para mantener el valor actual.");
            }
        }
    }
}
