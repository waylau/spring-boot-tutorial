# 将项目导入IDE

本节内容讲述了如何将Spring Boot项目，导入IDE中进行开发。本例子所选用的IDE为 Eclipse。

## 配置环境

本例子采用的开发环境如下：

* JDK 8
* Gradle 3.3
* Eclipse Neon.2 Release (4.6.2)

## 配置 Eclipse

###  1. 配置用户安装的 Gradle（可选）

如果，项目里面是使用用户安装的 Gradle，则需要先进行该配置。在新版的Eclipse里面，已经集成了 Gradle的插件。

在“Windows”->“Preferences” 对 Gradle 进行设置，指定一个 Gradle 的用户安装目录。

![]()

###  2. 导入项目到 Eclipse

在导入类型时，我们选择“Gradle Project”。

![]()



Loading Gradle project preview failed due to an error in the referenced Gradle build.
Could not fetch model of type 'GradleBuild' using Gradle distribution 'https://services.gradle.org/distributions/gradle-3.3-bin.zip'.

A problem occurred configuring root project 'hello-world'.
Could not resolve all dependencies for configuration ':classpath'.
Could not resolve org.springframework.boot:spring-boot-gradle-plugin:1.5.1.RELEASE.
Required by:
    project :
Could not resolve org.springframework.boot:spring-boot-gradle-plugin:1.5.1.RELEASE.
Could not get resource 'https://repo1.maven.org/maven2/org/springframework/boot/spring-boot-gradle-plugin/1.5.1.RELEASE/spring-boot-gradle-plugin-1.5.1.RELEASE.pom'.
Could not GET 'https://repo1.maven.org/maven2/org/springframework/boot/spring-boot-gradle-plugin/1.5.1.RELEASE/spring-boot-gradle-plugin-1.5.1.RELEASE.pom'.
Connect to 127.0.0.1:8087 [/127.0.0.1] failed: Connection refused: connect
Connection refused: connect
org.gradle.tooling.BuildException: Could not fetch model of type 'GradleBuild' using Gradle distribution 'https://services.gradle.org/distributions/gradle-3.3-bin.zip'.
	at org.gradle.tooling.internal.consumer.ExceptionTransformer.transform(ExceptionTransformer.java:51)
	at org.gradle.tooling.internal.consumer.ExceptionTransformer.transform(ExceptionTransformer.java:29)
	at org.gradle.tooling.internal.consumer.ResultHandlerAdapter.onFailure(ResultHandlerAdapter.java:41)
	at org.gradle.tooling.internal.consumer.async.DefaultAsyncConsumerActionExecutor$1$1.run(DefaultAsyncConsumerActionExecutor.java:57)
	at org.gradle.internal.concurrent.ExecutorPolicy$CatchAndRecordFailures.onExecute(ExecutorPolicy.java:54)
	at org.gradle.internal.concurrent.StoppableExecutorImpl$1.run(StoppableExecutorImpl.java:40)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(Unknown Source)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(Unknown Source)
	at java.lang.Thread.run(Unknown Source)
	at org.gradle.tooling.internal.consumer.BlockingResultHandler.getResult(BlockingResultHandler.java:46)
	at org.gradle.tooling.internal.consumer.DefaultModelBuilder.get(DefaultModelBuilder.java:51)
	at com.gradleware.tooling.toolingclient.internal.DefaultToolingClient.executeAndWait(DefaultToolingClient.java:106)
	at com.gradleware.tooling.toolingclient.internal.DefaultModelRequest.executeAndWait(DefaultModelRequest.java:79)
	at com.gradleware.tooling.toolingmodel.repository.internal.BaseModelRepository$1.get(BaseModelRepository.java:95)
	at com.gradleware.tooling.toolingmodel.repository.internal.BaseModelRepository.executeAndWait(BaseModelRepository.java:163)
	at com.gradleware.tooling.toolingmodel.repository.internal.BaseModelRepository.access$000(BaseModelRepository.java:41)
	at com.gradleware.tooling.toolingmodel.repository.internal.BaseModelRepository$2.call(BaseModelRepository.java:121)
	at com.google.common.cache.LocalCache$LocalManualCache$1.load(LocalCache.java:4724)
	at com.google.common.cache.LocalCache$LoadingValueReference.loadFuture(LocalCache.java:3522)
	at com.google.common.cache.LocalCache$Segment.loadSync(LocalCache.java:2315)
	at com.google.common.cache.LocalCache$Segment.lockedGetOrLoad(LocalCache.java:2278)
	at com.google.common.cache.LocalCache$Segment.get(LocalCache.java:2193)
	at com.google.common.cache.LocalCache.get(LocalCache.java:3932)
	at com.google.common.cache.LocalCache$LocalManualCache.get(LocalCache.java:4721)
	at com.gradleware.tooling.toolingmodel.repository.internal.BaseModelRepository.getFromCache(BaseModelRepository.java:138)
	at com.gradleware.tooling.toolingmodel.repository.internal.BaseModelRepository.executeRequest(BaseModelRepository.java:117)
	at com.gradleware.tooling.toolingmodel.repository.internal.BaseModelRepository.executeRequest(BaseModelRepository.java:88)
	at com.gradleware.tooling.toolingmodel.repository.internal.DefaultSingleBuildModelRepository.fetchGradleBuildStructure(DefaultSingleBuildModelRepository.java:132)
	at org.eclipse.buildship.core.projectimport.ProjectPreviewJob.fetchGradleBuildStructure(ProjectPreviewJob.java:94)
	at org.eclipse.buildship.core.projectimport.ProjectPreviewJob.runToolingApiJobInWorkspace(ProjectPreviewJob.java:83)
	at org.eclipse.buildship.core.util.progress.ToolingApiWorkspaceJob$1.run(ToolingApiWorkspaceJob.java:79)
	at org.eclipse.buildship.core.util.progress.ToolingApiInvoker.invoke(ToolingApiInvoker.java:63)
	at org.eclipse.buildship.core.util.progress.ToolingApiWorkspaceJob.runInWorkspace(ToolingApiWorkspaceJob.java:76)
	at org.eclipse.core.internal.resources.InternalWorkspaceJob.run(InternalWorkspaceJob.java:39)
	at org.eclipse.core.internal.jobs.Worker.run(Worker.java:55)
