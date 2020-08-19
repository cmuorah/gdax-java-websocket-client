package com.missd.gdaxjavawebsocketclient.message.channels;


import net.openhft.chronicle.wire.SelfDescribingMarshallable;

import java.time.Instant;
import java.util.Map;

import static com.missd.gdaxjavawebsocketclient.MessageAsMapKeys.LAST_TRADE_ID;
import static com.missd.gdaxjavawebsocketclient.MessageAsMapKeys.PRODUCT_ID;
import static com.missd.gdaxjavawebsocketclient.MessageAsMapKeys.SEQUENCE;
import static com.missd.gdaxjavawebsocketclient.MessageAsMapKeys.TIME;

/**
 * {
 *   "type": "heartbeat",
 *   "last_trade_id": 108173,
 *   "product_id": "ETH-USD",
 *   "sequence": 3210857,
 *   "time": "2018-07-30T17:45:44.381000Z"
 *  }
 */
public final class Heartbeat extends SelfDescribingMarshallable {
    private final int lastTradeId;
    private final String productId;
    private final long sequence;
    private final Instant time;

    private Heartbeat(int lastTradeId, String productId, long sequence, String time) {
        this.lastTradeId = lastTradeId;
        this.productId = productId;
        this.sequence = sequence;
        this.time = Instant.parse(time);
    }

    public static Heartbeat from(Map<String, Object> heartbeatAsMap) {
        return new Heartbeat(
                Integer.parseInt(heartbeatAsMap.get(LAST_TRADE_ID).toString()),
                heartbeatAsMap.get(PRODUCT_ID).toString(),
                Long.parseLong(heartbeatAsMap.get(SEQUENCE).toString()),
                heartbeatAsMap.get(TIME).toString()
        );
    }

    public int getLastTradeId() {
        return lastTradeId;
    }

    public String getProductId() {
        return productId;
    }

    public long getSequence() {
        return sequence;
    }

    public Instant getTime() {
        return time;
    }


}
