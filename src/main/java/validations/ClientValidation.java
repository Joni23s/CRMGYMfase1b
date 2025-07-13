package validations;

import model.Client;
import repository.ClientRepository;
import repository.ClientRepositoryImpl;

import java.util.Scanner;
import java.util.regex.Pattern;

public class ClientValidation {
    private final ClientRepository clientRepository = new ClientRepositoryImpl();
    private final GeneralValidation generalValidation = new GeneralValidation();
    private final Scanner console = new Scanner(System.in);
    private final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private final Pattern PHONE_PATTERN = Pattern.compile("^\\+?\\d{7,15}$");
    private final Pattern NAME_PATTERN = Pattern.compile("^[A-Z][a-z]+$");

    public int isDocumentIdDuplicated(String message) {
        while (true) {
            int checkedId = generalValidation.getIntInput(message);

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
            int checkedId = generalValidation.getIntInput(message, client.getDocumentId());

            Client clientExists = clientRepository.findById(checkedId);
            if (clientExists == null || (clientExists.getDocumentId() == client.getDocumentId())) {
                return checkedId;
            }
            System.out.println("❌ Ya existe un cliente con DNI: " + checkedId);
            System.out.println("Intente con un DNI diferente.");
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

    public String getNameInput(String message) {
        String input;

        do {
            System.out.print(message);
            input = console.nextLine();

            if (NAME_PATTERN.matcher(input).matches()) {
                return input.toLowerCase();
            } else {
                System.out.println("Error: el formato del nombre y/o apellido no es válido");
            }

        }while (true);
    }

    public String getNameInput(String message, String currentValue) {
        String input;

        do {
            System.out.print(message);
            input = console.nextLine();

            if (input.equals("-")){
                return currentValue;
            }

            if (NAME_PATTERN.matcher(input).matches()) {
                return input.toLowerCase();
            } else {
                System.out.println("Error: el formato del nombre y/o apellido no es válido");
            }

        }while (true);
    }

    public String getPhoneInput(String message) {
        String input;

        do {
            System.out.print(message);
            input = console.nextLine().trim();

            if (PHONE_PATTERN.matcher(input).matches()) {
                return input;
            } else {
                System.out.println("❌ Error: el número de teléfono no es válido.");
                System.out.println("📌 Debe tener entre 7 y 15 dígitos, y puede comenzar con '+'.");
            }

        } while (true);
    }

    public String getPhoneInput(String message, String currentValue) {
        String input;

        do {
            System.out.print(message);
            input = console.nextLine().trim();

            if (input.equals("-")){
                return currentValue;
            }

            if (PHONE_PATTERN.matcher(input).matches()) {
                return input;
            } else {
                System.out.println("❌ Error: el número de teléfono no es válido.");
                System.out.println("📌 Debe tener entre 7 y 15 dígitos, y puede comenzar con '+'.");
            }

        } while (true);
    }
}
