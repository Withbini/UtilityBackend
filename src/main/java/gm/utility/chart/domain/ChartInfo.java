package gm.utility.chart.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ChartInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    private String chart;

    @NonNull
    private String webPath;

    @NonNull
    private String fullFilePath;
    @NonNull
    private String publisher;

    private String memo;

    public ChartInfo(@NonNull String chart, @NonNull String webPath, @NonNull String fullFilePath, @NonNull String publisher, String memo) {
        this.chart = chart;
        this.webPath = webPath;
        this.fullFilePath = fullFilePath;
        this.publisher = publisher;
        this.memo = memo;
    }
}
