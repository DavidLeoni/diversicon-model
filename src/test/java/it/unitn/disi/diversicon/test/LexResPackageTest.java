package it.unitn.disi.diversicon.test;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.unitn.disi.diversicon.LexResPackage;
import it.unitn.disi.diversicon.exceptions.DivNotFoundException;

import org.junit.Assert;

/**
 * @since 0.1.0
 */
public class LexResPackageTest {

    private static final Logger LOG = LoggerFactory.getLogger(LexResPackageTest.class);

    /**
     * @since 0.1.0
     */    
    @Test
    public void testToStringEmpty(){
        LexResPackage pack = new LexResPackage();        
        LOG.debug(pack.toString());               
    }
    
    /**
     * @since 0.1.0
     */    
    @Test
    public void testToString(){                                    
        LOG.debug("\n" + makePack().toString());               
    }
    
    /**
     * @since 0.1.0
     */    
    public static LexResPackage makePack(){
        LexResPackage pack = new LexResPackage();
                
        pack.setName("my-name");
        pack.setLabel("my label");
        Map<String,String> ns = new HashMap<>();
        ns.put("a", "u");
        ns.put("b", "w");
        pack.setNamespaces(ns);
        pack.setPrefix("my-prefix");
        pack.setVersion("0.1.0");
        pack.setSampleXmlUri("http://my-res-sample.xml");
        pack.setXmlUri("http://my-res.xml");
        pack.setSqlUri("http://my-res.sql");
        pack.setH2DbUri("http://my-res.h2.db");
        
        return pack;
    }
    
    /**
     * @since 0.1.0
     */    
    @Test
    public void testEquality(){
        assertEquals(new LexResPackage(), new LexResPackage());
        assertEquals(makePack(), makePack());
    }
    
    /**
     * @since 0.1.0
     */
    @Test
    public void testNamespace(){

        try {
            LexResPackage pack = new LexResPackage();
            pack.namespace();
            Assert.fail("Shouldn't arrive here!");
        } catch (DivNotFoundException ex){
            
        }
        
        try {
            LexResPackage pack = new LexResPackage();
            pack.setPrefix("a");
            pack.namespace();
            Assert.fail("Shouldn't arrive here!");
        } catch (DivNotFoundException ex){
            
        }
        
        LexResPackage pack = new LexResPackage();
        pack.setPrefix("a");
        pack.putNamespace("a", "b");
        assertEquals("b", pack.namespace());
    }
}
