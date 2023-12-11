package kz.eub.reportretrievalservice.Domain.SoHo;

import kz.eub.reportretrievalservice.Domain.SoHo.ScoringData;
import lombok.Data;

@Data
public class ScoringResponse {
    private int result;
    private String resultDescr;
    private ScoringData data;

    // Геттеры и сеттеры
}