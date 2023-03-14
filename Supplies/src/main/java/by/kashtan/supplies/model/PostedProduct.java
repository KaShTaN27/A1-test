package by.kashtan.supplies.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class PostedProduct {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private Integer quantity;

    private String bUn;

    private Double amount;

    private String currency;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "posting_id")
    private Posting posting;

    public PostedProduct(String description, Integer quantity, String bUn, Double amount, String currency) {
        this.description = description;
        this.quantity = quantity;
        this.bUn = bUn;
        this.amount = amount;
        this.currency = currency;
    }
}
