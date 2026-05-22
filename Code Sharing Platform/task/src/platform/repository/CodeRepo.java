package platform.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import platform.service.Code;

import java.util.List;
import java.util.UUID;

@Repository
public interface CodeRepo extends CrudRepository<Code, UUID> {

    List<Code> findTop10ByOrderByDateDesc();
}
