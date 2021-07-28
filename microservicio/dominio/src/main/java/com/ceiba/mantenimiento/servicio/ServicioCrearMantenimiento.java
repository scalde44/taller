package com.ceiba.mantenimiento.servicio;

import java.time.LocalDateTime;

import com.ceiba.dominio.excepcion.ExcepcionCapacidad;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.mantenimiento.modelo.entidad.Mantenimiento;
import com.ceiba.mantenimiento.puerto.repositorio.RepositorioMantenimiento;

public class ServicioCrearMantenimiento {

	private static final String LA_MOTO_ESTA_EN_MANTENIMIENTO_ACTIVO = "La moto tiene un mantenimiento activo";
	private static final String EL_TALLER_ESTA_LLENO = "El taller ha llegado al tope de su capacidad: %s";
	private static final int CAPACIDAD_MAXIMA_DEL_TALLER = 10;

	private final RepositorioMantenimiento repositorioMantenimiento;

	public ServicioCrearMantenimiento(RepositorioMantenimiento repositorioMantenimiento) {
		this.repositorioMantenimiento = repositorioMantenimiento;
	}

	public Long ejecutar(Mantenimiento mantenimiento) {
		validarMantenimientoActivo(mantenimiento);
		validarDisponibilidad(mantenimiento.getFechaEntrada());
		return this.repositorioMantenimiento.crear(mantenimiento);
	}

	private void validarMantenimientoActivo(Mantenimiento mantenimiento) {
		boolean existe = this.repositorioMantenimiento.existeActivo(mantenimiento.getPlaca());
		if (existe) {
			throw new ExcepcionDuplicidad(LA_MOTO_ESTA_EN_MANTENIMIENTO_ACTIVO);
		}
	}

	private void validarDisponibilidad(LocalDateTime fecha) {
		int cantidadMantenimientos = this.repositorioMantenimiento.contarActivosPorFecha(fecha.toLocalDate());
		if (cantidadMantenimientos >= CAPACIDAD_MAXIMA_DEL_TALLER) {
			throw new ExcepcionCapacidad(String.format(EL_TALLER_ESTA_LLENO, CAPACIDAD_MAXIMA_DEL_TALLER));
		}
	}
}
