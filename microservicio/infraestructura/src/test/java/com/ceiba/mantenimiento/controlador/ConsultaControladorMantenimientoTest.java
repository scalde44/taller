package com.ceiba.mantenimiento.controlador;

import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

	private static final String PLACA_PRUEBA = "GCY035";
	private static final int CILINDRAJE_PRUEBA = 250;
	private static final String ESTADO_PRUEBA = "A";

	@Test
	public void listar() throws Exception {
		// arrange

		// act - assert
		mocMvc.perform(get("/mantenimientos").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id", is(1)));
	}

	@Test
	public void listarActivos() throws Exception {
		// arrange

		// act - assert
		mocMvc.perform(get("/mantenimientos/activos").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$[0].estado", is(ESTADO_PRUEBA)));
	}

	@Test
	public void buscarPorId() throws Exception {
		// arrange

		// act - assert
		mocMvc.perform(get("/mantenimientos/1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").exists()).andExpect(jsonPath("$.placa", is(PLACA_PRUEBA)))
				.andExpect(jsonPath("$.cilindraje", is(CILINDRAJE_PRUEBA)))
				.andExpect(jsonPath("$.estado", is(ESTADO_PRUEBA)));
	}

	@Test
	public void buscarPorFecha() throws Exception { // arrange

		// act - assert
		mocMvc.perform(get("/mantenimientos/activos/2021-07-27").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}
