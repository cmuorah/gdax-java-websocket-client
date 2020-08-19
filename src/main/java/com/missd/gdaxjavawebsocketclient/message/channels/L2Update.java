package com.missd.gdaxjavawebsocketclient.message.channels;

import net.openhft.chronicle.wire.SelfDescribingMarshallable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.missd.gdaxjavawebsocketclient.MessageAsMapKeys.CHANGES;
import static com.missd.gdaxjavawebsocketclient.MessageAsMapKeys.PRODUCT_ID;

/**
 * {
 * "type": "l2update",
 * "product_id": "BTC-EUR",
 * "changes": [
 * ["buy", "6500.09", "0.84702376"],
 * ["sell", "6507.00", "1.88933140"],
 * ["sell", "6505.54", "1.12386524"],
 * ["sell", "6504.38", "0"]
 * ]
 * }
 */
public final class L2Update extends SelfDescribingMarshallable {
    private final String productId;
    private final List<OrderBookChange> changes;

    private L2Update(String productId, List<OrderBookChange> changes) {
        this.productId = productId;
        this.changes = changes;
    }

    @SuppressWarnings("unchecked")
    public static L2Update from(Map<String, Object> l2UpdateAsMap) {
        List<List<?>> changesAsList = (List) l2UpdateAsMap.get(CHANGES);
        List<OrderBookChange> orderBookChanges = changesAsList.stream()
                .map(obc -> new OrderBookChange(Side.valueOf(obc.get(0).toString()), new OrderBookItem(new BigDecimal(String.valueOf(obc.get(1))), new BigDecimal(String.valueOf(obc.get(2))))))
                .collect(Collectors.toList());
        return new L2Update(String.valueOf(l2UpdateAsMap.get(PRODUCT_ID)), orderBookChanges);
    }

    public String getProductId() {
        return productId;
    }

    public List<OrderBookChange> getChanges() {
        return new ArrayList<>(changes);
    }

}
