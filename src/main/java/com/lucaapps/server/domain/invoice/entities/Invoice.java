package com.lucaapps.server.domain.invoice.entities;

import com.lucaapps.server.domain.common.BaseEntity;
import com.lucaapps.server.domain.user.entities.AppUser;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "invoice")
public class Invoice extends BaseEntity {

    @Column(name = "description")
    private String description;

    @Column(name = "payment_due")
    private LocalDateTime paymentDue;

    @OneToMany(mappedBy = "invoice", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Item> items = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;


    public BigDecimal getTotalCost() {
        return this.items.stream().map(i -> i.sum()).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
