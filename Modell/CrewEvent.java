package Modell;
public class CrewEvent {
    private int idCrewEvent;
    private int idEvent;
    private int idCrew;

    public CrewEvent() {}

    public CrewEvent(int idCrewEvent, int idEvent, int idCrew) {
        this.idCrewEvent = idCrewEvent;
        this.idEvent = idEvent;
        this.idCrew = idCrew;
    }

    public int getIdCrewEvent() {
        return idCrewEvent;
    }

    public void setIdCrewEvent(int idCrewEvent) {
        this.idCrewEvent = idCrewEvent;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public int getIdCrew() {
        return idCrew;
    }

    public void setIdCrew(int idCrew) {
        this.idCrew = idCrew;
    }
}
