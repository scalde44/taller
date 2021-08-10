package com.ceiba.mantenimiento.comando;

import java.time.LocalDateTime;

import com.ceiba.mantenimiento.modelo.entidad.EstadoMantenimiento;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoMantenimiento {
	private Long id;
	private String placa;
	private Integer cilindraje;
	private LocalDateTime fecha;
	private Integer tarifa;
	private EstadoMantenimiento estado;
}
