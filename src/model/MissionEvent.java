package model;

public class MissionEvent {
    private int id;
    private int astronautId;
    private int day;
    private MissionEventType type;
    private int basePoints;

    public MissionEvent(int id, int astronautId, int day, MissionEventType type, int basePoints) {
        this.id = id;
        this.astronautId = astronautId;
        this.day = day;
        this.type = type;
        this.basePoints = basePoints;
    }

    public int getId() { return id; }
    public int getAstronautId() { return astronautId; }
    public int getDay() { return day; }
    public MissionEventType getType() { return type; }
    public int getBasePoints() { return basePoints; }
}