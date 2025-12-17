package Model;

import javafx.beans.property.*;

public class RoomLab {
    private final IntegerProperty id;
    private final StringProperty name;
    private final StringProperty type; // "ROOM" or "LAB"
    private final IntegerProperty capacity;
    private final StringProperty status; // "AVAILABLE" or "OCCUPIED"

    public RoomLab(int id, String name, String type, int capacity, String status) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.type = new SimpleStringProperty(type);
        this.capacity = new SimpleIntegerProperty(capacity);
        this.status = new SimpleStringProperty(status);
    }

    // Getters
    public int getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public String getType() {
        return type.get();
    }

    public int getCapacity() {
        return capacity.get();
    }

    public String getStatus() {
        return status.get();
    }

    // Setters
    public void setId(int id) {
        this.id.set(id);
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public void setType(String type) {
        this.type.set(type);
    }

    public void setCapacity(int capacity) {
        this.capacity.set(capacity);
    }

    public void setStatus(String status) {
        this.status.set(status);
    }

    // Property getters (for TableView)
    public IntegerProperty idProperty() {
        return id;
    }

    public StringProperty nameProperty() {
        return name;
    }

    public StringProperty typeProperty() {
        return type;
    }

    public IntegerProperty capacityProperty() {
        return capacity;
    }

    public StringProperty statusProperty() {
        return status;
    }
}