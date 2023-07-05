package io.openjob.agent.java.command;

import io.openjob.worker.OpenjobWorker;
import picocli.CommandLine;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.4
 */
@CommandLine.Command(name = "Openjob-agent-java", mixinStandardHelpOptions = true, version = "1.0.4")
public class AgentCommand implements Runnable {

    /**
     * Server address to set `openjob.server.address`
     */
    @CommandLine.Option(names = {"-s", "--server-address"}, description = "Server address")
    private String serverAddress;

    /**
     * Application name to set `openjob.worker.app.name`
     */
    @CommandLine.Option(names = {"--worker-app-name"}, description = "Application name")
    private String workerAppName;

    /**
     * Worker host to set `openjob.worker.host`
     */
    @CommandLine.Option(names = {"--worker-host"}, description = "Worker host")
    private String workerHost;

    /**
     * Worker port to set `openjob.worker.port`
     */
    @CommandLine.Option(names = {"--worker-port"}, description = "Worker port")
    private String workerPort;

    /**
     * Worker heartbeat interval to set `openjob.worker.heartbeat.interval`
     */
    @CommandLine.Option(names = {"--worker-heartbeat-interval"}, description = "Worker heartbeat interval")
    private Integer workerHeartbeatInterval;

    /**
     * Worker heartbeat fail times to set `openjob.worker.heartbeat.fail.times`
     */
    @CommandLine.Option(names = {"--worker-heartbeat-fail-times"}, description = "Worker heartbeat fail times")
    private Integer workerHeartbeatFailTimes;

    public void run() {
        try {
            OpenjobWorker openjobWorker = new OpenjobWorker();
            openjobWorker.init();

            Thread.currentThread().join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
