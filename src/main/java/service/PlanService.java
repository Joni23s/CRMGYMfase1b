package service;

import mappers.PlanMapper;
import repository.PlanRepository;
import repository.PlanRepositoryImpl;
import validations.PlanValidation;

public class PlanService {

    private final PlanRepository planRepository = new PlanRepositoryImpl();
    private final PlanValidation planValidation = new PlanValidation();
    private final PlanMapper planMapper = new PlanMapper();

    public void listPlansbyStatus(boolean status) {
        System.out.printf("""
                    ══════════════════════════════════
                      📋 Listado de Planes %s
                    ══════════════════════════════════
                    %n
                """, status ? "Activos" : "Inactivos"
        );
        planRepository.findByIsActive(status)
                .stream()
                .map(planMapper::toDTO)
                .forEach(System.out::println);
        printSeparator();
    }

    public void listAllPlans() {
        System.out.println("""
                    ════════════════════════════════════
                      📋 Listado de Todos los Clientes
                    ════════════════════════════════════
                """
        );
        planRepository.findAll()
                .stream()
                .map(planMapper::toDTO)
                .forEach(System.out::println);
        printSeparator();
    }

    private void printSeparator() {
        System.out.println();
        System.out.println("═════════════════════════════════════════════════════════");
    }
}
