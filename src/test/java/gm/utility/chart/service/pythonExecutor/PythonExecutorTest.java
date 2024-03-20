package gm.utility.chart.service.pythonExecutor;

import gm.utility.chart.service.PythonExecutor;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.util.ReflectionTestUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
class PythonExecutorTest {
    @InjectMocks
    private PythonExecutor pythonExecutor;

    @BeforeEach()
    public void setup(){
        ReflectionTestUtils.setField(pythonExecutor,
                "pythonExecutionPath",
                "/opt/anaconda3/bin/python");
    }

    @Test
    @DisplayName("파이썬 파일 실행")
    public void test1() {
        Path pythonFilePath = Path.of("src/test/java/gm/utility/chart/service/pythonExecutor/test.py");
        Path dataFilePath = Path.of("src/test/java/gm/utility/chart/service/pythonExecutor/data.py");
        List<String> result = pythonExecutor.executePythonScript(String.format("%s %s -l -g",pythonFilePath.toAbsolutePath().toString(),
                dataFilePath.toAbsolutePath().toString()));
        assertThat(result.get(0)).isEqualTo("15");
    }
}