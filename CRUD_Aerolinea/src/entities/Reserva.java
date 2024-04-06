package entities;

import java.sql.Date;

public class Reserva {
    private int id_reserva;
    private Date fecha_reserva;
    private String asiento;
    private int fk_id_pasajero;
    private int fk_id_vuelo;
    private String pasajero;
    private String vuelo;

    public Reserva() {
    }

    public Reserva(Date fecha_reserva, String asiento, int fk_id_pasajero, int fk_id_vuelo) {
        this.fecha_reserva = fecha_reserva;
        this.asiento = asiento;
        this.fk_id_pasajero = fk_id_pasajero;
        this.fk_id_vuelo = fk_id_vuelo;
    }

    public int getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(int id_reserva) {
        this.id_reserva = id_reserva;
    }

    public Date getFecha_reserva() {
        return fecha_reserva;
    }

    public void setFecha_reserva(Date fecha_reserva) {
        this.fecha_reserva = fecha_reserva;
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }

    public int getFk_id_pasajero() {
        return fk_id_pasajero;
    }

    public void setFk_id_pasajero(int fk_id_pasajero) {
        this.fk_id_pasajero = fk_id_pasajero;
    }

    public int getFk_id_vuelo() {
        return fk_id_vuelo;
    }

    public void setFk_id_vuelo(int fk_id_vuelo) {
        this.fk_id_vuelo = fk_id_vuelo;
    }

    public String getPasajero() {
        return pasajero;
    }

    public void setPasajero(String pasajero) {
        this.pasajero = pasajero;
    }

    public String getVuelo() {
        return vuelo;
    }

    public void setVuelo(String vuelo) {
        this.vuelo = vuelo;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "id_reserva=" + id_reserva +
                ", fecha_reserva=" + fecha_reserva +
                ", asiento='" + asiento + '\'' +
                ", pasajero='" + pasajero + '\'' +
                ", vuelo='" + vuelo + '\'' +
                '}';
    }
}
