package net.ins.hw.netflix.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import static net.ins.hw.netflix.domain.Order.COLLECTION;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = COLLECTION)
public class Order {
    public static final String COLLECTION = "orders";
    @Id
    private String id;
    @Indexed
    private String ownerId;
    private String cardNumber;
    private Amount amount;
    @Indexed
    private OrderStatus status;
}
