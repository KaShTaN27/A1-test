package by.kashtan.supplies.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Posting {

    @Id
    @Column(name = "posting_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long postingNumber;

    @Temporal(TemporalType.DATE)
    private Date docDate;

    @Temporal(TemporalType.DATE)
    private Date postingDate;

    @OneToMany(mappedBy = "posting", fetch = FetchType.EAGER)
    private List<PostedProduct> products;

    private String username;

    private Boolean isAuthenticated = false;

    public Posting(Long postingNumber, Date docDate, Date postingDate, List<PostedProduct> products, String username) {
        this.postingNumber = postingNumber;
        this.docDate = docDate;
        this.postingDate = postingDate;
        this.products = products;
        this.username = username;
    }
}
