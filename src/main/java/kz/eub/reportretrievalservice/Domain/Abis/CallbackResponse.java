package kz.eub.reportretrievalservice.Domain.Abis;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CallbackResponse {
    private String product_code;
    private String bin;
    private int report_id;
    private String status;

    // геттеры и сеттеры
}