package gm.utility.chart.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(indexes = {@Index(name = "i_publisher", columnList = "publisher"),})
public class ChartInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NonNull
    @Lob
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
