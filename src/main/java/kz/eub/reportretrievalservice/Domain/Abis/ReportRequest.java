package kz.eub.reportretrievalservice.Domain.Abis;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ReportRequest {
    private String product_code;
    private int request_id;
    private String bin;
    private String callback_url;
    private String client_bin;

    // геттеры и сеттеры
}