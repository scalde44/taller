package com.ceiba.mantenimiento.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.ComandoRespuesta;
import com.ceiba.mantenimiento.comando.ComandoMantenimiento;
import com.ceiba.mantenimiento.comando.manejador.ManejadorActualizarMantenimiento;
import com.ceiba.mantenimiento.comando.manejador.ManejadorCrearMantenimiento;
import com.ceiba.mantenimiento.comando.manejador.ManejadorEliminarMantenimiento;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/mantenimientos")
@Api(tags = { "Controlador comando mantenimiento" })
public class ComandoControladorMantenimiento {
	private final ManejadorCrearMantenimiento manejadorCrearMantenimiento;
	private final ManejadorActualizarMantenimiento manejadorActualizarMantenimiento;
	private final ManejadorEliminarMantenimiento manejadorEliminarMantenimiento;

	@Autowired
	public ComandoControladorMantenimiento(ManejadorCrearMantenimiento manejadorCrearMantenimiento,
			ManejadorActualizarMantenimiento manejadorActualizarMantenimiento,
			ManejadorEliminarMantenimiento manejadorEliminarMantenimiento) {
		this.manejadorCrearMantenimiento = manejadorCrearMantenimiento;
		this.manejadorActualizarMantenimiento = manejadorActualizarMantenimiento;
		this.manejadorEliminarMantenimiento = manejadorEliminarMantenimiento;
	}

	@PostMapping
	@ApiOperation("Crear Mantenimiento")
	public ComandoRespuesta<Long> crear(@RequestBody ComandoMantenimiento comandoMantenimiento) {
		comandoMantenimiento.setEstado("A");
		return manejadorCrearMantenimiento.ejecutar(comandoMantenimiento);
	}

	@DeleteMapping(value = "/{id}")
	@ApiOperation("Eliminar Mantenimiento")
	public void eliminar(@PathVariable Long id) {
		manejadorEliminarMantenimiento.ejecutar(id);
	}

	@PutMapping(value = "/{id}")
	@ApiOperation("Finalizar mantenimiento")
	public void actualizar(@RequestBody ComandoMantenimiento comandoMantenimiento, @PathVariable Long id) {
		comandoMantenimiento.setId(id);
		comandoMantenimiento.setEstado("I");
		manejadorActualizarMantenimiento.ejecutar(comandoMantenimiento);
	}

}
