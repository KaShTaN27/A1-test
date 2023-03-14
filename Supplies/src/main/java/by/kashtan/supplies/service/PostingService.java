package by.kashtan.supplies.service;

import by.kashtan.supplies.model.Posting;
import by.kashtan.supplies.repository.PostingRepository;
import by.kashtan.supplies.specification.PostingSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostingService {

    private final PostedProductService productService;
    private final PostingSpecifications postingSpecifications;
    private final PostingRepository postingRepository;

    public List<Posting> saveAll(List<Posting> postings) {
        var saved = postingRepository.saveAll(postings.stream()
                .filter(p -> !postingRepository.existsByPostingNumber(p.getPostingNumber()))
                .toList());
        saved.forEach(p -> {
            p.getProducts().forEach(pr -> pr.setPosting(p));
            p.setProducts(productService.saveAll(p.getProducts()));
        });
        return saved;
    }

    public List<Posting> getAll(Date from, Date to, Boolean isAuth) {
        var filterByDateRangeAndAuth = postingSpecifications.filterByDateRangeAndAuth(from, to, isAuth);
        return postingRepository.findAll(filterByDateRangeAndAuth);
    }
}
