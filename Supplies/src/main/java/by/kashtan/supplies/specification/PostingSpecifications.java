package by.kashtan.supplies.specification;

import by.kashtan.supplies.model.Posting;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PostingSpecifications {

    public Specification<Posting> filterByDateRangeAndAuth(Date from, Date to, Boolean isAuth) {
        return Specification.where(isAuthenticated(isAuth))
                .and(isGraterOrEqualThanFromDate(from))
                .and(isLessOrEqualThanToDate(to));
    }

    private Specification<Posting> isGraterOrEqualThanFromDate(Date from) {
        if (from == null)
            return null;
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("postingDate"), from);
    }

    private Specification<Posting> isLessOrEqualThanToDate(Date to) {
        if (to == null)
            return null;
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("postingDate"), to);
    }

    private Specification<Posting> isAuthenticated(Boolean isAuth) {
        if (isAuth == null)
            return null;
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("isAuthenticated"), isAuth);
    }
}
