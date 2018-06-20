package schedule.controller.model;

/**
 * @author Rudkov Andrey
 * class {@link StationsFromTo} helps for
 * search stations without real object {@link schedule.model.Station} name. Class use similar
 * fields, which can be easily obtained from
 * jsp ModelAttribute
 */
public class StationsFromTo {
    private String stationFrom;
    private String stationTo;

    public String getStationFrom() {
        return stationFrom;
    }

    public void setStationFrom(String stationFrom) {
        this.stationFrom = stationFrom;
    }

    public String getStationTo() {
        return stationTo;
    }

    public void setStationTo(String stationTo) {
        this.stationTo = stationTo;
    }
}
