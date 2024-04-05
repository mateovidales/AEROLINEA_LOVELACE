package entities;

import java.sql.Date;
import java.sql.Time;

public class Vuelo {
    private int id_vuelo;
    private String destino;
    private Date fecha_salida;
    private Time hora_salida;
    private int fk_id_avion;

    public Vuelo() {
    }

    public Vuelo(String destino, Date fechaSalida, Time horaSalida, int fk_id_avion) {
        this.destino = destino;
        this.fecha_salida = fechaSalida;
        this.hora_salida = horaSalida;
        this.fk_id_avion = fk_id_avion;
    }

    public int getId_vuelo() {
        return id_vuelo;
    }

    public void setId_vuelo(int id_vuelo) {
        this.id_vuelo = id_vuelo;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public Date getFecha_salida() {
        return fecha_salida;
    }

    public void setFecha_salida(Date fecha_salida) {
        this.fecha_salida = fecha_salida;
    }

    public Time getHora_salida() {
        return hora_salida;
    }

    public void setHora_salida(Time hora_salida) {
        this.hora_salida = hora_salida;
    }

    public int getFk_id_avion() {
        return fk_id_avion;
    }

    public void setFk_id_avion(int fk_id_avion) {
        this.fk_id_avion = fk_id_avion;
    }

    public Vuelo(int id_vuelo, String destino, Date fecha_salida, Time hora_salida, int fk_id_avion) {
        this.id_vuelo = id_vuelo;
        this.destino = destino;
        this.fecha_salida = fecha_salida;
        this.hora_salida = hora_salida;
        this.fk_id_avion = fk_id_avion;
    }

    @Override
    public String toString() {
        return "Vuelo{" +
                "id_vuelo=" + id_vuelo +
                ", destino='" + destino + '\'' +
                ", fecha_salida=" + fecha_salida +
                ", hora_salida=" + hora_salida +
                ", fk_id_avion='" + fk_id_avion+ '\'' +
                '}';
    }
}
