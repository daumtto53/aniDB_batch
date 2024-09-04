package com.aniDB.aniDB_batch.processor;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Component
@RequiredArgsConstructor
@Log4j2
public class ProcessorCompilation {
    private final GenreProcessor genreProcessor;
    private final SeriesTypeProcessor seriesTypeProcessor ;
    private final PublisherProcessor publisherProcessor;
    private final PublicationProcessor publicationProcessor ;
    private final PlatformTransactionManager transactionManager;


    public void executeAllProcessors() {


        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            seriesTypeProcessor.insertAllSeriesType();
//            transactionManager.commit(status);

            genreProcessor.insertAllGenre();
//            transactionManager.commit(status);

            publisherProcessor.bulkInsertPublisher();
//            transactionManager.commit(status);

            publicationProcessor.iterateOverFile();
//            transactionManager.commit(status);
            publicationProcessor.insertNovel();
//            transactionManager.commit(status);
        } catch (Exception e) {
//            transactionManager.rollback(status);
            throw e;
        }
    }
}