Caused by: org.gradle.internal.exceptions.LocationAwareException: A problem occurred configuring root project 'hello-world'.
	at org.gradle.initialization.DefaultExceptionAnalyser.transform(DefaultExceptionAnalyser.java:74)
	at org.gradle.initialization.MultipleBuildFailuresExceptionAnalyser.transform(MultipleBuildFailuresExceptionAnalyser.java:47)
	at org.gradle.initialization.StackTraceSanitizingExceptionAnalyser.transform(StackTraceSanitizingExceptionAnalyser.java:30)
	at org.gradle.initialization.DefaultGradleLauncher.doBuild(DefaultGradleLauncher.java:122)
	at org.gradle.initialization.DefaultGradleLauncher.getBuildAnalysis(DefaultGradleLauncher.java:107)
	at org.gradle.launcher.exec.GradleBuildController.configure(GradleBuildController.java:79)
	at org.gradle.tooling.internal.provider.runner.BuildModelActionRunner.run(BuildModelActionRunner.java:52)
	at org.gradle.launcher.exec.ChainingBuildActionRunner.run(ChainingBuildActionRunner.java:35)
	at org.gradle.tooling.internal.provider.runner.RunAsBuildOperationBuildActionRunner$1.execute(RunAsBuildOperationBuildActionRunner.java:43)
	at org.gradle.tooling.internal.provider.runner.RunAsBuildOperationBuildActionRunner$1.execute(RunAsBuildOperationBuildActionRunner.java:40)
	at org.gradle.internal.Transformers$4.transform(Transformers.java:169)
	at org.gradle.internal.progress.DefaultBuildOperationExecutor.run(DefaultBuildOperationExecutor.java:106)
	at org.gradle.internal.progress.DefaultBuildOperationExecutor.run(DefaultBuildOperationExecutor.java:56)
	at org.gradle.tooling.internal.provider.runner.RunAsBuildOperationBuildActionRunner.run(RunAsBuildOperationBuildActionRunner.java:40)
	at org.gradle.tooling.internal.provider.runner.SubscribableBuildActionRunner.run(SubscribableBuildActionRunner.java:75)
	at org.gradle.launcher.exec.ChainingBuildActionRunner.run(ChainingBuildActionRunner.java:35)
	at org.gradle.launcher.exec.InProcessBuildActionExecuter.execute(InProcessBuildActionExecuter.java:41)
	at org.gradle.launcher.exec.InProcessBuildActionExecuter.execute(InProcessBuildActionExecuter.java:26)
	at org.gradle.tooling.internal.provider.ContinuousBuildActionExecuter.execute(ContinuousBuildActionExecuter.java:75)
	at org.gradle.tooling.internal.provider.ContinuousBuildActionExecuter.execute(ContinuousBuildActionExecuter.java:49)
	at org.gradle.tooling.internal.provider.ServicesSetupBuildActionExecuter.execute(ServicesSetupBuildActionExecuter.java:44)
	at org.gradle.tooling.internal.provider.ServicesSetupBuildActionExecuter.execute(ServicesSetupBuildActionExecuter.java:29)
	at org.gradle.launcher.daemon.server.exec.ExecuteBuild.doBuild(ExecuteBuild.java:67)
	at org.gradle.launcher.daemon.server.exec.BuildCommandOnly.execute(BuildCommandOnly.java:36)
	at org.gradle.launcher.daemon.server.api.DaemonCommandExecution.proceed(DaemonCommandExecution.java:120)
	at org.gradle.launcher.daemon.server.exec.WatchForDisconnection.execute(WatchForDisconnection.java:47)
	at org.gradle.launcher.daemon.server.api.DaemonCommandExecution.proceed(DaemonCommandExecution.java:120)
	at org.gradle.launcher.daemon.server.exec.ResetDeprecationLogger.execute(ResetDeprecationLogger.java:26)
	at org.gradle.launcher.daemon.server.api.DaemonCommandExecution.proceed(DaemonCommandExecution.java:120)
	at org.gradle.launcher.daemon.server.exec.RequestStopIfSingleUsedDaemon.execute(RequestStopIfSingleUsedDaemon.java:34)
	at org.gradle.launcher.daemon.server.api.DaemonCommandExecution.proceed(DaemonCommandExecution.java:120)
	at org.gradle.launcher.daemon.server.exec.ForwardClientInput$2.call(ForwardClientInput.java:74)
	at org.gradle.launcher.daemon.server.exec.ForwardClientInput$2.call(ForwardClientInput.java:72)
	at org.gradle.util.Swapper.swap(Swapper.java:38)
	at org.gradle.launcher.daemon.server.exec.ForwardClientInput.execute(ForwardClientInput.java:72)
	at org.gradle.launcher.daemon.server.api.DaemonCommandExecution.proceed(DaemonCommandExecution.java:120)
	at org.gradle.launcher.daemon.server.exec.LogAndCheckHealth.execute(LogAndCheckHealth.java:55)
	at org.gradle.launcher.daemon.server.api.DaemonCommandExecution.proceed(DaemonCommandExecution.java:120)
	at org.gradle.launcher.daemon.server.exec.LogToClient.doBuild(LogToClient.java:60)
	at org.gradle.launcher.daemon.server.exec.BuildCommandOnly.execute(BuildCommandOnly.java:36)
	at org.gradle.launcher.daemon.server.api.DaemonCommandExecution.proceed(DaemonCommandExecution.java:120)
	at org.gradle.launcher.daemon.server.exec.EstablishBuildEnvironment.doBuild(EstablishBuildEnvironment.java:72)
	at org.gradle.launcher.daemon.server.exec.BuildCommandOnly.execute(BuildCommandOnly.java:36)
	at org.gradle.launcher.daemon.server.api.DaemonCommandExecution.proceed(DaemonCommandExecution.java:120)
	at org.gradle.launcher.daemon.server.exec.StartBuildOrRespondWithBusy$1.run(StartBuildOrRespondWithBusy.java:50)
	at org.gradle.launcher.daemon.server.DaemonStateCoordinator$1.run(DaemonStateCoordinator.java:297)
	at org.gradle.internal.concurrent.ExecutorPolicy$CatchAndRecordFailures.onExecute(ExecutorPolicy.java:54)
	at org.gradle.internal.concurrent.StoppableExecutorImpl$1.run(StoppableExecutorImpl.java:40)
