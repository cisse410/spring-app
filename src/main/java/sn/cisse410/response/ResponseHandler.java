package sn.cisse410.response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseHandler {
    /**
     * Cette methode permet de personnaliser les responses de l'api
     * 
     * @param message
     * @param httpStatus
     * @param responseObject
     * @return
     */
    public static ResponseEntity<Object> customResponse(String message, HttpStatus httpStatus, Object responseObject) {
        Map<String, Object> responseFormat = new HashMap<>();
        responseFormat.put("message", message);
        responseFormat.put("status", httpStatus);
        responseFormat.put("data", responseObject);

        return new ResponseEntity<>(responseFormat, httpStatus);
    }
}
