package it.unitn.disi.diversicon;

import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class BuildInfoTest {
    
    private static final Logger LOG = LoggerFactory.getLogger(BuildInfoTest.class);
    
    @Test
    public void testGetBuildInfo(){
        BuildInfo buildInfo = BuildInfo.ofProperties(this.getClass());
        LOG.debug("Version is " + buildInfo.getVersion());
        assertFalse(buildInfo.getVersion().isEmpty());
        
    }

}
