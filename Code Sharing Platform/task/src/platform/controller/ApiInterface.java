package platform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import platform.service.Code;
import platform.service.CodeDto;
import platform.service.CodeService;

import javax.validation.Valid;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class ApiInterface {
    private final CodeService codeService;

    @Autowired
    public ApiInterface(CodeService codeService){
        this.codeService = codeService;
    }

    @GetMapping("/api/code/{N}")
    public ResponseEntity<Code> getCode(@PathVariable UUID N) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

        Code code = codeService.getCode(N);
        if(code.getInitialTime() > 0){
            if(Duration.between(code.getDate(), LocalDateTime.now()).getSeconds() >= code.getInitialTime()){
                return ResponseEntity.notFound().headers(headers).build();
            }
            else {
                code.setTime(code.getInitialTime() - Duration.between(code.getDate(), LocalDateTime.now()).getSeconds());
            }
        }
        if(code.getInitialViews() > 0){
            if(code.getViews() <= 0){
                return ResponseEntity.notFound().headers(headers).build();
            }
            else {
                code.setViews(code.getViews()-1);
            }
        }
        codeService.setCode(code);
        return ResponseEntity.ok()
                .headers(headers)
                .body(code);
    }

    @GetMapping("/api/code/latest")
    public ResponseEntity<List<Code>> getLatestCode(){
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

        return ResponseEntity.ok()
                .headers(headers)
                .body(codeService.getLatestCode());
    }

    @PostMapping("/api/code/new")
    public ResponseEntity<Map<String,String>> sendCode(@RequestBody @Valid CodeDto code){
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/json");

        Code newCode = new Code(code.getCode(), code.getTime(), code.getViews(), code.getTime(), code.getViews());
        codeService.setCode(newCode);

        return ResponseEntity.ok()
                .headers(headers)
                .body(Map.of("id",String.valueOf(newCode.getId().toString())));
    }
}
