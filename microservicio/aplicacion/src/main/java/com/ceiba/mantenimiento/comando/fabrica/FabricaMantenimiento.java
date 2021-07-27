package com.ceiba.mantenimiento.comando.fabrica;

import static com.ceiba.mantenimiento.comando.fabrica.CalculoMantenimiento.calcularFechaMantenimiento;
import static com.ceiba.mantenimiento.comando.fabrica.CalculoMantenimiento.calcularTarifa;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.ceiba.mantenimiento.comando.ComandoMantenimiento;
import com.ceiba.mantenimiento.modelo.entidad.Mantenimiento;

@Component
public class FabricaMantenimiento {
	public Mantenimiento crear(ComandoMantenimiento comandoMantenimiento) {
		LocalDateTime fechaMantenimiento = calcularFechaMantenimiento(comandoMantenimiento.getFecha());
		Integer tarifaMantenimiento = calcularTarifa(comandoMantenimiento.getCilindraje(), fechaMantenimiento);
		return new Mantenimiento(comandoMantenimiento.getId(), comandoMantenimiento.getPlaca(),
				comandoMantenimiento.getCilindraje(), fechaMantenimiento, tarifaMantenimiento,
				comandoMantenimiento.getEstado());
	}
}
