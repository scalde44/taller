package com.ceiba.mantenimiento.servicio;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionCapacidad;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionLongitudValor;
import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.dominio.excepcion.ExcepcionValorObligatorio;
import com.ceiba.mantenimiento.modelo.dto.DtoMantenimiento;
import com.ceiba.mantenimiento.modelo.entidad.Mantenimiento;
import com.ceiba.mantenimiento.puerto.repositorio.RepositorioMantenimiento;
import com.ceiba.mantenimiento.servicio.testdatabuilder.MantenimientoTestDataBuilder;

public class ServicioCrearMantenimientoTest {
	private static final String LA_MOTO_ESTA_EN_MANTENIMIENTO_ACTIVO = "La moto tiene un mantenimiento activo";
	private static final String EL_TALLER_ESTA_LLENO = "El taller ha llegado al tope de su capacidad: %s";
	private static final int CAPACIDAD_MAXIMA_DEL_TALLER = 10;
	private static final LocalDateTime FECHA_CON_HORA_VALIDA = LocalDateTime.of(2021, 8, 1, 10, 0);
	//
	private static final String SE_DEBE_INGRESAR_LA_PLACA = "Se debe ingresar la placa de la moto";
	private static final String LA_PLACA_DEBE_TENER_LONGITUD_MAYOR_O_IGUAL_A = "La placa debe tener una longitud mayor o igual a %s";
	private static final String LA_PLACA_DEBE_TENER_LONGITUD_MENOR_O_IGUAL_A = "La placa debe tener una longitud menor o igual a %s";
	private static final String EL_CILINDRAJE_DEBE_SER_MAYOR_O_IGUAL_A = "El cilindraje de la moto debe ser mayor o igual a %s";
	private static final String LA_TARIFA_DEBE_SER_MAYOR_O_IGUAL_A = "La tarifa debe ser mayor o igual a %s";
	private static final String EL_ESTADO_DEBE_TENER_LONGITUD_MENOR_A = "La longitud del estado debe ser menor a %s";
	private static final int LONGITUD_MINIMA_PLACA = 5;
	private static final int LONGITUD_MAXIMA_PLACA = 6;
	private static final int CILINDRAJE_MINIMO = 50;
	private static final int TARIFA_MINIMA = 80000;
	private static final int LONGITUD_ESTADO = 1;
	//
	private RepositorioMantenimiento repositorioMantenimiento;
	private ServicioCrearMantenimiento servicioCrearMantenimiento;

	@Before
	public void setup() {
		repositorioMantenimiento = Mockito.mock(RepositorioMantenimiento.class);
		servicioCrearMantenimiento = new ServicioCrearMantenimiento(repositorioMantenimiento);
	}

	@Test
	public void debeCrearUnMantenimiento() {
		Mantenimiento mantenimiento = new MantenimientoTestDataBuilder().conFechaEntrada(FECHA_CON_HORA_VALIDA).build();
		Mockito.when(repositorioMantenimiento.crear(Mockito.any())).thenReturn(1L);

		// act - assert

		assertEquals(1L, servicioCrearMantenimiento.ejecutar(mantenimiento), 0.0);
		assertEquals(1L, mantenimiento.getId(), 0);
		assertEquals("MKC314", mantenimiento.getPlaca());
		assertEquals(150, mantenimiento.getCilindraje(), 0);
		assertEquals(FECHA_CON_HORA_VALIDA, mantenimiento.getFechaEntrada());
		assertEquals(100000, mantenimiento.getTarifa(), 0);
		assertEquals("A", mantenimiento.getEstado());
	}

	@Test
	public void debeValidarMantenimientoActivoDeLaMoto() {
		Mantenimiento mantenimiento = new MantenimientoTestDataBuilder().conFechaEntrada(FECHA_CON_HORA_VALIDA).build();
		Mockito.when(repositorioMantenimiento.existeActivo(Mockito.anyString())).thenReturn(true);

		// act - assert
		BasePrueba.assertThrows(() -> servicioCrearMantenimiento.ejecutar(mantenimiento), ExcepcionDuplicidad.class,
				LA_MOTO_ESTA_EN_MANTENIMIENTO_ACTIVO);
	}

	@Test
	public void debeValidarDisponibilidadDelTaller() {
		Mantenimiento mantenimiento = new MantenimientoTestDataBuilder().conFechaEntrada(FECHA_CON_HORA_VALIDA).build();
		Mockito.when(repositorioMantenimiento.contarActivosPorFecha(Mockito.any()))
				.thenReturn(CAPACIDAD_MAXIMA_DEL_TALLER);

		// act - assert
		BasePrueba.assertThrows(() -> servicioCrearMantenimiento.ejecutar(mantenimiento), ExcepcionCapacidad.class,
				String.format(EL_TALLER_ESTA_LLENO, CAPACIDAD_MAXIMA_DEL_TALLER));
	}

