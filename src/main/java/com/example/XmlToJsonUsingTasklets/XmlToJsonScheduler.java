package com.example.XmlToJsonUsingTasklets;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;


public class XmlToJsonScheduler {
	@Autowired
	private JobLauncher joblauncher;
	@Autowired
	private Job XmlToJsonJob;

	@Scheduled(cron = "0 */1 * ? * *")
	public void scheduleXmlToJsonJob() {
		Map<String,JobParameter> params = new HashMap<>();
		params.put("timestamp", new JobParameter(System.currentTimeMillis()));

		try {
			joblauncher.run(XmlToJsonJob, new JobParameters(params));
			System.out.println("Job Triggered at: " + new Date());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
