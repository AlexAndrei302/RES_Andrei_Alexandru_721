package controller;

import model.*;
import service.MissionControlService;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class MissionControlController {
    private MissionControlService service;

    public MissionControlController(MissionControlService service) {
        this.service = service;
    }

    public void run() {
        try {
            //TASK 1
            service.loadData("src/astronauts.json",
                    "src/events.json",
                    "src/supplies.json");

            System.out.println("Astronauts loaded: " + service.getAllAstronauts().size());
            System.out.println("Events loaded: " + service.getEventsCount());
            System.out.println("Supplies loaded: " + service.getSuppliesCount());

            for (Astronaut a : service.getAllAstronauts()) {
                System.out.println(a);
            }

            // --- TASK 2: Filter ---
            handleSpacecraftFilter();
        }}



    private void handleSpacecraftFilter() {
        System.out.print("Input Spacecraft: ");
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNext()) {
            String input = scanner.next();

            List<Astronaut> filtered = service.filterAstronauts(input, AstronautStatus.ACTIVE);
            filtered.forEach(System.out::println);
        }
    }
}