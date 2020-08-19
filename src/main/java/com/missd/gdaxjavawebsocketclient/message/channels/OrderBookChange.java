package com.missd.gdaxjavawebsocketclient.message.channels;


import net.openhft.chronicle.wire.SelfDescribingMarshallable;

public final class OrderBookChange extends SelfDescribingMarshallable {
    private final Side side;
    private final OrderBookItem orderBookItem;

    public OrderBookChange(Side side, OrderBookItem orderBookItem) {
        this.side = side;
        this.orderBookItem = orderBookItem;
    }

    public Side getSide() {
        return side;
    }

    public OrderBookItem getOrderBookItem() {
        return new OrderBookItem(this.orderBookItem.getPrice(), this.orderBookItem.getSize());
    }


}
