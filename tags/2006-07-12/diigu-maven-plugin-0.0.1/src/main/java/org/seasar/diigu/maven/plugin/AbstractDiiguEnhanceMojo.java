package org.seasar.diigu.maven.plugin;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

public abstract class AbstractDiiguEnhanceMojo extends AbstractMojo implements
        EnhanceParameter {

    /**
     * packagenames
     * 
     * @parameter property="packagenames" default-value="*"
     */
    private String packagenames;

    /**
     * flag marking the task verbosity.
     * 
     * @parameter property="verbose"
     */
    private boolean verbose;

    public boolean isVerbose() {
        return verbose;
    }

    public String getPackagenames() {
        return packagenames;
    }

    public void execute() throws MojoExecutionException, MojoFailureException {
        final DiiguEnhancer enhancer = new DiiguEnhancer();
        enhancer.setLog(getLog());
        enhancer.enhance(this);
    }

}
