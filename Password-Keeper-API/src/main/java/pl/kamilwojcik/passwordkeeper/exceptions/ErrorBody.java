package pl.kamilwojcik.passwordkeeper.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorBody {

    private String errorCode;

    public ErrorBody(ErrorCode errorCode) {
        this.errorCode = errorCode.name();
    }
}
