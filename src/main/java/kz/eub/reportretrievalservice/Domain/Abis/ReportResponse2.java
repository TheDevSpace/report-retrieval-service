package kz.eub.reportretrievalservice.Domain.Abis;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ReportResponse2 {
    private String fcb_request_id;
    private String status;
    private String message;
    private ReportData data;

    // геттеры и сеттеры
}
