package io.openjob.agent.java.command;

import io.openjob.worker.OpenjobWorker;
import io.openjob.worker.constant.WorkerConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import picocli.CommandLine;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.4
 */
@Slf4j
@CommandLine.Command(name = "Openjob-agent-java", mixinStandardHelpOptions = true, version = "1.0.7")
public class AgentCommand implements Runnable {

    /**
     * Server address to set `openjob.server.address`
     */
    @CommandLine.Option(names = {"-s", "--server-address"}, description = "Server address, default is `http://localhost:8080`", defaultValue = "http://localhost:8080")
    private String serverAddress;

    /**
     * Application name to set `openjob.worker.app.name`
     */
    @CommandLine.Option(names = {"--worker-app-name"}, description = "Application name, default is `openjob`", defaultValue = "openjob")
    private String workerAppName;

    /**
     * Worker host to set `openjob.worker.host`
     */
    @CommandLine.Option(names = {"--worker-host"}, description = "Worker host, default is local IP")
    private String workerHost;

    /**
     * Worker port to set `openjob.worker.port`
     */
    @CommandLine.Option(names = {"--worker-port"}, description = "Worker port, default is `25588`", defaultValue = "25588")
    private String workerPort;

    /**
     * Worker heartbeat interval to set `openjob.worker.heartbeat.interval`
     */
    @CommandLine.Option(names = {"--worker-heartbeat-interval"}, description = "Worker heartbeat interval, default is `5`", defaultValue = "5")
    private Integer heartbeatInterval;

    /**
     * Worker heartbeat fail times to set `openjob.worker.heartbeat.fail.times`
     */
    @CommandLine.Option(names = {"--worker-heartbeat-fail-times"}, description = "Worker heartbeat fail times, default is `2`", defaultValue = "2")
    private Integer heartbeatFailTimes;

    /**
     * Run
     */
    public void run() {
        // Initialize
        this.init();

        try {
            // Start worker
            OpenjobWorker openjobWorker = new OpenjobWorker();
            openjobWorker.init();

            Thread.currentThread().join();
        } catch (Throwable e) {
            log.error("Openjob agent start failed!", e);
        }
    }

    /**
     * Initialize
     */
    public void init() {
        System.setProperty(WorkerConstant.SERVER_ADDRESS, this.serverAddress);
        System.setProperty(WorkerConstant.WORKER_APP_NAME, this.workerAppName);

        if (StringUtils.isNoneBlank(this.workerHost)) {
            System.setProperty(WorkerConstant.WORKER_HOST, this.workerHost);
        }

        System.setProperty(WorkerConstant.WORKER_PORT, this.workerPort);
        System.setProperty(WorkerConstant.WORKER_HEARTBEAT_INTERVAL, String.valueOf(this.heartbeatInterval));
        System.setProperty(WorkerConstant.WORKER_HEARTBEAT_FAIL_TIMES, String.valueOf(this.heartbeatFailTimes));

        log.info("===================================== Agent Arguments =====================================");
        log.info("--server-address={}", this.serverAddress);
        log.info("--worker-app-name={}", this.workerAppName);
        log.info("--worker-host={}", this.workerHost);
        log.info("--worker-port={}", this.workerPort);
        log.info("--worker-heartbeat-interval={}", this.heartbeatInterval);
        log.info("--worker-heartbeat-fail-times={}", this.heartbeatFailTimes);
    }
}
