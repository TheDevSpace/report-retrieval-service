package kz.eub.reportretrievalservice.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import kz.eub.reportretrievalservice.Service.AbisReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class AbisReportController {
    private final AbisReportService abisReportService;

    @GetMapping("/Report/ABIS")
    public ResponseEntity<String> getReportAbis(@RequestParam String bin) throws JsonProcessingException {
        String resp = abisReportService.getAbisData(bin);
        System.out.println("check: " + resp);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
}
