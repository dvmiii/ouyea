package com.Grupo10.EcoMarketSpa.Controller;

import com.Grupo10.EcoMarketSpa.Model.ItemPedido;
import com.Grupo10.EcoMarketSpa.Repository.ItemOrderRepository;
import com.Grupo10.EcoMarketSpa.Service.ItemOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ItemOrder")
public class ItemOrderController {

    @Autowired
    ItemOrderService itemOrderService;

    @GetMapping
    public String getAllItemOrder(){
        return itemOrderService.getAllItemOrders();
    }

    @PostMapping
    public String addItemOrder(@RequestBody ItemPedido itemOrder){
        return itemOrderService.addItmOrder(itemOrder);
    }

    @GetMapping("/{id}")
    public String getItemOrderById(@PathVariable int id) {
        return itemOrderService.getItemOrderById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteItemOrder(@PathVariable int id) {
        return itemOrderService.deleteItemOrder(id) ;
    }

    @PutMapping("/{id}")
    public String updateItemOrder(@PathVariable int id, @RequestBody ItemPedido itemOrder) {
        return itemOrderService.updateItemOrder(id, itemOrder);
    }
}
