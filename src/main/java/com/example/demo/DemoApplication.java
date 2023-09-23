package com.example.demo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@EnableBatchProcessing
@SpringBootApplication
public class DemoApplication {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	@Autowired
	private StepBuilderFactory stepBuilderFactory;


	@Bean
	public Job job(){
		return this.jobBuilderFactory.get("job")
				.start(step1())
				.build();
	}
	@Bean
	public Step step1(){
		return this.stepBuilderFactory.get("step1")
				.tasklet((contribution, chunkContext) -> {
					System.out.println("Hola Mundo...!");
					return RepeatStatus.FINISHED;
						}).build();
						
	}



	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
