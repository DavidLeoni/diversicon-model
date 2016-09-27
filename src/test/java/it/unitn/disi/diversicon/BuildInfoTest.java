package it.unitn.disi.diversicon;

import static org.junit.Assert.assertFalse;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @since 0.1.0
 *
 */
public class BuildInfoTest {
    
    private static final Logger LOG = LoggerFactory.getLogger(BuildInfoTest.class);
    
    /**
     * @since 0.1.0
     */
    @Test
    public void testGetBuildInfo(){
        BuildInfo buildInfo = BuildInfo.of(this.getClass());
        LOG.debug(buildInfo.toString());
        
        assertFalse(buildInfo.getVersion().isEmpty());
        assertFalse(buildInfo.getBuildJdk().isEmpty());
        assertFalse(buildInfo.getBuiltBy().isEmpty());
        assertFalse(buildInfo.getCreatedBy().isEmpty());
        assertFalse(buildInfo.getGitSha().isEmpty());
        assertFalse(buildInfo.getManualWebsite().isEmpty());
        assertFalse(buildInfo.getScmUrl().isEmpty());
        assertFalse(buildInfo.getServerWebsite().isEmpty());
        assertFalse(buildInfo.getTimestamp().isEmpty());
        assertFalse(buildInfo.getVersion().isEmpty());
    }
    
    /**
     * @since 0.1.0
     */
    // TODO improve
    @Test
    public void testToString(){
        BuildInfo buildInfo = BuildInfo.of(this.getClass());
        LOG.debug( buildInfo.toString());
    }
    

}
