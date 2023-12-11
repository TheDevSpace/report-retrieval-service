package kz.eub.reportretrievalservice.Domain.SoHo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ScoringData {
    private Long id;
    private String riskClass;
    private int ball;
    private String defaultRate;
    private boolean isFrozen;
    private LocalDateTime queryDate;
    private String lastName;
    private String middleName;
    private String firstName;
    private String badRate;
    private LocalDateTime timestamp;
    private String iin;
    private boolean isEnt;

}