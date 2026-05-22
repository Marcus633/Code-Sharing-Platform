package platform.controller;

import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import platform.service.Code;
import platform.service.CodeService;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Controller
public class WebInterface {

    private final CodeService codeService;

    @Autowired
    public WebInterface(CodeService codeService) {
        this.codeService = codeService;
    }

    @GetMapping("/code/{N}")
    public String getCode(Model model, @PathVariable UUID N) throws Exception {
        Code code = codeService.getCode(N);
        if(code.getInitialTime() > 0){
            if(Duration.between(code.getDate(), LocalDateTime.now()).getSeconds() >= code.getInitialTime()){
                throw new SecretException();
            }
            else {
                code.setTime(code.getInitialTime() - Duration.between(code.getDate(), LocalDateTime.now()).getSeconds());
            }
        }
        if(code.getInitialViews() > 0){
            if(code.getViews() <= 0){
                throw new SecretException();
            }
            else {
                code.setViews(code.getViews()-1);
            }
        }
        codeService.setCode(code);
        model.addAttribute("code", codeService.getCode(N).getCode());
        model.addAttribute("date", codeService.getCode(N).getDate());
        model.addAttribute("time_restriction", codeService.getCode(N).getTime());
        model.addAttribute("views_restriction", codeService.getCode(N).getViews());
        model.addAttribute("views_initial_restriction", codeService.getCode(N).getInitialViews());
        return "index";
    }

    @GetMapping("/code/new")
    public String getInputForm() {
        return "inputForm";
    }

    @GetMapping("/code/latest")
    public String getLatestCode(Model model) {
        model.addAttribute("latestList", codeService.getLatestCode());
        return "latestCode";
    }
}
