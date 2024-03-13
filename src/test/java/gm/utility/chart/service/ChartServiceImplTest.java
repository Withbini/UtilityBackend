package gm.utility.chart.service;

import gm.utility.chart.domain.ChartInfo;
import gm.utility.chart.dto.UploadRequestDto;
import gm.utility.chart.dto.UploadResponseDto;
import gm.utility.chart.repository.ChartInfoRepository;
import gm.utility.chart.service.ChartServiceImpl;
import gm.utility.chart.service.FileManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class ChartServiceImplTest {
    @InjectMocks
    private ChartServiceImpl chartService;
    @Mock
    private ChartInfoRepository repository;
    @Mock
    private FileManager fileManager;


    @Test
    @DisplayName("파일 업로드 테스트")
    void uploadFile() {
        UploadRequestDto request = new UploadRequestDto("jaebin01.lee", "TVPLAT-111111", "data");
        ChartInfo expectedChart = new ChartInfo(
                request.data(),
                ChartServiceImpl.getWebPath(),
                "/jaebin01.lee/2000-01-01-01:00:00/chart.out",
                request.username(),
                request.memo());
        when(repository.save(any())).thenReturn(expectedChart);
        String filePath = expectedChart.getWebPath()+expectedChart.getFullFilePath();
        when(fileManager.createFile(any(),any(),any())).thenReturn(filePath);

        //when
        UploadResponseDto response = chartService.uploadFile(request);

        //then
        assertThat(response.filepath()).contains(ChartServiceImpl.getWebPath() + "/jaebin01.lee/");
    }

    @Test
    @DisplayName("파이썬 파일 수행")
    void runPythonScript(){
        //given
    }
}