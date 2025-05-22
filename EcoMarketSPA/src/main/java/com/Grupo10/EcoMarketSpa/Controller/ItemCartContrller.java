package com.Grupo10.EcoMarketSpa.Controller;

import com.Grupo10.EcoMarketSpa.Model.ItemCarrito;
import com.Grupo10.EcoMarketSpa.Service.ItemCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ItemCart")
public class ItemCartContrller {

    @Autowired
    ItemCartService itemCartService;

    @GetMapping
    public String getAllItemCart(){
        return itemCartService.getAllItemCarts();
    }

    @PostMapping
    public String addItemCart(@RequestBody ItemCarrito itemCart){
        return itemCartService.addItemCart(itemCart);
    }

    @GetMapping("/{id}")
    public String getItemCartById(@PathVariable int id) {
        return itemCartService.getItemCartById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteItemCart(@PathVariable int id) {
        return itemCartService.deleteItemCart(id) ;
    }

    @PutMapping("/{id}")
    public String updateItemCart(@PathVariable int id, @RequestBody ItemCarrito itemCart) {
        return itemCartService.updateItemCart(id, itemCart);
    }
}
