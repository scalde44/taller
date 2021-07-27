package com.ceiba.mantenimiento.consulta;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.ceiba.mantenimiento.modelo.dto.DtoMantenimiento;
import com.ceiba.mantenimiento.puerto.dao.DaoMantenimiento;

@Component
public class ManejadorBuscarMantenimientoPorId {
	private final DaoMantenimiento daoMantenimiento;

	public ManejadorBuscarMantenimientoPorId(DaoMantenimiento daoMantenimiento) {
		this.daoMantenimiento = daoMantenimiento;
	}

	public DtoMantenimiento ejecutar(Long id) {
		Optional<DtoMantenimiento> optionalMantenimiento = this.daoMantenimiento.buscarPorId(id);
		DtoMantenimiento dtoMantenimiento = null;
		if (optionalMantenimiento.isPresent()) {
			dtoMantenimiento = optionalMantenimiento.get();
		}
		return dtoMantenimiento;
	}

}
