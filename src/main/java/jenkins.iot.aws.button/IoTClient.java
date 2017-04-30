package jenkins.iot.aws.button;

import com.offbytwo.jenkins.JenkinsServer;
import com.offbytwo.jenkins.model.BuildResult;
import com.offbytwo.jenkins.model.BuildWithDetails;
import com.offbytwo.jenkins.model.Job;

import java.net.URI;
import java.util.Map;

public class IoTClient {

    public void executeFailBuilds(String ciServerUri, String userName, String password) throws Exception{

        JenkinsServer server = new JenkinsServer(new URI(ciServerUri), userName, password);
        Map<String, Job> jobs = server.getJobs();

        for (Job job: jobs.values()) {
            if (job.details().isBuildable()) {
                BuildWithDetails jobDetails = job.details().getLastBuild().details();
                if (jobDetails.getResult() == BuildResult.FAILURE) {
                    Map<String,String> parameters = jobDetails.getParameters();
                    if (parameters.size() > 0) {
                        job.build(parameters, true);
                    } else {
                        job.build(true);
                    }
                }
            }
        }
    }


}
