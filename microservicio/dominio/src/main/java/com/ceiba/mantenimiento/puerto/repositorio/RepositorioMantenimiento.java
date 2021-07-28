package com.ceiba.mantenimiento.puerto.repositorio;

import java.time.LocalDate;

import com.ceiba.mantenimiento.modelo.entidad.Mantenimiento;

public interface RepositorioMantenimiento {
	/**
	 * Permite crear un mantenimiento
	 * 
	 * @param mantenimiento
	 * @return el id generado
	 */
	Long crear(Mantenimiento mantenimiento);

	/**
	 * Permite actualizar un mantenimiento
	 * 
	 * @param usuario
	 */
	void actualizar(Mantenimiento mantenimiento);

	/**
	 * Permite eliminar un mantenimiento
	 * 
	 * @param id
	 */
	void eliminar(Long id);

	/**
	 * Permite validar si existe un mantenimiento con esa placa activo
	 * 
	 * @param placa
	 * @return si existe o no
	 */
	boolean existeActivo(String placa);

	/**
	 * Permite validar si existe un mantenimiento con una placa incluyendo un id
	 * 
	 * @param id, placa
	 * @return si existe o no
	 */
	boolean existeIncluyendoId(Long id, String placa);

	/**
	 * Permite contar la cantidad de mantenimientos activos en ese dia
	 * 
	 * @param fecha
	 * @return cantidad de mantenimientos activos en ese dia
	 */
	int contarActivosPorFecha(LocalDate fecha);
}
