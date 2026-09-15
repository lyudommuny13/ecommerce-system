package ctm.mptc.kh.ecommerce.order.persistence.entity;

import ctm.mptc.kh.ecommerce.domain.valueobject.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

// JPA Entity must be POJO (Plain old Java Object) class
// POJO
@Getter
@Setter
@NoArgsConstructor

@Entity // create table
@Table(name = "orders") // change table name
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private UUID customerId;
    private UUID businessId;
    private UUID trackingId;
    private OrderStatus orderStatus;
    private String failureMessages;
    private BigDecimal price;

    @OneToMany(mappedBy = "order")
    private List<OrderItemEntity> items;

    @OneToOne
    private OrderAddressEntity orderAddress;
}
