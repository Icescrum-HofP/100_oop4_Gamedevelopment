package sql;

public class Questions {

    private String frage;
    private String antwort;
    private int id;
    private int punkte;

    public Questions(int id_, String frage_, String antwort_, int punkte_) {
        id = id_;
        punkte = punkte_;
        frage = frage_;
        antwort = antwort_;
    }


//    Getter&setter


    public String getFrage() {
        return frage;
    }

    public void setFrage(String frage) {
        this.frage = frage;
    }

    public String getAntwort() {
        return antwort;
    }

    public void setAntwort(String antwort) {
        this.antwort = antwort;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPunkte() {
        return punkte;
    }

    public void setPunkte(int punkte) {
        this.punkte = punkte;
    }
}