Caused by: org.gradle.api.ProjectConfigurationException: A problem occurred configuring root project 'hello-world'.
	at org.gradle.configuration.project.LifecycleProjectEvaluator.addConfigurationFailure(LifecycleProjectEvaluator.java:94)
	at org.gradle.configuration.project.LifecycleProjectEvaluator.doConfigure(LifecycleProjectEvaluator.java:72)
	at org.gradle.configuration.project.LifecycleProjectEvaluator.access$000(LifecycleProjectEvaluator.java:33)
	at org.gradle.configuration.project.LifecycleProjectEvaluator$1.execute(LifecycleProjectEvaluator.java:53)
	at org.gradle.configuration.project.LifecycleProjectEvaluator$1.execute(LifecycleProjectEvaluator.java:50)
	at org.gradle.internal.Transformers$4.transform(Transformers.java:169)
	at org.gradle.internal.progress.DefaultBuildOperationExecutor.run(DefaultBuildOperationExecutor.java:106)
	at org.gradle.internal.progress.DefaultBuildOperationExecutor.run(DefaultBuildOperationExecutor.java:61)
	at org.gradle.configuration.project.LifecycleProjectEvaluator.evaluate(LifecycleProjectEvaluator.java:50)
	at org.gradle.api.internal.project.DefaultProject.evaluate(DefaultProject.java:628)
	at org.gradle.api.internal.project.DefaultProject.evaluate(DefaultProject.java:129)
	at org.gradle.execution.TaskPathProjectEvaluator.configure(TaskPathProjectEvaluator.java:35)
	at org.gradle.execution.TaskPathProjectEvaluator.configureHierarchy(TaskPathProjectEvaluator.java:60)
	at org.gradle.configuration.DefaultBuildConfigurer.configure(DefaultBuildConfigurer.java:38)
	at org.gradle.initialization.DefaultGradleLauncher$1.execute(DefaultGradleLauncher.java:161)
	at org.gradle.initialization.DefaultGradleLauncher$1.execute(DefaultGradleLauncher.java:158)
	at org.gradle.internal.Transformers$4.transform(Transformers.java:169)
	at org.gradle.internal.progress.DefaultBuildOperationExecutor.run(DefaultBuildOperationExecutor.java:106)
	at org.gradle.internal.progress.DefaultBuildOperationExecutor.run(DefaultBuildOperationExecutor.java:56)
	at org.gradle.initialization.DefaultGradleLauncher.doBuildStages(DefaultGradleLauncher.java:158)
	at org.gradle.initialization.DefaultGradleLauncher.doBuild(DefaultGradleLauncher.java:119)
	... 44 more
