package it.unitn.disi.diversicon;

import javax.annotation.Nullable;

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
    private String prefix;
    private DiversiconResource sample;

    public DiversiconResource(){
        id = "";
        prefix = "";        
        xmlUri = "";
        sqlUri = "";
        h2DbUri = "";
        version = "";        
        sample = null;
    }
    
    
    /**
     * Returns a resource which is a much reduced sample of the this one. 
     * If it doesn't exist returns null. 
     * 
     * @since 0.1.0
     */
    @Nullable
    public DiversiconResource getSample() {
        return sample;
    }

    
    /**
     * See {@link #getSample()}
     * 
     * @since 0.1.0
     */
    public void setSample(@Nullable DiversiconResource sample) {
        this.sample = sample;
    }


    /**
     * Worldwide unique id so for your resources, pick a reasonably long id. Diversicon well-known resources 
     * might have a short id for convenience, i.e. {@code div-wn31}
     * 
     * @since 0.1.0
     */
    public String getId(){
        return id;
    }

    /**
     * The default common short prefix to identify this resource, ending with no colon, 
     * see <a href="https://www.w3.org/TR/2006/REC-xml-names11-20060816/#NT-Prefix" target="_blank">XML specs</a>,.     
     * 
     * @since 0.1.0
     */
    public String getPrefix(){
        return prefix;
    }
    
    
    /**
     * 
     * The URI to an XML representing this resource in UBY-LMF format. The file may be compressed.
     * 
     * The URI may start with special {@code classpath:} prefix.
     * 
     * If missing, the empty string is returned.
     *  
     * @since 0.1.0
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
     * 
     * @since 0.1.0
     */    
    public void setXmlUri(String xmlUri) {
        this.xmlUri = xmlUri;
    }

    /**
     * See {@link #getSqlUri()}
     * 
     * @since 0.1.0
     */    
    public void setSqlUri(String sqlUri) {
        this.sqlUri = sqlUri;
    }

    /**
     * See {@link #getH2DbUri()}
     *
     * @since 0.1.0
     */   
    public void setH2DbUri(String h2DbUri) {
        this.h2DbUri = h2DbUri;
    }

    /**
     * See {@link #getVersion()}
     * 
     * @since 0.1.0
     */
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     * See {@link #getPrefix()}
     * 
     * @since 0.1.0
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
