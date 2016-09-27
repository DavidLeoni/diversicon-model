package it.unitn.disi.diversicon;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.disi.unitn.diversicon.exceptions.DivIoException;
import it.disi.unitn.diversicon.exceptions.DivNotFoundException;

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
    private String serverWebsite = "";
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
     * See {@link #getServerWebsite()}
     * 
     * @since 0.1.0
     */
    public void setServerWebsite(String website) {
        this.serverWebsite = website;
    }
    
    /**
     * See {@link #getManualWebsite()}
     * 
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
     * in specified class resources. If not present, fails silently and returns an empty object. 
     * 
     * 
     * @see #hasProperties(Class)
     * @since 0.1.0
     */
    public static BuildInfo of(Class referenceClass) {
        try {
            InputStream stream = referenceClass.getResourceAsStream("/" + BUILD_PROPERTIES_PATH);
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
            buildInfo.setServerWebsite(props.getProperty("server-website", ""));
            buildInfo.setManualWebsite(props.getProperty("manual-website", ""));
            return buildInfo;
        } catch (Exception ex) {
            Logger.getLogger(referenceClass.getName())
                  .log(Level.SEVERE,
                          "COULD NOT LOAD BUILD INFORMATION! DEFAULTING TO EMPTY BUILD INFO!", ex);
            return new BuildInfo();
        }
    }


    /**
     * The website of the main instance of the program (i.e. http://diversicon-db.com)
     * 
     * @since 0.1.0
     */
    public String getServerWebsite() {
        return serverWebsite;
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
                + "\n  serverWebsite=" + serverWebsite 
                + "\n  manualWebsite=" + manualWebsite ;
    }
    
    
    
}
