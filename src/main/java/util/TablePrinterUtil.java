package util;


import dto.ClientDTO;
import dto.HistoricalPlanDTO;
import dto.PlanDTO;

import java.util.List;

public class TablePrinterUtil {

    public static void printClientsTable(List<ClientDTO> clients) {
        String formatRow = "| %-10s | %-15s | %-15s | %-25s | %-12s | %-8s | %-15s |%n";

        System.out.println("""
                ╔════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗
                ║                                                📋 Listado de Clientes                                                  ║
                ╠════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣
                """);

        System.out.printf(formatRow, "DNI", "Nombre", "Apellido", "Email", "Celular", "Estado", "Plan");
        System.out.println("╠════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");

        clients.forEach(dto ->
                System.out.printf(formatRow,
                        dto.getDocumentId(),
                        dto.getName(),
                        dto.getLastName(),
                        dto.getEmail(),
                        dto.getPhoneNumber(),
                        dto.getStatus(),
                        dto.getNamePlan() != null ? dto.getNamePlan() : "Sin Plan"
                )
        );

        System.out.println("╚════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
    }

    public static void printClientRow(ClientDTO client) {
        printClientsTable(List.of(client));
    }

    public static void printPlansTable(List<PlanDTO> plans) {
        String formatRow = "| %-8s | %-20s | %-12s | %-14s | %-10s | %-20s | %-8s |%n";

        System.out.println("""
        ╔══════════════════════════════════════════════════════════════════════════════════════════════════════════════════╗
        ║                                                📋 Listado de Planes                                              ║
        ╠══════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣
        """);

        System.out.printf(formatRow, "ID", "Nombre", "Días", "Horas", "Valor", "Notas", "Estado");
        System.out.println("╠══════════════════════════════════════════════════════════════════════════════════════════════════════════════════╣");

        plans.forEach(plan ->
                System.out.printf(formatRow,
                        plan.getIdPlan(),
                        plan.getNamePlan(),
                        plan.getDaysEnabled(),
                        plan.getHoursEnabled(),
                        plan.getValue(),
                        plan.getNotes() != null ? plan.getNotes() : "-",
                        plan.getStatus()
                )
        );

        System.out.println("╚══════════════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
    }

    public static void printPlanRow(PlanDTO plan) {
        printPlansTable(List.of(plan));
    }

    public static void printHistoryTable(List<HistoricalPlanDTO> history) {
        String formatRow = "| %-5s | %-12s | %-12s | %-8s | %-25s | %-20s |%n";

        System.out.println("""
        ╔══════════════════════════════════════════════════════════════════════════════════════════════════════════╗
        ║                                        📋 Historial de Planes                                           ║
        ╠══════════════════════════════════════════════════════════════════════════════════════════════════════════╣
        """);

        System.out.printf(formatRow, "ID", "Inicio", "Fin", "Estado", "Cliente", "Plan");
        System.out.println("╠══════════════════════════════════════════════════════════════════════════════════════════════════════════╣");

        history.forEach(h ->
                System.out.printf(formatRow,
                        h.getIdHistorical(),
                        h.getStartDate(),
                        h.getEndDate() != null ? h.getEndDate() : "-",
                        h.getIsActive(),
                        h.getClientName(),
                        h.getPlanName()
                )
        );

        System.out.println("╚══════════════════════════════════════════════════════════════════════════════════════════════════════════╝");
    }

    public static void printHistoryRow(HistoricalPlanDTO h) {
        printHistoryTable(List.of(h));
    }


}
