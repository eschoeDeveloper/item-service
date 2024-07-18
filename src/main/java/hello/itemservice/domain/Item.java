package hello.itemservice.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter @Builder
@ToString
public class Item {

    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;

    public Item() {

    }

    public Item(Long id, String itemName, Integer quantity) {
        this.id = id;
        this.itemName = itemName;
        this.quantity = quantity;
    }

    public Item(Long id, String itemName, Integer price, Integer quantity) {
        this.id = id;
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }

    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
