/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.apache.maven.repository.internal.MavenRepositorySystemUtils
 *  org.eclipse.aether.DefaultRepositorySystemSession
 *  org.eclipse.aether.RepositorySystem
 *  org.eclipse.aether.RepositorySystemSession
 *  org.eclipse.aether.artifact.Artifact
 *  org.eclipse.aether.artifact.DefaultArtifact
 *  org.eclipse.aether.collection.CollectRequest
 *  org.eclipse.aether.connector.basic.BasicRepositoryConnectorFactory
 *  org.eclipse.aether.graph.Dependency
 *  org.eclipse.aether.impl.DefaultServiceLocator
 *  org.eclipse.aether.repository.LocalRepository
 *  org.eclipse.aether.repository.RemoteRepository
 *  org.eclipse.aether.repository.RemoteRepository$Builder
 *  org.eclipse.aether.resolution.DependencyRequest
 *  org.eclipse.aether.resolution.DependencyResolutionException
 *  org.eclipse.aether.resolution.DependencyResult
 *  org.eclipse.aether.spi.connector.RepositoryConnectorFactory
 *  org.eclipse.aether.spi.connector.transport.TransporterFactory
 *  org.eclipse.aether.transfer.AbstractTransferListener
 *  org.eclipse.aether.transfer.TransferCancelledException
 *  org.eclipse.aether.transfer.TransferEvent
 *  org.eclipse.aether.transfer.TransferListener
 *  org.eclipse.aether.transport.http.HttpTransporterFactory
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package org.bukkit.plugin.java;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.maven.repository.internal.MavenRepositorySystemUtils;
import org.bukkit.plugin.PluginDescriptionFile;
import org.eclipse.aether.DefaultRepositorySystemSession;
import org.eclipse.aether.RepositorySystem;
import org.eclipse.aether.RepositorySystemSession;
import org.eclipse.aether.artifact.Artifact;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.eclipse.aether.collection.CollectRequest;
import org.eclipse.aether.connector.basic.BasicRepositoryConnectorFactory;
import org.eclipse.aether.graph.Dependency;
import org.eclipse.aether.impl.DefaultServiceLocator;
import org.eclipse.aether.repository.LocalRepository;
import org.eclipse.aether.repository.RemoteRepository;
import org.eclipse.aether.resolution.DependencyRequest;
import org.eclipse.aether.resolution.DependencyResolutionException;
import org.eclipse.aether.resolution.DependencyResult;
import org.eclipse.aether.spi.connector.RepositoryConnectorFactory;
import org.eclipse.aether.spi.connector.transport.TransporterFactory;
import org.eclipse.aether.transfer.AbstractTransferListener;
import org.eclipse.aether.transfer.TransferCancelledException;
import org.eclipse.aether.transfer.TransferEvent;
import org.eclipse.aether.transfer.TransferListener;
import org.eclipse.aether.transport.http.HttpTransporterFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

class LibraryLoader {
    private final Logger logger;
    private final RepositorySystem repository;
    private final DefaultRepositorySystemSession session;
    private final List<RemoteRepository> repositories;

    public LibraryLoader(final @NotNull Logger logger) {
        this.logger = logger;
        DefaultServiceLocator locator = MavenRepositorySystemUtils.newServiceLocator();
        locator.addService(RepositoryConnectorFactory.class, BasicRepositoryConnectorFactory.class);
        locator.addService(TransporterFactory.class, HttpTransporterFactory.class);
        this.repository = (RepositorySystem)locator.getService(RepositorySystem.class);
        this.session = MavenRepositorySystemUtils.newSession();
        this.session.setChecksumPolicy("fail");
        this.session.setLocalRepositoryManager(this.repository.newLocalRepositoryManager((RepositorySystemSession)this.session, new LocalRepository("libraries")));
        this.session.setTransferListener((TransferListener)new AbstractTransferListener(){

            public void transferStarted(@NotNull TransferEvent event) throws TransferCancelledException {
                logger.log(Level.INFO, "Downloading {0}", String.valueOf(event.getResource().getRepositoryUrl()) + event.getResource().getResourceName());
            }
        });
        this.session.setReadOnly();
        this.repositories = this.repository.newResolutionRepositories((RepositorySystemSession)this.session, Arrays.asList(new RemoteRepository.Builder("central", "default", "https://repo.maven.apache.org/maven2").build()));
    }

    @Nullable
    public ClassLoader createLoader(@NotNull PluginDescriptionFile desc) {
        DependencyResult result;
        if (desc.getLibraries().isEmpty()) {
            return null;
        }
        this.logger.log(Level.INFO, "[{0}] Loading {1} libraries... please wait", new Object[]{desc.getName(), desc.getLibraries().size()});
        ArrayList<Dependency> dependencies = new ArrayList<Dependency>();
        for (String library : desc.getLibraries()) {
            DefaultArtifact artifact = new DefaultArtifact(library);
            Dependency dependency = new Dependency((Artifact)artifact, null);
            dependencies.add(dependency);
        }
        try {
            result = this.repository.resolveDependencies((RepositorySystemSession)this.session, new DependencyRequest(new CollectRequest(null, dependencies, this.repositories), null));
        }
        catch (DependencyResolutionException ex) {
            throw new RuntimeException("Error resolving libraries", ex);
        }
        ArrayList<URL> jarFiles = new ArrayList<URL>();
        for (DefaultArtifact artifact : result.getArtifactResults()) {
            URL url;
            File file = artifact.getArtifact().getFile();
            try {
                url = file.toURI().toURL();
            }
            catch (MalformedURLException ex) {
                throw new AssertionError((Object)ex);
            }
            jarFiles.add(url);
            this.logger.log(Level.INFO, "[{0}] Loaded library {1}", new Object[]{desc.getName(), file});
        }
        URLClassLoader loader = new URLClassLoader(jarFiles.toArray(new URL[jarFiles.size()]), this.getClass().getClassLoader());
        return loader;
    }
}

