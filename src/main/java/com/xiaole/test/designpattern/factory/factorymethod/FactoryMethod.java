package com.xiaole.test.designpattern.factory.factorymethod;

import com.xiaole.test.designpattern.factory.IPay;

public interface FactoryMethod {

    IPay createPay(String payType);

}
