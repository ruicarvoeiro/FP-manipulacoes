package trabalho_final;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.*;

public class FileUtils {
	public static String lerFicheiroTexto(String caminhoParaFicheiro){
		String text=null;
		try {
			  byte[] encoded = Files.readAllBytes(Paths.get(caminhoParaFicheiro));
			  return new String(encoded, Charset.defaultCharset());
				 
		}catch(IOException e ){
			 
			 showMessageDialog("Erro ao abrir o ficheiro: " +e.getMessage());
		}
	
		return text;
	}
	
	public static boolean guardarEmFicheiro(String caminhoParaFicheiro, String texto){
		String text=null;
		boolean sucesso=false;
		try {
			  File file = new File(caminhoParaFicheiro);
			  PrintWriter out = new PrintWriter(caminhoParaFicheiro); 
			  out.print(texto);
			  out.close();
			  sucesso=true;
		}catch(IOException e ){
			 showMessageDialog("Erro ao guardar o ficheiro: " +e.getMessage());
			 sucesso=false;
		}
		return sucesso;
	}
	
	private static void showMessageDialog(String text){
		JDialog md= new JDialog();
		JLabel msg= new JLabel();
		md.setBounds(0, 0, 200, 120);
		md.setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - md.getWidth()/2, (Toolkit.getDefaultToolkit().getScreenSize().height)/2 - md.getHeight()/2);
		msg.setHorizontalAlignment(JLabel.CENTER); 
		msg.setText(text);
		Box.createHorizontalGlue();
		md.add(msg);
		md.setVisible(true);
		
	}

}
