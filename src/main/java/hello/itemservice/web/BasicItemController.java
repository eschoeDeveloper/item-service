package hello.itemservice.web;

import hello.itemservice.domain.Item;
import hello.itemservice.domain.ItemRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(Model model, @PathVariable("itemId") Long itemId) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        return "basic/addForm";
    }

    //@PostMapping("/add")
    public String save(Model model,
      @RequestParam String itemName,
      @RequestParam Integer price,
      @RequestParam Integer quantity ) {

        Item reqItem = Item.builder()
                .itemName(itemName)
                .price(price)
                .quantity(quantity)
                .build();

        Item savedItem = itemRepository.save(reqItem);

        model.addAttribute("item", savedItem);

        return "basic/item";

    }

    //@PostMapping("/add")
    public String saveV2(Model model,
                       @ModelAttribute("item") Item item ) {

        Item savedItem = itemRepository.save(item);
        model.addAttribute("item", savedItem);

        return "basic/item";

    }

    //@PostMapping("/add")
    public String saveV3(@ModelAttribute Item item ) {

        item = itemRepository.save(item);
        //model.addAttribute("item", savedItem);

        return "basic/item";

    }

    //@PostMapping("/add")
    public String saveV4( Item item ) {

        item = itemRepository.save(item);
        //model.addAttribute("item", savedItem);

        return "basic/item";

    }

    //@PostMapping("/add")
    public String saveV5( Item item ) {

        Item reqItem = Item.builder()
                .itemName(item.getItemName())
                .price(item.getPrice())
                .quantity(item.getQuantity())
                .build();

        reqItem = itemRepository.save(reqItem);

        return "redirect:/basic/items/" + reqItem.getId();
    }

    @PostMapping("/add")
    public String saveV6(Item item, RedirectAttributes redirectAttributes) {

        Item reqItem = Item.builder()
                .itemName(item.getItemName())
                .price(item.getPrice())
                .quantity(item.getQuantity())
                .build();

        Item savedItem = itemRepository.save(reqItem);

        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);

        return "redirect:/basic/items/{itemId}";

    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item findItem = itemRepository.findById(itemId);
        model.addAttribute("item", findItem);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }

    @PostConstruct
    public void init() {
        itemRepository.save(new Item("itemA", 100000, 20));
        itemRepository.save(new Item("itemB", 200000, 50));
    }

}
