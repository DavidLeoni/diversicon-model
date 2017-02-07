package eu.kidf.diversicon.core;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Nullable;

import eu.kidf.diversicon.core.exceptions.DivNotFoundException;

/**
 * A Diversicon lexical resource packaged with metadata. To model a resource as a packaged maven artifact, 
 * you can extend this class and create a singleton like 
 * in <a href="https://github.com/diversicon-kb/diversicon-wordnet-3.1" href="_target">DivWn31</a>.  
 * 
 * @since 0.1.0
 */
public class LexResPackage {
        
    private String name;
    private String label;
    private String prefix;
    private String xmlUri;
    private String sqlUri;
    private String h2DbUri;
    private String version;
    
    private String sampleXmlUri;    
    private Map<String, String> namespaces;

    /**
     * @since 0.1.0
     */ 
    public LexResPackage(){
        name = "";
        label = "";
        prefix = "";        
        xmlUri = "";
        sqlUri = "";
        h2DbUri = "";
        version = "";        
        xmlUri = "";
        sampleXmlUri = "";
        namespaces = new HashMap<>();        
    }
    
    
    /**
     * A resource which is a much reduced sample of the this one. 
     * If it doesn't exist returns an empty string. 
     * 
     * @since 0.1.0
     */
    public String getSampleXmlUri() {
        return sampleXmlUri;
    }

    
    /**
     * See {@link #getSampleXmlUri()}
     * 
     * @since 0.1.0
     */
    public void setSampleXmlUri(String sampleXmlUri) {
        this.sampleXmlUri = sampleXmlUri;
    }


    /**
     * Worldwide unique id so for your resources, pick a reasonably long id.
     * Diversicon well-known resources might have a short id for convenience, i.e. {@code div-wn31}
     * 
     * <p>
     * Note: it is called 'name' because Uby uses 'name' as lexical resource id. 
     * </p>
     * 
     * @see #getLabel()
     * @see #getPrefix()
     * @since 0.1.0
     */
    public String getName(){
        return name;
    }

    /**
     * A descriptive name, like "Diversicon WordNet 3.1". It's usually extracted from 
     * {@code GlobalInformation} object.
     * 
     * @see #getName()
     * @see #getPrefix()
     * 
     * @since 0.1.0
     */
    @Nullable
    public String getLabel() {
        return label;
    }    
    
    /**
     * The default common short prefix (like 'wn31') to identify this resource, ending with no colon, 
     * see <a href="https://www.w3.org/TR/2006/REC-xml-names11-20060816/#NT-Prefix" target="_blank">XML specs</a>.     
     *
     * @see #getId()
     * @see #getLabel()
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
     * If missing, an empty string is returned.
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
     * If missing, an empty string is returned.
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
     * If missing, an empty string is returned.
     * 
     * @since 0.1.0
     */        
    public String getH2DbUri(){
        return h2DbUri;
    }
    
    /**
     * The version according to <a href="http://semver.org" target="_blank"> semantic versioning </a> rules
     * i.e. 1.1.0, 2.3.0-SNAPSHOT, ...
     * 
     * If missing, an empty string is returned.
     * 
     * @since 0.1.0
     */          
    public String getVersion(){
        return version;
    }
    
    /**
     * See {@link #getName()}
     * 
     * @since 0.1.0
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * See {@link #getLabel()}
     * 
     * @since 0.1.0
     */
    public void setLabel(String label) {
        this.label = label;
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

    /**
     * A map of the resources referenced by this resource, as prefix:url pairs.
     * 
     * @since 0.1.0
     */
    public Map<String, String> getNamespaces() {
        return namespaces;
    }

    /**
     * See {@link #getNamespaces()}.
     * 
     * @param namespaces must be non-null.
     * 
     * @since 0.1.0
     */
    public void setNamespaces(Map<String, String> namespaces) {
        Objects.requireNonNull(namespaces);
        this.namespaces = namespaces;
    }

    /**
     * @since 0.1.0
     */
    public void putNamespace(String prefix, String uri) {        
        this.namespaces.put(prefix, uri);
    }

    /**
     * Returns a nice human-readable description  of the fields.
     * 
     * @since 0.1.0
     */
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("LexicalResourcePackage:\n");
        sb.append("  name         = " + name + "\n");
        sb.append("  label        = " + label + "\n");        
        sb.append("  prefix       = " + prefix + "\n");        
        sb.append("  version      = " + version + "\n");        
        sb.append("  namespaces   =\n");
        for (String prefix : namespaces.keySet()){
        sb.append("                 " + prefix + ":" + namespaces.get(prefix) + "\n");
        }       

        sb.append("  sampleXmlUri = "+ sampleXmlUri + "\n");
        sb.append("  xmlUri       = "+xmlUri + "\n");        
        sb.append("  sqlUri       = "+ sqlUri + "\n");
        sb.append("  h2DbUri      = " + h2DbUri + "\n");               
            
        return sb.toString();
    }


    /**
     * @since 0.1.0
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((h2DbUri == null) ? 0 : h2DbUri.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((label == null) ? 0 : label.hashCode());
        result = prime * result + ((namespaces == null) ? 0 : namespaces.hashCode());
        result = prime * result + ((prefix == null) ? 0 : prefix.hashCode());
        result = prime * result + ((sampleXmlUri == null) ? 0 : sampleXmlUri.hashCode());
        result = prime * result + ((sqlUri == null) ? 0 : sqlUri.hashCode());
        result = prime * result + ((version == null) ? 0 : version.hashCode());
        result = prime * result + ((xmlUri == null) ? 0 : xmlUri.hashCode());
        return result;
    }


    /**
     * @since 0.1.0
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LexResPackage other = (LexResPackage) obj;
        if (h2DbUri == null) {
            if (other.h2DbUri != null)
                return false;
        } else if (!h2DbUri.equals(other.h2DbUri))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (label == null) {
            if (other.label != null)
                return false;
        } else if (!label.equals(other.label))
            return false;
        if (namespaces == null) {
            if (other.namespaces != null)
                return false;
        } else if (!namespaces.equals(other.namespaces))
            return false;
        if (prefix == null) {
            if (other.prefix != null)
                return false;
        } else if (!prefix.equals(other.prefix))
            return false;
        if (sampleXmlUri == null) {
            if (other.sampleXmlUri != null)
                return false;
        } else if (!sampleXmlUri.equals(other.sampleXmlUri))
            return false;
        if (sqlUri == null) {
            if (other.sqlUri != null)
                return false;
        } else if (!sqlUri.equals(other.sqlUri))
            return false;
        if (version == null) {
            if (other.version != null)
                return false;
        } else if (!version.equals(other.version))
            return false;
        if (xmlUri == null) {
            if (other.xmlUri != null)
                return false;
        } else if (!xmlUri.equals(other.xmlUri))
            return false;
        return true;
    }
    
    /**
     * Returns the namespace associated to this package
     * 
     * @throws DivNotFoundException if there is no namespace associated to {@link #getPrefix() prefix}
     * 
     *  @since 0.1.0
     */
    public String namespace(){
        if (prefix == null || prefix.isEmpty()){
            throw new DivNotFoundException("Couldn't find the prefix!");
        }
        String ret = namespaces.get(prefix);
        if (ret == null || ret.isEmpty()){
            throw new DivNotFoundException("Couldn't find the namespace!");
        }
        return ret;
    }
    
}
