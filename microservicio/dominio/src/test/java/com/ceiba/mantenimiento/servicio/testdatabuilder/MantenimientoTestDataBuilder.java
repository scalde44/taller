package com.ceiba.mantenimiento.servicio.testdatabuilder;

import java.time.LocalDateTime;

import com.ceiba.mantenimiento.modelo.entidad.EstadoMantenimiento;
import com.ceiba.mantenimiento.modelo.entidad.Mantenimiento;

public class MantenimientoTestDataBuilder {
	private Long id;
	private String placa;
	private Integer cilindraje;
	private LocalDateTime fechaEntrada;
	private EstadoMantenimiento estado;

	public MantenimientoTestDataBuilder() {
		id = 1L;
		placa = "MKC314";
		cilindraje = 150;
		fechaEntrada = LocalDateTime.now();
		estado = EstadoMantenimiento.A;
	}

	public MantenimientoTestDataBuilder conPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public MantenimientoTestDataBuilder conCilindraje(Integer cilindraje) {
		this.cilindraje = cilindraje;
		return this;
	}

	public MantenimientoTestDataBuilder conFechaEntrada(LocalDateTime fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
		return this;
	}

	public MantenimientoTestDataBuilder conEstado(EstadoMantenimiento estado) {
		this.estado = estado;
		return this;
	}

	public Mantenimiento build() {
		return new Mantenimiento(id, placa, cilindraje, fechaEntrada, estado);
	}
}
