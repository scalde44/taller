package com.ceiba.mantenimiento.comando.manejador;

import org.springframework.stereotype.Component;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.mantenimiento.servicio.ServicioEliminarMantenimiento;

@Component
public class ManejadorEliminarMantenimiento implements ManejadorComando<Long> {
	private final ServicioEliminarMantenimiento servicioEliminarMantenimiento;

	public ManejadorEliminarMantenimiento(ServicioEliminarMantenimiento servicioEliminarMantenimiento) {
		super();
		this.servicioEliminarMantenimiento = servicioEliminarMantenimiento;
	}

	@Override
	public void ejecutar(Long id) {
		this.servicioEliminarMantenimiento.ejecutar(id);
	}
}
