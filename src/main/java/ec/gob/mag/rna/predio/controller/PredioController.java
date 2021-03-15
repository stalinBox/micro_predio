package ec.gob.mag.rna.predio.controller;

import java.io.IOException;
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

import ec.gob.mag.rna.predio.domain.Predio;
import ec.gob.mag.rna.predio.domain.validations.ValidatePredio;
import ec.gob.mag.rna.predio.dto.ResponseOperations;
import ec.gob.mag.rna.predio.dto.ResponseUpdate;
import ec.gob.mag.rna.predio.services.PredioService;
import ec.gob.mag.rna.predio.util.ConvertEntityUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.swagger.annotations.ApiResponse;

@RestController
@Api(value = "MicroservicioPredio", description = "Rest Api example", tags = "PREDIO")
@ApiResponses(value = { @ApiResponse(code = 200, message = "Objeto recuperado"),
		@ApiResponse(code = 201, message = "Objeto creado"),
		@ApiResponse(code = 404, message = "Recurso no encontrado") })
public class PredioController {
	public static final Logger LOGGER = LoggerFactory.getLogger(PredioController.class);
	@Autowired
	@Qualifier("predioService")
	private PredioService predioService;

	@Autowired
	@Qualifier("convertEntityUtil")
	private ConvertEntityUtil convertEntityUtil;

	@RequestMapping(value = "/predio/create", method = RequestMethod.POST)
	@ApiOperation(value = "Crea un nuevo predio", response = ResponseUpdate.class)
	@ResponseStatus(HttpStatus.CREATED)
	public Object createPredio(@Validated @RequestBody ValidatePredio predio,
			@RequestHeader(name = "Authorization") String token) throws NoSuchFieldException, SecurityException,
			IllegalArgumentException, IllegalAccessException, IOException {
		Object predioB = predio;
		Predio predioValidado = convertEntityUtil.ConvertSingleEntityGET(Predio.class, predioB);
		predioValidado.setPreSuperficeCultivable(predio.getPreSuperficieTotal());
		Predio predioR = predioService.save(predioValidado);
		LOGGER.info("createPredio: " + predioR.toString());
		return new ResponseUpdate("PREDIO", predioR.getPreId());
	}

	@RequestMapping(value = "/predio/find", method = RequestMethod.GET)
	@ApiOperation(value = "Busca todos los predios", response = Predio.class)
	@ResponseStatus(HttpStatus.OK)
	public List<Predio> getPredios(@RequestHeader(name = "Authorization") String token) {
		List<Predio> predios = (List<Predio>) predioService.findAll();
		LOGGER.info("getPredios: " + predios.toString());
		return predios;
	}

	@RequestMapping(value = "/predio/findById/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Busca predio por su Id", response = Predio.class)
	@ResponseStatus(HttpStatus.OK)
	public Optional<Predio> getPredioById(@Validated @PathVariable String id,
			@RequestHeader(name = "Authorization") String token) {
		Optional<Predio> predio = predioService.findByPreId(Long.parseLong(id));
		LOGGER.info("getPredioById: " + predio.toString());
		return predio;
	}

	@RequestMapping(value = "/predio/findPetiId/{petiIid}", method = RequestMethod.GET)
	@ApiOperation(value = "Busca un predio por el id de Persona Tipo", response = Predio.class)
	@ResponseStatus(HttpStatus.OK)
	public List<Predio> getPredioByPetyId(@Validated @PathVariable String petiIid) {
		List<Predio> predios = predioService.findByPetiId(Long.parseLong(petiIid));
		LOGGER.info("getPredioByPetyId: " + predios.toString());
		return predios;
	}

	@RequestMapping(value = "/predio/findOrgId/{orgId}", method = RequestMethod.GET)
	@ApiOperation(value = "Busca un predio por Organizacion Id", response = Predio.class)
	@ResponseStatus(HttpStatus.OK)
	public List<Predio> finPredioByOrgId(@Validated @PathVariable String orgId) {
		List<Predio> predios = predioService.findByOrgId(Long.parseLong(orgId));
		LOGGER.info("getPredioByOrgId: " + predios.toString());
		return predios;
	}

	@RequestMapping(value = "/predio/countByPetiId/{petiIid}", method = RequestMethod.GET)
	@ApiOperation(value = "Cuenta numero predio por el id de Persona Tipo", response = ResponseOperations.class)
	@ResponseStatus(HttpStatus.OK)
	public ResponseOperations counPredioByPetyId(@Validated @PathVariable Long petiIid) {
		Integer numeroPredios = predioService.countByPetiId(petiIid);
		LOGGER.info("Numero de predios de la Persona Tipo " + petiIid + " : " + numeroPredios.toString());
		return new ResponseOperations("Count", numeroPredios);
	}

	@RequestMapping(value = "/predio/findUbiId/{ubiId}", method = RequestMethod.GET)
	@ApiOperation(value = "Busca un predio por el id de Persona Tipo", response = Predio.class)
	@ResponseStatus(HttpStatus.OK)
	public List<Predio> getPredioByUbiId(@Validated @PathVariable Long ubiId) {
		List<Predio> predios = predioService.findByUbiId(ubiId);
		LOGGER.info("getPredioByUbiId: " + predios.toString());
		return predios;
	}
}
