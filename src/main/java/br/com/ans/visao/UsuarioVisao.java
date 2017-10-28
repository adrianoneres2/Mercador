package br.com.ans.visao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.ans.dao.PerfilService;
import br.com.ans.model.Perfil;
import br.com.ans.model.Usuario;
import br.com.ans.service.UsuarioService;

//@RequestScoped
//@SessionScoped

@javax.faces.view.ViewScoped
@Named
public class UsuarioVisao implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<Usuario> usuarios;
	
	@Inject
	private Usuario usuario;
	
	@Inject
	private PerfilService perfilService;
	
	private List<Perfil> perfis;
	 
	@Inject
	private UsuarioService usuarioService;
	
	@Inject
	private AutenticadorVisao usuarioLogado;
	
	private Perfil perfil;
	
	private Long perfilSelecionado;
	
	boolean alterarRegistro;
	
	String descricaoSituacao;
	
	HashMap<Long,String> listaPerfil;
	
	boolean mostrarModalAlteraUsuario = true;
	
	public UsuarioVisao() {
		this.setUsuarios(new ArrayList<Usuario>());
	}	
	
	@PostConstruct
	public void init(){
		this.carregarListaPerfis();
		//this.setPerfis(getPerfilService().obterPerfil());
		System.out.println("NOme "+this.usuario.getNomeUsuario());
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	public Usuario getUsuario() {
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
	
	public void carregarListaPerfis(){
		
		this.setPerfis(getPerfilService().obterPerfil());
			
			HashMap<Long, String> mapaPerfil = new HashMap<Long, String>();
			///mapaPerfil.put(0L, "Selecionar");
			
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

	public void consultarUsuarioPorNome(){
		setUsuarios(usuarioService.obterUsuarioPorNome(usuario.getNomeUsuario()));
	}
	
	public String inserirNovo() throws Exception {

		this.getUsuario().setPerfil(getPerfilService().obterPerfilPorCodigo(this.getPerfilSelecionado()));
		
		if (getUsuarioService().validarCamposCadastro(this.getUsuario())){			
			
		   //Atribui usuário logado!!!	
		   this.getUsuario().setCodigoUsuarioCadastro(this.getUsuarioLogado().getUsuario().getCodigoUsuario());	
		   this.getUsuario().setDataCadastro(new Date());
		   this.getUsuario().setUsuarioAtivo("S");
		   getUsuarioService().cadastrarUsuario(this.getUsuario());
		   //this.setPerfis(getPerfilService().obterPerfil());
		}
		return null;
	}
	
	public void excluir(Usuario usuario){
		
		System.out.println(usuario.getNomeUsuario());
		usuarioService.excluir(usuario);
		this.consultarUsuarioPorNome();
		
	}
	
	public void alterar(Usuario usuario){
		this.setUsuario(usuario);	 
	}

	
	public void alterarUsuario() throws Exception {
		
		/*Seta o novo perfil da tela de alteração antes da validação dos campos. 
		 * Obs: isso foi necessário para que o perfil já cadastrado não seja validado e sim o da alteração. */
		this.getUsuario().setPerfil(getPerfilService().obterPerfilPorCodigo(this.getPerfilSelecionado()));
		
		if (getUsuarioService().validarCamposCadastro(this.getUsuario())){			
		   //this.getUsuario().setPerfil(getPerfilService().obterPerfilPorCodigo(this.getPerfilSelecionado()));
		   getUsuarioService().cadastrarUsuario(this.getUsuario());
		}
	}
	
	public boolean isAlterarRegistro() {
		return alterarRegistro;
	}

	public void setAlterarRegistro(boolean alterarRegistro) {
		this.alterarRegistro = alterarRegistro;
	}

	public String getDescricaoSituacao() {
		
		if (this.usuario.getUsuarioAtivo().equals("S")){
			this.setDescricaoSituacao("Ativo");
		}else{
			this.setDescricaoSituacao("Inativo");
		};
		return descricaoSituacao;
	}

	public void setDescricaoSituacao(String descricaoSituacao) {
		this.descricaoSituacao = descricaoSituacao;
	}

	public HashMap<Long, String> getListaPerfil() {
		return listaPerfil;
	}

	public void setListaPerfil(HashMap<Long, String> listaPerfil) {		
			
		this.listaPerfil = listaPerfil;
	}

	public boolean isMostrarModalAlteraUsuario() {
		return mostrarModalAlteraUsuario;
	}

	public void setMostrarModalAlteraUsuario(boolean mostrarModalAlteraUsuario) {
		this.mostrarModalAlteraUsuario = mostrarModalAlteraUsuario;
	}
	
	
	
}
