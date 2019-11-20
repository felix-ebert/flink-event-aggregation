package model;

public class GasStationEvent {

    private String fuelType;
    private Double numOfLitres;
    private Long createdAt;

    public GasStationEvent(String fuelType, Double numOfLitres, Long createdAt) {
        this.fuelType = fuelType;
        this.numOfLitres = numOfLitres;
        this.createdAt = createdAt;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Double getNumOfLitres() {
        return numOfLitres;
    }

    public void setNumOfLitres(Double numOfLitres) {
        this.numOfLitres = numOfLitres;
    }

    public Long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Long createdAt) {
        this.createdAt = createdAt;
    }
}
