package it.unitn.disi.diversicon;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.GregorianCalendar;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.Nullable;

import it.unitn.disi.diversicon.exceptions.DivIoException;
import it.unitn.disi.diversicon.exceptions.DivNotFoundException;

/**
 * To retrieve build info, call {@link BuildInfo#of(Class)}
 * 
 * @since 0.1.0
 * 
 */
public final class BuildInfo {

    private static final long serialVersionUID = 1L;

    public static final String BUILD_PROPERTIES_PATH = "diversicon.build.properties";

    private String timestamp = "";
    private String scmUrl = "";
    private String gitSha = "";
    private String createdBy = "";
    private String buildJdk = "";
    private String builtBy = "";
    private String version = "";
    private String server = "";
    private String devServer = "";
    private String manualWebsite = "";

    /**
     * i.e. 1.0
     * 
     * @since 0.1.0
     */
    public String getVersion() {
        return version;
    }

    /**
     * i.e. David Leoni
     * 
     * @since 0.1.0
     */
    public String getBuiltBy() {
        return builtBy;
    }

    /**
     * i.e. JDK 1.7.0_17
     * 
     * @since 0.1.0
     */
    public String getBuildJdk() {
        return buildJdk;
    }

    /**
     * i.e. Apache Maven 3.0.5
     * 
     * @since 0.1.0
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * i.e. git commit SHA, i.e. bdd5c2c75e6438da0d23ac8f2368f9e9cacf2087
     * 
     * @since 0.1.0
     */
    public String getGitSha() {
        return gitSha;
    }

    /**
     * Returns the source repository url, i.e.
     * https://github.com/opendatatrentino/traceprov
     * 
     * @since 0.1.0
     */
    public String getScmUrl() {
        return scmUrl;
    }
        

    /**
     * Returns the timestamp, i.e. 1421246376174
     * 
     * @since 0.1.0
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     * See {@link #getTimestamp()}
     * 
     * @since 0.1.0
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * See {@link #getScmUrl()}
     * 
     * @since 0.1.0
     */
    public void setScmUrl(String scmUrl) {
        this.scmUrl = scmUrl;
    }

    /**
     * See {@link #getGitSha()}
     * 
     * @since 0.1.0
     */
    public void setGitSha(String gitSha) {
        this.gitSha = gitSha;
    }

    /**
     * See {@link #getCreatedBy()}
     * 
     * @since 0.1.0
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * See {@link #getBuildJdk()}
     * 
     * @since 0.1.0
     */

    public void setBuildJdk(String buildJdk) {
        this.buildJdk = buildJdk;
    }

