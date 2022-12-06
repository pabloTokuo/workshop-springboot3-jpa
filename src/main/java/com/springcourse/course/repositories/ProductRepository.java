package com.springcourse.course.repositories;

import com.springcourse.course.entities.Category;
import com.springcourse.course.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {


}
