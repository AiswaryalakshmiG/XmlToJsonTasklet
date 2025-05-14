package com.example.XmlToJsonUsingTasklets;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
 
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
 
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
 
    @Autowired
    private XmlToJsonTasklet xmlToJsonTasklet;
    
 
    @Bean
    public Job xmlToJsonJob() {
        return jobBuilderFactory.get("xmlToJsonJob")
        		.incrementer(new RunIdIncrementer())
                .start(xmlToJsonStep())
                .build();
    }
 
    
    @Bean
    public Step xmlToJsonStep() {
        return stepBuilderFactory.get("xmlToJsonStep")
                .tasklet(xmlToJsonTasklet)
                .build();
    }
}

