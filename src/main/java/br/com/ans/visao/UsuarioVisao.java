package br.com.ans.visao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ans.dao.PerfilService;
import br.com.ans.model.Perfil;
import br.com.ans.model.Usuario;
import br.com.ans.service.UsuarioService;
import enumerations.FuncionalidadeEnum;

//@RequestScoped
//@SessionScoped

@javax.faces.view.ViewScoped
@Named
public class UsuarioVisao implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Usuario> usuarios;

	@Inject
	private Usuario usuario;

	@Inject
	private PerfilService perfilService;

	private List<Perfil> perfis;

	@Inject
	private UsuarioService usuarioService;

	private Perfil perfil;

	private Long perfilSelecionado;

	@Inject
	private AutenticadorVisao usuarioLogado;

	private String email;

	HashMap<Long, String> listaPerfil;

	@Inject
	MenuVisao menuVisao;

	public UsuarioVisao() {
		this.setUsuarios(new ArrayList<Usuario>());
		this.setUsuario(usuario);
	}

	@PostConstruct
	public void init() {
		this.carregarListaPerfis();
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario getUsuario() {
		if (usuario == null) {
			return new Usuario();
		}
		return usuario;

	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public AutenticadorVisao getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(AutenticadorVisao usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public UsuarioService getUsuarioService() {
		return usuarioService;
	}

	public void setUsuarioService(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	public PerfilService getPerfilService() {
		return perfilService;
	}

	public void setPerfilService(PerfilService perfilService) {
		this.perfilService = perfilService;
	}

	public List<Perfil> getPerfis() {
		return perfis;
	}

	public void setPerfis(List<Perfil> perfis) {
		this.perfis = perfis;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void carregarListaPerfis() {

		this.setPerfis(getPerfilService().obterPerfil());

		HashMap<Long, String> mapaPerfil = new HashMap<Long, String>();
		/// mapaPerfil.put(0L, "Selecionar");

		for (Perfil perfil : perfis) {
			mapaPerfil.put(perfil.getCodigoPerfil(), perfil.getNomePerfil());
		}
		this.setListaPerfil(mapaPerfil);
	}

	public Long getPerfilSelecionado() {
		return perfilSelecionado;
	}

	public void setPerfilSelecionado(Long perfilSelecionado) {
		this.perfilSelecionado = (Long) perfilSelecionado;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public void consultarUsuario() {
		if (menuVisao.acessarFuncionalidade(FuncionalidadeEnum.CONSULTAUSUARIO)) {
			consultarUsuarioPorNome();
		}
	}

	public void consultarUsuarioPorNome() {
		if (usuario == null) {
			setUsuarios(usuarioService.obterUsuarioPorNome(""));
		} else {
			setUsuarios(usuarioService.obterUsuarioPorNome(usuario.getNomeUsuario()));
		}
	}

	public void cadastrar() throws Exception {
		if (menuVisao.acessarFuncionalidade(FuncionalidadeEnum.CADASTROUSUARIO)) {
			usuario.setPerfil(getPerfilService().obterPerfilPorCodigo(this.getPerfilSelecionado()));

			if (getUsuarioService().validarCamposCadastro(usuario)) {
				// Atribui usuário logado!!!
				this.getUsuario().setCodigoUsuarioCadastro(this.getUsuarioLogado().getUsuario().getCodigoUsuario());
				this.getUsuario().setDataCadastro(new Date());
				this.getUsuario().setSituacaoUsuario(1L);
				getUsuarioService().cadastrarUsuario(usuario);
				usuario = new Usuario();
				// this.setPerfis(getPerfilService().obterPerfil());
			}
		}
	}

	public void ativarInativar(Usuario usuario) {
		if (menuVisao.acessarFuncionalidade(FuncionalidadeEnum.ATIVARINATIVARUSUARIO)) {
			usuarioService.ativarInativar(usuario);
			this.consultarUsuarioPorNome();
		}
	}

	public void editarUsuario(Usuario usuarioEdicao) {

		if (menuVisao.acessarFuncionalidade(FuncionalidadeEnum.ALTERAUSUARIO)) {
			this.usuario = usuarioEdicao;
			this.setPerfilSelecionado(this.usuario.getPerfil().getCodigoPerfil());
			/* Guarda o objeto usuário na sessão flash. */
			///FacesContext.getCurrentInstance().getExternalContext().getFlash().put("usuario", usuarioEdicao);
		}
	}

//	public void aplicacao() {
//		acessar(FuncionalidadeEnum.APLICACAO);
//		//return getPaginaAtual();
//	}

//	public String acessarFuncionalidade(FuncionalidadeEnum funcionalidadeEnum) {
//		return menuVisao.acessar(usuarioLogado.getUsuario(), funcionalidadeEnum);
//	}

//	public void acessar(FuncionalidadeEnum funcionalidadeEnum) {
//		setPaginaAtual(menuVisao.acessar(usuarioLogado.getUsuario(), funcionalidadeEnum).concat(".xhtml"));
//		getPaginaAtual();
//	}

	public void alterar() throws Exception {

		// Seta o novo perfil da tela de alteração antes da validação dos campos. Obs:
		// isso foi necessário para que o perfil já cadastrado não seja validado e sim o
		// da alteração.
		this.getUsuario().setPerfil(getPerfilService().obterPerfilPorCodigo(this.getPerfilSelecionado()));

		if (getUsuarioService().validarCamposCadastro(this.getUsuario())) {
			getUsuarioService().cadastrarUsuario(this.getUsuario());
		}
	}

	/*
	 * public String getDescricaoSituacao() {
	 * 
	 * if (this.usuario.getUsuarioAtivo().equals("S")){
	 * this.setDescricaoSituacao("Ativo"); }else{
	 * this.setDescricaoSituacao("Inativo"); }; return descricaoSituacao; }
	 */

	/*
	 * public void setDescricaoSituacao(String descricaoSituacao) {
	 * this.descricaoSituacao = descricaoSituacao; }
	 */

	public HashMap<Long, String> getListaPerfil() {
		return listaPerfil;
	}

	public void setListaPerfil(HashMap<Long, String> listaPerfil) {
		this.listaPerfil = listaPerfil;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
