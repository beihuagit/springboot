package com.xiaole.dowel.designpattern.factory.factorymethod;

import com.xiaole.dowel.designpattern.factory.IPay;

public class BFactory implements FactoryMethod {

    @Override
    public IPay createPay(String payType) {
        return null;
    }

}
