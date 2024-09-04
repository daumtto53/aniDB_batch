package com.aniDB.aniDB_batch;

import com.aniDB.aniDB_batch.processor.*;
import com.aniDB.aniDB_batch.service.PublicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AniDbBatchApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(AniDbBatchApplication.class, args);

		ProcessorCompilation processor = context.getBean(ProcessorCompilation.class);
		processor.executeAllProcessors();
	}

}
