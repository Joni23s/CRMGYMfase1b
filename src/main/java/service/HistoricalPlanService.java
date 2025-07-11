package service;

import dto.HistoricalPlanDTO;
import mappers.HistoricalPlanMapper;
import model.HistoricalPlan;
import repository.HistoricalPlanRepository;
import repository.HistoricalPlanRepositoryImpl;
import validations.GeneralValidation;
import util.TablePrinterUtil;

import java.util.List;

public class HistoricalPlanService {

    private final HistoricalPlanRepository historicalPlanRepo = new HistoricalPlanRepositoryImpl();
    private final HistoricalPlanMapper mapper = new HistoricalPlanMapper();
    private final GeneralValidation generalValidation = new GeneralValidation();

    public void listAllHistory() {
        List<HistoricalPlan> list = historicalPlanRepo.findAll();
        printHistory(list, "de Planes");
    }

    public void listByClient() {
        int id = generalValidation.getIntInput("🔍 Ingresá el DNI del Cliente: ");
        List<HistoricalPlan> list = historicalPlanRepo.findByClient(id);
        printHistory(list, "del Cliente " + id);
    }

    public void listByPlan() {
        int id = generalValidation.getIntInput("🔍 Ingresá el ID del Plan: ");
        List<HistoricalPlan> list = historicalPlanRepo.findByPlan(id);
        printHistory(list, "del Plan " + id);
    }

    public void listByStatus(boolean status) {
        List<HistoricalPlan> list = historicalPlanRepo.findByIsActive(status);
        String estado = status ? "Activos" : "Inactivos";
        printHistory(list, "con Estado: " + estado);
    }

    // 🧩 Método reutilizable para imprimir con encabezado
    private void printHistory(List<HistoricalPlan> list, String header) {
        if (list.isEmpty()) {
            System.out.println("❌ No hay registros " + header.toLowerCase() + ".");
        } else {
            System.out.printf("""
                ════════════════════════════════════
                  📋 Historial %s
                ════════════════════════════════════
                %n""", header);

            List<HistoricalPlanDTO> dtoList = list
                    .stream()
                    .map(mapper::toDTO)
                    .toList();

            TablePrinterUtil.printHistoryTable(dtoList);
        }
        printSeparator();
    }

    private void printSeparator() {
        System.out.println();
        System.out.println("═════════════════════════════════════════════════════════");
    }
}
