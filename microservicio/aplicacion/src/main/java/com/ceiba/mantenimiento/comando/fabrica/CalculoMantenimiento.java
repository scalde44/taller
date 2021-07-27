package com.ceiba.mantenimiento.comando.fabrica;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class CalculoMantenimiento {
	private static final int TARIFA_MENOR_A_250_CC = 80000;
	private static final int TARIFA_MAYOR_O_IGUAL_A_250_CC = 100000;
	private static final int TARIFA_RECARGO_FIN_DE_SEMANA = 10000;

	private CalculoMantenimiento() {
	}

	public static int calcularTarifa(int cilindraje, LocalDateTime fecha) {
		int tarifa = TARIFA_MENOR_A_250_CC;
		if (cilindraje >= 250) {
			tarifa = TARIFA_MAYOR_O_IGUAL_A_250_CC;
		}
		if (fecha.getDayOfWeek() == DayOfWeek.SATURDAY || fecha.getDayOfWeek() == DayOfWeek.SUNDAY) {
			tarifa += TARIFA_RECARGO_FIN_DE_SEMANA;
		}
		return tarifa;
	}

	public static LocalDateTime calcularFechaMantenimiento(LocalDateTime fecha) {
		return (fecha == null) ? LocalDateTime.now() : fecha;
	}
}
