package com.shimunmatic.ecommerce.item.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ItemRepositoryTest {
    @Autowired
    private ItemRepository itemRepository;

    @Test
    void findByKeyword() {
        itemRepository.findByKeyword("test");
    }
}