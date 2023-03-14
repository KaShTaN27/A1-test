package by.kashtan.supplies.service;

import by.kashtan.supplies.model.PostedProduct;
import by.kashtan.supplies.repository.PostedProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostedProductService {

    private final PostedProductRepository productRepository;

    public List<PostedProduct> saveAll(List<PostedProduct> products) {
        return productRepository.saveAll(products);
    }
}
