package com.ceiba.mantenimiento.consulta;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ceiba.mantenimiento.modelo.dto.DtoMantenimiento;
import com.ceiba.mantenimiento.puerto.dao.DaoMantenimiento;

@Component
public class ManejadorListarMantenimientosActivos {
	private final DaoMantenimiento daoMantenimiento;

	public ManejadorListarMantenimientosActivos(DaoMantenimiento daoMantenimiento) {
		this.daoMantenimiento = daoMantenimiento;
	}

	public List<DtoMantenimiento> ejecutar() {
		return this.daoMantenimiento.listarActivos();
	}
}
