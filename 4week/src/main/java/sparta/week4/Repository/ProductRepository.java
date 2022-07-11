package sparta.week4.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.week4.Entity.Product;


import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByUserId(Long userId); //userid를 가진 product를 찾음
}