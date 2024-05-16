package pe.com.veriinfo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import pe.com.veriinfo.model.ApiErrorResponse;
import pe.com.veriinfo.model.CustomException;

import javax.validation.ConstraintViolationException;

@Slf4j
@RestControllerAdvice
public class ExceptionTareaHandler {
    public static final String DATOS_INCORRECTOS = "Datos incorrectos.";
    public static final String INTENTAR_MAS_TARDE = "Lo sentimos por favor vuelva intentar mas tarde.";
    @ExceptionHandler
    public ResponseEntity<ApiErrorResponse> tareaException(CustomException exs) {
        boolean exiteError = StringUtils.hasText(exs.getMessage());

        if (exiteError) {
            return ResponseEntity.status(exs.getHttpStatus()).body(new ApiErrorResponse(exs.getCode(),exs.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiErrorResponse("8888",INTENTAR_MAS_TARDE));
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class, ConstraintViolationException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<ApiErrorResponse> handleException(
            Exception ex) {
        log.error(ex.getMessage());
        ApiErrorResponse errorResponse = ApiErrorResponse.builder().codigo("111").mensaje(ex.getMessage()).build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ApiErrorResponse> methodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiErrorResponse("9999",(exception.getMessage())));
    }
}
