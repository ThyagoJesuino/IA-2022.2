import java.util.ArrayList;
import java.util.List;

enum Position {RIGHT, LEFT}

public class Estado {

	private int canibaisEsquerda;
	private int missionariosEsquerda;
	private int canibaisDireita;
	private int missionariosDireita;
	private Position barco;

	private Estado estadoParent;

	public Estado(int canibaisEsquerda, int missionariosEsquerda, Position barco,
			int canibaisDireita, int missionariosDireita) {
		this.canibaisEsquerda = canibaisEsquerda;
		this.missionariosEsquerda = missionariosEsquerda;
		this.barco = barco;
		this.canibaisDireita = canibaisDireita;
		this.missionariosDireita = missionariosDireita;
	}

	public boolean inicio() {
		return canibaisEsquerda == 0 && missionariosEsquerda == 0;
	}

	public boolean valido() {
		if (missionariosEsquerda >= 0 && missionariosDireita >= 0 && canibaisEsquerda >= 0 && canibaisDireita >= 0
	               && (missionariosEsquerda == 0 || missionariosEsquerda >= canibaisEsquerda)
	               && (missionariosDireita == 0 || missionariosDireita >= canibaisDireita)) {
			return true;
		}
		return false;
	}

	public List<Estado> gerarSucessor() {
		List<Estado> sucessores = new ArrayList<Estado>();
		if (barco == Position.LEFT) {
			testaeadd(sucessores, new Estado(canibaisEsquerda, missionariosEsquerda - 2, Position.RIGHT,
					canibaisDireita, missionariosDireita + 2)); // Dois missionarios atravessa da esquerda para direita.
			testaeadd(sucessores, new Estado(canibaisEsquerda - 2, missionariosEsquerda, Position.RIGHT,
					canibaisDireita + 2, missionariosDireita)); // Dois canibais atravessa da esquerda para direita.
			testaeadd(sucessores, new Estado(canibaisEsquerda - 1, missionariosEsquerda - 1, Position.RIGHT,
					canibaisDireita + 1, missionariosDireita + 1)); // Um missionario e um canibal atravessa da esquerda pra direita.
			testaeadd(sucessores, new Estado(canibaisEsquerda, missionariosEsquerda - 1, Position.RIGHT,
					canibaisDireita, missionariosDireita + 1)); // Um missionario atravessa da esquerda para direita.
			testaeadd(sucessores, new Estado(canibaisEsquerda - 1, missionariosEsquerda, Position.RIGHT,
					canibaisDireita + 1, missionariosDireita)); // Um canibal atravessa da esquerda pra direita.
		} else {
			testaeadd(sucessores, new Estado(canibaisEsquerda, missionariosEsquerda + 2, Position.LEFT,
					canibaisDireita, missionariosDireita - 2)); // Dois missionarios atravessa da direita para esquerda.
			testaeadd(sucessores, new Estado(canibaisEsquerda + 2, missionariosEsquerda, Position.LEFT,
					canibaisDireita - 2, missionariosDireita)); // Dois canibais atravessa da direita para esquerda.
			testaeadd(sucessores, new Estado(canibaisEsquerda + 1, missionariosEsquerda + 1, Position.LEFT,
					canibaisDireita - 1, missionariosDireita - 1)); // Um missionario e um canibal atravessa da direita pra esquerda.
			testaeadd(sucessores, new Estado(canibaisEsquerda, missionariosEsquerda + 1, Position.LEFT,
					canibaisDireita, missionariosDireita - 1)); // Um missionario atravessa da esquerda para direita.
			testaeadd(sucessores, new Estado(canibaisEsquerda + 1, missionariosEsquerda, Position.LEFT,
					canibaisDireita - 1, missionariosDireita)); // Um canibal atravessa da esquerda pra direita.
		}
		return sucessores;
	}

	private void testaeadd(List<Estado> sucessores, Estado newState) {
		if (newState.valido()) {
			newState.setestadoParent(this);
			sucessores.add(newState);
		}
	}

	public int getcanibaisEsquerda() {
		return canibaisEsquerda;
	}

	public void setcanibaisEsquerda(int canibaisEsquerda) {
		this.canibaisEsquerda = canibaisEsquerda;
	}

	public int getmissionariosEsquerda() {
		return missionariosEsquerda;
	}

	public void setmissionariosEsquerda(int missionariosEsquerda) {
		this.missionariosEsquerda = missionariosEsquerda;
	}

	public int getcanibaisDireita() {
		return canibaisDireita;
	}

	public void setcanibaisDireita(int canibaisDireita) {
		this.canibaisDireita = canibaisDireita;
	}

	public int getmissionariosDireita() {
		return missionariosDireita;
	}

	public void setmissionariosDireita(int missionariosDireita) {
		this.missionariosDireita = missionariosDireita;
	}

	public void vaiParaEsquerda() {
		barco = Position.LEFT;
	}

	public void vaiParaDireita() {
		barco = Position.RIGHT;
	}

	public boolean estaNaEsquerda() {
		return barco == Position.LEFT;
	}

	public boolean estaNaDireita() {
		return barco == Position.RIGHT;
	}

	public Estado getestadoParent() {
		return estadoParent;
	}

	public void setestadoParent(Estado estadoParent) {
		this.estadoParent = estadoParent;
	}

	@Override
	public String toString() {
		if (barco == Position.LEFT) {
			return "(" + canibaisEsquerda + "," + missionariosEsquerda + ",L,"
        			+ canibaisDireita + "," + missionariosDireita + ")";
		} else {
			return "(" + canibaisEsquerda + "," + missionariosEsquerda + ",R,"
        			+ canibaisDireita + "," + missionariosDireita + ")";
		}
     }

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Estado)) {
			return false;
		}
		Estado s = (Estado) obj;
        return (s.canibaisEsquerda == canibaisEsquerda && s.missionariosEsquerda == missionariosEsquerda
        		&& s.barco == barco && s.canibaisDireita == canibaisDireita
        		&& s.missionariosDireita == missionariosDireita);
	}
}