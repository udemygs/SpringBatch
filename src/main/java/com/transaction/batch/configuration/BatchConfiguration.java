package com.transaction.batch.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.transaction.batch.TransactionBO;
import com.transaction.batch.TransactionProcessor;
import com.transaction.batch.TransactionWriter;
import com.transaction.entity.Transactions;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration extends DataSourceConfig {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job job() {
		return jobBuilderFactory.get("job").incrementer(new RunIdIncrementer()).start(step1()).build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").<TransactionBO, TransactionBO>chunk(10).reader(reader())
				.processor(processor()).writer(writer()).build();
	}

	@Bean
	public FlatFileItemReader reader() {
		FlatFileItemReader<TransactionBO> itemReader = new FlatFileItemReader<>();
		itemReader.setResource(new ClassPathResource("dataSource.txt"));
		itemReader.setLinesToSkip(1);

		DefaultLineMapper<TransactionBO> lineMapper = new DefaultLineMapper<>();
		lineMapper.setLineTokenizer(new DelimitedLineTokenizer("|") {
			{
				setNames(new String[] { "accountNumber", "trxAmount", "description", "trxDate", "trxTime",
						"customerId" });
			}
		});
		lineMapper.setFieldSetMapper(new BeanWrapperFieldSetMapper<TransactionBO>() {
			{
				setTargetType(TransactionBO.class);
			}
		});

		itemReader.setLineMapper(lineMapper);
		return itemReader;
	}

	@Bean
	public TransactionProcessor processor() {
		return new TransactionProcessor();
	}

	@Bean
	public ItemWriter<Transactions> writer() {
		return new TransactionWriter();
	}
}
