package gm.utility.global.response;

import lombok.Data;

public record ErrorResponse(String errorMessage, String errorDetail, ErrorCode errorCode) {
}
