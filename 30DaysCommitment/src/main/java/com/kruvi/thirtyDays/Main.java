package com.kruvi.thirtyDays;

import com.outbrain.ob1k.server.Server;
import com.outbrain.ob1k.server.jetty.build.ServerBuilder;
import com.outbrain.swinfra.metrics.api.Counter;
import com.outbrain.swinfra.metrics.api.Gauge;
import com.outbrain.swinfra.metrics.api.Histogram;
import com.outbrain.swinfra.metrics.api.Meter;
import com.outbrain.swinfra.metrics.api.MetricFactory;
import com.outbrain.swinfra.metrics.api.Timer;

import java.io.IOException;

/**
 * Created by yotam on 4/10/15.
 */

public class Main {

    public static void main(String[] args) throws IOException {
        final ServerBuilder serverBuilder = new ServerBuilder();

        serverBuilder
                .configurationPorts(8080, 8443)
                .setApplicationName("30DaysCommitment");
        final String environment = System.getProperty("environment", "dev");
        System.out.println("environment = " + environment);
        if ("prod".equals(environment)) {
            serverBuilder.setAppServerClass(Main.class);
        }
        Server server = serverBuilder
                .setContextPath("/")
                .setMetricFactory(new MetricFactory() {
                    @Override
                    public Timer createTimer(String s, String s1) {
                        return null;
                    }

                    @Override
                    public Counter createCounter(String s, String s1) {
                        return null;
                    }

                    @Override
                    public <T> Gauge<T> registerGauge(String s, String s1, Gauge<T> gauge) {
                        return null;
                    }

                    @Override
                    public Meter createMeter(String s, String s1, String s2) {
                        return null;
                    }

                    @Override
                    public Histogram createHistogram(String s, String s1, boolean b) {
                        return null;
                    }
                })
                .build();

        server.start();

    }
}
