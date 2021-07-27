package com.ceiba.mantenimiento.modelo.entidad;

import static com.ceiba.dominio.ValidadorArgumento.validarFechaEntreHoras;
import static com.ceiba.dominio.ValidadorArgumento.validarLongitud;
import static com.ceiba.dominio.ValidadorArgumento.validarLongitudMaxima;
import static com.ceiba.dominio.ValidadorArgumento.validarMenor;
import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class Mantenimiento {

	private static final String SE_DEBE_INGRESAR_LA_PLACA = "Se debe ingresar la placa de la moto";
	private static final String LA_PLACA_DEBE_TENER_LONGITUD_MAYOR_O_IGUAL_A = "La placa debe tener una longitud mayor o igual a %s";
	private static final String LA_PLACA_DEBE_TENER_LONGITUD_MENOR_O_IGUAL_A = "La placa debe tener una longitud menor o igual a %s";
	private static final String SE_DEBE_INGRESAR_EL_CILINDRAJE = "Se debe ingresar el cilindraje de la moto";
	private static final String EL_CILINDRAJE_DEBE_SER_MAYOR_O_IGUAL_A = "El cilindraje de la moto debe ser mayor o igual a %s";
	private static final String SE_DEBE_INGRESAR_FECHA = "Se debe ingresar fecha";
	private static final String LA_FECHA_DEBE_ESTAR_ENTRE = "El horario del mantenimiento esta entre %s y %s";
	private static final String SE_DEBE_INGRESAR_LA_TARIFA = "Se debe ingresar la tarifa";
	private static final String LA_TARIFA_DEBE_SER_MAYOR_O_IGUAL_A = "La tarifa debe ser mayor o igual a %s";
	private static final String SE_DEBE_INGRESAR_ESTADO_MANTENIMIENTO = "Se debe ingresar el estado del mantenimiento";
	private static final String EL_ESTADO_DEBE_TENER_LONGITUD_MENOR_A = "La longitud del estado debe ser menor a %s";

	private static final int LONGITUD_MINIMA_PLACA = 5;
	private static final int LONGITUD_MAXIMA_PLACA = 6;
	private static final long CILINDRAJE_MINIMO = 50L;
	private static final long TARIFA_MINIMA = 80000L;
	private static final int LONGITUD_ESTADO = 2;
	private static final int HORA_ENTRADA = 7;
	private static final int HORA_SALIDA = 17;

	private Long id;
	private String placa;
	private Integer cilindraje;
	private LocalDateTime fechaEntrada;
	private Integer tarifa;
	private String estado;

	public Mantenimiento(Long id, String placa, Integer cilindraje, LocalDateTime fechaEntrada, Integer tarifa,
			String estado) {
		validarObligatorio(placa, SE_DEBE_INGRESAR_LA_PLACA);
		validarLongitud(placa, LONGITUD_MINIMA_PLACA,
				String.format(LA_PLACA_DEBE_TENER_LONGITUD_MAYOR_O_IGUAL_A, LONGITUD_MINIMA_PLACA));
		validarLongitudMaxima(placa, LONGITUD_MAXIMA_PLACA,
				String.format(LA_PLACA_DEBE_TENER_LONGITUD_MENOR_O_IGUAL_A, LONGITUD_MAXIMA_PLACA));
		validarObligatorio(cilindraje, SE_DEBE_INGRESAR_EL_CILINDRAJE);
		validarMenor(CILINDRAJE_MINIMO, cilindraje.longValue(),
				String.format(EL_CILINDRAJE_DEBE_SER_MAYOR_O_IGUAL_A, CILINDRAJE_MINIMO));
		validarObligatorio(fechaEntrada, SE_DEBE_INGRESAR_FECHA);
		validarFechaEntreHoras(fechaEntrada, HORA_ENTRADA, HORA_SALIDA,
				String.format(LA_FECHA_DEBE_ESTAR_ENTRE, HORA_ENTRADA, HORA_SALIDA));
		validarObligatorio(tarifa, SE_DEBE_INGRESAR_LA_TARIFA);
		validarMenor(TARIFA_MINIMA, tarifa.longValue(),
				String.format(LA_TARIFA_DEBE_SER_MAYOR_O_IGUAL_A, TARIFA_MINIMA));
		validarObligatorio(estado, SE_DEBE_INGRESAR_ESTADO_MANTENIMIENTO);
		validarLongitudMaxima(estado, LONGITUD_ESTADO,
				String.format(EL_ESTADO_DEBE_TENER_LONGITUD_MENOR_A, LONGITUD_ESTADO));
		this.id = id;
		this.placa = placa;
		this.cilindraje = cilindraje;
		this.fechaEntrada = fechaEntrada;
		this.tarifa = tarifa;
		this.estado = estado;
	}

}