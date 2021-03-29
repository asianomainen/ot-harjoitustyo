package com.mycompany.unicafe;

import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {
    Kassapaate paate;
    Maksukortti kortti;
    
    public KassapaateTest() {
        paate = new Kassapaate();
        kortti = new Maksukortti(1000);
    }
    
    @Test
    public void kassassaOikeaRahamaara() {
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void edullisiaLounaitaEiOleMyyty() {
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void maukkaitaLounaitaEiOleMyyty() {
        assertEquals(0, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void syoEdullisestiToimiiJaPalauttaaOikeanRahamaaran() {
        assertEquals(10, paate.syoEdullisesti(250));
    }
    
    @Test
    public void syoEdullisestiPalauttaaRahatJosRahatEiRiitaLounaaseen() {
        assertEquals(200, paate.syoEdullisesti(200));
    }
    
    @Test
    public void syoMaukkaastiToimiiJaPalauttaaOikeanRahamaaran() {
        assertEquals(50, paate.syoMaukkaasti(450));
    }
    
    @Test
    public void syoMaukkaastiPalauttaaRahatJosRahatEiRiitaLounaaseen() {
        assertEquals(300, paate.syoMaukkaasti(300));
    }
    
    @Test
    public void kassassaOnOikeaMaaraRahaa() {
        paate.syoEdullisesti(250);
        assertEquals(100240, paate.kassassaRahaa());
    }
    
    @Test
    public void myytyjenLounaidenMaaraKasvaa() {
        paate.syoEdullisesti(250);
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void palauttaaOikeanVaihtorahan() {
        assertEquals(50, paate.syoMaukkaasti(450));
    }
    
    @Test
    public void lounastaEiMyydaJosRahatEiRiitä() {
        paate.syoEdullisesti(200);
        assertEquals(0, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kaikkiRahatPalautetaanJosRahatEiRiita() {
        assertEquals(200, paate.syoEdullisesti(200));
    }
    
    @Test
    public void kassanRahamaaraEiKasvaJosLounastaEiMyyty() {
        paate.syoEdullisesti(200);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void edullisenLounaanOstaminenToimiiKortilla() {
        assertEquals(true, paate.syoEdullisesti(kortti));
    }
    
    @Test
    public void maukkaanLounaanOstaminenToimiiKortilla() {
        assertEquals(true, paate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void korttiostoKasvattaaMyytyjenLounaidenMaaraa() {
        paate.syoMaukkaasti(kortti);
        assertEquals(1, paate.maukkaitaLounaitaMyyty());
    }
        
    @Test
    public void kortinRahamaaraEiMuutuJosSillaEiOleTarpeeksiRahaa() {
        paate.syoMaukkaasti(kortti);
        paate.syoMaukkaasti(kortti);
        //Nyt jäljellä 200
        paate.syoMaukkaasti(kortti);
        assertEquals(200, kortti.saldo());
    }
    
    @Test
    public void myytyjenLounaidenMaaraEiMuutuJosKortillaEiOleTarpeeksiRahaa() {
        paate.syoMaukkaasti(kortti);
        paate.syoMaukkaasti(kortti);
        //Nyt myyty 2
        paate.syoMaukkaasti(kortti);
        assertEquals(2, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void syoEdullisestiPalauttaaFalseJosKortillaEiOleTarpeeksiRahaa() {
        paate.syoMaukkaasti(kortti);
        paate.syoMaukkaasti(kortti);
        assertEquals(false, paate.syoEdullisesti(kortti));
    }
        
    @Test
    public void syoMaukkaastiPalauttaaFalseJosKortillaEiOleTarpeeksiRahaa() {
        paate.syoMaukkaasti(kortti);
        paate.syoMaukkaasti(kortti);
        assertEquals(false, paate.syoMaukkaasti(kortti));
    }
    
    @Test
    public void kassanRahamaaraEiMuutuKortillaOstettaessa() {
        paate.syoMaukkaasti(kortti);
        assertEquals(100000, paate.kassassaRahaa());
    }
    
    @Test
    public void kortinSaldoKasvaaOikein() {
        paate.lataaRahaaKortille(kortti, 100);
        assertEquals(1100, kortti.saldo());
    }
    
    @Test
    public void kortilleEiVoiLadataNegatiivistaRahamaaraa() {
        paate.lataaRahaaKortille(kortti, -100);
        assertEquals(1000, kortti.saldo());
    }
    
    @Test
    public void kassanRahamaaraKasvaaKortinSaldoaOstaessa() {
        paate.lataaRahaaKortille(kortti, 100);
        assertEquals(100100, paate.kassassaRahaa());
    }
}
