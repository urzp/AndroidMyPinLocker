package com.example.my_pinlocker;

/**
 * The listener that triggers callbacks for various events
 * in the {@link PinLockView}
 *
 * Created by aritraroy on 31/05/16.
 */


public interface PinLockListener {

    void onComplete(String pin);

    void onEmpty();

    void onPinChange(int pinLength, String intermediatePin);
}
