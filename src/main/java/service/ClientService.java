package service;

import dto.ClientDTO;
import mappers.ClientMapper;
import model.Client;
import model.HistoricalPlan;
import model.Plan;
import repository.*;
import validations.ClientValidation;
import validations.GeneralValidation;
import util.TablePrinterUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ClientService {

    private final ClientRepository clientRepository = new ClientRepositoryImpl();
    private final ClientValidation clientValidation = new ClientValidation();
    private final ClientMapper clientMapper = new ClientMapper();
    private final GeneralValidation generalValidation = new GeneralValidation();
    private final PlanRepository planRepository = new PlanRepositoryImpl();
    private final HistoricalPlanRepository historicalPlanRepository = new HistoricalPlanRepositoryImpl();

    public void listClientsByStatus(boolean status) {
        List<Client> list = clientRepository.findByIsActive(status);
        printClients(list, "Clientes " + (status ? "Activos" : "Inactivos"));
    }

    public void listAllClients() {
        List<Client> list = clientRepository.findAll();
        printClients(list, "de Todos los Clientes");
    }

    public void findClientById() {
        int documentId = generalValidation.getIntInput("🔍 Ingrese el DNI del Cliente: ");
        Client client = clientRepository.findById(documentId);

        if (client != null) {
            TablePrinterUtil.printClientRow(clientMapper.toDTO(client));
        } else {
            System.out.println("\n❌ No hay cliente con ID: " + documentId);
        }

        printSeparator();
    }

    public void findClientByName() {
        String name = generalValidation.getStringInput("🔍 Ingrese el Nombre del Cliente: ");
        List<Client> list = clientRepository.findByName(name);
        printClients(list, "con nombre: " + name);
    }

    public void findClientByLastName() {
        String lastName = generalValidation.getStringInput("🔍 Ingrese el Apellido del Cliente: ");
        List<Client> list = clientRepository.findByLastName(lastName);
        printClients(list, "con apellido: " + lastName);
    }

    public void findClientByEmail() {
        String email = clientValidation.getEmailInput("🔍 Ingrese el Email del Cliente: ");
        Optional<Client> client = clientRepository.findByEmail(email);

        client.ifPresentOrElse(
                c -> {
                    System.out.println("\n✅ Cliente encontrado:");
                    TablePrinterUtil.printClientRow(clientMapper.toDTO(c));
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
                """);

        int documentId = clientValidation.isDocumentIdDuplicated("DNI: ");
        String name = generalValidation.getStringInput("Nombre: ");
        String lastName = generalValidation.getStringInput("Apellido: ");
        String email = clientValidation.getEmailInput("Email: ");
        String phoneNumber = generalValidation.getStringInput("Celular: ");
        boolean isActive = true;

        int planId = generalValidation.getIntInput("Id del plan que se asignará al cliente: ");
        Plan plan = planRepository.findById(planId);

        if (plan == null || !plan.isActive()) {
            System.out.println("❌ El plan no existe o está inactivo.");
            return;
        }

        Client client = new Client(documentId, name, lastName, email, phoneNumber, isActive, plan);
        clientRepository.save(client);

        createHistoricalPlan(client, plan);

        System.out.println("✅ Cliente agregado con éxito:");
        TablePrinterUtil.printClientRow(clientMapper.toDTO(client));
        printSeparator();
    }

    public void updateClient() {
        System.out.println("""
                    ══════════════════════════════════
                         ✏️ Modificar un Cliente
                    ══════════════════════════════════
                """);

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

        int planId = generalValidation.getIntInput("Id del nuevo Plan: ");
        Plan plan = planRepository.findById(planId);

        if (plan == null || !plan.isActive()) {
            System.out.println("❌ El plan no existe o está inactivo.");
            return;
        }

        List<HistoricalPlan> historial = client.getHistoricalPlans();
        if (!historial.isEmpty()) {
            HistoricalPlan anterior = historial.getLast();
            anterior.setEndDate(LocalDate.now());
            anterior.setActive(false);
            historicalPlanRepository.update(anterior);
        }

        client.setCurrentPlan(plan);
        clientRepository.update(client);

        createHistoricalPlan(client, plan);

        System.out.println("✅ Cliente actualizado:");
        TablePrinterUtil.printClientRow(clientMapper.toDTO(client));
        printSeparator();
    }

    public void deactivateClient() {
        System.out.println("""
                    ══════════════════════════════════
                       ❌ Dar de baja a un Cliente
                    ══════════════════════════════════
                """);

        int documentId = generalValidation.getIntInput("Ingrese el DNI del Cliente a dar de baja: ");
        Client client = clientRepository.findById(documentId);

        if (client == null) {
            System.out.println("❌ No se encontró registro de Cliente con DNI: " + documentId);
        } else {
            clientRepository.logicalDelete(documentId);

            List<HistoricalPlan> historial = client.getHistoricalPlans();
            if (!historial.isEmpty()) {
                HistoricalPlan actual = historial.getLast();
                historicalPlanRepository.logicalDelete(actual.getIdHistorical());
            }

            System.out.println("✅ Cliente dado de baja:");
            TablePrinterUtil.printClientRow(clientMapper.toDTO(client));
        }

        printSeparator();
    }

    public void reactivateClient() {
        System.out.println("""
                    ══════════════════════════════════
                         ✅ Reactivar Cliente
                    ══════════════════════════════════
                """);

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
            System.out.println("✅ Cliente reactivado:");
            TablePrinterUtil.printClientRow(clientMapper.toDTO(client));
        }

        printSeparator();
    }

    // 🧩 Utilidad común para imprimir listas
    private void printClients(List<Client> list, String header) {
        if (list.isEmpty()) {
            System.out.println("❌ No hay clientes " + header.toLowerCase() + ".");
        } else {
            System.out.printf("""
                ════════════════════════════════════
                  📋 Listado de %s
                ════════════════════════════════════
                %n""", header);

            List<ClientDTO> dtoList = list.stream()
                    .map(clientMapper::toDTO)
                    .toList();

            TablePrinterUtil.printClientsTable(dtoList);
        }
        printSeparator();
    }

    private void createHistoricalPlan(Client client, Plan plan) {
        HistoricalPlan historical = new HistoricalPlan();
        historical.setStartDate(LocalDate.now());
        historical.setEndDate(null);
        historical.setActive(true);
        historical.setClient(client);
        historical.setPlan(plan);
        historicalPlanRepository.save(historical);
    }

    private void printSeparator() {
        System.out.println();
        System.out.println("═════════════════════════════════════════════════════════");
    }
}
