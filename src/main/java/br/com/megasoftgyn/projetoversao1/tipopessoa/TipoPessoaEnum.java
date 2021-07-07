package br.com.megasoftgyn.projetoversao1.tipopessoa;

public enum TipoPessoaEnum {
	FISICA(1), JURIDICA(2), ESPECIAL(3), OUTROS(4);
	private int valor;
	
	TipoPessoaEnum(int valor) {
		this.valor= valor;
	}
	
	public int getValor() {
		return this.valor;
	}
}
