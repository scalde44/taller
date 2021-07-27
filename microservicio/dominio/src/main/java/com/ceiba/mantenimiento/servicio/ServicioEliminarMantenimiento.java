package com.ceiba.mantenimiento.servicio;

import com.ceiba.mantenimiento.puerto.repositorio.RepositorioMantenimiento;

public class ServicioEliminarMantenimiento {
	private final RepositorioMantenimiento repositorioMantenimiento;

	public ServicioEliminarMantenimiento(RepositorioMantenimiento repositorioMantenimiento) {
		this.repositorioMantenimiento = repositorioMantenimiento;
	}

	public void ejecutar(Long id) {
		this.repositorioMantenimiento.eliminar(id);
	}
}
