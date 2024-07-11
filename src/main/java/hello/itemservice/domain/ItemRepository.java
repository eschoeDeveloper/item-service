package hello.itemservice.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>();
    private static Long sequence = 0L;

    public Item save(Item item) {

        Item savedItem = Item.builder()
                .id(++sequence)
                .quantity(item.getQuantity())
                .price(item.getPrice())
                .itemName(item.getItemName())
                .build();

        store.put(savedItem.getId(), savedItem);

        return savedItem;

    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long itemId, Item updateItem) {

        Item findItem = findById(itemId);
        Item updateParam = findItem.builder()
                .id(itemId)
                .itemName(updateItem.getItemName())
                .price(updateItem.getPrice())
                .quantity(updateItem.getQuantity())
                .build();

        store.remove(itemId);
        store.put(itemId, updateParam);

    }

    public void clearStore() {
        store.clear();
    }

}
