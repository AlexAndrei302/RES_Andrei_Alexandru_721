package model;

public class Supply {
    private int id;
    private int astronautId;
    private SupplyType type;
    private int value;

    public Supply(int id, int astronautId, SupplyType type, int value) {
        this.id = id;
        this.astronautId = astronautId;
        this.type = type;
        this.value = value;
    }

    public int getAstronautId() { return astronautId; }
    public int getValue() { return value; }
}