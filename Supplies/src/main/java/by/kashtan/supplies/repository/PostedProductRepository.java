package by.kashtan.supplies.repository;

import by.kashtan.supplies.model.PostedProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostedProductRepository extends JpaRepository<PostedProduct, Long> {
}