Caused by: org.gradle.api.artifacts.ResolveException: Could not resolve all dependencies for configuration ':classpath'.
	at org.gradle.api.internal.artifacts.ivyservice.DefaultLenientConfiguration.rethrowFailure(DefaultLenientConfiguration.java:169)
	at org.gradle.api.internal.artifacts.ivyservice.DefaultLenientConfiguration$1.collectFiles(DefaultLenientConfiguration.java:118)
	at org.gradle.api.internal.artifacts.configurations.DefaultConfiguration.doGetFiles(DefaultConfiguration.java:519)
	at org.gradle.api.internal.artifacts.configurations.DefaultConfiguration.getFiles(DefaultConfiguration.java:356)
	at org.gradle.api.internal.artifacts.configurations.DefaultConfiguration_Decorated.getFiles(Unknown Source)
	at org.gradle.api.internal.initialization.DefaultScriptHandler.getScriptClassPath(DefaultScriptHandler.java:69)
	at org.gradle.plugin.use.internal.DefaultPluginRequestApplicator.defineScriptHandlerClassScope(DefaultPluginRequestApplicator.java:193)
	at org.gradle.plugin.use.internal.DefaultPluginRequestApplicator.applyPlugins(DefaultPluginRequestApplicator.java:78)
	at org.gradle.configuration.DefaultScriptPluginFactory$ScriptPluginImpl.apply(DefaultScriptPluginFactory.java:156)
	at org.gradle.configuration.project.BuildScriptProcessor.execute(BuildScriptProcessor.java:39)
	at org.gradle.configuration.project.BuildScriptProcessor.execute(BuildScriptProcessor.java:26)
	at org.gradle.configuration.project.ConfigureActionsProjectEvaluator.evaluate(ConfigureActionsProjectEvaluator.java:34)
	at org.gradle.configuration.project.LifecycleProjectEvaluator.doConfigure(LifecycleProjectEvaluator.java:70)
	... 63 more
