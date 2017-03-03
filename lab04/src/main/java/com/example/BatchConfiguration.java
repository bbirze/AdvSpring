package com.example;

import org.apache.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.builder.SimpleJobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.builder.TaskletStepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	private Logger log = Logger.getLogger(BatchConfiguration.class);

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Step step1()  	{
		Tasklet task = new Tasklet()   {   // tasklet is UOW within a step

			@Override
			public RepeatStatus execute(StepContribution contribution, 
										ChunkContext chunkContext) throws Exception {
				log.info("STEP ONE!");
				return RepeatStatus.FINISHED;
			}			
		};
		StepBuilder  sb = stepBuilderFactory.get("step1");
		TaskletStepBuilder tsb = sb.tasklet(task);

		return tsb.build();
	}
	
	@Bean
	public Step step2()  	{
		Tasklet task = new Tasklet()   {

			@Override
			public RepeatStatus execute(StepContribution contribution, 
										ChunkContext chunkContext) throws Exception {
				log.info("STEP TWO!");
				return RepeatStatus.FINISHED;
			}			
		};
		StepBuilder  sb = stepBuilderFactory.get("step2");
		TaskletStepBuilder tsb = sb.tasklet(task);

		return tsb.build();
	}

	@Bean
	public Job job() throws Exception {
		RunIdIncrementer incrementer = new RunIdIncrementer();
		JobBuilder jb = jobBuilderFactory.get("job1");
		jb.incrementer(incrementer);
		                 // create job builder that will execute steps 
		SimpleJobBuilder sjb = jb.start(step1());
		sjb.next(step2());
		return sjb.build();
	}
	
}
