package it.unitn.disi.diversicon;


/**
 * A Diversicon resource. To model a resource as a packaged maven artifact, 
 * you can extend this class and create a singleton like 
 * in <a href="https://github.com/DavidLeoni/diversicon-wordnet-3.1" href="_target">DivWn31</a>.  
 * 
 * @since 0.1.0
 */
public class DiversiconResource {
    
    private String id;
    private String xmlUri;
    private String sqlUri;
    private String h2DbUri;
    private String version;

    public DiversiconResource(){
        id = "";
        xmlUri = "";
        sqlUri = "";
        h2DbUri = "";
        version = "";
    }
    
    /**
     * Short-hand id, i.e. {@code div-wn30}
     * 
     * @since 0.1.0
     */
    public String getId(){
        return id;
    }
    
    /**
     * 
     * The URI to an XML representing this resource in UBY-LMF format. The file may be compressed.
     * 
     * The URI may start with special {@code classpath:} prefix.
     * 
     * If missing, the empty string is returned.
     *  
     *  @since 0.1.0
     */    
    public String getXmlUri(){
        return xmlUri;
    }
    
    /**
     * 
     * The URI to a SQL file representing this resource. The file may be compressed. 
     * 
     * The URI may start with special {@code classpath:} prefix.
     * 
     * If missing, the empty string is returned.
     * 
     * @since 0.1.0
     */    
    public String getSqlUri(){
        return sqlUri;
    }
    
    /**
     * The URI to a H2 database {@code .h2.db} file representing this resource. The file may be compressed. 
     * 
     * The URI may start with special {@code classpath:} prefix.
     *  
     * If missing, the empty string is returned.
     * 
     * @since 0.1.0
     */        
    public String getH2DbUri(){
        return h2DbUri;
    }
    
    /**
     * Should follow <a href="http://semver.org" target="_blank"> semantic versioning </a> rules
     * i.e. 1.1.0, 2.3.0-SNAPSHOT, ...
     * 
     * If missing, the empty string is returned.
     * 
     * @since 0.1.0
     */          
    public String getVersion(){
        return version;
    }
    
    /**
     * See {@link #getId()}
     * @since 0.1.0
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * See {@link #getXmlUri()}
     * @since 0.1.0
     */    
    public void setXmlUri(String xmlUri) {
        this.xmlUri = xmlUri;
    }

    /**
     * See {@link #getSqlUri()}
     * @since 0.1.0
     */    
    public void setSqlUri(String sqlUri) {
        this.sqlUri = sqlUri;
    }

    /**
     * See {@link #getH2DbUri()}
     * @since 0.1.0
     */   
    public void setH2DbUri(String h2DbUri) {
        this.h2DbUri = h2DbUri;
    }

    /**
     * See {@link #getVersion()}
     * @since 0.1.0
     */
    public void setVersion(String version) {
        this.version = version;
    }
    
    
}
