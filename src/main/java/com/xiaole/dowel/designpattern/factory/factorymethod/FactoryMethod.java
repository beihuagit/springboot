package com.xiaole.dowel.designpattern.factory.factorymethod;

import com.xiaole.dowel.designpattern.factory.IPay;

public interface FactoryMethod {

    IPay createPay(String payType);

}
