package br.com.clay.enums;

public enum TipoSexo {
	
	M("Masculino"),
	F("Feminino");
	
	private String descTipoSexo;
	
	private TipoSexo(String descTipoSexo) {
		this.descTipoSexo = descTipoSexo;
	}

	/**
	 * @return
	 */
	public String getDescTipoSexo() {
		return descTipoSexo;
	}
	
}
