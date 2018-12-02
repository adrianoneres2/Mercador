package br.com.ans.visao;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import util.ObjetoSessao;
import br.com.ans.dao.PerfilService;
import br.com.ans.model.Perfil;
import br.com.ans.model.Usuario;
import br.com.ans.service.UsuarioService;
import enumerations.FuncionalidadeEnum;

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
	
	///String descricaoSituacao;
	
	private String email;
	
	HashMap<Long,String> listaPerfil;
	
	@Inject
	MenuVisao menuVisao;
	
	private ObjetoSessao objetoSessao = new ObjetoSessao();
	
	private Long identificador;

	public UsuarioVisao() {
		this.setUsuarios(new ArrayList<Usuario>());
	}

	
	@PostConstruct
	public void init(){
		this.carregarListaPerfis();
		
		///Entra quando um atributo foi passado como parametro exemplo: codigo do usuário para alteração usuário.
		if(objetoSessao != null){
			this.setUsuario(getUsuarioService().obterUsuarioPorCodigo(objetoSessao.getAtributo("codigoUsuario")));
			objetoSessao.removeAtributo("codigoUsuario");
			identificador = null;
		}
	}
	
	public void setIdentificador(Long identificador) {
		this.identificador = identificador;
		objetoSessao.setAtributo(identificador, "codigoUsuario");
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	public Usuario getUsuario() {
		if(usuario == null){
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
		if(usuario == null){
			setUsuarios(usuarioService.obterUsuarioPorNome(""));
		}else{
			setUsuarios(usuarioService.obterUsuarioPorNome(usuario.getNomeUsuario()));
		}
	}
	
	public String cadastrar() throws Exception {
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
	
	public void ativarInativar(Usuario usuario){ 
		if(acessarFuncionalidade(FuncionalidadeEnum.ATIVARINATIVARUSUARIO) != null){
			usuarioService.ativarInativar(usuario);
			this.consultarUsuarioPorNome();
		}		
	}
	
/*	public String alterar(){
		return menuVisao.acessar(usuarioLogado.getUsuario(), FuncionalidadeEnum.ALTERAUSUARIO);
	}*/

	public String acessarFuncionalidade(FuncionalidadeEnum funcionalidadeEnum){
		return menuVisao.acessar(usuarioLogado.getUsuario(), funcionalidadeEnum);
	}
	
	public void alterarUsuario() throws Exception {
		/***Seta o novo perfil da tela de alteração antes da validação dos campos. 
		 *** Obs: isso foi necessário para que o perfil já cadastrado não seja validado e sim o da alteração. 
		 ***/
		this.getUsuario().setPerfil(getPerfilService().obterPerfilPorCodigo(this.getPerfilSelecionado()));
		
		if (getUsuarioService().validarCamposCadastro(this.getUsuario())){			
		   getUsuarioService().cadastrarUsuario(this.getUsuario());
		}
	}

/*	public String getDescricaoSituacao() {
		
		if (this.usuario.getUsuarioAtivo().equals("S")){
			this.setDescricaoSituacao("Ativo");
		}else{
			this.setDescricaoSituacao("Inativo");
		};
		return descricaoSituacao;
	}
	*/

/*	public void setDescricaoSituacao(String descricaoSituacao) {
		this.descricaoSituacao = descricaoSituacao;
	}
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
