package usp.icmc.ssc.lasdpc.management.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
public class BusinessException extends Exception {
    public BusinessException(String s) {
        super(s);
    }
}
