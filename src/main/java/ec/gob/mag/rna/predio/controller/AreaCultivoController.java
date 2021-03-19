package ec.gob.mag.rna.predio.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ec.gob.mag.rna.predio.domain.AreaCultivo;
import ec.gob.mag.rna.predio.services.AreaCultivoService;
import ec.gob.mag.rna.predio.util.Util;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/api")
@Api(value = "AreaCultivo", description = "Rest Api example", tags = "AREACULTIVO")
@ApiResponses(value = { @ApiResponse(code = 200, message = "Objeto recuperado"),
		@ApiResponse(code = 201, message = "Objeto creado"),
		@ApiResponse(code = 404, message = "Recurso no encontrado") })
public class AreaCultivoController implements ErrorController {
	private static final String PATH = "/error";

	public static final Logger LOGGER = LoggerFactory.getLogger(AreaCultivoController.class);
	@Autowired
	@Qualifier("areaCultivoService")
	private AreaCultivoService areaCultivoService;

	@Autowired
	@Qualifier("util")
	private Util util;

	@RequestMapping(value = "/create_area_cultivo", method = RequestMethod.POST)
	@ApiOperation(value = "Insert areaCultivo", response = Long.class)
	@ResponseStatus(HttpStatus.CREATED)
	public Long createAreaCultivo(@Validated @RequestBody AreaCultivo areaCultivo,
			@RequestHeader(name = "Authorization") String token) {
		AreaCultivo areaCultivoR = areaCultivoService.save(areaCultivo);
		LOGGER.info("AreaCultivo Save: " + areaCultivoR.toString() + " usuario: " + util.filterUsuId(token));
		return areaCultivo.getAcId();
	}

	@RequestMapping(value = "/areaCultivo/find", method = RequestMethod.GET)
	@ApiOperation(value = "Get all areaCultivos", response = AreaCultivo.class)
	@ResponseStatus(HttpStatus.OK)
	public List<AreaCultivo> getAreaCultivos(@RequestHeader(name = "Authorization") String token) {
		List<AreaCultivo> listAreaCultivos = (List<AreaCultivo>) areaCultivoService.findAll();
		LOGGER.info("Find All: " + listAreaCultivos.toString() + " usuario: " + util.filterUsuId(token));
		return listAreaCultivos;

	}

	@RequestMapping(value = "/areaCultivo/find/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Get AreaCultivo by id", response = AreaCultivo.class)
	@ResponseStatus(HttpStatus.OK)
	public Optional<AreaCultivo> getAreaCultivo(@Validated @PathVariable String id,
			@RequestHeader(name = "Authorization") String token) {
		Optional<AreaCultivo> areaCultivo = areaCultivoService.findByAcId(Long.parseLong(id));
		LOGGER.info("Find All: " + areaCultivo.toString() + " usuario: " + util.filterUsuId(token));
		return areaCultivo;
	}

	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return PATH;
	}

}
