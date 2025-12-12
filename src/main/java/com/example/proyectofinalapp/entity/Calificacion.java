package com.example.proyectofinalapp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "calificaciones")
public class Calificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "calificador_id")
    private Usuario calificador;

    @ManyToOne
    @JoinColumn(name = "calificado_id")
    private Usuario calificado;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    private Double puntuacion;
    private String comentario;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha = new Date();

	public Calificacion() {
		super();
	}

	public Calificacion(Long id, Usuario calificador, Usuario calificado, Producto producto, Double puntuacion,
			String comentario, Date fecha) {
		super();
		this.id = id;
		this.calificador = calificador;
		this.calificado = calificado;
		this.producto = producto;
		this.puntuacion = puntuacion;
		this.comentario = comentario;
		this.fecha = fecha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getCalificador() {
		return calificador;
	}

	public void setCalificador(Usuario calificador) {
		this.calificador = calificador;
	}

	public Usuario getCalificado() {
		return calificado;
	}

	public void setCalificado(Usuario calificado) {
		this.calificado = calificado;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Double getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(Double puntuacion) {
		this.puntuacion = puntuacion;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
    
    
}
