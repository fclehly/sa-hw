package com.fwz.batch;

import com.fwz.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.excel.mapping.PassThroughRowMapper;
import org.springframework.batch.item.excel.poi.PoiItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

/**
 * Created by fwz on 2017/6/21.
 */
@Configuration
@EnableBatchProcessing
public class ExcelFileToDatabaseJobConfig {

    private Logger logger =
            LoggerFactory.getLogger(ExcelFileToDatabaseJobConfig.class);

    private static final int LINES_TO_SKIP = 0;

    private static final int CHUNK_SIZE = 0;


    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Component
    public static class ExcelFileToDatabaseJobExecutionListener implements JobExecutionListener {

        @Override
        public void beforeJob(JobExecution jobExecution) {

        }

        @Override
        public void afterJob(JobExecution jobExecution) {

        }
    }

    @Bean
    public ItemReader<String[]> excelStudentReader() throws Exception {
        PoiItemReader<String[]> reader = new PoiItemReader<String[]>();
        reader.setLinesToSkip(LINES_TO_SKIP);
        reader.setResource(new ClassPathResource("data/students.xlsx"));
        reader.setRowMapper(new PassThroughRowMapper());
        return reader;
    }

    @Bean
    public ItemProcessor<String[], Student> excelFileToDatabaseItemProcessor() {
        return new ExcelFileToDatabaseItemProcessor();
    }

    @Bean
    public ItemWriter<Student> excelStudentWriter() {
        return new ExcelFileToDatabaseItemWriter();
    }

    @Bean
    public Step excelFileToDatabaseStep() throws Exception {
        return stepBuilderFactory.get("excelFileToDatabaseStep")
                .<String[], Student> chunk(CHUNK_SIZE)
                .reader(excelStudentReader())
                .processor(excelFileToDatabaseItemProcessor())
                .writer(excelStudentWriter())
                .build();
    }

    @Bean
    public Job excelFileToDatabaseJob(ExcelFileToDatabaseJobExecutionListener listener) throws Exception {
        return jobBuilderFactory.get("excelFileToDatabaseJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(excelFileToDatabaseStep())
                .end()
                .build();
    }
}
