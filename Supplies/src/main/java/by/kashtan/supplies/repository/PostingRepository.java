package by.kashtan.supplies.repository;

import by.kashtan.supplies.model.Posting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface PostingRepository extends JpaRepository<Posting, Long>, JpaSpecificationExecutor<Posting> {
    List<Posting> findAllByPostingNumberIn(Collection<Long> postingNumber);
}
