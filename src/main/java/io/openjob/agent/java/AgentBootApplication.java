package io.openjob.agent.java;

import io.openjob.agent.java.command.AgentCommand;
import picocli.CommandLine;

/**
 * @author stelin swoft@qq.com
 * @since 1.0.4
 */
public class AgentBootApplication {
    public static void main(String[] args) {
        new CommandLine(new AgentCommand()).execute(args);
    }
}
