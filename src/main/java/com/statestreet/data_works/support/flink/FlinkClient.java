package com.statestreet.data_works.support.flink;

import org.apache.flink.api.common.JobID;
import org.apache.flink.api.common.JobSubmissionResult;
import org.apache.flink.client.deployment.ClusterDescriptor;
import org.apache.flink.client.deployment.StandaloneClusterDescriptor;
import org.apache.flink.client.deployment.StandaloneClusterId;
import org.apache.flink.client.program.ClusterClient;
import org.apache.flink.client.program.PackagedProgram;
import org.apache.flink.client.program.PackagedProgramUtils;
import org.apache.flink.client.program.rest.RestClusterClient;
import org.apache.flink.configuration.*;
import org.apache.flink.core.fs.Path;
import org.apache.flink.runtime.jobgraph.JobGraph;
import org.apache.flink.runtime.jobgraph.JobGraphBuilder;
import org.apache.flink.table.api.*;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.concurrent.TimeUnit;

@Component
public class FlinkClient {


    public static void main(String[] args) throws Exception {
        Configuration configuration = new Configuration();
        configuration.setString(JobManagerOptions.ADDRESS, "127.0.0.1");
        configuration.setInteger(RestOptions.PORT, 8081);
        String jarFilePath = "/Users/cqx/Projects/somethingnew/stn-flink/target/stn-flink-1.0-SNAPSHOT.jar";
        RestClusterClient<StandaloneClusterId> client = new RestClusterClient<>(configuration, StandaloneClusterId.getInstance());
        int parallelism = 1;
        PackagedProgram program = PackagedProgram.newBuilder()
                .setJarFile(new File(jarFilePath))
                .setArguments(args)
                .build();
        JobGraph jobGraph = PackagedProgramUtils.createJobGraph(program, configuration, parallelism, false);
        JobID jobID = client.submitJob(jobGraph).get(30, TimeUnit.SECONDS);
        System.out.println(jobID);
    }
}
