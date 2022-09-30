package com.statestreet.data_works.support.flink;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.flink.api.common.JobID;
import org.apache.flink.client.deployment.StandaloneClusterId;
import org.apache.flink.client.program.ClusterClient;
import org.apache.flink.client.program.rest.RestClusterClient;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.configuration.JobManagerOptions;
import org.apache.flink.configuration.RestOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Slf4j
@Component
public class FlinkClient {
    @Value("${flink.sql-client}")
    private String sqlClientPath;
    public String runSqlJob(String sql) {
        try {
            String tmpDir = System.getProperty("java.io.tmpdir");
            String sqlFilePath = tmpDir + "tmp.sql";
            File sqlFile = new File(sqlFilePath);
            FileOutputStream fileOutputStream = new FileOutputStream(sqlFile);
            IOUtils.write(sql, fileOutputStream, Charset.forName("UTF-8"));
            Runtime runtime = Runtime.getRuntime();
            String command = sqlClientPath + " -f " + sqlFilePath;
            System.out.println(command);
            Process exec = runtime.exec(command);
            java.util.Scanner s = new java.util.Scanner(exec.getInputStream()).useDelimiter("\\A");
            String result = s.hasNext() ? s.next() : "";
            Pattern compile = Pattern.compile("(?<=Job ID: ).*");
            Matcher matcher = compile.matcher(result);
            matcher.find();
            String jobId = matcher.group();
            log.info("jobId:", jobId);
            return jobId;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void stopJob(String jobId) {
        ClusterClient client = getClient();
        JobID jobIdObj = JobID.fromHexString(jobId);
        client.cancel(jobIdObj);
    }

    private ClusterClient getClient() {
        Configuration configuration = new Configuration();
        configuration.setString(JobManagerOptions.ADDRESS, "127.0.0.1");
        configuration.setInteger(RestOptions.PORT, 8081);
        try {
            RestClusterClient<StandaloneClusterId> client = new RestClusterClient<>(configuration, StandaloneClusterId.getInstance());
            return client;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        String out = "[INFO] Submitting SQL update statement to the cluster...\n" +
                "[INFO] SQL update statement has been successfully submitted to the cluster:\n" +
                "Job ID: a00ee38a84c8b725e1342f2e41a609bb\n";
        Pattern compile = Pattern.compile("(?<=Job ID: ).*");
        Matcher matcher = compile.matcher(out);
        boolean b = matcher.find();
        System.out.println(matcher.group());


        String sql =
                "CREATE TABLE orders (\n" +
                        "   order_id INT,\n" +
                        "   order_date TIMESTAMP(0),\n" +
                        "   customer_name STRING,\n" +
                        "   price DECIMAL(10, 5),\n" +
                        "   product_id INT,\n" +
                        "   order_status BOOLEAN,\n" +
                        "   PRIMARY KEY (order_id) NOT ENFORCED\n" +
                        " ) WITH (\n" +
                        "   'connector' = 'mysql-cdc',\n" +
                        "   'hostname' = 'localhost',\n" +
                        "   'port' = '3306',\n" +
                        "   'username' = 'root',\n" +
                        "   'password' = '123456',\n" +
                        "   'database-name' = 'mydb',\n" +
                        "   'table-name' = 'orders'\n" +
                        " ); \n" +
                        "CREATE TABLE orders_out (\n" +
                        "   order_id INT,\n" +
                        "   order_date TIMESTAMP(0),\n" +
                        "   customer_name STRING,\n" +
                        "   price DECIMAL(10, 5),\n" +
                        "   product_id INT,\n" +
                        "   order_status BOOLEAN,\n" +
                        "   PRIMARY KEY (order_id) NOT ENFORCED\n" +
                        " ) WITH (\n" +
                        "   'connector' = 'jdbc',\n" +
                        "   'url' = 'jdbc:mysql://localhost:3306/mydb',\n" +
                        "   'username' = 'root',\n" +
                        "   'password' = '123456',\n" +
                        "   'table-name' = 'orders'\n" +
                        " );\n" +
                        "insert into orders_out select * from orders;";
        FlinkClient flinkClient = new FlinkClient();
        flinkClient.runSqlJob(sql);
    }

}
