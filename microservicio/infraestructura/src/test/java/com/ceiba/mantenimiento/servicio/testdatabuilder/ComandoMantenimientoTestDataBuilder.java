package com.ceiba.mantenimiento.servicio.testdatabuilder;

import java.time.LocalDateTime;

import com.ceiba.mantenimiento.comando.ComandoMantenimiento;

public class ComandoMantenimientoTestDataBuilder {
	private Long id;
	private String placa;
	private Integer cilindraje;
	private LocalDateTime fecha;
	private Integer tarifa;
	private String estado;

	public ComandoMantenimientoTestDataBuilder() {
		id = 2L;
		placa = "MKC314";
		cilindraje = 150;
		fecha = LocalDateTime.now();
		tarifa = 100000;
		estado = "A";
	}

	public ComandoMantenimientoTestDataBuilder conPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public ComandoMantenimientoTestDataBuilder conFecha(LocalDateTime fecha) {
		this.fecha = fecha;
		return this;
	}

	public ComandoMantenimiento build() {
		return new ComandoMantenimiento(id, placa, cilindraje, fecha, tarifa, estado);
	}
}
