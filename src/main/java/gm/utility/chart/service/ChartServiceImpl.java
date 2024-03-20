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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChartServiceImpl implements ChartService {
    private final ChartInfoRepository repository;
    private final FileManager fileManager;
    private final PythonExecutor pythonExecutor;

    //TODO: 마운트 예정.
    @Value("${file-save-prefix-path}")
    @Getter
    private String saveFilePrefixPath = "";

    @Value("${file-web-path}")
    @Getter
    private String apacheWebFileViewerPath = "";

    @Value("${wmp-path}")
    @Getter
    private String wmpPath = "";

    @Value("${python-script-arg}")
    @Getter
    private String scriptArgs = "";

    @Transactional
    @Override
    public UploadResponseDto uploadFile(UploadRequestDto request) {
        log.info("[uploadFile] request :{}", request);
        String physicalFilePath = fileManager.createFile(saveFilePrefixPath, request.username(), request.data());
        ChartInfo chart = new ChartInfo(request.data(), apacheWebFileViewerPath,
                physicalFilePath, request.username(), request.memo());
        repository.save(chart);
        return new UploadResponseDto(saveFilePrefixPath + "/" + physicalFilePath, chart.getId());
    }

    @Transactional
    @Override
    public ExecutePythonResponseDto executePythonFile(ExecutePythonRequestDto request) {
        ChartInfo chart = findById(request.id());
        String wmpResultPath = "";
        // execute python {wmp-path}/{sbsi argument} + {chart file path}
        //TODO: support GET chart info and custom python path
        List<String> pythonResult = pythonExecutor.executePythonScript(String.format("%s/%s %s", wmpPath, scriptArgs,
                saveFilePrefixPath + "/" + chart.getFullFilePath()));

        wmpResultPath = fileManager.getFilePath(pythonResult);
        log.info("wmp result :{}", wmpResultPath);

        return new ExecutePythonResponseDto(apacheWebFileViewerPath + "/" + wmpResultPath);
    }

    private ChartInfo findById(long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("해당 차트가 존재하지 않습니다."));
    }
}
