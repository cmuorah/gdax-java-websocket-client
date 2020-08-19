package com.missd.gdaxjavawebsocketclient.message;


import net.openhft.chronicle.wire.SelfDescribingMarshallable;

import java.util.Map;

import static com.missd.gdaxjavawebsocketclient.MessageAsMapKeys.MESSAGE;
import static com.missd.gdaxjavawebsocketclient.MessageAsMapKeys.REASON;

public final class ErrorMessage extends SelfDescribingMarshallable {
    private final String errorMessage;
    private final String reason;

    private ErrorMessage(String errorMessage, String reason) {
        this.errorMessage = errorMessage;
        this.reason = reason;
    }

    public static ErrorMessage from(Map<String, Object> errorAsMap) {
        return new ErrorMessage(String.valueOf(errorAsMap.get(MESSAGE)),
                String.valueOf(errorAsMap.get(REASON)));
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getReason() {
        return reason;
    }

}