    /**
     * See {@link #getBuiltBy()}
     * 
     * @since 0.1.0
     */
    public void setBuiltBy(String builtBy) {
        this.builtBy = builtBy;
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
     * See {@link #getServer()}
     * 
     * @since 0.1.0
     */
    public void setServer(String server) {
        this.server = server;
    }
 
    /**
     * See {@link #getDevServer()}
     * 
     * @since 0.1.0
     */
    public void setDevServer(String devServer) {
        this.devServer = devServer;
    }
     
    
    /**
     * See {@link #getManualWebsite()}
     * s
     * @since 0.1.0
     */
    public void setManualWebsite(String website) {
        this.manualWebsite = website;
    }
    
    
    /**
     * @since 0.1.0
     */
    public static boolean hasProperties(Class referenceClass){
        return referenceClass.getResourceAsStream("/" + BUILD_PROPERTIES_PATH) != null;
    }
    /**
     * Returns build info for diversicon maven packages. 
     * In order for this to work there must be file {@link #BUILD_PROPERTIES_PATH} 
     * in specified class resources. If not present, logs an error and returns an empty object. 
     * 
     * 
     * @see #hasProperties(Class)
     * @since 0.1.0
     */    
    // todo implementation tries to work dev time but it's rough  
    public static BuildInfo of(Class referenceClass) {               
        
        try {
           
            String className = referenceClass.getSimpleName() + ".class";
            String classPath = referenceClass.getResource(className).toString();
            
            //System.out.println("classPath = "  + classPath);
            
            @Nullable 
            InputStream stream = null;
            
            if (classPath.startsWith("jar")) {
                String propPath = classPath.substring(0, classPath.lastIndexOf("!") + 1) + 
                        "/" + BUILD_PROPERTIES_PATH;                    
                stream = new URL(propPath).openStream();                
            } else {
                Enumeration<URL> resources = referenceClass.getClassLoader()
                        .getResources( BUILD_PROPERTIES_PATH);
                      while (resources.hasMoreElements()) {
                          
                          try {
                            URL url = resources.nextElement();
                            //System.out.println("url = "  + url);
                            String prefix = greatestCommonPrefix(classPath, url.toString());
                            
                            //System.out.println("prefix = "  + prefix);
                            if (prefix.endsWith("target/test-classes/")
                                    || prefix.endsWith("target/classes/")
                                    || prefix.endsWith("target/")){
                                stream = url.openStream();
                                break;
                            }                            
                          } catch (IOException e) {
                              throw new DivIoException("Error while searching " + BUILD_PROPERTIES_PATH, e);
                          }
                      }
            }
                                                       
            Properties props = new Properties();
            if (stream == null) {
                throw new DivNotFoundException("Couldn't find " + BUILD_PROPERTIES_PATH
                        + " file in resources of package containing class " + referenceClass.getSimpleName() + "  !!");
            } else {
                try {
                    props.load(stream);
                } catch (IOException ex) {
                    throw new DivIoException(
                            "Couldn't load " + BUILD_PROPERTIES_PATH + " file in resources of package containing class "
                                    + referenceClass.getSimpleName() + "  !!",
                            ex);
                }
            }
            BuildInfo buildInfo = new BuildInfo();
            buildInfo.setBuildJdk(props.getProperty("build-jdk", ""));

            buildInfo.setBuiltBy(props.getProperty("built-by", ""));
            buildInfo.setCreatedBy(props.getProperty("created-by", ""));
            buildInfo.setGitSha(props.getProperty("git-sha", ""));
            buildInfo.setScmUrl(props.getProperty("scm-url", ""));
            buildInfo.setTimestamp(props.getProperty("timestamp", ""));
            buildInfo.setVersion(props.getProperty("version", ""));
            buildInfo.setServer(props.getProperty("server", ""));
            buildInfo.setDevServer(props.getProperty("dev-server", ""));
            buildInfo.setManualWebsite(props.getProperty("manual-website", ""));
            return buildInfo;
        } catch (Exception ex) {
            
            Logger.getLogger(referenceClass.getName())
            .log(Level.SEVERE,
                          "COULD NOT LOAD BUILD INFORMATION " + BUILD_PROPERTIES_PATH + " FOR CLASS "
                     + referenceClass.getName() + "! DEFAULTING TO EMPTY BUILD INFO!", ex);
            return new BuildInfo();
        }
    }

    /**
     * @since 0.1.0
     */
    private static String greatestCommonPrefix(String a, String b) {
        int minLength = Math.min(a.length(), b.length());
        for (int i = 0; i < minLength; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                return a.substring(0, i);
            }
        }
        return a.substring(0, minLength);
    }
    
    /**
     * The main instance of the program (i.e. http://diversicon-kb.eu)
     * 
     * @since 0.1.0
     */
    public String getServer() {
        return server;
    }
    
    
    /**
     * The development instance of the program (i.e. http://dev.diversicon-kb.eu)
     * 
     * @since 0.1.0
     */
    public String getDevServer() {
        return devServer;
    }
    
    /**
     * The website of the manual of the program (i.e. http://diversicon-db.com/manual)
     * 
     * @since 0.1.0
     */
    public String getManualWebsite() {
        return manualWebsite;
    }    
    
    /**
     * Return Josman docs position at this version.
     * 
     * @since 0.1.0
     */
    public String docsAtVersion() {
        return getManualWebsite() + "/" + getVersion();
    }
    
    /**
     * Returns the url to the github source file at version, 
     * like https://github.com/DavidLeoni/diversicon/blob/0.1.0/src/main/java/it/unitn/disi/diversicon/DbInfo.java
     *  
     * @since 0.1.0
     */    
    public String sourceAtVersion(String path) {
        Objects.requireNonNull(path);
        
        String sep;
        
        if (path.startsWith("/") || path.isEmpty() ){            
            sep = "";
        } else {
            sep = "/";
        } 
        
        // https://github.com/DavidLeoni/diversicon/blob/master/src/main/java/it/unitn/disi/diversicon/DbInfo.java
        return getScmUrl() + "/blob/" + getVersion() + sep + path;
    }

    /**
     * Returns the url to the github source file at tag, i.e. if version is 'master', could return
     * this 
     * like https://github.com/DavidLeoni/diversicon/blob/master/src/main/java/it/unitn/disi/diversicon/DbInfo.java
     *  
     * @throws IllegalArgumentException 
     *  
     * @since 0.1.0
     */    
    public String sourceAtTag(String tag, String path) {
        Objects.requireNonNull(path);
        Objects.requireNonNull(tag);
        if (tag.trim().isEmpty()){
            throw new IllegalArgumentException("Provided tag is blank!");
        }
        
        String sep;
        
        if (path.startsWith("/") || path.isEmpty() ){            
            sep = "";
        } else {
            sep = "/";
        } 
        
        // https://github.com/DavidLeoni/diversicon/blob/master/src/main/java/it/unitn/disi/diversicon/DbInfo.java
        return getScmUrl() + "/blob/" + tag + sep + path;
    }
    
    
    /**
     * <p>Generates a human-readable </p> 
     * 
     * {@inheritDoc}
     * 
     * @since 0.1.0
     */
    @Override
    public String toString() {
        return "BuildInfo: "
                + "\n  timestamp=" + timestamp
                + "\n  scmUrl=" + scmUrl 
                + "\n  gitSha=" + gitSha 
                + "\n  createdBy="+ createdBy
                + "\n  buildJdk=" + buildJdk
                + "\n  builtBy=" + builtBy
                + "\n  version=" + version
                + "\n  serverWebsite=" + server 
                + "\n  manualWebsite=" + manualWebsite ;
    }
    
    
    
}
