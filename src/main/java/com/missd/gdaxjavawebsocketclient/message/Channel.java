package com.missd.gdaxjavawebsocketclient.message;


import net.openhft.chronicle.wire.SelfDescribingMarshallable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class Channel extends SelfDescribingMarshallable {
    public static final String NAME = "name";
    public static final String PRODUCT_IDS = "product_ids";
    private final ChannelName name;
    private final List<String> product_ids;

    public Channel(ChannelName channelName) {
        this.name = channelName;
        this.product_ids = new ArrayList<>();
    }

    @SuppressWarnings("unchecked")
    public static Channel from(Map<String, Object> channelAsMap) {
        return new Channel(ChannelName.valueOf(String.valueOf(channelAsMap.get(NAME))))
                .addProductIds((List)channelAsMap.get(PRODUCT_IDS));
    }

    public Channel addProductIds(List<String> channelIds) {
        this.product_ids.addAll(channelIds);
        return this;
    }

    public ChannelName getName() {
        return name;
    }

    public List<String> getProduct_ids() {
        return new ArrayList<>(product_ids);
    }

}
