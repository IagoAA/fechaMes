package br.com.centralit.api.service.impl;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.com.centralit.api.dao.AnexoDao;
import br.com.centralit.api.model.Anexo;
import br.com.centralit.api.model.Documento;
import br.com.centralit.api.service.AnexoService;
import br.com.centralit.api.service.DocumentoService;
import br.com.centralit.api.service.DominioService;
import br.com.centralit.framework.model.Dominio;
import br.com.centralit.framework.service.arquitetura.GenericServiceImpl;
import br.com.centralit.framework.util.UtilObjeto;

/**
 * <p>
 * <img src="http://centralit.com.br/images/logo_central.png">
 * </p>
 * 
 * <p>
 * <b>Company: </b> Central IT - Governança Corporativa -
 * </p>
 * 
 * <p>
 * <b>Title: </b>
 * </p>
 * 
 * <p>
 * <b>Description: </b>
 * </p>
 * 
 * @since 07/01/2015 - 11:31:46
 * 
 * @version 1.0.0
 * 
 * @author wilker.machado
 * 
 */
@Service("anexoService")
public class AnexoServiceImpl extends GenericServiceImpl<Anexo, Long> implements AnexoService {

	/** Atributo documentoService. */
	@Autowired
	private DocumentoService documentoService;

	/** Atributo dominioService. */
	@Autowired
	private DominioService dominioService;

	/** Atributo anexoDao. */
	private AnexoDao anexoDao;

	/**
	 * Responsável pela criação de novas instâncias desta classe.
	 * 
	 * @param anexoDao
	 */
	@Autowired
	public AnexoServiceImpl( AnexoDao anexoDao ) {

		this.dao = anexoDao;

		this.anexoDao = anexoDao;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean removeById(Long id) {
	
		Anexo anexo = this.find(id);
		
		return this.remove(anexo);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean remove(Anexo entity) {
		
		entity.setDocumentoInativo(entity.getDocumento());
		
		entity.setDocumento(null);
		
		return super.remove(entity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveAnexo(MultipartFile file, Long idDocumento) {

		Documento documento = this.documentoService.getReference(idDocumento);

		// caso exista o documento salvo feita a vinculação com o anexo(upload) em si
		if (UtilObjeto.isReferencia(documento)) {

			try {

				Anexo anexo = montarEntidadeAnexo(file, documento);

				this.anexoDao.save(anexo);

			} catch (IOException e) {

				// tratar erro
				System.out.println(e.getMessage());
			}
		}

	}

	/**
	 * 
	 * Método responsável por montar entidade anexo que chega do upload
	 * 
	 * @author wilker.machado
	 * 
	 * @param file
	 * @param documento
	 * @return
	 * @throws IOException
	 */
	private Anexo montarEntidadeAnexo(MultipartFile file, Documento documento) throws IOException {

		Anexo anexo = new Anexo();

		anexo.setAnexo(file.getBytes());
		
		anexo.setTamanho(file.getSize());

		anexo.setDocumento(documento);

		String extensao = file.getOriginalFilename().contains(".") ? file.getOriginalFilename().split("\\.")[1] : "";

		anexo.setDescricao(file.getOriginalFilename());

		// verifica se nao existe estensão, se existir e realizada a busca do dominio desse tipo de extenção
		if (StringUtils.isEmpty(extensao.trim())) {

			anexo.setDominioTipoAnexo(this.dominioService.findByChaveAndCodigo(Dominio.TIPO_ANEXO, Dominio.TIPO_ANEXO_XLS_SEM_EXTENSAO_CODIGO));

		} else {

			// repetição para verifica e comparar as extensões que estão presentes no sistema
			for (Dominio dominio : this.dominioService.listarPorChave(Dominio.TIPO_ANEXO)) {

				if (dominio.getNome().equalsIgnoreCase(extensao.trim())) {

					anexo.setDominioTipoAnexo(dominio);

					break;
				}
			}
			
			if(!UtilObjeto.isReferencia(anexo.getDominioTipoAnexo())){
				
				anexo.setDominioTipoAnexo(this.dominioService.findByChaveAndCodigo(Dominio.TIPO_ANEXO, Dominio.TIPO_ANEXO_XLS_SEM_EXTENSAO_CODIGO));
			}
		}

		return anexo;
	}

}
