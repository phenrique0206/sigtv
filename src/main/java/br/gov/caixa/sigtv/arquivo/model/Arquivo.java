package br.gov.caixa.sigtv.arquivo.model;

import br.gov.caixa.sigtv.util.validation.annotations.NullOrNotBlank;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.core.Relation;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "GTVTB011_ARQUIVO")
@ToString
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Relation(collectionRelation = "arquivos", value = "arquivo")
public class Arquivo {

  @Id
  @SequenceGenerator(name = "GTVSQ011_ARQUIVO", sequenceName = "GTVSQ011_ARQUIVO")
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "GTVSQ011_ARQUIVO")
  @Column(name = "NU_ARQUIVO")
  private Long id;

  @Size(min = 1, max = 512)
  @NullOrNotBlank()
  @NotNull
  @Column(name = "NO_NOME")
  private String nome;

  @Size(min = 1, max = 512)
  @NullOrNotBlank()
  @NotNull
  @Column(name = "NO_CONTENT_TYPE")
  private String contentType;

  @Size(min = 32, max = 32)
  @NullOrNotBlank()
  @NotNull
  @Column(name = "CO_UUID")
  private String uuid;

  @NotNull
  @Column(name = "NU_TAMANHO")
  private Long tamanho;

  @JsonIgnore
  @Transient
  private InputStream inputStream;

  public Arquivo() {}

  public Arquivo(MultipartFile file) {

    this.nome = file.getOriginalFilename();
    this.tamanho = file.getSize();
    this.uuid = UUID.randomUUID().toString();
    this.contentType = file.getContentType();

    try {
      this.inputStream = file.getInputStream();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}
