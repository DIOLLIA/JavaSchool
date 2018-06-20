package schedule.controller.model;

public class ScheduleToSend {
    private String station;
    private String depTime;
    private String arrTime;
    private int train;
    private int routeDailyId;
    private int stationOrder;

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public String getDepTime() {
        return depTime;
    }

    public void setDepTime(String depTime) {
        this.depTime = depTime;
    }

    public int getTrain() {
        return train;
    }

    public void setTrain(int train) {
        this.train = train;
    }

    public int getRouteDailyId() {
        return routeDailyId;
    }

    public void setRouteDailyId(int routeDailyId) {
        this.routeDailyId = routeDailyId;
    }

    public int getStationOrder() {
        return stationOrder;
    }

    public void setStationOrder(int stationOrder) {
        this.stationOrder = stationOrder;
    }

    public String getArrTime() {
        return arrTime;
    }

    public void setArrTime(String arrTime) {
        this.arrTime = arrTime;
    }
}
