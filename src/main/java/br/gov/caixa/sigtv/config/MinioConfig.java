package br.gov.caixa.sigtv.config;

import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class MinioConfig {

  @Value("${br.gov.caixa.sigtv.minio.url}")
  private String URL;

  @Value("${br.gov.caixa.sigtv.minio.access_key}")
  private String ACCESS_KEY;

  @Value("${br.gov.caixa.sigtv.minio.secret_key}")
  private String SECRET_KEY;

  @Value("${br.gov.caixa.sigtv.minio.bucket}")
  private String BUCKET;

  @Bean
  public MinioClient getMinioClient() {

    try {
      MinioClient client = new MinioClient(URL, ACCESS_KEY, SECRET_KEY);

      if (!client.bucketExists(BUCKET)) {
        client.makeBucket(BUCKET);
      }

      return client;

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
