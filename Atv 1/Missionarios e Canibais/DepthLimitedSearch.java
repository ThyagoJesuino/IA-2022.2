import java.util.List;
//Busca de Profundidade Limitada
public class DepthLimitedSearch{

	public Estado exec(Estado estadoInicial) {
		int limite = 20;
		return DLSrecursivo(estadoInicial, limite);
	}

	private Estado DLSrecursivo(Estado estado, int limite) {
		if (estado.inicio()) {
			return estado;
		} else if (limite == 0) {
			return null;
		} else {
			List<Estado> sucessores = estado.gerarSucessor();
			for (Estado filho : sucessores) {
				Estado resultado = DLSrecursivo(filho, limite - 1);
				if (null != resultado) {
					return resultado;
				}
			}
			return null;
		}
	}
}