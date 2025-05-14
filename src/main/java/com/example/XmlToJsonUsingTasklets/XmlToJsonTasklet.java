package com.example.XmlToJsonUsingTasklets;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class XmlToJsonTasklet implements Tasklet {

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws IOException {
		ClassPathResource xmlResource = new ClassPathResource("source/basic-structure.xml");
		String xmlContent = FileUtils.readFileToString(xmlResource.getFile(), "UTF-8");

		JSONObject jsonObject = XML.toJSONObject(xmlContent);
		System.out.println("Before Converted XML to JSON successfully.");

		File jsonOutput = new File("targets/jsonFile.json");
		try (FileWriter writer = new FileWriter(jsonOutput)) {
			writer.write(jsonObject.toString(4));
		}

		System.out.println("Converted XML to JSON successfully.");
		return RepeatStatus.FINISHED;
	}
}
