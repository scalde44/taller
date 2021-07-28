package com.ceiba.mantenimiento.servicio;

import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.mantenimiento.modelo.entidad.Mantenimiento;
import com.ceiba.mantenimiento.puerto.repositorio.RepositorioMantenimiento;
import com.ceiba.mantenimiento.servicio.testdatabuilder.MantenimientoTestDataBuilder;

public class ServicioActualizarMantenimientoTest {
	private static final String LA_MOTO_NO_EXISTE = "La moto no existe";
	private static final LocalDateTime FECHA_CON_HORA_VALIDA = LocalDateTime.of(2021, 8, 3, 10, 0);

	private RepositorioMantenimiento repositorioMantenimiento;
	private ServicioActualizarMantenimiento servicioActualizarMantenimiento;

	@Before
	public void setup() {
		repositorioMantenimiento = Mockito.mock(RepositorioMantenimiento.class);
		servicioActualizarMantenimiento = new ServicioActualizarMantenimiento(repositorioMantenimiento);
	}

	@Test
	public void debeActualizarUnMantenimiento() {
		Mantenimiento mantenimiento = new MantenimientoTestDataBuilder().conFechaEntrada(FECHA_CON_HORA_VALIDA).build();
		Mockito.when(repositorioMantenimiento.existeIncluyendoId(Mockito.anyLong(), Mockito.anyString()))
				.thenReturn(true);

		servicioActualizarMantenimiento.ejecutar(mantenimiento);

		verify(repositorioMantenimiento, atLeast(1)).actualizar(mantenimiento);
	}

	@Test
	public void debeValidarExistenciaPreviaDeLaMoto() {
		Mantenimiento mantenimiento = new MantenimientoTestDataBuilder().conFechaEntrada(FECHA_CON_HORA_VALIDA).build();
		Mockito.when(repositorioMantenimiento.existeIncluyendoId(Mockito.anyLong(), Mockito.anyString()))
				.thenReturn(false);

		// act - assert
		BasePrueba.assertThrows(() -> servicioActualizarMantenimiento.ejecutar(mantenimiento), ExcepcionSinDatos.class,
				LA_MOTO_NO_EXISTE);
	}

}
