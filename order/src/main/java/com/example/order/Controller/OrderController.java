package com.example.order.Controller;

import com.example.order.Commonutils.DAOGenerator;
import com.example.order.Model.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("api/v1")
public class OrderController {
    private DAOGenerator daoGenerator;

    public OrderController(DAOGenerator daoGenerator){
        this.daoGenerator = daoGenerator;
    }

    // In this case, Optional should be used to handle cases with or without values :
    // avoid problems and errors like NullPointerException.
    public ResponseEntity<String> orderRequest(@RequestParam(name = "orders") Optional<Long> orders,
                                               @RequestParam(name = "items") Optional<Long> items){
        List<OrderDao> orderDao = daoGenerator.ListOrderDao(orders, items);
        return null;
    }
}
