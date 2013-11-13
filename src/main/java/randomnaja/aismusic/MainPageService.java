package randomnaja.aismusic;

import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.assets.AssetsBundle;
import com.yammer.dropwizard.client.HttpClientBuilder;
import com.yammer.dropwizard.client.HttpClientConfiguration;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.util.Duration;
import com.yammer.dropwizard.views.ViewBundle;
import com.yammer.metrics.core.HealthCheck;
import org.apache.http.client.HttpClient;

public class MainPageService extends Service<MainConfiguration> {

    public static void main(String[] args) throws Exception {
        new MainPageService().run(new String[]{"server", "toneconfig.yaml"});
    }

    @Override
    public void initialize(Bootstrap<MainConfiguration> bootstrap) {
        bootstrap.setName("My AIS Music");
        bootstrap.addBundle(new ViewBundle());
        bootstrap.addBundle(new AssetsBundle());
    }

    @Override
    public void run(MainConfiguration configuration, Environment environment) throws Exception {
        environment.addHealthCheck(new HealthCheck("Just always up") {
            @Override
            protected Result check() throws Exception {
                //as a simple healthcheck
                return Result.healthy();
            }
        });

        HttpClientConfiguration config = new HttpClientConfiguration();
        config.setTimeout(Duration.seconds(10));
        final HttpClient httpClient = new HttpClientBuilder().using(config).build();
        ExternalServiceResource externalHttp = new ExternalServiceResource(httpClient, configuration);
        environment.addResource(externalHttp);
        environment.addResource(new MainPageResource(externalHttp));
        environment.addResource(new DownloadSongResource(externalHttp));
    }
}
