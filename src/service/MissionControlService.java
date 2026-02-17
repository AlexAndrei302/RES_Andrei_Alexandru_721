package service;

import model.*;
import repository.MissionRepository;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class MissionControlService {
    private MissionRepository repository;
    private List<Astronaut> astronauts;
    private List<MissionEvent> events;
    private List<Supply> supplies;

    public MissionControlService(MissionRepository repository) {
        this.repository = repository;
    }

    public void loadData(String aFile, String eFile, String sFile) throws IOException {
        this.astronauts = repository.loadAstronauts(aFile);
        this.events = repository.loadEvents(eFile);
        this.supplies = repository.loadSupplies(sFile);
    }

    public List<Astronaut> getAllAstronauts() { return astronauts; }
    public int getEventsCount() { return events.size(); }
    public int getSuppliesCount() { return supplies.size(); }

    // Task 2
    public List<Astronaut> filterAstronauts(String spacecraft, AstronautStatus status) {
        return astronauts.stream()
                .filter(a -> a.getSpacecraft().equalsIgnoreCase(spacecraft) && a.getStatus() == status)
                .collect(Collectors.toList());
    }

    // Task 3
    public List<Astronaut> getSortedAstronauts() {
        return astronauts.stream()
                .sorted(Comparator.comparingInt(Astronaut::getExperienceLevel).reversed()
                        .thenComparing(Astronaut::getName))
                .collect(Collectors.toList());
    }

    // Task 4

    public void exportReverseSortedAstronauts(String filename) throws IOException {
        List<Astronaut> list = new ArrayList<>(getSortedAstronauts());
        Collections.reverse(list);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Astronaut a : list) {
                writer.write(a.toString());
                writer.newLine();
            }
        }
    }

    // Task 5
    public int calculateComputedPoints(MissionEvent e) {
        int bp = e.getBasePoints();
        int day = e.getDay();
        switch (e.getType()) {
            case EVA:            return bp + (2 * day);
            case SYSTEM_FAILURE: return bp - 3;
            case SCIENCE:        return bp + (day % 4);
            case MEDICAL:        return bp;
            case COMMUNICATION:  return bp + 5;
            default:             return bp;
        }
    }

    public List<MissionEvent> getFirstNEvents(int n) {
        return events.stream().limit(n).collect(Collectors.toList());
    }

    //Task 6
    public int calculateTotalScore(int astronautId) {
        int eventPoints = events.stream()
                .filter(e -> e.getAstronautId() == astronautId)
                .mapToInt(this::calculateComputedPoints)
                .sum();

        int supplyPoints = supplies.stream()
                .filter(s -> s.getAstronautId() == astronautId)
                .mapToInt(Supply::getValue)
                .sum();

        return eventPoints + supplyPoints;
    }

    public List<Astronaut> getTopAstronauts(int limit) {
        return astronauts.stream()
                .sorted((a1, a2) -> {
                    int s1 = calculateTotalScore(a1.getId());
                    int s2 = calculateTotalScore(a2.getId());
                    if (s1 != s2) return Integer.compare(s2, s1);
                    return a1.getName().compareTo(a2.getName());
                })
                .limit(limit)
                .collect(Collectors.toList());
    }




}