package com.clinica.services.v1;

import com.clinica.services.v1.medico.controller.MedicoController;
import com.clinica.services.v1.medico.domain.Medico;
import com.clinica.services.v1.medico.service.MedicoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@WebFluxTest(MedicoController.class)
class MedicoControllerTest {

	@Autowired
	private WebTestClient webTestClient;

	@MockBean
	private MedicoService medicoService;

	@Test
	void testObtenerMedicoPorId() {
		Medico medico = new Medico("123", "Fernando Tapia C", "Cardiología", true);
		Mockito.when(medicoService.obtenerPorId("123")).thenReturn(Mono.just(ResponseEntity.ok(medico)));
		//StepVerifier.create()
		webTestClient.get().uri("/v1/medicos/123")
				.exchange()
				.expectStatus().isOk()
				.expectBody()
				.jsonPath("$.nombre").isEqualTo("Fernando Tapia C")
				.jsonPath("$.especialidad").isEqualTo("Cardiología")
				.jsonPath("$.activo").isEqualTo(true);
	}
}
