package gm.utility.chart.service;

import gm.utility.chart.domain.ChartInfo;
import gm.utility.chart.dto.ExecutePythonRequestDto;
import gm.utility.chart.dto.ExecutePythonResponseDto;
import gm.utility.chart.dto.UploadResponseDto;
import gm.utility.chart.dto.UploadRequestDto;
import gm.utility.chart.repository.ChartInfoRepository;
import gm.utility.global.exception.ResourceNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ChartServiceImpl implements ChartService {
    private final ChartInfoRepository repository;
    private final FileManager fileManager;
    private final PythonExecutor pythonExecutor;

    //TODO: 마운트 예정.
    @Value("${file-save-prefix-path}")
    @Getter
    private static String savePrefixPath = "";
    @Value("${file-web-path}")
    @Getter
    private static String webPath = "";
    @Value("${wmp-path}")
    @Getter
    private static String wmpPath = "";
    @Value("${python-script-arg}")
    @Getter
    private static String scriptArgs = "";

    @Transactional
    @Override
    public UploadResponseDto uploadFile(UploadRequestDto request) {
        String physicalFilePath = fileManager.createFile(savePrefixPath, request.username(), request.data());
        ChartInfo chart = new ChartInfo(request.data(), webPath, physicalFilePath, request.username(), request.memo());
        repository.save(chart);
        return new UploadResponseDto(physicalFilePath, chart.getId());
    }

    @Transactional
    @Override
    public ExecutePythonResponseDto executePythonFile(ExecutePythonRequestDto request) {
        ChartInfo chart = findById(request.id());
        String wmpResultPath = pythonExecutor.executePythonScript(String.format("%s %s", scriptArgs, chart.getFullFilePath()));

        return new ExecutePythonResponseDto(webPath + "/" + wmpResultPath);
    }

    private ChartInfo findById(long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("해당 차트가 존재하지 않습니다."));
    }

}