package com.example.mkr.jeonju_bus.common.model;


import com.example.mkr.jeonju_bus.util.Logger;

public class RxEventBusProvider {
    private static final RxEventBus rxEventBus = new RxEventBus();

    public static RxEventBus provide(){
        Logger.log("event bus hashcode -> " + rxEventBus.hashCode());
        return rxEventBus;
    }
}
