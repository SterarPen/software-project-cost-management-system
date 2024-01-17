package com.starer.Enum;

public enum DemandStatus {

    HANDLING(0),
    NO_AGREE(1),
    AGREE(2),
    READY_DELETE(3),
    DELETE(-1);

    public final int status;


    DemandStatus(int status) {
        this.status = status;
    }

}
