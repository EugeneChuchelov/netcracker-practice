package buildings.exceptions;

public class BuildingUnderArrestException extends Exception {
    public BuildingUnderArrestException() {
        super();
    }

    public BuildingUnderArrestException(String message) {
        super(message);
    }

    public BuildingUnderArrestException(Throwable cause) {
        super(cause);
    }
}
