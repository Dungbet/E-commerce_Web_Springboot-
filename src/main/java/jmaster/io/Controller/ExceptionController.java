package jmaster.io.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jmaster.io.DTO.ResponseDTO;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ExceptionController {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST )
	public ResponseDTO<Void> notValid(MethodArgumentNotValidException e){

		List<ObjectError> errors = e.getBindingResult().getAllErrors();

		String msg = "";
		for (ObjectError err : errors) {
			FieldError fieldError = (FieldError) err;
			msg += fieldError.getField() + ":" + err.getDefaultMessage() + ";";
		}
		return ResponseDTO.<Void>builder().status(400).msg(msg).build();
		
	}
	
}
