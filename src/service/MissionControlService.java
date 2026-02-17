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

}