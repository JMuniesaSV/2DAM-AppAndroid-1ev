package com.svalero.transporteApp.domain;

import android.os.Parcel;
import android.os.Parcelable;
import java.time.LocalDate;

public class Vehiculo implements Parcelable {

    private int id;
    private String matricula;
    private String marca;
    private String modelo;
    private Boolean disponible;
    private Float capacidadCarga;
    private LocalDate fechaFabricacion;
    private double latitud;
    private double longitud;

    public Vehiculo(String matricula, String marca, String modelo, Float capacidadCarga, double latitud, double longitud) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.disponible = disponible;
        this.capacidadCarga = capacidadCarga;
        this.fechaFabricacion = fechaFabricacion;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    protected Vehiculo(Parcel in) {
        id = in.readInt();
        matricula = in.readString();
        marca = in.readString();
        modelo = in.readString();
        disponible = in.readByte() != 0;
        capacidadCarga = in.readFloat();
        latitud = in.readDouble();
        longitud = in.readDouble();
    }

    public static final Creator<Vehiculo> CREATOR = new Creator<Vehiculo>() {
        @Override
        public Vehiculo createFromParcel(Parcel in) {
            return new Vehiculo(in);
        }

        @Override
        public Vehiculo[] newArray(int size) {
            return new Vehiculo[size];
        }
    };

    public int getId() {
        return id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public Float getCapacidadCarga() {
        return capacidadCarga;
    }

    public void setCapacidadCarga(Float capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    public LocalDate getFechaFabricacion() {
        return fechaFabricacion;
    }

    public void setFechaFabricacion(LocalDate fechaFabricacion) {
        this.fechaFabricacion = fechaFabricacion;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(matricula);
        dest.writeString(marca);
        dest.writeString(modelo);
        dest.writeByte((byte) (disponible ? 1 : 0));
        dest.writeFloat(capacidadCarga);
        dest.writeDouble(latitud);
        dest.writeDouble(longitud);
    }

}
