package com.ceiba.mantenimiento.controlador;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ceiba.mantenimiento.consulta.ManejadorBuscarMantenimientoPorId;
import com.ceiba.mantenimiento.consulta.ManejadorListarMantenimientos;
import com.ceiba.mantenimiento.consulta.ManejadorListarMantenimientosActivos;
import com.ceiba.mantenimiento.consulta.ManejadorListarMantenimientosPorFecha;
import com.ceiba.mantenimiento.modelo.dto.DtoMantenimiento;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/mantenimientos")
@Api(tags = { "Controlador consulta mantenimiento" })
public class ConsultaControladorMantenimiento {

	private final ManejadorListarMantenimientos manejadorListarMantenimientos;
	private final ManejadorListarMantenimientosActivos manejadorListarMantenimientosActivos;
	private final ManejadorBuscarMantenimientoPorId manejadorBuscarMantenimientoPorId;
	private final ManejadorListarMantenimientosPorFecha manejadorListarMantenimientosPorFecha;

	public ConsultaControladorMantenimiento(ManejadorListarMantenimientos manejadorListarMantenimientos,
			ManejadorListarMantenimientosActivos manejadorListarMantenimientosActivos,
			ManejadorBuscarMantenimientoPorId manejadorBuscarMantenimientoPorId,
			ManejadorListarMantenimientosPorFecha manejadorListarMantenimientosPorFecha) {
		this.manejadorListarMantenimientos = manejadorListarMantenimientos;
		this.manejadorListarMantenimientosActivos = manejadorListarMantenimientosActivos;
		this.manejadorBuscarMantenimientoPorId = manejadorBuscarMantenimientoPorId;
		this.manejadorListarMantenimientosPorFecha = manejadorListarMantenimientosPorFecha;
	}

	@GetMapping
	@ApiOperation("Listar Mantenimientos")
	public List<DtoMantenimiento> listar() {
		return this.manejadorListarMantenimientos.ejecutar();
	}

	@GetMapping(value = "/activos")
	@ApiOperation("Listar Mantenimientos Activos")
	public List<DtoMantenimiento> listarActivos() {
		return this.manejadorListarMantenimientosActivos.ejecutar();
	}

	@GetMapping(value = "/{id}")
	@ApiOperation("Buscar mantenimiento por id")
	public DtoMantenimiento buscarPorId(@PathVariable Long id) {
		return this.manejadorBuscarMantenimientoPorId.ejecutar(id);
	}

	@GetMapping(value = "/activos/{fecha}")
	@ApiOperation("Listar mantenimientos activos por fecha")
	public List<DtoMantenimiento> buscarPorFecha(@PathVariable String fecha) {
		return this.manejadorListarMantenimientosPorFecha.ejecutar(fecha);
	}

}
