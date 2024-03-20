package gm.utility.chart.controller;

import gm.utility.chart.dto.ExecutePythonRequestDto;
import gm.utility.chart.dto.ExecutePythonResponseDto;
import gm.utility.chart.dto.UploadResponseDto;
import gm.utility.chart.service.ChartService;
import gm.utility.global.response.APIResponse;
import gm.utility.chart.dto.UploadRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChartGeneratorController {
    private final ChartService chartService;

    @PostMapping("/chart")
    ResponseEntity<APIResponse<UploadResponseDto>> uploadFile(@RequestBody @Validated UploadRequestDto uploadRequestDto,
                                                              BindingResult bindingResult) {
        final var result = chartService.uploadFile(uploadRequestDto);
        return APIResponse.createPutResponse(result);
    }

    @PostMapping("/chart/python")
    ResponseEntity<APIResponse<ExecutePythonResponseDto>> executePython(@RequestBody @Validated ExecutePythonRequestDto request){
        final var result = chartService.executePythonFile(request);
        return APIResponse.createPutResponse(result);
    }
}
