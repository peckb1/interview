package com.github.peckb1.interview.database;

import com.github.peckb1.interview.util.RetryWithDelay;
import io.reactivex.Flowable;
import io.reactivex.Maybe;
import io.reactivex.schedulers.Schedulers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class ConnectionFactory {

    private static final String MYSQL_CONNECTION_STRING = "jdbc:mysql://address=(protocol=tcp)(host=%s)(port=%s)/%s";
    private static final int BACKOFF_EXPONENTIAL_MULTIPLIER = 2;
    private static final int BASE_RETRY_TIME_MILLIS = 2500;
    private static final int MAX_RETRIES = 6;

    public Connection createConnection() {
        return createRetryingConnectionFlowable().blockingFirst();
    }

    public void createConnection(Consumer<Maybe<Connection>> consumer) {
        createRetryingConnectionFlowable()
                .subscribeOn(Schedulers.newThread())
                .subscribe(
                        connection -> consumer.accept(Maybe.just(connection)),
                        error -> consumer.accept(Maybe.error(error)));
    }

    private Flowable<Connection> createRetryingConnectionFlowable() {
        return Flowable.fromCallable(this::createNewConnection)
                .delay(BASE_RETRY_TIME_MILLIS, TimeUnit.MILLISECONDS)
                .retryWhen(new RetryWithDelay(MAX_RETRIES, BASE_RETRY_TIME_MILLIS, BACKOFF_EXPONENTIAL_MULTIPLIER));
    }

    private Connection createNewConnection() throws SQLException, ClassNotFoundException {
        /*
        String connectionString = String.format(MYSQL_CONNECTION_STRING,
                mySqlHost,
                mySqlPort,
                databaseName);
        return DriverManager.getConnection(connectionString, username, password);
        */
    }

}
