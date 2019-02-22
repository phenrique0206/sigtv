package br.gov.caixa.sigtv.arquivo.controller;

import br.gov.caixa.sigtv.arquivo.model.Arquivo;
import br.gov.caixa.sigtv.arquivo.service.ArquivoService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RepositoryRestController
public class ArquivoController {

  private final Logger logger;
  private final ArquivoService arquivoService;

  @PostMapping("/arquivo")
  public ResponseEntity<?> salvar(@RequestParam("arquivos") MultipartFile[] multipartFiles) {

    List<Arquivo> arquivos = Arrays.stream(multipartFiles)
        .map(Arquivo::new)
        .collect(Collectors.toList());

    List<Arquivo> arquivosSalvos = this.arquivoService.save(arquivos);
    Resources<Resource<Arquivo>> arquivosResource = new Resources<>(
        arquivosSalvos.stream().map(Resource::new).collect(Collectors.toList())
    );

    return ResponseEntity.ok(arquivosResource);

  }

  @DeleteMapping("/arquivo/{id}")
  public ResponseEntity<?> deleteByUuid(@PathVariable Long id) {
    this.arquivoService.deleteById(id);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/arquivo/download/{uuid}")
  public void findByUuid(
      @PathVariable String uuid,
      HttpServletResponse response
  ) throws IOException {
    Optional<Arquivo> arquivoOptional = this.arquivoService.findByUuid(uuid);

    if (arquivoOptional.isPresent()) {

      Arquivo arquivo = arquivoOptional.get();

      response.setContentType(MediaType.APPLICATION_OCTET_STREAM.toString());
      response.setHeader(
          HttpHeaders.CONTENT_DISPOSITION,
          String.format("attachment; filename=\"%s\"", arquivo.getNome())
      );
      response.setStatus(HttpStatus.OK.value());

      InputStream stream = arquivo.getInputStream();
      byte[] streamBytes = new byte[stream.available()];
      stream.read(streamBytes);

      response.getOutputStream().write(streamBytes);
      response.flushBuffer();
      stream.close();

    } else {
      response.sendError(HttpStatus.NOT_FOUND.value());
    }
  }

}
