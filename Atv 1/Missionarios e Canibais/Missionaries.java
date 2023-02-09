import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Missionaries{
	public static void main(String[] args) {
		System.out.println("==== Missionarios e Canibais ====");
		System.out.println("Escolha sua opçao: ");
		System.out.println("\t 1. DFS");
		System.out.println("\t 0. Sair");
		System.out.print("\nEscolha a opçao e aperte ENTER: ");

		String opcaoStr = null;
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			opcaoStr = in.readLine();
		} catch (IOException e) {
			System.out.println("[ERROR] falha na leitura da opcao.");
			e.printStackTrace();
		}

		int opcao = Integer.parseInt(opcaoStr);
		Estado estadoInicial = new Estado (3, 3, Position.LEFT, 0, 0);
		switch(opcao) {
		case 1:
            executarDLS(estadoInicial);
            break;
		case 0:
			System.out.println("Algoritmo finalizado!");
			return;
		default:
			System.out.println("[ERROR] Opcao invalida.");
		}
	}
	private static void executarDLS(Estado estadoInicial) {
		DepthLimitedSearch procura = new DepthLimitedSearch();
		Estado solucao = procura.exec(estadoInicial);
		printsolucao(solucao);
	}

	private static void printsolucao(Estado solucao) {
		if (null == solucao) {
			System.out.print("\nNenhuma solução encontrada.");
		} else {
			System.out.println("\nSolução (canibaisEsquerda,missionariosEsquerda,barco,canibaisDireita,missionariosDireita): ");
			List<Estado> path = new ArrayList<Estado>();
			Estado estado = solucao;
			while(null!=estado) {
				path.add(estado);
				estado = estado.getestadoParent();
			}

			int profundidade = path.size() - 1;
			for (int i = profundidade; i >= 0; i--) {
				estado = path.get(i);
				if (estado.inicio()) {
					System.out.print(estado.toString());
				} else {
					System.out.print(estado.toString() + " -> \n");
				}
			}
			System.out.println("\nprofundidade: " + profundidade);
		}
	}
}