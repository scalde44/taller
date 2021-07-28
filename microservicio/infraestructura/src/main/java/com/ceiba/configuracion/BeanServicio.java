package com.ceiba.configuracion;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ceiba.mantenimiento.puerto.repositorio.RepositorioMantenimiento;
import com.ceiba.mantenimiento.servicio.ServicioActualizarMantenimiento;
import com.ceiba.mantenimiento.servicio.ServicioCrearMantenimiento;
import com.ceiba.mantenimiento.servicio.ServicioEliminarMantenimiento;

@Configuration
public class BeanServicio {

	@Bean
	public ServicioCrearMantenimiento servicioCrearMantenimiento(RepositorioMantenimiento repositorioMantenimiento) {
		return new ServicioCrearMantenimiento(repositorioMantenimiento);
	}

	@Bean
	public ServicioEliminarMantenimiento servicioEliminarMantenimiento(
			RepositorioMantenimiento repositorioMantenimiento) {
		return new ServicioEliminarMantenimiento(repositorioMantenimiento);
	}

	@Bean
	public ServicioActualizarMantenimiento servicioActualizarMantenimiento(
			RepositorioMantenimiento repositorioMantenimiento) {
		return new ServicioActualizarMantenimiento(repositorioMantenimiento);
	}

}
