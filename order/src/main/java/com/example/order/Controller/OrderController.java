package com.example.order.Controller;

import com.example.common.entity.order;
import com.example.order.Commonutils.CommonObjectGenerator;
import com.example.order.Commonutils.DAOGenerator;
import com.example.order.Model.OrderDao;
import com.example.order.Service.KafkaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("api/v1")
public class OrderController {
    private final DAOGenerator daoGenerator;
    private final CommonObjectGenerator commonObjectGenerator;
    private final KafkaService kafkaService;

    public OrderController(DAOGenerator daoGenerator, KafkaService kafkaService, CommonObjectGenerator commonObjectGenerator){
        this.daoGenerator = daoGenerator;
        this.kafkaService = kafkaService;
        this.commonObjectGenerator = commonObjectGenerator;
    }

    // In this case, Optional should be used to handle cases with or without values :
    // avoid problems and errors like NullPointerException.
    public ResponseEntity<String> orderRequest(@RequestParam(name = "orders") Optional<Long> orders,
                                               @RequestParam(name = "items") Optional<Long> items){
        List<OrderDao> listOrderDao = daoGenerator.ListOrderDao(orders, items);

        List<order> listOrderToKafka = listOrderDao.stream().map(commonObjectGenerator::orderGenerator
                ).collect(Collectors.toList());

        listOrderToKafka.forEach(order -> kafkaService.sendOrder(order.getOrderNumber(), order));
        return null;
    }
}
