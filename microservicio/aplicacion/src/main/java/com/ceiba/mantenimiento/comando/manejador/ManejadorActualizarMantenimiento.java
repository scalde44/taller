package com.ceiba.mantenimiento.comando.manejador;

import org.springframework.stereotype.Component;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.mantenimiento.comando.ComandoMantenimiento;
import com.ceiba.mantenimiento.comando.fabrica.FabricaMantenimiento;
import com.ceiba.mantenimiento.modelo.entidad.Mantenimiento;
import com.ceiba.mantenimiento.servicio.ServicioActualizarMantenimiento;

@Component
public class ManejadorActualizarMantenimiento implements ManejadorComando<ComandoMantenimiento> {

	private final FabricaMantenimiento fabricaMantenimiento;
	private final ServicioActualizarMantenimiento servicioActualizarMantenimiento;

	public ManejadorActualizarMantenimiento(FabricaMantenimiento fabricaMantenimiento,
			ServicioActualizarMantenimiento servicioActualizarMantenimiento) {
		this.fabricaMantenimiento = fabricaMantenimiento;
		this.servicioActualizarMantenimiento = servicioActualizarMantenimiento;
	}

	public void ejecutar(ComandoMantenimiento comandoMantenimiento) {
		Mantenimiento mantenimiento = this.fabricaMantenimiento.crear(comandoMantenimiento);
		this.servicioActualizarMantenimiento.ejecutar(mantenimiento);
	}
}
