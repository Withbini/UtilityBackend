package gm.utility.chart.controller;

import gm.utility.chart.dto.UploadResponseDto;
import gm.utility.chart.service.ChartService;
import gm.utility.global.response.APIResponse;
import gm.utility.chart.dto.UploadRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChartGeneratorController {
    private final ChartService chartService;

    @PostMapping("/chart/upload")
    ResponseEntity<APIResponse<UploadResponseDto>> uploadFile(@Validated UploadRequestDto uploadRequestDto,
                                                              BindingResult bindingResult) {
        final var result = chartService.uploadFile(uploadRequestDto);
        return APIResponse.createPutResponse(result);
    }
}
