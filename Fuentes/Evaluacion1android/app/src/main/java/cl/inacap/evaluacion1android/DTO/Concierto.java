package cl.inacap.evaluacion1android.DTO;

import java.util.Date;

public class Concierto {
    private String nombreArtista;
    private Date fechaEvento;
    private String generoMusical;
    private int valorEntrada;
    private int calificacion;

    public String getNombreArtista() {
        return nombreArtista;
    }

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public String getGeneroMusical() {
        return generoMusical;
    }

    public int getValorEntrada() {
        return valorEntrada;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setNombreArtista(String nombreArtista) {
        this.nombreArtista = nombreArtista;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public void setGeneroMusical(String generoMusical) {
        this.generoMusical = generoMusical;
    }

    public void setValorEntrada(int valorEntrada) {
        this.valorEntrada = valorEntrada;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }
}
