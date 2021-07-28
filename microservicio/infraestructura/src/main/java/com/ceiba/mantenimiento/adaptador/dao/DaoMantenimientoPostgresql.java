package com.ceiba.mantenimiento.adaptador.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.mantenimiento.modelo.dto.DtoMantenimiento;
import com.ceiba.mantenimiento.puerto.dao.DaoMantenimiento;

@Component
public class DaoMantenimientoPostgresql implements DaoMantenimiento {
	private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

	@SqlStatement(namespace = "mantenimiento", value = "listar")
	private static String sqlListar;
	@SqlStatement(namespace = "mantenimiento", value = "listarActivos")
	private static String sqlListarActivos;
	@SqlStatement(namespace = "mantenimiento", value = "buscarPorId")
	private static String sqlBuscarPorId;
	@SqlStatement(namespace = "mantenimiento", value = "listarActivosPorFecha")
	private static String sqlListarActivosPorFecha;

	public DaoMantenimientoPostgresql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
		this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
	}

	@Override
	public List<DtoMantenimiento> listar() {
		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListar,
				new MapeoMantenimiento());
	}

	@Override
	public List<DtoMantenimiento> listarActivos() {
		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListarActivos,
				new MapeoMantenimiento());
	}

	@Override
	public List<DtoMantenimiento> listarActivosPorFecha(LocalDate fecha) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("fecha", fecha);
		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListarActivosPorFecha,
				paramSource, new MapeoMantenimiento());
	}

	@Override
	public Optional<DtoMantenimiento> buscarPorId(Long id) {
		MapSqlParameterSource paramSource = new MapSqlParameterSource();
		paramSource.addValue("id", id);

		return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
				.query(sqlBuscarPorId, paramSource, new MapeoMantenimiento()).stream().findFirst();
	}

}
