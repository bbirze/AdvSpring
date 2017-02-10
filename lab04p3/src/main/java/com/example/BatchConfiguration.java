package com.example;

import org.apache.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.job.builder.SimpleJobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.builder.SimpleStepBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.domain.Game;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	private Logger log = Logger.getLogger(BatchConfiguration.class);
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public ItemReader itemReader() {
		return new MyItemReader();
	}
	
	
	@Bean
	public ItemWriter itemWriter() {
		return new MyItemWriter();
	}
	
	@Bean
	public Step step1()   {
		StepBuilder sb = stepBuilderFactory.get("step1");
		SimpleStepBuilder<Game, Game> ssb = sb.chunk(3);
		ssb.reader(itemReader());
		ssb.writer(itemWriter());
		
		return ssb.build();
	}
	
	@Bean
	public Job job() throws Exception {
		RunIdIncrementer incrementer = new RunIdIncrementer();
		JobBuilder jb = jobBuilderFactory.get("job1");
		jb.incrementer(incrementer);
		
		SimpleJobBuilder sjb = jb.start(step1());
		return sjb.build();
	}
	
}
