package com.missd.gdaxjavawebsocketclient.message.channels;

import net.openhft.chronicle.wire.SelfDescribingMarshallable;

import java.math.BigDecimal;
import java.util.Map;

import static com.missd.gdaxjavawebsocketclient.MessageAsMapKeys.*;

/**
 * {
 * "type": "ticker",
 * "sequence": 3213383,
 * "product_id": "ETH-USD",
 * "price": "666.10000000",
 * "open_24h": "511.01000000",
 * "volume_24h": "7464.43543717",
 * "low_24h": "505.33000000",
 * "high_24h": "666.10000000",
 * "volume_30d": "345657.51947284",
 * "best_bid": "505.33",
 * "best_ask": "666.1"
 * }
 */
public final class Ticker extends SelfDescribingMarshallable {
    private final long sequence;
    private final String productId;
    private final BigDecimal price;
    private final BigDecimal open_24h;
    private final BigDecimal volume_24h;
    private final BigDecimal low_24h;
    private final BigDecimal high_24h;
    private final BigDecimal volume_30d;
    private final BigDecimal best_bid;
    private final BigDecimal best_ask;

    private Ticker(long sequence, String productId, BigDecimal price, BigDecimal open_24h, BigDecimal volume_24h, BigDecimal low_24h, BigDecimal high_24h, BigDecimal volume_30d, BigDecimal best_bid, BigDecimal best_ask) {
        this.sequence = sequence;
        this.productId = productId;
        this.price = price;
        this.open_24h = open_24h;
        this.volume_24h = volume_24h;
        this.low_24h = low_24h;
        this.high_24h = high_24h;
        this.volume_30d = volume_30d;
        this.best_bid = best_bid;
        this.best_ask = best_ask;
    }

    public static Ticker from(Map<String, Object> tickerAsMap) {
        return new Ticker(
                Long.parseLong(String.valueOf(tickerAsMap.get(SEQUENCE))),
                String.valueOf(tickerAsMap.get(PRODUCT_ID)),
                new BigDecimal(String.valueOf(tickerAsMap.get(PRICE))),
                new BigDecimal(String.valueOf(tickerAsMap.get(OPEN_24_H))),
                new BigDecimal(String.valueOf(tickerAsMap.get(VOLUME_24_H))),
                new BigDecimal(String.valueOf(tickerAsMap.get(LOW_24_H))),
                new BigDecimal(String.valueOf(tickerAsMap.get(HIGH_24_H))),
                new BigDecimal(String.valueOf(tickerAsMap.get(VOLUME_30_D))),
                new BigDecimal(String.valueOf(tickerAsMap.get(BEST_BID))),
                new BigDecimal(String.valueOf(tickerAsMap.get(BEST_ASK)))
        );
    }

    public long getSequence() {
        return sequence;
    }

    public String getProductId() {
        return productId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getOpen_24h() {
        return open_24h;
    }

    public BigDecimal getVolume_24h() {
        return volume_24h;
    }

    public BigDecimal getLow_24h() {
        return low_24h;
    }

    public BigDecimal getHigh_24h() {
        return high_24h;
    }

    public BigDecimal getVolume_30d() {
        return volume_30d;
    }

    public BigDecimal getBest_bid() {
        return best_bid;
    }

    public BigDecimal getBest_ask() {
        return best_ask;
    }
}
