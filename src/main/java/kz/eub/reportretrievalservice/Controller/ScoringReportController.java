package kz.eub.reportretrievalservice.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import kz.eub.reportretrievalservice.Service.ScoringReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScoringReportController {
    private final ScoringReportService scoringReportService;

    @GetMapping("/Scoring/SOHO")
    public ResponseEntity<String> getScoring(@RequestParam String bin) throws JsonProcessingException {
        String resp = scoringReportService.getScoringData(bin);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
}
