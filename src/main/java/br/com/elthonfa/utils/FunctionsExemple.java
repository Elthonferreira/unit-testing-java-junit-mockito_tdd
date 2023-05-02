package br.com.elthonfa.utils;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class FunctionsExemple {

    @After
    public void funcaoQueIniciaDEPOISDeCadaTeste() {
        System.out.println("After");
    }

    @BeforeClass
    public void funcaoQueIniciaANTESDeCadaClass() {
        System.out.println("BeforeClass");
    }

    @AfterClass
    public void funcaoQueIniciaDEPOISDeCadaClass() {
        System.out.println("AfterClass");
    }
}
