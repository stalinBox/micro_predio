package ec.gob.mag.rna.predio.controller;

import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ec.gob.mag.rna.predio.domain.Cobertura;
import ec.gob.mag.rna.predio.services.CoberturaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.swagger.annotations.ApiResponse;

@RestController
@RequestMapping("/api")
@Api(value = "MicroservicioCobertura", description = "Rest Api example", tags = "COBERTURA")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Objeto recuperado"),
        @ApiResponse(code = 201, message = "Objeto creado"),
        @ApiResponse(code = 404, message = "Recurso no encontrado")
})
public class CoberturaController  {

	// @Autowired
	//OfficerService OfficerService = new OfficerService();

	public static final Logger LOGGER = LoggerFactory.getLogger(CoberturaController.class);
	@Autowired
	@Qualifier("coberturaService")
	private CoberturaService coberturaService;
	@Autowired
	private MessageSource messageSource;

	
	@RequestMapping(value = "/create_cobertura", method = RequestMethod.POST)
	@ApiOperation(value = "Crea una nueva cobertura", response = Long.class)
	@ResponseStatus(HttpStatus.CREATED)
	public Long createCobertura(@Valid @RequestBody  Cobertura cobertura)  {
		Cobertura coberturaR =coberturaService.save(cobertura);
		LOGGER.info("createCobertura: "+coberturaR.toString());
		return coberturaR.getCobId();		
	}
	
	
	@RequestMapping(value = "/cobertura/find", method = RequestMethod.GET)
	@ApiOperation( value = "Busca todas las coberturas", response = Cobertura.class )
	@ResponseStatus(HttpStatus.OK)
	public List<Cobertura> getCoberturas() {
		List<Cobertura>listCoberturas = (List<Cobertura>)coberturaService.findAll();
		LOGGER.info("getCoberturas: " + listCoberturas.toString());
		return listCoberturas;
		
	}
	
	@RequestMapping(value = "/cobertura/find/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Busca una cobertura por id", response = Cobertura.class)
	@ResponseStatus(HttpStatus.OK)
	public Optional<Cobertura> getCoberturaById( @Valid @PathVariable String id ) {
		Optional<Cobertura> cobertura = coberturaService.findByCobId( Long.parseLong( id ) );
		LOGGER.info("getCoberturaById: "+ cobertura.toString());
		return cobertura;
		
	}
	

}
