package com.ceiba.mantenimiento.controlador;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ceiba.ApplicationMock;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationMock.class)
@WebMvcTest(ConsultaControladorMantenimiento.class)
public class ConsultaControladorMantenimientoTest {
	@Autowired
	private MockMvc mocMvc;

	private static final String PLACA_PRUEBA_INACTIVO = "FNF327";
	private static final int CILINDRAJE_PRUEBA_INACTIVO = 150;
	private static final String ESTADO_ACTIVO = "A";
	private static final String ESTADO_INACTIVO = "I";
	private static final long ID_PRUEBA_INACTIVO = 2L;

	@Test
	public void listar() throws Exception {
		mocMvc.perform(get("/mantenimientos").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2))).andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[1].placa", is(PLACA_PRUEBA_INACTIVO)));
	}

	@Test
	public void listarActivos() throws Exception {
		mocMvc.perform(get("/mantenimientos/activos").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].estado", is(ESTADO_ACTIVO)));
	}

	@Test
	public void buscarPorId() throws Exception {
		mocMvc.perform(get("/mantenimientos/{id}", ID_PRUEBA_INACTIVO).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.id").exists())
				.andExpect(jsonPath("$.placa", is(PLACA_PRUEBA_INACTIVO)))
				.andExpect(jsonPath("$.cilindraje", is(CILINDRAJE_PRUEBA_INACTIVO)))
				.andExpect(jsonPath("$.estado", is(ESTADO_INACTIVO)));
	}

	@Test
	public void buscarPorFecha() throws Exception {
		mocMvc.perform(
				get("/mantenimientos/activos/{fecha}", LocalDate.now().toString()).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)));
	}

}
