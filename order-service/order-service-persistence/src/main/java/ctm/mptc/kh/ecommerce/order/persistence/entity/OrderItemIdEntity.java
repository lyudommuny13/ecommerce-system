package ctm.mptc.kh.ecommerce.order.persistence.entity;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class OrderItemIdEntity implements Serializable {
    private Integer id;
    private OrderEntity order;
}
