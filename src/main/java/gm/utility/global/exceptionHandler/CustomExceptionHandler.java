package gm.utility.global.exceptionHandler;


import gm.utility.global.exception.Base64DecodingException;
import gm.utility.global.exception.DtoValidationException;
import gm.utility.global.exception.ResourceNotFoundException;
import gm.utility.global.response.APIResponse;
import gm.utility.global.response.ErrorCode;
import gm.utility.global.response.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static gm.utility.global.response.APIResponse.createErrResponse;

@RestControllerAdvice
public class CustomExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(DtoValidationException.class)
    public ResponseEntity<APIResponse<ErrorResponse>> validationApiException(DtoValidationException e) {
        log.error(e.getMessage());
        return createErrResponse(HttpStatus.BAD_REQUEST,
                new ErrorResponse(
                        HttpStatus.BAD_REQUEST.getReasonPhrase(),
                        e.getMessage() + ": " + e.getErrorMap().toString(),
                        ErrorCode.INVALID_REQUEST));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class,})
    public ResponseEntity<APIResponse<ErrorResponse>> validationException(Exception e) {
        log.error(e.getMessage());
        return createErrResponse(HttpStatus.BAD_REQUEST,
                new ErrorResponse(
                        HttpStatus.BAD_REQUEST.getReasonPhrase(),
                        "필수 파라미터가 잘못되었습니다.",
                        ErrorCode.INVALID_REQUEST));
    }

    @ExceptionHandler({Base64DecodingException.class})
    public ResponseEntity<APIResponse<ErrorResponse>> base64Exception(Base64DecodingException e) {
        log.error(e.getMessage());
        return createErrResponse(HttpStatus.BAD_REQUEST,
                new ErrorResponse(
                        HttpStatus.BAD_REQUEST.getReasonPhrase(),
                        "base64 디코딩에 실패했습니다. base64 인코딩이 정상적인지 확인해주세요.",
                        ErrorCode.INVALID_REQUEST));
    }

