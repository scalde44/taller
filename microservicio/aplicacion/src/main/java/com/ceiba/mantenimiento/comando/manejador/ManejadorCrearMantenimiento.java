package com.ceiba.mantenimiento.comando.manejador;

import org.springframework.stereotype.Component;

import com.ceiba.ComandoRespuesta;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import com.ceiba.mantenimiento.comando.ComandoMantenimiento;
import com.ceiba.mantenimiento.comando.fabrica.FabricaMantenimiento;
import com.ceiba.mantenimiento.modelo.entidad.Mantenimiento;
import com.ceiba.mantenimiento.servicio.ServicioCrearMantenimiento;

@Component
public class ManejadorCrearMantenimiento
		implements ManejadorComandoRespuesta<ComandoMantenimiento, ComandoRespuesta<Long>> {

	private final FabricaMantenimiento fabricaMantenimiento;
	private final ServicioCrearMantenimiento servicioCrearMantenimiento;

	public ManejadorCrearMantenimiento(FabricaMantenimiento fabricaMantenimiento,
			ServicioCrearMantenimiento servicioCrearMantenimiento) {
		this.fabricaMantenimiento = fabricaMantenimiento;
		this.servicioCrearMantenimiento = servicioCrearMantenimiento;
	}

	@Override
	public ComandoRespuesta<Long> ejecutar(ComandoMantenimiento comandoMantenimiento) {
		Mantenimiento mantenimiento = this.fabricaMantenimiento.crear(comandoMantenimiento);
		return new ComandoRespuesta<>(this.servicioCrearMantenimiento.ejecutar(mantenimiento));
	}
}
