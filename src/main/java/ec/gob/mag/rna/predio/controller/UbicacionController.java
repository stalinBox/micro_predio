package ec.gob.mag.rna.predio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ec.gob.mag.rna.predio.domain.Ubicacion;
import ec.gob.mag.rna.predio.services.UbicacionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/api")
@Api(value = "Ubicacion", description = "Controlador de Ubicacion", tags = "UBICACION")
@ApiResponses(value = { @ApiResponse(code = 200, message = "Objeto recuperado"),
		@ApiResponse(code = 201, message = "Objeto creado"),
		@ApiResponse(code = 404, message = "Recurso no encontrado") })
public class UbicacionController implements ErrorController {
	private static final String PATH = "/error";

	public static final Logger LOGGER = LoggerFactory.getLogger(UbicacionController.class);
	@Autowired
	@Qualifier("ubicacionService")
	private UbicacionService ubicacionService;

	@RequestMapping(value = "/ubicacion/findByUbiId/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Obtiene los datos de la ubicacion y de todos sus ubicaciones padre por id", response = Ubicacion.class)
	@ResponseStatus(HttpStatus.OK)
	public Ubicacion getUbicacion(@PathVariable Long id) {
		Ubicacion ubicaciones = ubicacionService.findByUbiId(id);
		return ubicaciones;
	}

	@Override
	public String getErrorPath() {
		// TODO Auto-generated method stub
		return PATH;
	}

}
