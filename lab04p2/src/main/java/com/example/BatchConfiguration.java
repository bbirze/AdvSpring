package com.example;

import javax.sql.DataSource;

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
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.example.domain.Account;

@Configuration                      // Java Configuration Class
@EnableBatchProcessing
public class BatchConfiguration {
	
	private static String dir = "C:/Users/bbirze/Documents/ContractInstructor/ClassMaterials/Spring/workspaceSpring/AdvSpring/lab04p2/";
	
	private Logger log = Logger.getLogger(BatchConfiguration.class);
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	// Setup Database Beans
	// ===================================================
	@Bean
	public DataSource dataSource() {
		DataSourceBuilder dsb = DataSourceBuilder.create();
		dsb.url("jdbc:hsqldb:hsql://localhost/");
		dsb.driverClassName("org.hsqldb.jdbcDriver");
		dsb.username("SA");
		dsb.password("");
		
		return dsb.build();
	}
	
	@Bean
	public ItemWriter<Account> jdbcItemWriter()  {
		final String sql = "INSERT INTO account " +
					"(balance,interestRate,customerID) " +
					"VALUES(:balance,:interestRate,:customerID)";
		
		JdbcBatchItemWriter<Account> itemWriter = new JdbcBatchItemWriter<>();
		itemWriter.setDataSource(dataSource());
		itemWriter.setSql(sql);
		itemWriter.setItemSqlParameterSourceProvider(
						new BeanPropertyItemSqlParameterSourceProvider<>());
		return itemWriter;
	}
	
	// Setup Beans to Read and Map CVX file to Account
	// ===================================================

	@Bean
	public LineMapper<Account> lineMapper()  {
		DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
		tokenizer.setDelimiter(",");
		tokenizer.setNames(new String[] {"accountID", "balance", 
			                            "interestRate", "customerID"});
		
		BeanWrapperFieldSetMapper<Account> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(Account.class);
		
		DefaultLineMapper<Account> lineMapper = new DefaultLineMapper<>();
		lineMapper.setLineTokenizer(tokenizer);          // line mapper has tokenized data
		lineMapper.setFieldSetMapper(fieldSetMapper);    // line mapper has account class to map to
		return lineMapper;
	}
	
	@Bean
	public ItemReader<Account> csvFileReader()  {
		Resource res = new FileSystemResource(dir + "accounts.csv");
		
		FlatFileItemReader<Account> itemReader = new FlatFileItemReader<>();
		itemReader.setLineMapper(lineMapper());       // reader has lineMapper
		itemReader.setResource(res);                  // reader has res source, file
		return itemReader;
	}
	
	// Setup Batch Core Beans (Step and Job)
	// ===================================================

	@Bean
	public Step step1()   {
		StepBuilder sb = stepBuilderFactory.get("step1");
		SimpleStepBuilder<Account, Account> ssb = sb.chunk(3);
		ssb.reader(csvFileReader());                    // read from flat file
		ssb.writer(jdbcItemWriter());                   // write to db
		
		return ssb.build();
	}
	
	@Bean
	public Job insertIntoDbFromCsvJob()  {
		RunIdIncrementer incrementer = new RunIdIncrementer();
		JobBuilder jb = jobBuilderFactory.get("job1");
		jb.incrementer(incrementer);
		
		SimpleJobBuilder sjb = jb.start(step1());
		return sjb.build();
	}
	
}
