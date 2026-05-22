package platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import platform.repository.CodeRepo;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CodeService {

    private final CodeRepo codeRepo;

    @Autowired
    public CodeService(CodeRepo codeRepo) {
        this.codeRepo = codeRepo;
    }

    public Code getCode(UUID id) {
        return codeRepo.findById(id).orElse(null);
    }

    public void setCode(Code code) {
        codeRepo.save(code);
    }

    public List<Code> getLatestCode() {
        //codeRepo.findTop10ByOrderByDateDesc();
        List<Code> list = (List<Code>) codeRepo.findAll();
        list = list.stream().filter(a -> a.getTime() <= 0).filter(a -> a.getViews() <= 0).collect(Collectors.toList());
        Collections.reverse(list);
        list = list.stream().limit(10).collect(Collectors.toList());
        return list;
    }
}
