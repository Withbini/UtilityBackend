package gm.utility.chart.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Component
@Slf4j
public class PythonExecutor {
    @Value("${python-path}")
    public static String pythonExecutionPath;

    public String executePythonScript(String commandList) {
        try {
            //TODO:명령어를 맞게 입력
            String[] commands = parseCommandList(commandList);
            ProcessBuilder pb = new ProcessBuilder(commands);

            log.info("[executePythonScript] command:{}", (Object) commands);

            Process process = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            String result = "";
            while ((line = reader.readLine()) != null) {
                log.info(line);
                result = line;
            }

            // Wait for the process to complete
            int exitCode = process.waitFor();
            log.info("Python script execution finished with exit code: {}. result :{}", exitCode, result);
            return result;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String[] parseCommandList(String commandList) {
        String[] parsedCommandList = commandList.split(" ");
        String[] commands = new String[parsedCommandList.length + 1];
        commands[0] = pythonExecutionPath;
        for (int i = 0; i < parsedCommandList.length; ++i) {
            commands[i + 1] = parsedCommandList[i];
        }
        return commands;
    }
}