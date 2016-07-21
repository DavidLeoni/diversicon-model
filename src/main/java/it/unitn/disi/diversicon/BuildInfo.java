package it.unitn.disi.diversicon;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import it.disi.unitn.diversicon.exceptions.DivIoException;
import it.disi.unitn.diversicon.exceptions.DivNotFoundException;

/**
 * To retrieve build info, call {@link BuildInfo#ofProperties(Class)}
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

    public static boolean hasProperties(Class referenceClass){
        return referenceClass.getResourceAsStream("/" + BUILD_PROPERTIES_PATH) != null;
    }
    /**
     * Returns build info for diversicon maven packages. If it can't load it,
     * fails silently and returns an empty object.
     * 
     * @see #hasProperties(Class)
     * @since 0.1.0
     */
    public static BuildInfo ofProperties(Class referenceClass) {
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
            return buildInfo;
        } catch (Exception ex) {
            Logger.getLogger(referenceClass.getName())
                  .log(Level.SEVERE,
                          "COULD NOT LOAD BUILD INFORMATION! DEFAULTING TO EMPTY BUILD INFO!", ex);
            return new BuildInfo();
        }
    }

}
