package net.ins.hw.netflix.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import static net.ins.hw.netflix.domain.Limit.COLLECTION;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = COLLECTION)
public class Limit {
    public static final String COLLECTION = "limits";
    @Id
    private String cardNumber;
    private String currencyCode;
    private Long limit;
}
