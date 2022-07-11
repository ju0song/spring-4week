package sparta.week4.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sparta.week4.Entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> { }