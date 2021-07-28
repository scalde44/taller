package com.ceiba.mantenimiento.adaptador.repositorio;

import java.time.LocalDate;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.mantenimiento.modelo.entidad.Mantenimiento;
import com.ceiba.mantenimiento.puerto.repositorio.RepositorioMantenimiento;

@Repository
public class RepositorioMantenimientoPostgresql implements RepositorioMantenimiento {
	private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

	@SqlStatement(namespace = "mantenimiento", value = "crear")
	private static String sqlCrear;

	@SqlStatement(namespace = "mantenimiento", value = "actualizar")
	private static String sqlActualizar;

	@SqlStatement(namespace = "mantenimiento", value = "eliminar")
	private static String sqlEliminar;

	@SqlStatement(namespace = "mantenimiento", value = "existeActivo")
	private static String sqlExisteActivo;

	@SqlStatement(namespace = "mantenimiento", value = "existeIncluyendoId")
	private static String sqlExisteIncluyendoId;

	@SqlStatement(namespace = "mantenimiento", value = "contarActivosPorFecha")
	private static String sqlContarActivosPorFecha;

	public RepositorioMantenimientoPostgresql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
		this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
	}

	@Override
	public Long crear(Mantenimiento mantenimiento) {
		return this.customNamedParameterJdbcTemplate.crear(mantenimiento, sqlCrear);
	}

	@Override
	public void actualizar(Mantenimiento mantenimiento) {
		this.customNamedParameterJdbcTemplate.actualizar(mantenimiento, sqlActualizar);
	}

	@Override
	public void eliminar(Long id) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", id);

		this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().update(sqlEliminar, paramSource);
	}

	@Override
	public boolean existeActivo(String placa) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("placa", placa);

		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().queryForObject(sqlExisteActivo,
				paramSource, Boolean.class);
	}

	@Override
	public boolean existeIncluyendoId(Long id, String placa) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", id);
		paramSource.addValue("placa", placa);

		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
				.queryForObject(sqlExisteIncluyendoId, paramSource, Boolean.class);
	}

	@Override
	public int contarActivosPorFecha(LocalDate fecha) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("fecha", fecha);

		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
				.queryForObject(sqlContarActivosPorFecha, paramSource, int.class);
	}

}
