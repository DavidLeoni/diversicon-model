package eu.kidf.diversicon.data;

import javax.annotation.Nullable;

import eu.kidf.diversicon.core.BuildInfo;
import eu.kidf.diversicon.core.LexResPackage;

/**
 * Example terminology used for testing XML validation.   
 * 
 * @since 0.1.0
 *
 */
public class Examplicon extends LexResPackage {

    
    /**
     * @since 0.1.0
     */
    public static final String SHORT_NAME = "examplicon";
    
    /**
     * @since 0.1.0
     */
    public static final String NAME = "div-" + SHORT_NAME;
    
    /**
     * 
     * @since 0.1.0
     */
    public static final String LABEL = "The Examplicon";    
    
    /**
     * @since 0.1.0
     */
    public static final String PREFIX = "ex";

    /**
     * @since 0.1.0
     */
    private static final String CLASSPATH = "classpath:/" + SHORT_NAME;
    
    /**
     * @since 0.1.0
     */
    public static final String SQL_URI = CLASSPATH + ".sql";
    
    /**
     * @since 0.1.0
     */
    public static final String H2DB_URI = CLASSPATH + ".h2.db";
    
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
    private static final Examplicon INSTANCE = new Examplicon();   
    
    static {
        INSTANCE.setName(NAME);
        INSTANCE.setPrefix(PREFIX);
        INSTANCE.setLabel(LABEL);
        //INSTANCE.setH2DbUri(CLASSPATH + ".h2.db");
        //INSTANCE.setSqlUri(CLASSPATH + ".sql");
        INSTANCE.setXmlUri(CLASSPATH + ".xml");
        INSTANCE.setSampleXmlUri(CLASSPATH + ".xml"); // sample of itself
        if (BuildInfo.hasProperties(Examplicon.class)){
            BuildInfo buildInfo = BuildInfo.of(Examplicon.class);
            INSTANCE.setVersion(buildInfo.getVersion());
            INSTANCE.putNamespace(PREFIX, buildInfo.sourceAtTag("master", "src/main/resources/" + SHORT_NAME + ".xml") );
            // can't because of dep cycle!
            // INSTANCE.putNamespace(DivWn31.PREFIX, DivWn31.of().namespace());
            INSTANCE.putNamespace("wn31", "https://github.com/diversicon-kb/diversicon-wordnet-3.1");
        } else {            
            throw new IllegalStateException("Couldn't find properties file " + BuildInfo.BUILD_PROPERTIES_PATH + " for class " + Examplicon.class.getCanonicalName());
        }        
        
    }
       
    /**
     * @since 0.1.0
     */
    public static Examplicon of(){
        return INSTANCE;
    }

   
}