	/* PREGUNTAR */
	@Test
	public void debeCrearUnDtoMantenimiento() {
		DtoMantenimiento dtoMantenimiento = new DtoMantenimiento(1L, "GCR233", CILINDRAJE_MINIMO, FECHA_CON_HORA_VALIDA,
				TARIFA_MINIMA, "A");

		Assert.assertEquals(1L, dtoMantenimiento.getId(), 0);
		Assert.assertEquals("GCR233", dtoMantenimiento.getPlaca());
		Assert.assertEquals(CILINDRAJE_MINIMO, dtoMantenimiento.getCilindraje(), 0);
		Assert.assertEquals(FECHA_CON_HORA_VALIDA, dtoMantenimiento.getFechaEntrada());
		Assert.assertEquals(TARIFA_MINIMA, dtoMantenimiento.getTarifa(), 0);
		Assert.assertEquals("A", dtoMantenimiento.getEstado());
	}

	@Test
	public void validarPlacaObligatoria() {
		MantenimientoTestDataBuilder mantenimientoTestDataBuilder = new MantenimientoTestDataBuilder()
				.conFechaEntrada(FECHA_CON_HORA_VALIDA).conPlaca(null);

		BasePrueba.assertThrows(() -> mantenimientoTestDataBuilder.build(), ExcepcionValorObligatorio.class,
				SE_DEBE_INGRESAR_LA_PLACA);
	}

	@Test
	public void validarLongitudMinimaPlaca() {
		MantenimientoTestDataBuilder mantenimientoTestDataBuilder = new MantenimientoTestDataBuilder()
				.conFechaEntrada(FECHA_CON_HORA_VALIDA).conPlaca("VEB1");

		BasePrueba.assertThrows(() -> mantenimientoTestDataBuilder.build(), ExcepcionLongitudValor.class,
				String.format(LA_PLACA_DEBE_TENER_LONGITUD_MAYOR_O_IGUAL_A, LONGITUD_MINIMA_PLACA));
	}

	@Test
	public void validarLongitudMaximaPlaca() {
		MantenimientoTestDataBuilder mantenimientoTestDataBuilder = new MantenimientoTestDataBuilder()
				.conFechaEntrada(FECHA_CON_HORA_VALIDA).conPlaca("VEB123V");

		BasePrueba.assertThrows(() -> mantenimientoTestDataBuilder.build(), ExcepcionLongitudValor.class,
				String.format(LA_PLACA_DEBE_TENER_LONGITUD_MENOR_O_IGUAL_A, LONGITUD_MAXIMA_PLACA));
	}

	@Test
	public void validarMinimoCilindraje() {
		MantenimientoTestDataBuilder mantenimientoTestDataBuilder = new MantenimientoTestDataBuilder()
				.conFechaEntrada(FECHA_CON_HORA_VALIDA).conCilindraje(CILINDRAJE_MINIMO - 1);

		BasePrueba.assertThrows(() -> mantenimientoTestDataBuilder.build(), ExcepcionValorInvalido.class,
				String.format(EL_CILINDRAJE_DEBE_SER_MAYOR_O_IGUAL_A, CILINDRAJE_MINIMO));
	}

	@Test
	public void validarMinimoTarifa() {
		MantenimientoTestDataBuilder mantenimientoTestDataBuilder = new MantenimientoTestDataBuilder()
				.conFechaEntrada(FECHA_CON_HORA_VALIDA).conTarifa(TARIFA_MINIMA - 1);

		BasePrueba.assertThrows(() -> mantenimientoTestDataBuilder.build(), ExcepcionValorInvalido.class,
				String.format(LA_TARIFA_DEBE_SER_MAYOR_O_IGUAL_A, TARIFA_MINIMA));
	}

	@Test
	public void validarLongitudMaximaEstado() {
		MantenimientoTestDataBuilder mantenimientoTestDataBuilder = new MantenimientoTestDataBuilder()
				.conFechaEntrada(FECHA_CON_HORA_VALIDA).conEstado("AA");

		BasePrueba.assertThrows(() -> mantenimientoTestDataBuilder.build(), ExcepcionLongitudValor.class,
				String.format(EL_ESTADO_DEBE_TENER_LONGITUD_MENOR_A, LONGITUD_ESTADO));
	}
}
