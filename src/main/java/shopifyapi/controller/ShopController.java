package shopifyapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import shopifyapi.manager.ShopManager;
import shopifyapi.model.Shop;

@RestController
@RequestMapping(value = "/shops")
public class ShopController extends BaseController<Shop, ShopManager> {

    @Autowired
    public ShopController(ShopManager manager) {
        super(manager);
    }
}
