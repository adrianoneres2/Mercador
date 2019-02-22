package enumerations;

public enum FuncionalidadeEnum {
	APLICACAO(0, "/aplicacao"),
	COMPRA(1, ""),
	VENDA(2, ""),
	CADASTROUSUARIO(3, "/visao/usuario/cadastroUsuario"),
	CADASTROPRODUTO(4, "/visao/produto/cadastroProduto"),
	CADASTROCLIENTE(5, ""),
	CONSULTAUSUARIO(6, "/visao/usuario/consultaUsuario"),
	ALTERAUSUARIO(7, "/visao/usuario/alterarUsuario"),
	ATIVARINATIVARUSUARIO(8, ""),
	CADASTROPERFIL(9, "/visao/perfil/cadastroPerfil"),
	MANTERPERFIL(10, "/visao/perfil/manterPerfil"),
	CONSULTAPRODUTO(11, "/visao/produto/consultaProduto"),
	ATIVARINATIVARPRODUTO(12, "");

	private int codigo;
	private String url;
	
	FuncionalidadeEnum(int codigo, String url){
		this.codigo = codigo;
		this.url = url;	
	}

	public int getCodigo() {
		return codigo;
	}

	public String getUrl() {
		return url;
	}
}
