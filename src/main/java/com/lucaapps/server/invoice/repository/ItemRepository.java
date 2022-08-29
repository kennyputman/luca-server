package com.lucaapps.server.invoice.repository;

import com.lucaapps.server.invoice.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT i FROM Item i WHERE i.id = :item_id AND i.invoice.id = :invoice_id")
    Optional<Item> findById(@Param("invoice_id") Long invoice_id, @Param("item_id") Long item_id);
}
