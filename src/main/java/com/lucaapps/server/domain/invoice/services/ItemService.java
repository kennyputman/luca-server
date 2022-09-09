package com.lucaapps.server.domain.invoice.services;

import com.lucaapps.server.domain.invoice.entities.Item;

import java.util.Optional;

public interface ItemService {
    Optional<Item> getItemById(Long invoice_id, Long item_id);
}
