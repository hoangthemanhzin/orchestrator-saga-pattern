package com.example.order.Repository;

import com.example.order.Model.OrderDao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface OrderRepository extends CrudRepository<OrderDao, Long> {

}
