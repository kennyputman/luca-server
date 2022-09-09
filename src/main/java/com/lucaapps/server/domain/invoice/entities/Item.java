package com.lucaapps.server.domain.invoice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lucaapps.server.domain.common.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "item")
public class Item extends BaseEntity {

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    public BigDecimal sum(){
        return this.price.multiply(BigDecimal.valueOf(this.quantity));
    }

}
