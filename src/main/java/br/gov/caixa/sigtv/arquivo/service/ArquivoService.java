package br.gov.caixa.sigtv.arquivo.service;

import br.gov.caixa.sigtv.arquivo.controller.ArquivoController;
import br.gov.caixa.sigtv.arquivo.model.Arquivo;
import br.gov.caixa.sigtv.arquivo.repository.IArquivoRepository;
import br.gov.caixa.sigtv.util.storage.MinioService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

@Transactional
@Service
@RequiredArgsConstructor
public class ArquivoService {

  private static final String ARQUIVO_NAO_ENCONTRADO = "br.gov.caixa.sigtv.arquivo.service.ArquivoService.aquivo_nao_encontrado";

  private final MinioService minioService;
  private final IArquivoRepository arquivoRepository;
  private final MessageSource messages;

  public List<Arquivo> save(Collection<Arquivo> arquivos) {

    return arquivos.stream().map(this::save).collect(Collectors.toList());

  }

  public Arquivo save(Arquivo arquivo) {
    Arquivo arquivoSalvo = this.arquivoRepository.save(arquivo);
    this.minioService.sendFile(arquivo);

    this.attachFile(arquivoSalvo);

    return arquivoSalvo;
  }

  public Optional<Arquivo> findByUuid(String uuid) {
    Optional<Arquivo> arquivoOpt = this.arquivoRepository.findOneByUuid(uuid);
    arquivoOpt.ifPresent(this::attachFile);
    return arquivoOpt;
  }

  public Optional<Arquivo> findById(Long id) {
    Optional<Arquivo> arquivoOpt = this.arquivoRepository.findById(id);
    arquivoOpt.ifPresent(this::attachFile);
    return arquivoOpt;
  }

  private void attachFile(Arquivo a) {
    Optional<InputStream> inputStreamOpt = this.minioService.findByUuid(a.getUuid());

    inputStreamOpt.map(inputStream -> {

      a.setInputStream(inputStream);
      return inputStream;

    }).orElseThrow(() -> new RuntimeException(
        messages.getMessage(ARQUIVO_NAO_ENCONTRADO, null, Locale.getDefault()))
    );
  }

  public void deleteById(Long id) {
    Optional<Arquivo> arquivoOpt = this.arquivoRepository.findById(id);
    arquivoOpt.ifPresent(a -> {
      this.minioService.deleteByUuid(a.getUuid());
      this.arquivoRepository.delete(a);
    });
  }

}
