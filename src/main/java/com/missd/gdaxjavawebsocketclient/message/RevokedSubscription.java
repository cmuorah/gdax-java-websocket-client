package com.missd.gdaxjavawebsocketclient.message;

import net.openhft.chronicle.wire.SelfDescribingMarshallable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class RevokedSubscription  extends SelfDescribingMarshallable {

    private final MessageType type;
    private final List<String> productIds;
    private final List<ChannelName> channels;

    public RevokedSubscription() {
        this.type = MessageType.unsubscribe;
        this.productIds = new ArrayList<>();
        this.channels = new ArrayList<>();
    }

    public RevokedSubscription forProfuctId(String productId) {
        this.productIds.add(productId);
        return this;
    }

    public RevokedSubscription forChannels(ChannelName... channels) {
        this.channels.addAll(Arrays.asList(channels));
        return this;
    }

    public MessageType getType() {
        return type;
    }

    public List<String> getProductIds() {
        return new ArrayList<>(productIds);
    }

    public List<ChannelName> getChannels() {
        return new ArrayList<>(channels);
    }


}
