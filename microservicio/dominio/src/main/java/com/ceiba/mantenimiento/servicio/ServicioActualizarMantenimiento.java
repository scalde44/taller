package com.ceiba.mantenimiento.servicio;

import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.mantenimiento.modelo.entidad.Mantenimiento;
import com.ceiba.mantenimiento.puerto.repositorio.RepositorioMantenimiento;

public class ServicioActualizarMantenimiento {
	private static final String LA_MOTO_NO_EXISTE = "La moto no existe";

	private final RepositorioMantenimiento repositorioMantenimiento;

	public ServicioActualizarMantenimiento(RepositorioMantenimiento repositorioMantenimiento) {
		this.repositorioMantenimiento = repositorioMantenimiento;
	}

	public void ejecutar(Mantenimiento mantenimiento) {
		validarExistenciaPrevia(mantenimiento);
		this.repositorioMantenimiento.actualizar(mantenimiento);
	}

	private void validarExistenciaPrevia(Mantenimiento mantenimiento) {
		boolean existe = this.repositorioMantenimiento.existeIncluyendoId(mantenimiento.getId(),
				mantenimiento.getPlaca());
		if (!existe) {
			throw new ExcepcionSinDatos(LA_MOTO_NO_EXISTE);
		}
	}
}
