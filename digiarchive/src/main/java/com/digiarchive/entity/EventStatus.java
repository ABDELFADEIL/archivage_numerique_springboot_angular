package com.digiarchive.entity;


public enum EventStatus {
    /** customer is created. whith contract or account */
    START_CUSTOMER_RELATIONSHIP(0),
    /** end of customer relationship. */
    END_RELATION_CLIENT(1),
    /**  contract is closed.  */
    CONTRACT_CLOS(2),
    /**  account is closed .*/
    ACCOUNT_CLOS(3);

    private final Byte value;

    /**
     * Constructor of the object.
     *
     * @param value a value
     */
    EventStatus(int value) {
        this.value = (byte) value;
    }

    /**
     * Gets the value for this enum
     *
     * @return the value for this enum
     */
    public final Byte getValue() {
        return this.value;
    }

    /**
     * Gets the primitive value for this enum
     *
     * @return the primitive value for this enum
     */
    public final byte getPrimitiveValue() {
        return this.value.byteValue();
    }

    /**
     * Transform a value into an enum
     *
     * @param value a value
     * @return the enum. Default is start customer relationship
     */
    public static final EventStatus fromValue(Number value) {
        if (value != null) {
            if (value.byteValue() == EventStatus.END_RELATION_CLIENT.getPrimitiveValue()) {
                return END_RELATION_CLIENT;
            }
            if (value.byteValue() == EventStatus.CONTRACT_CLOS.getPrimitiveValue()) {
                return CONTRACT_CLOS;
            }
            if (value.byteValue() == EventStatus.ACCOUNT_CLOS.getPrimitiveValue()) {
                return ACCOUNT_CLOS;
            }

        }
        return START_CUSTOMER_RELATIONSHIP;
    }

    /**
     * Checks if value is in supported enum values
     *
     * @param value a value
     * @return true this value is in supported enum value
     */
    public static final boolean inRange(Number value) {
        if (value == null) {
            return false;
        }
        EventStatus[] all = EventStatus.values();
        for (EventStatus elm : all) {
            if (elm.getPrimitiveValue() == value.byteValue()) {
                return true;
            }
        }
        return false;
    }
}
