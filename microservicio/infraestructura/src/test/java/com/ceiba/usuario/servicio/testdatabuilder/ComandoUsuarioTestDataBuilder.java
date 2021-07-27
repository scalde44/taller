package com.ceiba.usuario.servicio.testdatabuilder;

import java.time.LocalDateTime;

public class ComandoUsuarioTestDataBuilder {

	private Long id;
	private String nombre;
	private String clave;
	private LocalDateTime fecha;
	/**
	 * public ComandoUsuarioTestDataBuilder() { nombre =
	 * UUID.randomUUID().toString(); clave = "1234"; fecha = LocalDateTime.now(); }
	 * 
	 * public ComandoUsuarioTestDataBuilder conNombre(String nombre) { this.nombre =
	 * nombre; return this; }
	 * 
	 * public ComandoUsuario build() { return new ComandoUsuario(id,nombre,
	 * clave,fecha); }
	 */
}