Caused by: org.gradle.internal.resolve.ModuleVersionResolveException: Could not resolve org.springframework.boot:spring-boot-gradle-plugin:1.5.1.RELEASE.
Required by:
    project :
	at org.gradle.api.internal.artifacts.ivyservice.ivyresolve.RepositoryChainComponentMetaDataResolver.resolveModule(RepositoryChainComponentMetaDataResolver.java:83)
	at org.gradle.api.internal.artifacts.ivyservice.ivyresolve.RepositoryChainComponentMetaDataResolver.resolve(RepositoryChainComponentMetaDataResolver.java:59)
	at org.gradle.api.internal.artifacts.ivyservice.resolveengine.ComponentResolversChain$ComponentMetaDataResolverChain.resolve(ComponentResolversChain.java:85)
	at org.gradle.api.internal.artifacts.ivyservice.clientmodule.ClientModuleResolver.resolve(ClientModuleResolver.java:45)
	at org.gradle.api.internal.artifacts.ivyservice.resolveengine.graph.DependencyGraphBuilder$ModuleVersionResolveState.resolve(DependencyGraphBuilder.java:622)
	at org.gradle.api.internal.artifacts.ivyservice.resolveengine.graph.DependencyGraphBuilder$ModuleVersionResolveState.getMetaData(DependencyGraphBuilder.java:633)
	at org.gradle.api.internal.artifacts.ivyservice.resolveengine.graph.DependencyGraphBuilder$DependencyEdge.calculateTargetConfigurations(DependencyGraphBuilder.java:280)
	at org.gradle.api.internal.artifacts.ivyservice.resolveengine.graph.DependencyGraphBuilder$DependencyEdge.attachToTargetConfigurations(DependencyGraphBuilder.java:253)
	at org.gradle.api.internal.artifacts.ivyservice.resolveengine.graph.DependencyGraphBuilder.traverseGraph(DependencyGraphBuilder.java:161)
	at org.gradle.api.internal.artifacts.ivyservice.resolveengine.graph.DependencyGraphBuilder.resolve(DependencyGraphBuilder.java:98)
	at org.gradle.api.internal.artifacts.ivyservice.resolveengine.DefaultArtifactDependencyResolver.resolve(DefaultArtifactDependencyResolver.java:85)
	at org.gradle.api.internal.artifacts.ivyservice.CacheLockingArtifactDependencyResolver$1.run(CacheLockingArtifactDependencyResolver.java:43)
	at org.gradle.internal.Factories$1.create(Factories.java:25)
	at org.gradle.cache.internal.DefaultCacheAccess.useCache(DefaultCacheAccess.java:187)
	at org.gradle.cache.internal.DefaultCacheAccess.useCache(DefaultCacheAccess.java:170)
	at org.gradle.cache.internal.DefaultPersistentDirectoryStore.useCache(DefaultPersistentDirectoryStore.java:129)
	at org.gradle.cache.internal.DefaultCacheFactory$ReferenceTrackingCache.useCache(DefaultCacheFactory.java:191)
	at org.gradle.api.internal.artifacts.ivyservice.DefaultCacheLockingManager.useCache(DefaultCacheLockingManager.java:56)
	at org.gradle.api.internal.artifacts.ivyservice.CacheLockingArtifactDependencyResolver.resolve(CacheLockingArtifactDependencyResolver.java:41)
	at org.gradle.api.internal.artifacts.ivyservice.DefaultConfigurationResolver.resolveGraph(DefaultConfigurationResolver.java:119)
	at org.gradle.api.internal.artifacts.ivyservice.ShortCircuitEmptyConfigurationResolver.resolveGraph(ShortCircuitEmptyConfigurationResolver.java:72)
	at org.gradle.api.internal.artifacts.ivyservice.ErrorHandlingConfigurationResolver.resolveGraph(ErrorHandlingConfigurationResolver.java:66)
	at org.gradle.api.internal.artifacts.configurations.DefaultConfiguration$3.execute(DefaultConfiguration.java:443)
	at org.gradle.api.internal.artifacts.configurations.DefaultConfiguration$3.execute(DefaultConfiguration.java:436)
	at org.gradle.internal.Transformers$4.transform(Transformers.java:169)
	at org.gradle.internal.progress.DefaultBuildOperationExecutor.run(DefaultBuildOperationExecutor.java:106)
	at org.gradle.internal.progress.DefaultBuildOperationExecutor.run(DefaultBuildOperationExecutor.java:56)
	at org.gradle.api.internal.artifacts.configurations.DefaultConfiguration.resolveGraphIfRequired(DefaultConfiguration.java:436)
	at org.gradle.api.internal.artifacts.configurations.DefaultConfiguration.resolveToStateOrLater(DefaultConfiguration.java:411)
	at org.gradle.api.internal.artifacts.configurations.DefaultConfiguration.doGetFiles(DefaultConfiguration.java:518)
	... 73 more
