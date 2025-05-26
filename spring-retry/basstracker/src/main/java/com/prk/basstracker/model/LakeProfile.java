package com.prk.basstracker.model;

import java.util.UUID;

public class LakeProfile {
    private UUID id;
    private String name;
    private String location;
    private double areaSqKm;
    private double depthMeters;
    private String type;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getAreaSqKm() {
        return areaSqKm;
    }

    public void setAreaSqKm(double areaSqKm) {
        this.areaSqKm = areaSqKm;
    }

    public double getDepthMeters() {
        return depthMeters;
    }

    public void setDepthMeters(double depthMeters) {
        this.depthMeters = depthMeters;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
