package com.cognizant.model;

public class WareHouse {
    private long id;
    private String name;
    private String location;
    private Employee employee;
    private float capacity;

    public WareHouse(long id, String name, String location, Employee employee, float capacity) {
        super();
        this.id = id;
        this.name = name;
        this.location = location;
        this.employee = employee;
        this.capacity = capacity;
    }

    public WareHouse() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public float getCapacity() {
        return capacity;
    }

    public void setCapacity(float capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "WareHouse [id=" + id + ", name=" + name + ", location=" + location + ", employee="
                + employee + ", capacity=" + capacity + "]";
    }

}