Caused by: org.gradle.internal.resolve.ModuleVersionResolveException: Could not resolve org.springframework.boot:spring-boot-gradle-plugin:1.5.1.RELEASE.
	at org.gradle.api.internal.artifacts.ivyservice.ivyresolve.ErrorHandlingModuleComponentRepository$ErrorHandlingModuleComponentRepositoryAccess.resolveComponentMetaData(ErrorHandlingModuleComponentRepository.java:96)
	at org.gradle.api.internal.artifacts.ivyservice.ivyresolve.ComponentMetaDataResolveState.process(ComponentMetaDataResolveState.java:66)
	at org.gradle.api.internal.artifacts.ivyservice.ivyresolve.ComponentMetaDataResolveState.resolve(ComponentMetaDataResolveState.java:58)
	at org.gradle.api.internal.artifacts.ivyservice.ivyresolve.RepositoryChainComponentMetaDataResolver.findBestMatch(RepositoryChainComponentMetaDataResolver.java:116)
	at org.gradle.api.internal.artifacts.ivyservice.ivyresolve.RepositoryChainComponentMetaDataResolver.findBestMatch(RepositoryChainComponentMetaDataResolver.java:99)
	at org.gradle.api.internal.artifacts.ivyservice.ivyresolve.RepositoryChainComponentMetaDataResolver.resolveModule(RepositoryChainComponentMetaDataResolver.java:72)
	... 102 more
