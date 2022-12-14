package com.springcourse.course.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.springcourse.course.entities.enums.OrderStatus;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_order")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    Formato de hora e data
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant moment;

    private Integer orderStatus;

    @ManyToOne // Muitos para um
    @JoinColumn(name = "client_id")
    private User client;

    // ID.ORDER PQ O ORDERITEM TEM O ATRIBUTO ID
    // E ESSE ATRIBUTO ID DO TIPO ORDERITEMPK
    // TEM POR SUA VEZ O ORDER
    // POR ISSO ID.ORDER
    @OneToMany(mappedBy = "id.order")
    private Set<OrderItem> items = new HashSet<>();

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL) // Mapeando o pedido "ORDER" de payment e usando cascade para que o pedido e o payment seja o mesmo ID
    private Payment payment;

    public Order() {
    }

    public Order(Long id, Instant moment, OrderStatus orderStatus,User client) {
        this.id = id;
        this.moment = moment;
        setOrderStatus(orderStatus);
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

// Pegando o numero inteiro e transformando em OrderStatus
    public OrderStatus getOrderStatus() {
        return OrderStatus.valueOf(orderStatus);
    }

//    Guardar um numero inteiro
    public void setOrderStatus(OrderStatus orderStatus) {
//        Metodo defensivo, caso o programador passe um OrderStatus nulo
        if (orderStatus != null) {
            this.orderStatus = orderStatus.getCode();
        }
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    // Metodo preco total items
    public Double getTotal(){
        double sum = 0.0;
        for (OrderItem x : items) { // Varrer x na lista de items
            sum += x.getSubTotal();
        }
        return sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id.equals(order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
