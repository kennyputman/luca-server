package com.lucaapps.server.invoice.services;

import com.lucaapps.server.invoice.entities.Item;
import com.lucaapps.server.invoice.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    @Override
    public Optional<Item> getItemById(Long invoice_id, Long item_id) {
        return this.itemRepository.findById(invoice_id, item_id);
    }
}
