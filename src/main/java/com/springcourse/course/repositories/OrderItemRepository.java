package com.springcourse.course.repositories;

import com.springcourse.course.entities.OrderItem;
import com.springcourse.course.entities.pk.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {


}
