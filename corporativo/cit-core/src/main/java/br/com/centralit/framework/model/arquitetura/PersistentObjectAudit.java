package br.com.centralit.framework.model.arquitetura;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.centralit.framework.json.JsonCalendarSerializer;
import br.com.centralit.framework.json.Views;
import br.com.centralit.framework.model.Usuario;
import br.com.centralit.framework.util.UtilDate;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@MappedSuperclass
public abstract class PersistentObjectAudit extends PersistentObject {
	@Column(name = "dataEdicao", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	protected Calendar dataEdicao;

	@Column(name="dataCriacao", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = JsonCalendarSerializer.class)
	@JsonView({ Views.GenericView.class })
	protected Calendar dataCriacao;

	@Version
	@JsonView(Views.GenericView.class)
	protected Long version;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "autor_id", updatable = false)
	@JsonView({Views.MapaOrganizacionalListView.class, Views.EstruturaOrganizacionalAutoCompleteView.class})
	protected Usuario autor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "editor_id")
	protected Usuario editor;

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

	public Usuario getEditor() {
		return editor;
	}

	public void setEditor(Usuario editor) {
		this.editor = editor;
	}

	public Calendar getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Calendar dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Calendar getDataEdicao() {
		return dataEdicao;
	}

	public void setDataEdicao(Calendar dataEdicao) {
		this.dataEdicao = dataEdicao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	@PrePersist
	protected void onPrePersist() {
		if(this.dataCriacao == null){
			this.setDataCriacao(UtilDate.getCalendarDaDataAtual());

			Usuario usuario = null;
			if (SecurityContextHolder.getContext().getAuthentication() != null) {
				if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof Usuario) {
					usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				}
			}
			this.setAutor(usuario);
		}

		populateAudit();
	}

	@PreUpdate
	protected void onPreUpdate() {
		populateAudit();
	}

	protected void populateAudit() {
		this.setDataEdicao(UtilDate.getCalendarDaDataAtual());

		Usuario usuario = null;
		if (SecurityContextHolder.getContext().getAuthentication() != null) {
			if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof Usuario) {
				usuario = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			}
		}
		this.setEditor(usuario);
	}

	private static final long serialVersionUID = 1L;
}