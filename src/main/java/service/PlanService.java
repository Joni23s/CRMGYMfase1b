package service;

import dto.PlanDTO;
import mappers.PlanMapper;
import model.Plan;
import repository.PlanRepository;
import repository.PlanRepositoryImpl;
import validations.GeneralValidation;
import validations.PlanValidation;
import util.TablePrinterUtil;

import java.math.BigDecimal;
import java.util.List;

public class PlanService {

    private final PlanRepository planRepository = new PlanRepositoryImpl();
    private final GeneralValidation generalValidation = new GeneralValidation();
    private final PlanValidation planValidation = new PlanValidation();
    private final PlanMapper planMapper = new PlanMapper();

    public void listPlansByStatus(boolean status) {
        List<Plan> list = planRepository.findByIsActive(status);
        printPlans(list, "Planes " + (status ? "Activos" : "Inactivos"));
    }

    public void listAllPlans() {
        List<Plan> list = planRepository.findAll();
        printPlans(list, "de Todos los Planes");
    }

    public void updatePlan() {
        System.out.println("""
                ══════════════════════════════════
                     ✏️ Modificar un Plan
                ══════════════════════════════════
            """);

        int id = generalValidation.getIntInput("Id del Plan a modificar: ");
        Plan plan = planRepository.findById(id);

        if (plan == null) {
            System.out.println("❌ No se encontró un Plan con el Id: " + id);
            printSeparator();
            return;
        }

        System.out.println("ℹ️ Ingrese un '-' para NO modificar un campo");

        plan.setNamePlan(generalValidation.getStringInput("Nombre: ", plan.getNamePlan()));
        plan.setDaysEnabled(generalValidation.getIntInput("Días habilitados: ", plan.getDaysEnabled()));
        plan.setHoursEnabled(generalValidation.getIntInput("Horas habilitadas: ", plan.getHoursEnabled()));
        plan.setValue(planValidation.getValueInput("Ingrese el valor: ", plan.getValue()));
        plan.setNotes(generalValidation.getStringInput("Notas: ", plan.getNotes()));
        plan.setActive(generalValidation.getStateInput("Estado (activo/inactivo): ", plan.isActive()));

        planRepository.update(plan);

        System.out.println("✅ Plan actualizado:");
        TablePrinterUtil.printPlanRow(planMapper.toDTO(plan));
        printSeparator();
    }

    public void addPlan() {
        System.out.println("""
                ══════════════════════════════════
                    ➕ Agregar un nuevo Plan
                ══════════════════════════════════
            """);

        int id = generalValidation.getIntInput("Id del nuevo Plan: ");
        String namePlan = generalValidation.getStringInput("Nombre del Plan: ");
        int daysEnabled = generalValidation.getIntInput("Días habilitados: ");
        int hoursEnabled = generalValidation.getIntInput("Horas habilitadas: ");
        BigDecimal value = planValidation.getValueInput("Ingrese el valor: ");
        String notes = generalValidation.getStringInput("Notas: ");
        boolean isActive = true;

        Plan plan = new Plan(id, namePlan, daysEnabled, hoursEnabled, value, notes, isActive);

        planRepository.save(plan);

        System.out.println("✅ Plan agregado con éxito:");
        TablePrinterUtil.printPlanRow(planMapper.toDTO(plan));
        printSeparator();
    }

    public void disablePlan() {
        System.out.println("""
                ══════════════════════════════════
                   ❌ Dar de baja a un Plan
                ══════════════════════════════════
            """);

        int id = generalValidation.getIntInput("Ingrese el Id del Plan a dar de baja: ");
        Plan plan = planRepository.findById(id);

        if (plan == null) {
            System.out.println("❌ No se encontró registro de un Plan con Id " + id);
        } else {
            planRepository.logicalDelete(id);
            System.out.println("✅ Plan dado de baja:");
            TablePrinterUtil.printPlanRow(planMapper.toDTO(plan));
        }

        printSeparator();
    }

    public void reactivatePlan() {
        System.out.println("""
                ══════════════════════════════════
                     ✅ Reactivar Plan
                ══════════════════════════════════
            """);

        int id = generalValidation.getIntInput("Ingrese el Id del Plan a reactivar: ");
        Plan plan = planRepository.findById(id);

        if (plan == null) {
            System.out.println("❌ No se encontró registro de un Plan con Id: " + id);
        } else {
            if (plan.isActive()) {
                System.out.println("⚠️ El plan con Id : " + id + " ya se encuentra activo.");
                return;
            }
            plan.setActive(true);
            planRepository.update(plan);
            System.out.println("✅ Plan reactivado:");
            TablePrinterUtil.printPlanRow(planMapper.toDTO(plan));
        }

        printSeparator();
    }

    // 🧩 Método común para impresión de listas
    private void printPlans(List<Plan> list, String header) {
        if (list.isEmpty()) {
            System.out.println("❌ No hay planes " + header.toLowerCase() + ".");
        } else {
            System.out.printf("""
                ════════════════════════════════════
                  📋 Listado de %s
                ════════════════════════════════════
                %n""", header);

            List<PlanDTO> dtoList = list.stream()
                    .map(planMapper::toDTO)
                    .toList();

            TablePrinterUtil.printPlansTable(dtoList);
        }
        printSeparator();
    }

    private void printSeparator() {
        System.out.println();
        System.out.println("═════════════════════════════════════════════════════════");
    }
}
