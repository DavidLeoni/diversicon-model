package eu.kidf.diversicon.data;


import javax.annotation.Nullable;

import eu.kidf.diversicon.core.BuildInfo;
import eu.kidf.diversicon.core.LexResPackage;

/**
 * Diversicon Upper Lexical Resource   
 * 
 * @since 0.1.0
 *
 */
public class DivUpper extends LexResPackage {

    /**
     * 
     * Synset id of the root of all domains, as specified in DivUpper lexical resource 
     *
     * @since 0.1.0
     */
    public static final String SYNSET_ROOT_DOMAIN = "div_ss_n_domain";

       
    /**
     * @since 0.1.0
     */
    public static final String NAME = "div-upper";
    
    /**
     * 
     * @since 0.1.0
     */
    public static final String LABEL = "Diversicon Upper Lexical Resource";    
    
    /**
     * @since 0.1.0
     */
    public static final String PREFIX = "div";

    /**
     * @since 0.1.0
     */
    private static final String CLASSPATH = "classpath:/" + NAME;
    
    
    /**
     * @since 0.1.0
     */
    public static final String XML_URI = CLASSPATH + ".xml";  
    
    /**
     * @since 0.1.0
     */    
    public static final String LEXICON_ENG = "div_lex_eng";
    
    /**
     * @since 0.1.0
     */
    @Nullable
    private static BuildInfo buildInfo;
        
    /**
     * @since 0.1.0
     */
    private static final DivUpper INSTANCE = new DivUpper();   
    
    static {
        INSTANCE.setName(NAME);
        INSTANCE.setPrefix(PREFIX);
        INSTANCE.setLabel(LABEL);
        //INSTANCE.setH2DbUri(CLASSPATH + ".h2.db");
        //INSTANCE.setSqlUri(CLASSPATH + ".sql");
        INSTANCE.setXmlUri(CLASSPATH + ".xml");
        INSTANCE.setSampleXmlUri(CLASSPATH + ".xml"); // sample of itself
        if (BuildInfo.hasProperties(DivUpper.class)){
            BuildInfo buildInfo = BuildInfo.of(DivUpper.class);
            INSTANCE.setVersion(buildInfo.getVersion());
            INSTANCE.putNamespace(PREFIX, buildInfo.sourceAtTag("master", "src/main/resources/" + NAME + ".xml") );
        } else {            
            throw new IllegalStateException("Couldn't find properties file " + BuildInfo.BUILD_PROPERTIES_PATH + " for class " + DivUpper.class.getCanonicalName());
        }        
        
    }
       
    /**
     * @since 0.1.0
     */
    public static DivUpper of(){
        return INSTANCE;
    }
   
       
}



