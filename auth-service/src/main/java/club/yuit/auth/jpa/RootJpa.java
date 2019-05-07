package club.yuit.auth.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @author yuit
 * @date 2019/4/24 11:00
 */
@NoRepositoryBean
public interface RootJpa<T,D extends Serializable>  extends JpaRepository<T,D>, JpaSpecificationExecutor<T> {
}
