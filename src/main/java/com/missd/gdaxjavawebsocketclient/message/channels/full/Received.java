package com.missd.gdaxjavawebsocketclient.message.channels.full;

import com.missd.gdaxjavawebsocketclient.message.channels.Side;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;

import static com.missd.gdaxjavawebsocketclient.MessageAsMapKeys.FUNDS;
import static com.missd.gdaxjavawebsocketclient.MessageAsMapKeys.ORDER_ID;
import static com.missd.gdaxjavawebsocketclient.MessageAsMapKeys.ORDER_TYPE;
import static com.missd.gdaxjavawebsocketclient.MessageAsMapKeys.PRICE;
import static com.missd.gdaxjavawebsocketclient.MessageAsMapKeys.PRODUCT_ID;
import static com.missd.gdaxjavawebsocketclient.MessageAsMapKeys.PROFILE_ID;
import static com.missd.gdaxjavawebsocketclient.MessageAsMapKeys.SEQUENCE;
import static com.missd.gdaxjavawebsocketclient.MessageAsMapKeys.SIDE;
import static com.missd.gdaxjavawebsocketclient.MessageAsMapKeys.SIZE;
import static com.missd.gdaxjavawebsocketclient.MessageAsMapKeys.TIME;
import static com.missd.gdaxjavawebsocketclient.MessageAsMapKeys.USER_ID;

/**
 * {
 *     "type": "received",
 *     "time": "2014-11-07T08:19:27.028459Z",
 *     "product_id": "BTC-USD",
 *     "sequence": 10,
 *     "order_id": "d50ec984-77a8-460a-b958-66f114b0de9b",
 *     "size": "1.34",
 *     "price": "502.1",
 *     "side": "buy",
 *     "order_type": "limit"
 * }
 * {
 *     "type": "received",
 *     "time": "2014-11-09T08:19:27.028459Z",
 *     "product_id": "BTC-USD",
 *     "sequence": 12,
 *     "order_id": "dddec984-77a8-460a-b958-66f114b0de9b",
 *     "funds": "3000.234",
 *     "side": "buy",
 *     "order_type": "market"
 * }
 */
public final class Received extends OrderUpdate {
    private final OrderType orderType;
    private final BigDecimal size;
    private final BigDecimal price;
    private final BigDecimal funds;

    public Received(Instant time, String productId, long sequence, UUID orderId, Side side, String userId, String profileId, OrderType orderType, BigDecimal size, BigDecimal price, BigDecimal funds) {
        super(time, productId, sequence, orderId, side, userId, profileId);
        this.orderType = orderType;
        this.size = size;
        this.price = price;
        this.funds = funds;
    }

    public static Received from(Map<String, Object> asMap) {
        return new Received(
                Instant.parse(asMap.get(TIME).toString()),
                asMap.get(PRODUCT_ID).toString(),
                Long.parseLong(asMap.get(SEQUENCE).toString()),
                UUID.fromString(asMap.get(ORDER_ID).toString()),
                Side.valueOf(asMap.get(SIDE).toString()),
                getStringOrNull(asMap, USER_ID),
                getStringOrNull(asMap, PROFILE_ID),
                OrderType.valueOf(asMap.get(ORDER_TYPE).toString()),
                getBigDecimalOrNull(asMap, SIZE),
                getBigDecimalOrNull(asMap, PRICE),
                getBigDecimalOrNull(asMap, FUNDS)
        );
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public BigDecimal getSize() {
        return size;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getFunds() {
        return funds;
    }

}
