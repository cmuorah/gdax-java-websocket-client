package com.missd.gdaxjavawebsocketclient.message.channels.full;

import com.missd.gdaxjavawebsocketclient.message.channels.Side;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;

import static com.missd.gdaxjavawebsocketclient.MessageAsMapKeys.ORDER_ID;
import static com.missd.gdaxjavawebsocketclient.MessageAsMapKeys.PRICE;
import static com.missd.gdaxjavawebsocketclient.MessageAsMapKeys.PRODUCT_ID;
import static com.missd.gdaxjavawebsocketclient.MessageAsMapKeys.PROFILE_ID;
import static com.missd.gdaxjavawebsocketclient.MessageAsMapKeys.REMAINING_SIZE;
import static com.missd.gdaxjavawebsocketclient.MessageAsMapKeys.SEQUENCE;
import static com.missd.gdaxjavawebsocketclient.MessageAsMapKeys.SIDE;
import static com.missd.gdaxjavawebsocketclient.MessageAsMapKeys.TIME;
import static com.missd.gdaxjavawebsocketclient.MessageAsMapKeys.USER_ID;

/**
 * {
 *     "type": "open",
 *     "time": "2014-11-07T08:19:27.028459Z",
 *     "product_id": "BTC-USD",
 *     "sequence": 10,
 *     "order_id": "d50ec984-77a8-460a-b958-66f114b0de9b",
 *     "price": "200.2",
 *     "remaining_size": "1.00",
 *     "side": "sell"
 * }
 */
public final class Open extends OrderUpdate {
    private final BigDecimal price;
    private final BigDecimal remainingSize;

    public Open(Instant time, String productId, long sequence, UUID orderId, Side side, String userId, String profileId, BigDecimal price, BigDecimal remainingSize) {
        super(time, productId, sequence, orderId, side, userId, profileId);
        this.price = price;
        this.remainingSize = remainingSize;
    }

    public static Open from(Map<String, Object> asMap) {
        return new Open(
                Instant.parse(asMap.get(TIME).toString()),
                asMap.get(PRODUCT_ID).toString(),
                Long.parseLong(asMap.get(SEQUENCE).toString()),
                UUID.fromString(asMap.get(ORDER_ID).toString()),
                Side.valueOf(asMap.get(SIDE).toString()),
                getStringOrNull(asMap, USER_ID),
                getStringOrNull(asMap, PROFILE_ID),
                getBigDecimalOrNull(asMap, PRICE),
                getBigDecimalOrNull(asMap, REMAINING_SIZE)
        );
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getRemainingSize() {
        return remainingSize;
    }

}
