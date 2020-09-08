package com.xiaole.test.designpattern.factory.factorymethod;

import com.xiaole.test.designpattern.factory.IPay;

public class BFactory implements FactoryMethod {

    @Override
    public IPay createPay(String payType) {
        return null;
    }

}
