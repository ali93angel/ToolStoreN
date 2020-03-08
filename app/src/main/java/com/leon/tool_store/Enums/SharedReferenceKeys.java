package com.leon.tool_store.Enums;

/**
 * Created by Leon on 1/9/2018.
 */

public enum SharedReferenceKeys {
    ACCOUNT_NUMBER("account_number"),
    BILL_ID("bill_id"),
    MOBILE_NUMBER("mobile_number"),
    LENGTH("length"),
    INDEX("index"),
    API_KEY("api_key");

    private final String value;

    SharedReferenceKeys(final String newValue) {
        value = newValue;
    }

    public String getValue() {
        return value;
    }
}
