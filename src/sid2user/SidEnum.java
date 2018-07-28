package sid2user;

import java.io.IOException;

public class SidEnum {

	public static void main(String[] args) {
		// String soft =
		// "D:\\Escritorio\\Instituto\\DAM2\\WorkSpace\\Sid2User\\sid2user.exe";
		// String netbiosName = "\\\\Bryan_Ti";
		// String sid = "S-1-5-21-2875363957-1484684990-2383394187-501";
		// Sid2User su = new Sid2User(soft, netbiosName, sid);
		// int inicio = 1;
		// int fin = 2000;
		if (args.length == 6) {
			Sid2User su = new Sid2User(args[0], args[1], args[2], Integer.parseInt(args[4]), Integer.parseInt(args[5]));
			if (args[3].equalsIgnoreCase("true") && !args[3].equalsIgnoreCase("false")) {
				su.setVerbose(true);
				execute(su);
			} else if (args[3].equalsIgnoreCase("false") && !args[3].equalsIgnoreCase("true")) {
				su.setVerbose(false);
				execute(su);
			} else {
				System.out.println("[ERROR] no se contempla el parámetro [ " + args[3] + " ]");
			}
		} else {
			info();
		}

	}

	private static void info() {

		System.out.println(
				"\n\n+--------------------------------INICIO AYUDA-----------------------------------------------");
		System.out.println(
				"  ____           ___         ____     ___                                                         \r\n"
						+ " 6MMMMb\\68b      `MM         `MM'     `M'                                                         \r\n"
						+ "6M'    `Y89       MM          MM       M                                                          \r\n"
						+ "MM      ___   ____MM   ____   MM       M   ____     ____  ___  __                                 \r\n"
						+ "YM.     `MM  6MMMMMM  6MMMMb  MM       M  6MMMMb\\  6MMMMb `MM 6MM                                 \r\n"
						+ " YMMMMb  MM 6M'  `MM MM'  `Mb MM       M MM'    ` 6M'  `Mb MM69 \"                                 \r\n"
						+ "     `Mb MM MM    MM      ,MM MM       M YM.      MM    MM MM'                                    \r\n"
						+ "      MM MM MM    MM     ,MM' MM       M  YMMMMb  MMMMMMMM MM                                     \r\n"
						+ "      MM MM MM    MM   ,M'    YM       M      `Mb MM       MM                                     \r\n"
						+ "L    ,M9 MM YM.  ,MM ,M'       8b     d8 L    ,MM YM    d9 MM                                     \r\n"
						+ "MYMMMM9 _MM_ YMMMMMM_MMMMMMMM   YMMMMM9  MYMMMM9   YMMMM9 _MM_ ");
		System.out.println("\n Esta herramienta permite obtener los nombres de usuario a partir de un SID.");
		System.out.println(" Debe recibir 4 parámetros obligatorios");
		System.out.println(" [0] Ruta del programa Sid2User.exe");
		System.out.println(" [1] Nombre Netbios del equipo.");
		System.out.println(" [2] SID completo");
		System.out.println(" [3] true|false para activar modo verboso.");
		System.out.println(" [4] valor inicial del RID.");
		System.out.println(" [5] valor final del RID");
		System.out.println(
				"\tEJEMPLO: D:\\Escritorio\\Instituto\\DAM2\\WorkSpace\\Sid2User\\sid2user.exe \\\\Bryan_Ti S-1-5-21-2875363957-1484684990-2383394187-501 false 1 2000");
		System.out.println(
				"+--------------------------------FIN DE AYUDA-----------------------------------------------\n\n");
		System.out.println("`MMMMMMMb.                                             MMMMMMMMMM 68b MM                          \r\n" + 
					       " MM    `Mb                                             /   MM   \\ Y89 MM                          \r\n" + 
					       " MM     MM ___  __ ____    ___   ___   ___  __             MM     ___ MM____      ___   ___  __   \r\n" + 
					       " MM    .M9 `MM 6MM `MM(    )M' 6MMMMb  `MM 6MMb            MM     `MM MMMMMMb   6MMMMb  `MM 6MMb  \r\n" + 
					       " MMMMMMM(   MM69 \"  `Mb    d' 8M'  `Mb  MMM9 `Mb           MM      MM MM'  `Mb 8M'  `Mb  MMM9 `Mb \r\n" + 
					       " MM    `Mb  MM'      YM.  ,P      ,oMM  MM'   MM           MM      MM MM    MM     ,oMM  MM'   MM \r\n" + 
					       " MM     MM  MM        MM  M   ,6MM9'MM  MM    MM           MM      MM MM    MM ,6MM9'MM  MM    MM \r\n" + 
					       " MM     MM  MM        `Mbd'   MM'   MM  MM    MM           MM      MM MM    MM MM'   MM  MM    MM \r\n" + 
					       " MM    .M9  MM         YMP    MM.  ,MM  MM    MM           MM      MM MM.  ,M9 MM.  ,MM  MM    MM \r\n" + 
							"_MMMMMMM9' _MM_         M     `YMMM9'Yb_MM_  _MM_         _MM_    _MM_MYMMMM9  `YMMM9'Yb_MM_  _MM_");
	}

	/**
	 * @param su
	 */
	private static void execute(Sid2User su) {
		for (int i = su.getStart(); i <= su.getEnd(); i++) {
			try {
				ProcessBuilder pb = su.builderProcess(i);
				String result = su.readResults(pb, i);
				if (!result.isEmpty()) {
					System.out.println(result);
				}

			} catch (IOException e) {
				System.out.println("[ERROR] " + e.getMessage());
				break;
			}
		}
	}

}
