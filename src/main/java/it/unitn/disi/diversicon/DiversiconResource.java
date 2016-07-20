package it.unitn.disi.diversicon;

import javax.annotation.Nullable;

/**
 * A Diversicon resource 
 * 
 * @since 0.1.0
 */
public abstract class DiversiconResource {
    
    /**
     * Short-hand id, i.e. {@code div-wn30}
     * 
     * @since 0.1.0
     */
    public abstract String getId();
    
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
    public abstract String getXmlUri();
    
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
    public abstract String getSqlUri();
    
    /**
     * The URI to a H2 database {@code .h2.db} file representing this resource. The file may be compressed. 
     * 
     * The URI may start with special {@code classpath:} prefix.
     *  
     * If missing, the empty string is returned.
     * 
     * @since 0.1.0
     */        
    public abstract String getH2DbUri();
    
    /**
     * Should follow <a href="http://semver.org" target="_blank"> semantic versioning </a> rules
     * i.e. 1.1.0, 2.3.0-SNAPSHOT, ...
     * 
     * If missing, the empty string is returned.
     * 
     * @since 0.1.0
     */       
    public abstract String getVersion();
}
