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
	
	String descricaoSituacao;
	
	private String email;
	
	HashMap<Long,String> listaPerfil;
	
	@Inject
	MenuVisao menuVisao;
	
	private String retorno;
	
	public UsuarioVisao() {
		this.setUsuarios(new ArrayList<Usuario>());
	}

	
	@PostConstruct
	public void init(){
		this.carregarListaPerfis();
		
		///Entra quando a opção for alterar usuário
		if(usuarioLogado.getCodigoAuxiliar()!= null){
			this.setUsuario(getUsuarioService().obterUsuarioPorCodigo(usuarioLogado.getCodigoAuxiliar()));
			usuarioLogado.setCodigoAuxiliar(null);
		}
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
		
		retorno = acessarFuncionalidade(FuncionalidadeEnum.ATIVARINATIVARUSUARIO); 
		
		if(retorno != null){
			usuarioService.ativarInativar(usuario);
			this.consultarUsuarioPorNome();
		}		
	}
	
	public String alterar(){
		
		retorno = menuVisao.acessar(usuarioLogado.getUsuario(), FuncionalidadeEnum.ALTERAUSUARIO);
		
		if (retorno != ""){
			return retorno;
		}else{
			usuarioLogado.setCodigoAuxiliar(null);
			return "";
		}
	}

	public String acessarFuncionalidade(FuncionalidadeEnum funcionalidadeEnum){
		return menuVisao.acessar(usuarioLogado.getUsuario(), funcionalidadeEnum);
	}
	
	public void alterarUsuario() throws Exception {
		/***Seta o novo perfil da tela de alteração antes da validação dos campos. 
		 *** Obs: isso foi necessário para que o perfil já cadastrado não seja validado e sim o da alteração. 
		 ***/
		this.getUsuario().setPerfil(getPerfilService().obterPerfilPorCodigo(this.getPerfilSelecionado()));
		
		if (getUsuarioService().validarCamposCadastro(this.getUsuario())){			
		   //this.getUsuario().setPerfil(getPerfilService().obterPerfilPorCodigo(this.getPerfilSelecionado()));
		   getUsuarioService().cadastrarUsuario(this.getUsuario());
		}
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	
}
