package com.ceiba.mantenimiento.comando.fabrica;

import org.springframework.stereotype.Component;

import com.ceiba.mantenimiento.comando.ComandoMantenimiento;
import com.ceiba.mantenimiento.modelo.entidad.Mantenimiento;

@Component
public class FabricaMantenimiento {
	public Mantenimiento crear(ComandoMantenimiento comandoMantenimiento) {
		return new Mantenimiento(comandoMantenimiento.getId(), comandoMantenimiento.getPlaca(),
				comandoMantenimiento.getCilindraje(), comandoMantenimiento.getFecha(),
				comandoMantenimiento.getEstado());
	}
}
