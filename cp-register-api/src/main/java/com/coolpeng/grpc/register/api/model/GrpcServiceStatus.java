package com.coolpeng.grpc.register.api.model;

/**
 * Created by luanhaipeng on 16/10/31.
 */
public enum GrpcServiceStatus {
    OK(0),
    ERROR(1),
    UNKNOWN(999);

    private int value;

    private GrpcServiceStatus(int i) {
        this.value = i;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
