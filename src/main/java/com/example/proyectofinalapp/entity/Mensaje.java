package com.example.proyectofinalapp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "mensajes")
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "remitente_id")
    private Usuario remitente;

    @ManyToOne
    @JoinColumn(name = "receptor_id")
    private Usuario receptor;

    private String contenido;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEnvio = new Date();

    private Boolean leido = false;

	public Mensaje() {
		super();
	}

	public Mensaje(Long id, Usuario remitente, Usuario receptor, String contenido, Date fechaEnvio, Boolean leido) {
		super();
		this.id = id;
		this.remitente = remitente;
		this.receptor = receptor;
		this.contenido = contenido;
		this.fechaEnvio = fechaEnvio;
		this.leido = leido;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getRemitente() {
		return remitente;
	}

	public void setRemitente(Usuario remitente) {
		this.remitente = remitente;
	}

	public Usuario getReceptor() {
		return receptor;
	}

	public void setReceptor(Usuario receptor) {
		this.receptor = receptor;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Date getFechaEnvio() {
		return fechaEnvio;
	}

	public void setFechaEnvio(Date fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	public Boolean getLeido() {
		return leido;
	}

	public void setLeido(Boolean leido) {
		this.leido = leido;
	}
    
}
