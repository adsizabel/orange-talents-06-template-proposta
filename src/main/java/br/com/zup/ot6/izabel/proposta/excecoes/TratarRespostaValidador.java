package br.com.zup.ot6.izabel.proposta.excecoes;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratarRespostaValidador {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BindException.class)
    public ErrosDTO tratamentoGenerico(BindException e){
        
		List<FieldError> fieldErros =  e.getBindingResult().getFieldErrors();
		List<ObjectError> errosGlobais= e.getBindingResult().getGlobalErrors();
		
		return montaObjetoErrosDTO(fieldErros, errosGlobais);
		
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrosDTO tratamentoGenericoMethodArgument(MethodArgumentNotValidException e){
        
		List<FieldError> fieldErros =  e.getBindingResult().getFieldErrors();
		List<ObjectError> errosGlobais= e.getBindingResult().getGlobalErrors();
		
		return montaObjetoErrosDTO(fieldErros, errosGlobais);
		
	}
	
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	@ExceptionHandler(IllegalStateException.class)
    public ErrosDTO tratamentoGenericoUnprocessableEntity(IllegalStateException e){
        
		ErrosDTO errosDTO = new ErrosDTO();
		
		String campo = e.getLocalizedMessage();
		String mensagem = e.getMessage();
		
		errosDTO.addErrosCampos(campo, mensagem);

		return errosDTO;
	}
	
	private ErrosDTO montaObjetoErrosDTO(List<FieldError> fieldErros, List<ObjectError> errosGlobais) {
		return new ErrosDTO(getErrosCampos(fieldErros), getErrosGlobais(errosGlobais));		
	}
	
	private List<String> getErrosGlobais(List<ObjectError> errosGlobais){
		List<String> globais = new ArrayList<>();
		for(ObjectError obj: errosGlobais) {
			globais.add(obj.getDefaultMessage());
		}
		return globais;
	}

	private List<RespostaPadraoDTO> getErrosCampos(List<FieldError> errosCampos){
		List<RespostaPadraoDTO> dto = new ArrayList<>();
		for(FieldError c: errosCampos) {
			dto.add(new RespostaPadraoDTO(c.getField(), c.getDefaultMessage()));
		}
		return dto;
	}

	
	
}