//
//    @ExceptionHandler(NotValidFormatException.class)
//    public ResponseEntity<APIResponse<ErrorResponse>> notValidFormatException(Exception e) {
//        log.error(e.getMessage());
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .body(createErrResponse(HttpStatus.BAD_REQUEST,
//                        new ErrorResponse(
//                                HttpStatus.BAD_REQUEST.getReasonPhrase(),
//                                e.getMessage(),
//                                ErrorCode.INVALID_REQUEST)));
//    }
//
//    @ExceptionHandler(MissingServletRequestParameterException.class)
//    public ResponseEntity<APIResponse<ErrorResponse>> missingParamException(MissingServletRequestParameterException e) {
//        log.error(e.getMessage());
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .body(createErrResponse(HttpStatus.BAD_REQUEST,
//                        new ErrorResponse(
//                                HttpStatus.BAD_REQUEST.getReasonPhrase(),
//                                "필수 파라미터가 누락되었습니다.",
//                                ErrorCode.INVALID_REQUEST)));
//    }
//
//    @ExceptionHandler(FileUploadBase.SizeLimitExceededException.class)
//    public ResponseEntity<APIResponse<ErrorResponse>> SizeLimitExceededException(FileUploadBase.SizeLimitExceededException e) {
//        log.error(e.getMessage());
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .body(createErrResponse(HttpStatus.BAD_REQUEST,
//                        new ErrorResponse(
//                                HttpStatus.BAD_REQUEST.getReasonPhrase(),
//                                "파일 사이즈가 너무 큽니다. 다른 이미지를 사용해주세요",
//                                ErrorCode.INVALID_REQUEST)));
//    }
//
//    @ExceptionHandler(IllegalRequestException.class)
//    public ResponseEntity<APIResponse<ErrorResponse>> illegalRequestException(IllegalRequestException e) {
//        log.error(e.getMessage());
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .body(createErrResponse(HttpStatus.BAD_REQUEST,
//                        new ErrorResponse(
//                                HttpStatus.BAD_REQUEST.getReasonPhrase(),
//                                e.getMessage(),
//                                ErrorCode.INVALID_REQUEST)));
//    }
//
//    @ExceptionHandler(NoHandlerFoundException.class)
//    protected ResponseEntity<APIResponse<ErrorResponse>> handleNoHandlerFoundException(NoHandlerFoundException e) {
//        log.error(e.getMessage());
//        return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                .body(createErrResponse(HttpStatus.NOT_FOUND,
//                        new ErrorResponse(
//                                HttpStatus.NOT_FOUND.getReasonPhrase(),
//                                "해당 페이지가 존재하지 않습니다",
//                                ErrorCode.NOT_FOUND_HANDLER)));
//    }
//
//    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//    public ResponseEntity<APIResponse<ErrorResponse>> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
//        log.error(e.getMessage());
//        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
//                .body(createErrResponse(HttpStatus.METHOD_NOT_ALLOWED,
//                        new ErrorResponse(
//                                HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase(),
//                                "해당 메소드를 지원하지 않습니다. 파라미터를 확인해주세요.",
//                                ErrorCode.NOT_SUPPORTED_METHOD)));
//    }
//
//    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
//    public ResponseEntity<APIResponse<ErrorResponse>> httpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
//        log.error(e.getMessage());
//        return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
//                .body(createErrResponse(HttpStatus.UNSUPPORTED_MEDIA_TYPE,
//                        new ErrorResponse(
//                                HttpStatus.UNSUPPORTED_MEDIA_TYPE.getReasonPhrase(),
//                                "요청 media type이 맞지 않습니다.",
//                                ErrorCode.NOT_SUPPORTED_MEDIA_TYPE)));
//    }
//
//    @ExceptionHandler(InternalAuthenticationServiceException.class)
//    public ResponseEntity<APIResponse<ErrorResponse>> internalAuthenticationServiceException(InternalAuthenticationServiceException e) {
//        log.error(e.getMessage());
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .body(createErrResponse(HttpStatus.BAD_REQUEST,
//                        new ErrorResponse(
//                                HttpStatus.BAD_REQUEST.getReasonPhrase(),
//                                e.getMessage(),
//                                ErrorCode.INVALID_REQUEST)));
//    }
//
//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public ResponseEntity<APIResponse<ErrorResponse>> httpMessageNotReadableException(HttpMessageNotReadableException e) {
//        log.error(e.getMessage());
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .body(createErrResponse(HttpStatus.BAD_REQUEST,
//                        new ErrorResponse(
//                                HttpStatus.BAD_REQUEST.getReasonPhrase(),
//                                "request JSON 파싱 에러. request body를 확인해주세요.",
//                                ErrorCode.INVALID_REQUEST)));
//    }
//
//    @ExceptionHandler(UuidValidException.class)
//    public ResponseEntity<APIResponse<ErrorResponse>> uuidValidException(UuidValidException e) {
//        log.error(e.getMessage());
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
//                .body(createErrResponse(HttpStatus.BAD_REQUEST,
//                        new ErrorResponse(
//                                HttpStatus.BAD_REQUEST.getReasonPhrase(),
//                                "요청 id 에러. " + e.getMessage(),
//                                ErrorCode.INVALID_REQUEST)));
//    }
//
//    @ExceptionHandler(JwtTokenPayloadException.class)
//    public ResponseEntity<APIResponse<ErrorResponse>> jwtTokenPayloadException(JwtTokenPayloadException e) {
//        log.error(e.getMessage());
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(createErrResponse(HttpStatus.INTERNAL_SERVER_ERROR,
//                        new ErrorResponse(
//                                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
//                                e.getMessage(),
//                                ErrorCode.FAIL_TOKEN_PARSING)));
//    }
//
//    @ExceptionHandler(GenericJDBCException.class)
//    public ResponseEntity<APIResponse<ErrorResponse>> GenericJDBCExceptionException(GenericJDBCException e) {
//        log.error(e.getMessage());
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                .body(createErrResponse(HttpStatus.INTERNAL_SERVER_ERROR,
//                        new ErrorResponse(
//                                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
//                                "내부 쿼리 수행 에러. 관리자 문의",
//                                ErrorCode.FAIL_QUERY)));
//    }
//
//    @ExceptionHandler(IdNotFoundException.class)
//    public ResponseEntity<APIResponse<ErrorResponse>> idNotFoundException(IdNotFoundException e) {
//        log.error(e.getMessage());
//        return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                .body(createErrResponse(HttpStatus.NOT_FOUND,
//                        new ErrorResponse(
//                                HttpStatus.NOT_FOUND.getReasonPhrase(),
//                                e.getMessage(),
//                                ErrorCode.NOT_FOUND_USER)));
//    }
//
//    @ExceptionHandler(ForbiddenException.class)
//    public ResponseEntity<APIResponse<ErrorResponse>> forbiddenException(ForbiddenException e) {
//        log.error(e.getMessage());
//        return ResponseEntity.status(HttpStatus.FORBIDDEN)
//                .body(createErrResponse(HttpStatus.FORBIDDEN,
//                        new ErrorResponse(
//                                HttpStatus.FORBIDDEN.getReasonPhrase(),
//                                e.getMessage(),
//                                ErrorCode.FORBIDDEN_ERROR)));
//    }
//
//    @ExceptionHandler(DuplicatedResourceException.class)
//    public ResponseEntity<APIResponse<ErrorResponse>> duplicatedIdException(DuplicatedResourceException e) {
//        log.error(e.getMessage());
//        return ResponseEntity.status(HttpStatus.CONFLICT)
//                .body(createErrResponse(HttpStatus.CONFLICT,
//                        new ErrorResponse(
//                                HttpStatus.CONFLICT.getReasonPhrase(),
//                                e.getMessage(),
//                                ErrorCode.DUPLICATED_REQUEST)));
//    }
//
//    @ExceptionHandler(AmazonS3Exception.class)
//    public ResponseEntity<APIResponse<ErrorResponse>> S3ArgumentException(AmazonS3Exception e) {
//        log.error(e.getMessage());
//        return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                .body(createErrResponse(HttpStatus.NOT_FOUND,
//                        new ErrorResponse(
//                                HttpStatus.NOT_FOUND.getReasonPhrase(),
//                                e.getMessage(),
//                                ErrorCode.NOT_FOUND_RESOURCE)));
//    }
//
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIResponse<ErrorResponse>> resourceNotFoundException(ResourceNotFoundException e) {
        log.error(e.getMessage());
        return createErrResponse(HttpStatus.NOT_FOUND,
                        new ErrorResponse(
                                HttpStatus.NOT_FOUND.getReasonPhrase(),
                                e.getMessage(),
                                ErrorCode.NOT_FOUND_RESOURCE));
    }
//
//    @ExceptionHandler(ExpiredTokenException.class)
//    public ResponseEntity<APIResponse<ErrorResponse>> expiredTokenException(ExpiredTokenException e) {
//        log.error(e.getMessage());
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                .body(createErrResponse(HttpStatus.UNAUTHORIZED,
//                        new ErrorResponse(
//                                HttpStatus.UNAUTHORIZED.getReasonPhrase(),
//                                e.getMessage(),
//                                ErrorCode.UNAUTHORIZED_ERROR)));
//    }
//
//    @ExceptionHandler(BadCredentialsException.class)
//    public ResponseEntity<APIResponse<ErrorResponse>> mismatchException(BadCredentialsException e) {
//        log.error(e.getMessage());
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                .body(createErrResponse(HttpStatus.UNAUTHORIZED,
//                        new ErrorResponse(
//                                HttpStatus.UNAUTHORIZED.getReasonPhrase(),
//                                e.getMessage(),
//                                ErrorCode.UNAUTHORIZED_ERROR)));
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<ErrorResponse>> allException(Exception e) {
        log.error(e.getMessage());
        return createErrResponse(HttpStatus.INTERNAL_SERVER_ERROR,
                new ErrorResponse(
                        HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                        "서버 에러. 관리자 문의." + e.getMessage(),
                        ErrorCode.FAIL));
    }
}
