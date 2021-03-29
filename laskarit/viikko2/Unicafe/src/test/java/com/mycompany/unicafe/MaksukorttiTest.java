package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(1000);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoAlussaOikein() {
        assertEquals("saldo: 10.0", kortti.toString());
    }
    
    @Test
    public void saldonLataaminenToimiiOikein() {
        kortti.lataaRahaa(100);
        assertEquals("saldo: 11.0", kortti.toString());
    }
    
    @Test
    public void rahanOttaminenToimii() {
        kortti.otaRahaa(100);
        assertEquals("saldo: 9.0", kortti.toString());
    }

    @Test
    public void rahaaEiVoiOttaaJosSitaEiOleTarpeeksi() {
        kortti.otaRahaa(2000);
        assertEquals("saldo: 10.0", kortti.toString());
    }
    
    @Test
    public void metodiPalauttaaTrueJosRahatRiittivat() {
        assertEquals(true , kortti.otaRahaa(100));
    }
    
    @Test
    public void metodiPalauttaaFalseJosRahatEivatRiittaneet() {
        assertEquals(false , kortti.otaRahaa(2000));
    }
    
    @Test
    public void saldoPalautuuOikein() {
        assertEquals(1000, kortti.saldo());
    }
}
