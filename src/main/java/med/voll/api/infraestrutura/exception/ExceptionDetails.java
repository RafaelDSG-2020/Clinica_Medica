package med.voll.api.infraestrutura.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExceptionDetails {

    private LocalDateTime timestamp;
    private int status;
    private String title;
    private String message;
    private String path;
    private List<Object> objects;



    @Getter
    @Builder
    public static class Object {
        private String field;
        private String messageField;
    }
}
