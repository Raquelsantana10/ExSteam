package View;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class SteamController {

	public SteamController() {
		super();
	}

	public void infos(String path, String nome) throws IOException {
		File dir = new File(path);
		if (dir.exists() && dir.isDirectory()) {
			File arq = new File(path, nome);
			if (arq.exists() && arq.isFile()) {
				FileInputStream fluxo = new FileInputStream(arq);
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				String mes = JOptionPane.showInputDialog("Digite o mês que deseja verificar");
				String ano = JOptionPane.showInputDialog("Digite o ano que deseja verificar");
				double media = Double.parseDouble(JOptionPane.showInputDialog("Digite a média que deseja verificar"));
				while (linha != null) {
					if (linha.contains(mes) && linha.contains(ano)) {
						String[] info = linha.split(",");
						double mediaArquivo = Double.parseDouble(info[3]);
						if (mediaArquivo >= media) {
							System.out.println("Nome: " + info[0] + " | Média: " + info[3]);
							String l = "Nome: " + info[0] + " | Média: " + info[3];
							createFile(path, "nome.csv", l);
						}
					}
					linha = buffer.readLine();
				}
				buffer.close();
				leitor.close();
				fluxo.close();
			} else {
				throw new IOException("Arquivo Inválido");
			}
		}
	}

	public void createFile(String path, String nome, String l) throws IOException {
		File dir = new File(path);
		File arq = new File(path, nome);
		if (dir.exists() && dir.isDirectory()) {
			boolean existe = false;
			if (arq.exists()) {
				existe = true;
			}
			String conteudo = geraTxt(l);
			FileWriter fileWriter = new FileWriter(arq, existe);
			PrintWriter print = new PrintWriter(fileWriter);
			print.write(conteudo);
			print.flush();
			print.close();
			fileWriter.close();
		} else {
			throw new IOException("Diretório inválido!");
		}
	}

	private String geraTxt(String l) {
		StringBuffer buffer = new StringBuffer();
		String linha = "";
		buffer.append(l + "\n");

		return buffer.toString();
	}
}