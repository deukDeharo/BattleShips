package salvo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * Created by gerarddeharoramirez on 15/5/17.
 */
@RepositoryRestResource
public interface SalvoRepository extends JpaRepository<Salvo,Long>{

}
