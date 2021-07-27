package com.ceiba.mantenimiento.puerto.dao;

import java.util.List;
import java.util.Optional;

import com.ceiba.mantenimiento.modelo.dto.DtoMantenimiento;

public interface DaoMantenimiento {
	/**
	 * Permite listar los mantenimiento
	 * 
	 * @return los mantenimientos
	 */
	List<DtoMantenimiento> listar();

	/**
	 * Permite listar los mantenimientos activos
	 * 
	 * @return los mantenimientos activos
	 */
	List<DtoMantenimiento> listarActivos();

	/**
	 * Permite listar los mantenimientos activos en esa fecha
	 * 
	 * @param fecha
	 * @return los mantenimientos activos en esa fecha
	 */
	List<DtoMantenimiento> listarActivosPorFecha(String fecha);

	/**
	 * Permite buscar un mantenimiento por id
	 * 
	 * @param id
	 * @return un mantenimiento
	 */
	Optional<DtoMantenimiento> buscarPorId(Long id);
}
