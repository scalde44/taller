package com.ceiba.dominio.excepcion;

public class ExcepcionCapacidad extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ExcepcionCapacidad(String message) {
		super(message);
	}
}
