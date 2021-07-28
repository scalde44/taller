package com.ceiba.mantenimiento.consulta;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ceiba.mantenimiento.modelo.dto.DtoMantenimiento;
import com.ceiba.mantenimiento.puerto.dao.DaoMantenimiento;

@Component
public class ManejadorListarMantenimientosPorFecha {
	private final DaoMantenimiento daoMantenimiento;

	public ManejadorListarMantenimientosPorFecha(DaoMantenimiento daoMantenimiento) {
		this.daoMantenimiento = daoMantenimiento;
	}

	public List<DtoMantenimiento> ejecutar(String fecha) {
		return this.daoMantenimiento.listarActivosPorFecha(LocalDate.parse(fecha));
	}
}
