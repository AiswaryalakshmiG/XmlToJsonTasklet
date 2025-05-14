package com.example.XmlToJsonUsingTasklets;

import java.util.Date;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class XmlToJsonScheduler {
	@Autowired
	private JobLauncher joblauncher;
	@Autowired
	private Job XmlToJsonJob;

	@Scheduled(cron = "0 */1 * ? * *")
	public void scheduleXmlToJsonJob() {
		JobParameters jobParameters = new JobParameters();
		try {
			joblauncher.run(XmlToJsonJob, jobParameters);
			System.out.println("Job Triggered at: " + new Date());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
