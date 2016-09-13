package it.unitn.disi.diversicon;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        
        pack.setId("my-id");
        pack.setName("my name");
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
    
}
