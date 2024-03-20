package gm.utility.chart.dto;

import java.util.List;

public record ExecutePythonRequestDto(long id, String username, String command, List<String> arguments) {
}
