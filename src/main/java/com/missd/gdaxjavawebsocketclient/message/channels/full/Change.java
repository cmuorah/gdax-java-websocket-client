package com.missd.gdaxjavawebsocketclient.message.channels.full;

import com.missd.gdaxjavawebsocketclient.message.channels.Side;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;

import static com.missd.gdaxjavawebsocketclient.MessageAsMapKeys.NEW_FUNDS;
import static com.missd.gdaxjavawebsocketclient.MessageAsMapKeys.NEW_SIZE;
import static com.missd.gdaxjavawebsocketclient.MessageAsMapKeys.OLD_FUNDS;
import static com.missd.gdaxjavawebsocketclient.MessageAsMapKeys.OLD_SIZE;
import static com.missd.gdaxjavawebsocketclient.MessageAsMapKeys.ORDER_ID;
import static com.missd.gdaxjavawebsocketclient.MessageAsMapKeys.PRICE;
import static com.missd.gdaxjavawebsocketclient.MessageAsMapKeys.PRODUCT_ID;
import static com.missd.gdaxjavawebsocketclient.MessageAsMapKeys.PROFILE_ID;
import static com.missd.gdaxjavawebsocketclient.MessageAsMapKeys.SEQUENCE;
import static com.missd.gdaxjavawebsocketclient.MessageAsMapKeys.SIDE;
import static com.missd.gdaxjavawebsocketclient.MessageAsMapKeys.TIME;
import static com.missd.gdaxjavawebsocketclient.MessageAsMapKeys.USER_ID;

/**
 * {
 *     "type": "change",
 *     "time": "2014-11-07T08:19:27.028459Z",
 *     "sequence": 80,
 *     "order_id": "ac928c66-ca53-498f-9c13-a110027a60e8",
 *     "product_id": "BTC-USD",
 *     "new_size": "5.23512",
 *     "old_size": "12.234412",
 *     "price": "400.23",
 *     "side": "sell"
 * }
 * {
 *     "type": "change",
 *     "time": "2014-11-07T08:19:27.028459Z",
 *     "sequence": 80,
 *     "order_id": "ac928c66-ca53-498f-9c13-a110027a60e8",
 *     "product_id": "BTC-USD",
 *     "new_funds": "5.23512",
 *     "old_funds": "12.234412",
 *     "price": "400.23",
 *     "side": "sell"
 * }
 */
public final class Change extends OrderUpdate {
    private final BigDecimal price;
    private final BigDecimal newSize;
    private final BigDecimal oldSize;
    private final BigDecimal newFunds;
    private final BigDecimal oldfunds;

    private Change(Instant time, String productId, long sequence, UUID orderId, Side side, String userId, String profileId, BigDecimal price, BigDecimal newSize, BigDecimal oldSize, BigDecimal newFunds, BigDecimal oldfunds) {
        super(time, productId, sequence, orderId, side, userId, profileId);
        this.price = price;
        this.newSize = newSize;
        this.oldSize = oldSize;
        this.newFunds = newFunds;
        this.oldfunds = oldfunds;
    }

    public static Change from(Map<String, Object> asMap) {
        return new Change(
                Instant.parse(asMap.get(TIME).toString()),
                asMap.get(PRODUCT_ID).toString(),
                Long.parseLong(asMap.get(SEQUENCE).toString()),
                UUID.fromString(asMap.get(ORDER_ID).toString()),
                Side.valueOf(asMap.get(SIDE).toString()),
                getStringOrNull(asMap, USER_ID),
                getStringOrNull(asMap, PROFILE_ID),
                getBigDecimalOrNull(asMap, PRICE),
                getBigDecimalOrNull(asMap, NEW_SIZE),
                getBigDecimalOrNull(asMap, OLD_SIZE),
                getBigDecimalOrNull(asMap, NEW_FUNDS),
                getBigDecimalOrNull(asMap, OLD_FUNDS)
        );
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getNewSize() {
        return newSize;
    }

    public BigDecimal getOldSize() {
        return oldSize;
    }

    public BigDecimal getNewFunds() {
        return newFunds;
    }

    public BigDecimal getOldfunds() {
        return oldfunds;
    }

}
