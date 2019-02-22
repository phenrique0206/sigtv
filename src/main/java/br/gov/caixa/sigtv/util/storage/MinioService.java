package br.gov.caixa.sigtv.util.storage;

import br.gov.caixa.sigtv.arquivo.model.Arquivo;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MinioService {

  private final Logger logger;

  @Value("${br.gov.caixa.sigtv.minio.bucket}")
  private String BUCKET;

  private final MinioClient client;

  public void sendFile(Arquivo arquivo) {

    Map<String, String> metadata = new HashMap<>();

    try {
      this.client.putObject(
          BUCKET,
          arquivo.getUuid(),
          arquivo.getInputStream(),
          arquivo.getTamanho(),
          metadata
      );
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public Optional<InputStream> findByUuid(String uuid) {
    try {
      return Optional.of(this.client.getObject(BUCKET, uuid));
    } catch (Exception e) {
      this.logger.warn(e.getMessage());
      return Optional.empty();
    }
  }

  public void deleteByUuid(String uuid) {
    try {
      this.client.removeObject(BUCKET, uuid);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
