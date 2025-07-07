package service;

import mappers.ClientMapper;
import model.Client;
import repository.ClientRepository;
import repository.ClientRepositoryImpl;
import validations.ClientValidation;
import validations.GeneralValidation;

import java.util.List;
import java.util.Optional;

public class ClientService {

    private final ClientRepository clientRepository = new ClientRepositoryImpl();
    private final ClientValidation clientValidation = new ClientValidation();
    private final ClientMapper clientMapper = new ClientMapper();
    private final GeneralValidation generalValidation = new GeneralValidation();

    public void listClientsByStatus(boolean status) {
        System.out.printf("""
                ══════════════════════════════════
                  📋 Listado de Clientes %s
                ══════════════════════════════════
                %n
            """, status ? "Activos" : "Inactivos"
        );
        clientRepository.findByIsActive(status)
                .stream()
                .map(clientMapper::toDTO)
                .forEach(System.out::println);
        printSeparator();
    }

    public void listAllClients() {
        System.out.println("""
                ════════════════════════════════════
                  📋 Listado de Todos los Clientes
                ════════════════════════════════════
            """
        );
        clientRepository.findAll()
                .stream()
                .map(clientMapper::toDTO)
                .forEach(System.out::println);
        printSeparator();
    }

    public void findClientById() {
        int documentId = generalValidation.getIntInput("🔍 Ingrese el DNI del Cliente: ");
        Client client = clientRepository.findById(documentId);

        System.out.println(client != null ? "\n" + clientMapper.toDTO(client) : "\n❌ No hay cliente con ID: " + documentId);
        printSeparator();
    }

    public void findClientByName() {
        String name = generalValidation.getStringInput("🔍 Ingrese el Nombre del Cliente: ");
        List<Client> clients = clientRepository.findByName(name);

        if (!clients.isEmpty()) {
            System.out.println("\n✅ Clientes encontrados:");
            clients.stream()
                    .map(clientMapper::toDTO)
                    .forEach(System.out::println);
        } else {
            System.out.println("\n❌ No hay clientes con Nombre: " + name);
        }
        printSeparator();
    }

    public void findClientByLastName() {
        String lastName = generalValidation.getStringInput("🔍 Ingrese el Apellido del Cliente: ");
        List<Client> clients = clientRepository.findByLastName(lastName);

        if (!clients.isEmpty()) {
            System.out.println("\n✅ Clientes encontrados:");
            clients.stream()
                    .map(clientMapper::toDTO)
                    .forEach(System.out::println);
        } else {
            System.out.println("\n❌ No hay clientes con Apellido: " + lastName);
        }
        printSeparator();
    }

    public void findClientByEmail() {
        String email = clientValidation.getEmailInput("🔍 Ingrese el Email del Cliente: ");
        Optional<Client> client = clientRepository.findByEmail(email);

        client.ifPresentOrElse(
                c -> {
                    System.out.println("\n✅ Cliente encontrado:");
                    System.out.println(clientMapper.toDTO(c));
                },
                () -> System.out.println("\n❌ No hay clientes con el Email: " + email)
        );
        printSeparator();
    }

    public void addClient() {
        System.out.println("""
                ══════════════════════════════════
                    ➕ Agregar un nuevo Cliente
                ══════════════════════════════════
            """
        );

        int documentId = clientValidation.isDocumentIdDuplicated("DNI: ");
        String name = generalValidation.getStringInput("Nombre: ");
        String lastName = generalValidation.getStringInput("Apellido: ");
        String email = clientValidation.getEmailInput("Email: ");
        String phoneNumber = generalValidation.getStringInput("Celular: ");
        boolean isActive = true;

        Client client = new Client(
                documentId, name, lastName, email, phoneNumber,
                isActive, null
        );

        clientRepository.save(client);
        System.out.println("✅ Cliente agregado con éxito: \n" + clientMapper.toDTO(clientRepository.findById(documentId)));
        printSeparator();
    }

    public void updateClient() {
        System.out.println("""
                ══════════════════════════════════
                     ✏️ Modificar un Cliente
                ══════════════════════════════════
            """
        );

        int documentId = generalValidation.getIntInput("DNI del Cliente a modificar: ");
        Client client = clientRepository.findById(documentId);

        if (client == null) {
            System.out.println("❌ No se encontró un cliente con DNI: " + documentId);
            printSeparator();
            return;
        }

        System.out.println("ℹ️ Ingrese un '-' para NO modificar un campo");

        client.setName(generalValidation.getStringInput("Nombre: ", client.getName()));
        client.setLastName(generalValidation.getStringInput("Apellido: ", client.getLastName()));
        client.setEmail(clientValidation.getEmailInput("Email: ", client.getEmail()));
        client.setPhoneNumber(generalValidation.getStringInput("Celular: ", client.getPhoneNumber()));
        client.setActive(generalValidation.getStateInput("Estado (activo/inactivo): ", client.isActive()));

        clientRepository.update(client);
        System.out.println("✅ Cliente actualizado: \n" + clientMapper.toDTO(client));
        printSeparator();
    }

    public void deactivateClient() {
        System.out.println("""
                ══════════════════════════════════
                   ❌ Dar de baja a un Cliente
                ══════════════════════════════════
            """
        );

        int documentId = generalValidation.getIntInput("Ingrese el DNI del Cliente a dar de baja: ");
        Client client = clientRepository.findById(documentId);

        if (client == null) {
            System.out.println("❌ No se encontró registro de Cliente con DNI: " + documentId);
        } else {
            clientRepository.logicalDelete(documentId);
            System.out.println("✅ Cliente dado de baja (inactivado): \n" + clientMapper.toDTO(client));
        }
        printSeparator();
    }

    public void reactivateClient() {
        System.out.println("""
                ══════════════════════════════════
                     ✅ Reactivar Cliente
                ══════════════════════════════════
            """
        );

        int documentId = generalValidation.getIntInput("Ingrese el DNI del Cliente a reactivar: ");
        Client client = clientRepository.findById(documentId);

        if (client == null) {
            System.out.println("❌ No se encontró registro de Cliente con DNI: " + documentId);
        } else {
            if (client.isActive()) {
                System.out.println("⚠️ El cliente: " + documentId + " ya se encuentra activo.");
                return;
            }
            client.setActive(true);
            clientRepository.update(client);
            System.out.println("✅ Cliente reactivado: \n" + clientMapper.toDTO(client));
        }
        printSeparator();
    }

    private void printSeparator() {
        System.out.println();
        System.out.println("═════════════════════════════════════════════════════════");
    }
}

