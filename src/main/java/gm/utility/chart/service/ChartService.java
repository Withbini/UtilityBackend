package gm.utility.chart.service;

import gm.utility.chart.dto.ExecutePythonRequestDto;
import gm.utility.chart.dto.ExecutePythonResponseDto;
import gm.utility.chart.dto.UploadResponseDto;
import gm.utility.chart.dto.UploadRequestDto;

public interface ChartService {
    UploadResponseDto uploadFile(UploadRequestDto request);
    ExecutePythonResponseDto executePythonFile(ExecutePythonRequestDto request);
}
