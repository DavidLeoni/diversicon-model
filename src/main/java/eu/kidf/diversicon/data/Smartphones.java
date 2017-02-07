package eu.kidf.diversicon.data;

import javax.annotation.Nullable;

import eu.kidf.diversicon.core.BuildInfo;
import eu.kidf.diversicon.core.LexResPackage;

/**
 * Singleton holding references to sample {@code handheld-devices} files packaged for Diversicon
 * 
 * @since 0.1.0
 *
 */
public class Smartphones extends LexResPackage {

    /**
     * @since 0.1.0
     */    
    public static final String SHORT_NAME = "smartphones";
    
    /**
     * @since 0.1.0
     */
    public static final String NAME = "div-" + SHORT_NAME;
    
    /**
     * @since 0.1.0
     */
    public static final String LABEL = "Smartphones";
    
    /**
     * @since 0.1.0
     */
    public static final String PREFIX = "sm";

    /**
     * @since 0.1.0
     */
    private static final String CLASSPATH = "classpath:/" + SHORT_NAME;
       
    
    /**
     * @since 0.1.0
     */
    public static final String XML_URI = CLASSPATH + ".xml";    
    
    /**
     * @since 0.1.0
     */
    @Nullable
    private static BuildInfo buildInfo;
        
    /**
     * @since 0.1.0
     */
    private static final Smartphones INSTANCE = new Smartphones();   
    
    static {
        
        
        
        INSTANCE.setName(NAME);
        INSTANCE.setLabel(LABEL);
        INSTANCE.setPrefix(PREFIX);
        //INSTANCE.setH2DbUri(CLASSPATH + ".h2.db");
        //INSTANCE.setSqlUri(CLASSPATH + ".sql");
        INSTANCE.setXmlUri(CLASSPATH + ".xml");
        
        INSTANCE.setSampleXmlUri(CLASSPATH + ".xml"); // sample of itself
        if (BuildInfo.hasProperties(Smartphones.class)){
            BuildInfo buildInfo = BuildInfo.of(Smartphones.class);            
            INSTANCE.setVersion(buildInfo.getVersion());
            INSTANCE.putNamespace(PREFIX, buildInfo.sourceAtTag("master", "src/main/resources/" + SHORT_NAME + ".xml") );
            
            // can't because of dep cycle!
            // INSTANCE.putNamespace(DivWn31.PREFIX, DivWn31.of().namespace());
            INSTANCE.putNamespace("wn31", "https://github.com/diversicon-kb/diversicon-wordnet-3.1");
        } else {            
            throw new IllegalStateException("Couldn't find properties file " + BuildInfo.BUILD_PROPERTIES_PATH + " for class " + Smartphones.class.getCanonicalName());
        }

        
    }
       
    /**
     * @since 0.1.0
     */
    public static Smartphones of(){
        return INSTANCE;
    }
   
}



