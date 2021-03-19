package ec.gob.mag.rna.predio.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ec.gob.mag.rna.predio.domain.Coordenada;
import ec.gob.mag.rna.predio.services.CoordenadaService;
import ec.gob.mag.rna.predio.util.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/api")
@Api(value = "MicroservicioCoordenada", description = "Rest Api example", tags = "COORDENADA")
@ApiResponses(value = { @ApiResponse(code = 200, message = "Objeto recuperado"),
		@ApiResponse(code = 201, message = "Objeto creado"),
		@ApiResponse(code = 404, message = "Recurso no encontrado") })
public class CoordenadaController {

	public static final Logger LOGGER = LoggerFactory.getLogger(CoordenadaController.class);
	@Autowired
	@Qualifier("coordenadaService")
	private CoordenadaService coordenadaService;

	@Autowired
	@Qualifier("util")
	private Util util;
	
	@RequestMapping(value = "/create_coordenada", method = RequestMethod.POST)
	@ApiOperation(value = "Insert coordenada", response = Long.class)
	@ResponseStatus(HttpStatus.CREATED)
	public Long createdCoordenada(@Validated @RequestBody Coordenada coordenada,
			@RequestHeader(name = "Authorization") String token) {
		Coordenada coordenadaR = coordenadaService.save(coordenada);
		LOGGER.info("createdCoordenada: " + coordenadaR.toString()+ " usuario: " + util.filterUsuId(token));
		return coordenadaR.getCordId();
	}

	@RequestMapping(value = "/coordenada/find", method = RequestMethod.GET)
	@ApiOperation(value = "Busca todas las coordenadas", response = Coordenada.class)
	@ResponseStatus(HttpStatus.OK)
	public List<Coordenada> getCoordenadas(@RequestHeader(name = "Authorization") String token) {
		List<Coordenada> listCoordenadas = coordenadaService.findAll();
		LOGGER.info("getCoordenadas: " + listCoordenadas.toString()+ " usuario: " + util.filterUsuId(token));
		return listCoordenadas;
	}

	@RequestMapping(value = "/coordenada/find/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Busca una coordenada por id", response = Coordenada.class)
	@ResponseStatus(HttpStatus.OK)
	public Optional<Coordenada> getCoordenadaById(@Validated @PathVariable String id,
			@RequestHeader(name = "Authorization") String token) {
		Optional<Coordenada> coordenada = coordenadaService.findByCobId(Long.parseLong(id));
		LOGGER.info("getCoordenadaById: " + coordenada.toString()+ " usuario: " + util.filterUsuId(token));
		return coordenada;

	}
}
