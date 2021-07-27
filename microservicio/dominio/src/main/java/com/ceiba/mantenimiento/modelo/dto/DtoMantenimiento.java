package com.ceiba.mantenimiento.modelo.dto;

import java.time.LocalDateTime;

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
	private String estado;

}
