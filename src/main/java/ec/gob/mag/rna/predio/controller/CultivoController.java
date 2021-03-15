package ec.gob.mag.rna.predio.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.source.InvalidConfigurationPropertyValueException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import ec.gob.mag.rna.predio.domain.Cultivo;
import ec.gob.mag.rna.predio.services.CultivoService;
import ec.gob.mag.rna.predio.util.ResponseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/cultivo")
@Api(value = "Cultivo", description = "Rest Api example", tags = "CULTIVO")
@ApiResponses(value = { @ApiResponse(code = 200, message = "Objeto recuperado"),
		@ApiResponse(code = 201, message = "Objeto creado"),
		@ApiResponse(code = 404, message = "Recurso no encontrado") })
public class CultivoController implements ErrorController {
	private static final String PATH = "/error";
	public static final Logger LOGGER = LoggerFactory.getLogger(CultivoController.class);

	@Autowired
	@Qualifier("cultivoService")
	private CultivoService cultivoService;

	@Autowired
	@Qualifier("responseController")
	private ResponseController responseController;

	/**
	 * Busca todos los registros de la entidad
	 * 
	 * @param id: Identificador de la entidad
	 * @return Entidad: Retorna todos los registros.
	 * @RequestHeader(name = "Authorization") String token
	 */
	@GetMapping(value = "/findAll")
	@ApiOperation(value = "Obtiene todos los registros activos no eliminados logicamente", response = Cultivo.class)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<List<Cultivo>> findAll() {
		List<Cultivo> cultivo = cultivoService.findAll();
		LOGGER.info("Find All: " + cultivo.toString());
		return ResponseEntity.ok(cultivo);
	}

	/**
	 * Busca los registros por Id de la entidad
	 * 
	 * @param id: Identificador de la entidad
	 * @return parametrosCarga: Retorna el registro encontrado
	 */
	@GetMapping(value = "/findById/{id}")
	@ApiOperation(value = "Get Cultivo by id", response = Cultivo.class)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Optional<Cultivo>> findById(@Validated @PathVariable Long id,
			@RequestHeader(name = "Authorization") String token) {
		Optional<Cultivo> cultivo = cultivoService.findById(id);
		LOGGER.info("Find By Id: " + cultivo.toString());
		return ResponseEntity.ok(cultivo);
	}

	/**
	 * Inserta un nuevo registro en la entidad
	 * 
	 * @param entidad: entidad a insertar
	 * @return ResponseController: Retorna el id creado
	 */
	@PostMapping(value = "/create/{usuId}")
	@ApiOperation(value = "Crear nuevo registro", response = ResponseController.class)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ResponseController> postOfficer(@Validated @PathVariable Long usuId,
			@RequestBody Cultivo cultivo, @RequestHeader(name = "Authorization") String token) {
		cultivo.setCulRegUsu(usuId);
		Cultivo off = cultivoService.save(cultivo);
		LOGGER.info("Cultivo Save: " + cultivo);
		return ResponseEntity.ok(new ResponseController(off.getId(), "Creado"));
	}

	/**
	 * Actualiza un registro
	 * 
	 * @param usuId:   Identificador del usuario que va a actualizar
	 * 
	 * @param entidad: entidad a actualizar
	 * @return ResponseController: Retorna el id actualizado
	 */
	@PostMapping(value = "/update/{usuId}")
	@ApiOperation(value = "Actualizar los registros", response = ResponseController.class)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<ResponseController> update(@Validated @RequestBody Cultivo updateCultivo,
			@PathVariable Long usuId, @RequestHeader(name = "Authorization") String token) {
		updateCultivo.setCulActUsu(usuId);
		Cultivo off = cultivoService.update(updateCultivo);
		LOGGER.info("Update: " + off + " update by: " + usuId);
		return ResponseEntity.ok(new ResponseController(off.getId(), "Actualizado"));
	}

	/**
	 * Realiza un eliminado logico del registro
	 * 
	 * @param id:    Identificador del registro
	 * @param usuId: Identificador del usuario que va a eliminar
	 * @return ResponseController: Retorna el id eliminado
	 */
	@GetMapping(value = "/delete/{id}/{usuId}")
	@ApiOperation(value = "Remove cultivos by id")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<ResponseController> deleteOfficer(@Validated @PathVariable Long id, @PathVariable Long usuId,
			@RequestHeader(name = "Authorization") String token) {
		Cultivo deleteCultivo = cultivoService.findById(id)
				.orElseThrow(() -> new InvalidConfigurationPropertyValueException("Cultivo", "Id", id.toString()));
		deleteCultivo.setCulEliminado(true);
		deleteCultivo.setCulActUsu(usuId);
		Cultivo officerDel = cultivoService.save(deleteCultivo);
		LOGGER.info("Delete Cultivo id: " + id + " delete by: " + usuId);
		return ResponseEntity.ok(new ResponseController(officerDel.getId(), "eliminado"));
	}

	@Override
	public String getErrorPath() {
		return PATH;
	}

}
