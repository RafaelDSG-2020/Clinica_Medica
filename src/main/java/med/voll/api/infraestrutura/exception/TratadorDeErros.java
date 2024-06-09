package med.voll.api.infraestrutura.exception;


import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.time.ZoneId;

@RestControllerAdvice
public class TratadorDeErros {

    @ExceptionHandler(EntityNotFoundException.class)
    public final ResponseEntity<ExceptionDetails> tratarErro404(Exception ex, WebRequest request) {

        return new ResponseEntity<>(ExceptionDetails.builder()
                .timestamp(LocalDateTime.now(ZoneId.systemDefault()))
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .path(((ServletWebRequest) request).getRequest().getRequestURI())
                .build(), HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity tratarErro400(MethodArgumentNotValidException ex, WebRequest request) {

        var erros = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());

    }

    private record DadosErroValidacao (String campo, String mensagem){

        public DadosErroValidacao (FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }

}
