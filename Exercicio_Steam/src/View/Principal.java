package View;

import java.io.IOException;

public class Principal {

	public static void main(String[] args) {
		
		SteamController sC = new SteamController();
		
		String path = "C:\\Steam";
		String nome = "SteamCharts.csv";
		
		try {
			sC.infos(path, nome);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