Caused by: org.gradle.api.resources.ResourceException: Could not get resource 'https://repo1.maven.org/maven2/org/springframework/boot/spring-boot-gradle-plugin/1.5.1.RELEASE/spring-boot-gradle-plugin-1.5.1.RELEASE.pom'.
	at org.gradle.internal.resource.ResourceExceptions.failure(ResourceExceptions.java:69)
	at org.gradle.internal.resource.ResourceExceptions.getFailed(ResourceExceptions.java:52)
	at org.gradle.api.internal.artifacts.repositories.resolver.DefaultExternalResourceArtifactResolver.downloadStaticResource(DefaultExternalResourceArtifactResolver.java:106)
	at org.gradle.api.internal.artifacts.repositories.resolver.DefaultExternalResourceArtifactResolver.resolveArtifact(DefaultExternalResourceArtifactResolver.java:65)
	at org.gradle.api.internal.artifacts.repositories.resolver.ExternalResourceResolver.parseMetaDataFromArtifact(ExternalResourceResolver.java:198)
	at org.gradle.api.internal.artifacts.repositories.resolver.ExternalResourceResolver.resolveStaticDependency(ExternalResourceResolver.java:175)
	at org.gradle.api.internal.artifacts.repositories.resolver.MavenResolver.doResolveComponentMetaData(MavenResolver.java:110)
	at org.gradle.api.internal.artifacts.repositories.resolver.ExternalResourceResolver$RemoteRepositoryAccess.resolveComponentMetaData(ExternalResourceResolver.java:455)
	at org.gradle.api.internal.artifacts.ivyservice.ivyresolve.CacheLockReleasingModuleComponentsRepository$LockReleasingRepositoryAccess$2.run(CacheLockReleasingModuleComponentsRepository.java:78)
	at org.gradle.internal.Factories$1.create(Factories.java:25)
	at org.gradle.cache.internal.DefaultCacheAccess.longRunningOperation(DefaultCacheAccess.java:247)
	at org.gradle.cache.internal.DefaultCacheAccess.longRunningOperation(DefaultCacheAccess.java:318)
	at org.gradle.cache.internal.DefaultPersistentDirectoryStore.longRunningOperation(DefaultPersistentDirectoryStore.java:137)
	at org.gradle.cache.internal.DefaultCacheFactory$ReferenceTrackingCache.longRunningOperation(DefaultCacheFactory.java:183)
	at org.gradle.api.internal.artifacts.ivyservice.DefaultCacheLockingManager.longRunningOperation(DefaultCacheLockingManager.java:48)
	at org.gradle.api.internal.artifacts.ivyservice.ivyresolve.CacheLockReleasingModuleComponentsRepository$LockReleasingRepositoryAccess.resolveComponentMetaData(CacheLockReleasingModuleComponentsRepository.java:76)
	at org.gradle.api.internal.artifacts.ivyservice.ivyresolve.CachingModuleComponentRepository$ResolveAndCacheRepositoryAccess.resolveComponentMetaData(CachingModuleComponentRepository.java:312)
	at org.gradle.api.internal.artifacts.ivyservice.ivyresolve.BaseModuleComponentRepositoryAccess.resolveComponentMetaData(BaseModuleComponentRepositoryAccess.java:49)
	at org.gradle.api.internal.artifacts.ivyservice.ivyresolve.memcache.InMemoryCachedModuleComponentRepository$CachedAccess.resolveComponentMetaData(InMemoryCachedModuleComponentRepository.java:82)
	at org.gradle.api.internal.artifacts.ivyservice.ivyresolve.ErrorHandlingModuleComponentRepository$ErrorHandlingModuleComponentRepositoryAccess.resolveComponentMetaData(ErrorHandlingModuleComponentRepository.java:94)
	... 107 more
