package kz.eub.reportretrievalservice.Domain.Abis;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class ReportTypeResponse {
    private String status;
    private String message;
    private List<Date> dates;

}
