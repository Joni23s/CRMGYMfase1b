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
}
