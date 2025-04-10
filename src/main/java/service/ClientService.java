package service;

import model.Client;
import repository.ClientRepository;
import repository.ClientRepositoryImpl;
import validations.ClientValidation;

    public class ClientService {

        private final ClientRepository clientRepository = new ClientRepositoryImpl();
        private final ClientValidation clientValidation = new ClientValidation();

        public ClientService() {}

        public void listClients() {
            System.out.println("\t📋 Listado de Clientes ");
            clientRepository.findAll().forEach(System.out::println);
            printSeparator();
        }

        public void findClient() {
            int documentId = clientValidation.getIntInput("🔍 Ingrese el DNI del Cliente: ");
            Client client = clientRepository.findById(documentId);

            System.out.println(client != null ? "\n" + client : "\n❌ Cliente no encontrado.");
            printSeparator();
        }

        public void addClient() {
            System.out.println("➕ Agregar un nuevo Cliente");

            int documentId = clientValidation.isDocumentIdDuplicated("DNI: ");
            String name = clientValidation.getStringInput("Nombre: ");
            String lastName = clientValidation.getStringInput("Apellido: ");
            String email = clientValidation.getEmailInput("Email: ");
            String phoneNumber = clientValidation.getStringInput("Celular: ");
            boolean isActive = true;

            Client client = new Client(documentId, name, lastName, email, phoneNumber, isActive, null);
            clientRepository.save(client);

            System.out.println("✅ Cliente agregado con éxito: \n" + clientRepository.findById(documentId));
            printSeparator();
        }

        public void updateClient() {
            System.out.println("✏️ Modificar un Cliente");

            int documentId = clientValidation.getIntInput("DNI del Cliente a modificar: ");
            Client client = clientRepository.findById(documentId);

            if (client == null) {
                System.out.println("❌ No se encontró un cliente con DNI: " + documentId);
                printSeparator();
                return;
            }

            System.out.println("ℹ️ Ingrese un '-' para NO modificar un campo");

            client.setDocumentId(clientValidation.isDocumentIdDuplicated("Nuevo DNI: ", client));
            client.setName(clientValidation.getStringInput("Nombre: ", client.getName()));
            client.setLastName(clientValidation.getStringInput("Apellido: ", client.getLastName()));
            client.setEmail(clientValidation.getEmailInput("Email: ", client.getEmail()));
            client.setPhoneNumber(clientValidation.getStringInput("Celular: ", client.getPhoneNumber()));
            client.setActive(clientValidation.getStateInput("Estado (activo/inactivo): ", client.isActive()));

            clientRepository.update(client);
            System.out.println("✅ Cliente actualizado: \n" + client);
            printSeparator();
        }

        public void deleteClient() {
            System.out.println("❌ Eliminar un Cliente");

            int documentId = clientValidation.getIntInput("Ingrese el DNI del Cliente a eliminar: ");
            Client client = clientRepository.findById(documentId);

            if (client == null) {
                System.out.println("❌ No se encontró registro de Cliente con DNI: " + documentId);
            } else {
                clientRepository.delete(documentId);
                System.out.println("✅ Cliente eliminado con éxito: \n" + client);
            }
            printSeparator();
        }

        private void printSeparator() {
            System.out.println("═════════════════════════════════════════════════════════");
        }
    }
