package com.sige.entidad;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "auditoria")
public class Auditoria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	@Column(name = "campoclaveprimaria")
	private String campoClavePrimaria;

	@Column(name = "fechaactualizacion")
	private Timestamp fechaActualizacion;

	private String ip;

	private String motivo;

	@Column(name = "nombrecampo")
	private String nombreCampo;

	@Column(name = "nombretabla")
	private String nombreTabla;

	@Column(name = "nombreusuario")
	private String nombreUsuario;

	private String pc;

	private String tipo;

	@Column(name = "valorantiguo")
	private String valorAntiguo;

	@Column(name = "valorclaveprimaria")
	private String valorClavePrimaria;

	@Column(name = "valornuevo")
	private String valorNuevo;

	public Auditoria() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMotivo() {
		return this.motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getPc() {
		return this.pc;
	}

	public void setPc(String pc) {
		this.pc = pc;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCampoClavePrimaria() {
		return campoClavePrimaria;
	}

	public void setCampoClavePrimaria(String campoClavePrimaria) {
		this.campoClavePrimaria = campoClavePrimaria;
	}

	public Timestamp getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Timestamp fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public String getNombreCampo() {
		return nombreCampo;
	}

	public void setNombreCampo(String nombreCampo) {
		this.nombreCampo = nombreCampo;
	}

	public String getNombreTabla() {
		return nombreTabla;
	}

	public void setNombreTabla(String nombreTabla) {
		this.nombreTabla = nombreTabla;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getValorAntiguo() {
		return valorAntiguo;
	}

	public void setValorAntiguo(String valorAntiguo) {
		this.valorAntiguo = valorAntiguo;
	}

	public String getValorClavePrimaria() {
		return valorClavePrimaria;
	}

	public void setValorClavePrimaria(String valorClavePrimaria) {
		this.valorClavePrimaria = valorClavePrimaria;
	}

	public String getValorNuevo() {
		return valorNuevo;
	}

	public void setValorNuevo(String valorNuevo) {
		this.valorNuevo = valorNuevo;
	}

}