package cool.cfapps.springbootpostgresonk8s.repository;

import cool.cfapps.springbootpostgresonk8s.entity.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ValueRepository extends JpaRepository<Value, Long> {
    List<Value> findByDescription(String value);
}
