package LikeCount;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TeamRepository extends CrudRepository<Team, Long> {
    Team findByNameIgnoreCase(String name);
}