Caused by: org.gradle.internal.resource.transport.http.HttpRequestException: Could not GET 'https://repo1.maven.org/maven2/org/springframework/boot/spring-boot-gradle-plugin/1.5.1.RELEASE/spring-boot-gradle-plugin-1.5.1.RELEASE.pom'.
	at org.gradle.internal.resource.transport.http.HttpClientHelper.performRequest(HttpClientHelper.java:82)
	at org.gradle.internal.resource.transport.http.HttpClientHelper.performRawGet(HttpClientHelper.java:66)
	at org.gradle.internal.resource.transport.http.HttpClientHelper.performGet(HttpClientHelper.java:70)
	at org.gradle.internal.resource.transport.http.HttpResourceAccessor.openResource(HttpResourceAccessor.java:43)
	at org.gradle.internal.resource.transport.http.HttpResourceAccessor.openResource(HttpResourceAccessor.java:29)
	at org.gradle.internal.resource.transfer.DefaultExternalResourceConnector.openResource(DefaultExternalResourceConnector.java:56)
	at org.gradle.internal.resource.transfer.ProgressLoggingExternalResourceAccessor.openResource(ProgressLoggingExternalResourceAccessor.java:36)
	at org.gradle.internal.resource.transport.DefaultExternalResourceRepository.getResource(DefaultExternalResourceRepository.java:63)
	at org.gradle.internal.resource.transfer.DefaultCacheAwareExternalResourceAccessor.getResource(DefaultCacheAwareExternalResourceAccessor.java:76)
	at org.gradle.api.internal.artifacts.repositories.resolver.DefaultExternalResourceArtifactResolver.downloadStaticResource(DefaultExternalResourceArtifactResolver.java:97)
	... 124 more
Caused by: org.apache.http.conn.HttpHostConnectException: Connect to 127.0.0.1:8087 [/127.0.0.1] failed: Connection refused: connect
	at org.apache.http.impl.conn.DefaultHttpClientConnectionOperator.connect(DefaultHttpClientConnectionOperator.java:151)
	at org.apache.http.impl.conn.PoolingHttpClientConnectionManager.connect(PoolingHttpClientConnectionManager.java:353)
	at org.apache.http.impl.execchain.MainClientExec.establishRoute(MainClientExec.java:388)
	at org.apache.http.impl.execchain.MainClientExec.execute(MainClientExec.java:236)
	at org.apache.http.impl.execchain.ProtocolExec.execute(ProtocolExec.java:184)
	at org.apache.http.impl.execchain.RetryExec.execute(RetryExec.java:88)
	at org.apache.http.impl.execchain.RedirectExec.execute(RedirectExec.java:110)
	at org.apache.http.impl.client.InternalHttpClient.doExecute(InternalHttpClient.java:184)
	at org.apache.http.impl.client.CloseableHttpClient.execute(CloseableHttpClient.java:82)
	at org.gradle.internal.resource.transport.http.HttpClientHelper.performHttpRequest(HttpClientHelper.java:113)
	at org.gradle.internal.resource.transport.http.HttpClientHelper.executeGetOrHead(HttpClientHelper.java:89)
	at org.gradle.internal.resource.transport.http.HttpClientHelper.performRequest(HttpClientHelper.java:80)
	... 133 more
Caused by: java.net.ConnectException: Connection refused: connect
	at org.apache.http.conn.socket.PlainConnectionSocketFactory.connectSocket(PlainConnectionSocketFactory.java:74)
	at org.apache.http.impl.conn.DefaultHttpClientConnectionOperator.connect(DefaultHttpClientConnectionOperator.java:134)
	... 144 more

