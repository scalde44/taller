package com.ceiba.mantenimiento.controlador;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.ceiba.ApplicationMock;
import com.ceiba.mantenimiento.comando.ComandoMantenimiento;
import com.ceiba.mantenimiento.servicio.testdatabuilder.ComandoMantenimientoTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationMock.class)
@WebMvcTest(ComandoControladorMantenimiento.class)
public class ComandoControladorMantenimientoTest {
	@Autowired
	private MockMvc mocMvc;
	@Autowired
	private ObjectMapper objectMapper;

	private static final LocalDateTime FECHA_CON_HORA_VALIDA = LocalDateTime.of(2021, 8, 1, 10, 0);

	@Test
	public void crear() throws Exception {
		ComandoMantenimiento mantenimiento = new ComandoMantenimientoTestDataBuilder().conFecha(FECHA_CON_HORA_VALIDA)
				.build();

		mocMvc.perform(MockMvcRequestBuilders.post("/mantenimientos").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(mantenimiento)))
				.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(jsonPath("$.valor").exists());
	}

	@Test
	public void actualizar() throws Exception {
		ComandoMantenimiento mantenimiento = new ComandoMantenimientoTestDataBuilder().conFecha(FECHA_CON_HORA_VALIDA)
				.build();
		mocMvc.perform(MockMvcRequestBuilders.post("/mantenimientos").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(mantenimiento)))
				.andExpect(MockMvcResultMatchers.status().isOk()).andExpect(jsonPath("$.valor").exists());
		mocMvc.perform(MockMvcRequestBuilders.put("/mantenimientos/{id}", 2L).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(mantenimiento)))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void eliminar() throws Exception {
		mocMvc.perform(delete("/mantenimientos/{id}", 2L).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

}
