package com.ceiba.mantenimiento.modelo.dto;

import java.time.LocalDateTime;

import com.ceiba.mantenimiento.modelo.entidad.EstadoMantenimiento;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoMantenimiento {
	private Long id;
	private String placa;
	private Integer cilindraje;
	private LocalDateTime fechaEntrada;
	private Integer tarifa;
	private EstadoMantenimiento estado;

}
