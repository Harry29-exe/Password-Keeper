package pl.kamilwojcik.passwordkeeper.exceptions.validation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.kamilwojcik.passwordkeeper.exceptions.ErrorBody;

import java.util.List;

@Getter
@Setter
public class ValidationErrorBody extends ErrorBody {

    private List<String> invalidFields;

    public ValidationErrorBody(String errorCode, List<String> invalidFields) {
        super(errorCode);
        this.invalidFields = invalidFields;
    }
}
