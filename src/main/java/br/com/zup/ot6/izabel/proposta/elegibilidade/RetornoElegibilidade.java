package br.com.zup.ot6.izabel.proposta.elegibilidade;

public enum RetornoElegibilidade {
	
	COM_RESTRICAO(Elegibilidade.NAO_ELEGIVEL),
	SEM_RESTRICAO(Elegibilidade.ELEGIVEL);
	
	private Elegibilidade elegibilidade;
	
	RetornoElegibilidade(Elegibilidade elegibilidade) {
		this.elegibilidade = elegibilidade;
	}

	public Elegibilidade getElegibilidade() {
		return elegibilidade;
	}

}
