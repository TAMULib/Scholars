package edu.tamu.scholars.middleware.theme.advice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import edu.tamu.scholars.middleware.theme.exception.CreateActiveThemeException;
import edu.tamu.scholars.middleware.theme.exception.DeleteActiveThemeException;

@RestController
@ControllerAdvice
public class ThemeAdvice {

	@ResponseStatus(BAD_REQUEST)
	@ExceptionHandler(CreateActiveThemeException.class)
	public @ResponseBody String handleCreateActiveThemeException(CreateActiveThemeException exception) {
		return exception.getMessage();
	}

	@ResponseStatus(BAD_REQUEST)
	@ExceptionHandler(DeleteActiveThemeException.class)
	public @ResponseBody String handleDeleteActiveThemeException(DeleteActiveThemeException exception) {
		return exception.getMessage();
	}

}
