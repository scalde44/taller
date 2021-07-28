package com.ceiba.mantenimiento.servicio;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.mantenimiento.modelo.entidad.Mantenimiento;
import com.ceiba.mantenimiento.puerto.repositorio.RepositorioMantenimiento;
import com.ceiba.mantenimiento.servicio.testdatabuilder.MantenimientoTestDataBuilder;

public class ServicioEliminarMantenimientoTest {
	private static final LocalDateTime FECHA_CON_HORA_VALIDA = LocalDateTime.of(2021, 8, 3, 10, 0);

	private RepositorioMantenimiento repositorioMantenimiento;
	private ServicioEliminarMantenimiento servicioEliminarMantenimiento;

	@Before
	public void setup() {
		repositorioMantenimiento = Mockito.mock(RepositorioMantenimiento.class);
		servicioEliminarMantenimiento = new ServicioEliminarMantenimiento(repositorioMantenimiento);
	}

	@Test
	public void debeActualizarUnMantenimiento() {
		Mantenimiento mantenimiento = new MantenimientoTestDataBuilder().conFechaEntrada(FECHA_CON_HORA_VALIDA).build();

		servicioEliminarMantenimiento.ejecutar(mantenimiento.getId());

		verify(repositorioMantenimiento, atLeastOnce()).eliminar(1L);
	}
}
