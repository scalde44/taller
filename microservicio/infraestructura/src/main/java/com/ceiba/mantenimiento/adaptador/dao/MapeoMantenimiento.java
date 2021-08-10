package com.ceiba.mantenimiento.adaptador.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.springframework.jdbc.core.RowMapper;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.mantenimiento.modelo.dto.DtoMantenimiento;
import com.ceiba.mantenimiento.modelo.entidad.EstadoMantenimiento;

public class MapeoMantenimiento implements RowMapper<DtoMantenimiento>, MapperResult {

	@Override
	public DtoMantenimiento mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Long id = resultSet.getLong("id");
		String placa = resultSet.getString("placa");
		Integer cilindraje = resultSet.getInt("cilindraje");
		LocalDateTime fechaEntrada = extraerLocalDateTime(resultSet, "fecha_entrada");
		Integer tarifa = resultSet.getInt("tarifa");
		EstadoMantenimiento estado = EstadoMantenimiento.valueOf(resultSet.getString("estado"));

		return new DtoMantenimiento(id, placa, cilindraje, fechaEntrada, tarifa, estado);
	}

}
