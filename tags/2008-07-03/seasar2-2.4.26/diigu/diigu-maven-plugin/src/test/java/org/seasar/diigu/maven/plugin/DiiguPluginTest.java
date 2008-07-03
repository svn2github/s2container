package org.seasar.diigu.maven.plugin;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

import junit.framework.AssertionFailedError;
import junit.framework.TestCase;

import org.apache.maven.BuildFailureException;
import org.apache.maven.artifact.resolver.ArtifactNotFoundException;
import org.apache.maven.artifact.resolver.ArtifactResolutionException;
import org.apache.maven.cli.ConsoleDownloadMonitor;
import org.apache.maven.embedder.MavenEmbedder;
import org.apache.maven.embedder.MavenEmbedderConsoleLogger;
import org.apache.maven.embedder.PlexusLoggerAdapter;
import org.apache.maven.lifecycle.LifecycleExecutionException;
import org.apache.maven.monitor.event.DefaultEventMonitor;
import org.apache.maven.monitor.event.EventMonitor;
import org.apache.maven.project.DuplicateProjectException;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.ProjectBuildingException;
import org.codehaus.plexus.util.dag.CycleDetectedException;

/**
 * @author manhole
 */
public class DiiguPluginTest extends TestCase {

    protected MavenEmbedder maven;

    protected void setUp() throws Exception {
        super.setUp();
        maven = new MavenEmbedder();
        maven.setClassLoader(Thread.currentThread().getContextClassLoader());
        maven.setLogger(new MavenEmbedderConsoleLogger());
        maven.start();
    }

    protected void tearDown() throws Exception {
        maven.stop();
        super.tearDown();
    }

    public void testDiiguTest() throws Exception {
        runProjectTest("diigu-test");
    }

    protected void runProjectTest(final String projectName) throws IOException,
            ProjectBuildingException, ArtifactResolutionException,
            ArtifactNotFoundException, CycleDetectedException,
            LifecycleExecutionException, BuildFailureException,
            DuplicateProjectException {

        // ## Arrange ##
        // ## Act ##
        final long start = System.currentTimeMillis();
        File pom = new File(getTestProjectDirectory(projectName), "pom.xml");

        MavenProject mavenProject = maven.readProjectWithDependencies(pom);
        EventMonitor eventMonitor = new DefaultEventMonitor(
                new PlexusLoggerAdapter(new MavenEmbedderConsoleLogger()));

        final File executionRootDirectory = pom.getParentFile();
        System.out.println("executionRootDirectory=" + executionRootDirectory);
        maven.execute(mavenProject, Arrays.asList(new String[] { "clean",
                "package" }), eventMonitor, new ConsoleDownloadMonitor(),
                new Properties(), executionRootDirectory);

        // ## Assert ##
        File targetDir = new File(getTestProjectDirectory(projectName),
                "target");
        final File[] files = targetDir.listFiles();
        File jarFile = null;
        for (int i = 0; i < files.length; i++) {
            final File file = files[i];
            final String name = file.getName();
            if (name.startsWith(projectName) && name.endsWith(".jar")
                    && !name.endsWith("-sources.jar")
                    && !name.endsWith("-tests.jar")) {
                jarFile = file;
                break;
            }
        }

        assertEquals("test successed and created jar file: " + jarFile, true,
                jarFile.exists());
        assertEquals("created in this test", true, start < jarFile
                .lastModified());
    }

    private File getTestProjectDirectory(String projectName) throws IOException {
        final File base = new File(".").getCanonicalFile();
        final File dir = new File(base, "src/test/projects/" + projectName);
        if (!dir.isDirectory()) {
            throw new AssertionFailedError(dir + " is not directory.");
        }
        return dir;
    }

}
