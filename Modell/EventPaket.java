package Modell;
public class EventPaket {
    private int idEventPaket;
    private int idEvent;
    private int idPaket;

    public EventPaket() {}

    public EventPaket(int idEventPaket, int idEvent, int idPaket) {
        this.idEventPaket = idEventPaket;
        this.idEvent = idEvent;
        this.idPaket = idPaket;
    }

    public int getIdEventPaket() {
        return idEventPaket;
    }

    public void setIdEventPaket(int idEventPaket) {
        this.idEventPaket = idEventPaket;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public int getIdPaket() {
        return idPaket;
    }

    public void setIdPaket(int idPaket) {
        this.idPaket = idPaket;
    }
}
