package com.missd.gdaxjavawebsocketclient.message.channels;

import net.openhft.chronicle.wire.SelfDescribingMarshallable;

import java.math.BigDecimal;
import java.util.List;

public final class OrderBookItem extends SelfDescribingMarshallable {
    private final BigDecimal price;
    private final BigDecimal size;

    public OrderBookItem(BigDecimal price, BigDecimal size) {
        this.price = price;
        this.size = size;
    }

    public static OrderBookItem from(List<?> orderBookItemAsArr) {
        return new OrderBookItem(new BigDecimal(orderBookItemAsArr.get(0).toString()), new BigDecimal(orderBookItemAsArr.get(1).toString()));
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getSize() {
        return size;
    }


}
