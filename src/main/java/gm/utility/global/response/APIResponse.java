package gm.utility.global.response;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Setter
public class APIResponse<T> {
    private Integer statusCode;
    private LocalDateTime timestamp;
    private T response;

    public APIResponse(HttpStatus statusCode, LocalDateTime timestamp, T response) {
        this.statusCode = statusCode.value();
        this.timestamp = timestamp;
        this.response = response;
    }

    public static <T> ResponseEntity<APIResponse<T>> createGetResponse(T response) {
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse<T>(HttpStatus.OK, LocalDateTime.now(), response));
    }

    public static <T> ResponseEntity<APIResponse<T>> createPatchResponse(T response) {
        return ResponseEntity.status(HttpStatus.OK).body(new APIResponse<T>(HttpStatus.OK, LocalDateTime.now(), response));
    }

    public static <T> ResponseEntity<APIResponse<T>> createPostResponse(T response) {
        return  ResponseEntity.status(HttpStatus.OK).body(new APIResponse<T>(HttpStatus.CREATED, LocalDateTime.now(), response));
    }

    public static <T> ResponseEntity<APIResponse<T>> createDeleteResponse(T response) {
        return  ResponseEntity.status(HttpStatus.OK).body(new APIResponse<T>(HttpStatus.OK, LocalDateTime.now(), response));
    }

    public static <T> ResponseEntity<APIResponse<T>> createPutResponse(T response) {
        return  ResponseEntity.status(HttpStatus.OK).body(new APIResponse<T>(HttpStatus.OK, LocalDateTime.now(), response));
    }

    public static <T> ResponseEntity<APIResponse<T>> createErrResponse(HttpStatus statusCode, T response) {
        return ResponseEntity.status(statusCode).body(new APIResponse<T>(statusCode, LocalDateTime.now(), response));
    }
}