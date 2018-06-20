package schedule.controller.model;

/**
 * @author Rudkov Andrey
 * class {@link StationSearch} extend two String fields for
 * search realisation without creating and using {@link schedule.model.Station} object
 */

public class StationSearch {
    private String arrivalStation;
    private String departureStation;

    public String getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(String arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    public String getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(String departureStation) {
        this.departureStation = departureStation;
    }
}
