package com.lucaapps.server.invoice.services;

import com.lucaapps.server.invoice.entities.Item;

import java.util.Optional;

public interface ItemService {
    Optional<Item> getItemById(Long id);
}
