package com.xiaole.dowel.designpattern.factory.factorymethod;

import com.xiaole.dowel.designpattern.factory.IPay;

public class PayService {

    public void pay(String payType) {
        FactoryMethod aFactory = new AFactory();
        IPay pay = aFactory.createPay(payType);

    }

}
