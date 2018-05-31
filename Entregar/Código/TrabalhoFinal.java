package trabalho_final;
import java.util.Scanner;
public class TrabalhoFinal {

	public static void main(String[] args) {
		Scanner ler = new Scanner(System.in);
		int opcao;
		boolean sair=false;
		String [] textoPalavras = null, textoNumeros = null, otexto = null;
		String textoPrincipal = FileUtils.lerFicheiroTexto("C:\\Users\\rui_c\\Desktop\\Entregar\\ficheiro.txt");
		char [] pontuacao = {'.',',',':','?','!','*','~','/','+','(',')',';','<','>','=','@','[',']','^','ª','º','_','`','´','{','}','€','%','"','|','#'};
		char [] num = {'0','1','2','3','4','5','6','7','8','9'};

		for (int i=0;i<pontuacao.length;i++){
			textoPrincipal= textoPrincipal.replace(""+ pontuacao [i], "");
		}
		textoPrincipal=textoPrincipal.replaceAll("\\s+", " ");
		otexto=textoPrincipal.split(" ");
		int counter = 0;

		for (int i=0;i<num.length; i++){
			for (int j = 0;j<otexto.length; j++){
				if (otexto[j].charAt(0)==num[i]){
					counter++;
				}
			}
		}
		boolean achou = false;
		int a = 0;
		int voltas = 0;
		textoNumeros = new String[counter];
		textoPalavras = new String[otexto.length-textoNumeros.length];

		for (int i = 0; i < otexto.length; i++ ){
			achou = false;

			for(int j = 0; j < num.length; j++){
				if(otexto[i].charAt(0) == num[j]){
					textoNumeros[a] = otexto[i];
					a++;
					achou = true;
				}
			}
			if(achou == false){
				textoPalavras[voltas] = otexto[i];
				voltas++;
			}
		}
		while (sair==false){

			System.out.println("******** MENU *********** "
					+ "\n1 - Contar Palavras "
					+ "\n2 - Frequências globais"
					+ "\n3 - Palíndromes e Capicuas"
					+ "\n4 - Ordenar palavras"
					+ "\n5 - Sair"
					+ "\nOpção: ");
			opcao=ler.nextInt();

			switch(opcao){
			case 1: System.out.println("Total de Palavras: " + textoPalavras.length);
			break;
			case 2: fAbsolutaRelativa(otexto);
			break;
			case 3:
				System.out.println("Palíndromes encontradas:");
				if (textoPalavras.length<1){ 
					System.out.println("Não existem palíndromes" );
				}
				palindromes (textoPalavras);
				System.out.println("Capicuas encontradas:");
				if (textoNumeros.length<1){ 
					System.out.println("Não existem capicuas" );
				}
				palindromes (textoNumeros);
				break;
			case 4: String textoOrdenado = "";

			for (int i = 0; i < otexto.length; i++)textoOrdenado = textoOrdenado + shellSort(otexto[i])+ " ";
			textoOrdenado = textoOrdenado.substring(0, textoOrdenado.length()-1);
			System.out.println(textoOrdenado);
			FileUtils.guardarEmFicheiro("C:\\Users\\rui_c\\Desktop\\Entregar\\resultado.txt", textoOrdenado);

			break;
			case 5: sair=true;
			break;
			default:System.out.println("Ensira um número entre 1 e 5");
			}
		}
	}

	public static void palindromes (String []textoPrincipal){

		int counter = 0;
		for(int i=0;i < textoPrincipal.length;i++){
			if(ePalindrome(textoPrincipal[i])){
				counter++;

				System.out.println("#" + counter + " -" + textoPrincipal[i]);}	
		}

	}

	public static boolean ePalindrome(String otexto){
		int len,half;
		len =otexto.length();
		half=len/2;
		for(int i = 0; i < half; i++){
			if((otexto.toLowerCase()).charAt(i)!=otexto.toLowerCase().charAt(len-i-1)) 
				return false;
		}
		return true;
	}

	public static void fAbsolutaRelativa (String [] otexto){
		String [] listaPalavras = new String [otexto.length]; 
		int [] numeroPalavras = new int [otexto.length]; 
		int porcento;

		for(int i = 0; i < otexto.length; i++){
			listaPalavras[i]="";
		}
		int voltas = 0; // ? 
		boolean existe = false; 

		for(int i = 0; i < otexto.length; i++){
			existe = false;
			int counter = 0;
			for(int j = 0; j < otexto.length; j++){
				if(otexto[i].compareToIgnoreCase(listaPalavras[j]) == 0){
					existe = true;
				}
			}
			if(existe == false){
				listaPalavras[i] = otexto[i];
				for(int j = 0; j < otexto.length; j++){
					if(otexto[i].compareToIgnoreCase(otexto[j]) == 0){
						counter++;
					}
				}
			}
			numeroPalavras[voltas] = counter;
			voltas++;

		}
		for(int i = 0; i < otexto.length; i++){ //hora de mostrar os valores!
			if(listaPalavras[i] != " "){
				porcento = (int) Math.ceil((double) numeroPalavras[i]/voltas*100);
				if (porcento==0){
				}
				else{
					System.out.print("\n"+listaPalavras[i] + ": " + numeroPalavras[i] + " (" + porcento + "%)");
				}
			}
		}
		pausa();
	}

	static String shellSort(String palavras){
		boolean nenhumaTroca;
		String [] letra = palavras.split("");
		String ordenado = "";
		int intervalo;
		intervalo = palavras.length() / 2;
		do{
			do{
				nenhumaTroca =true;
				for (int j=0;j<palavras.length()-intervalo;j++)
					if (letra[j].compareTo(letra[j+intervalo])>0) {
						troca(letra, j, j+intervalo);
						nenhumaTroca = false;
					} }while (!nenhumaTroca);
			intervalo = intervalo / 2;
		}while ( intervalo > 0);
		for (int i = 0; i < letra.length; i++){
			ordenado = ordenado + (letra[i]);
		}
		return ordenado;
	}
	
	static void troca(String [] letra, int pos1, int pos2){
		String aux;
		aux=letra[pos1];
		letra[pos1]=letra[pos2];
		letra[pos2]=aux;
	}

	public static void pausa(){

		Scanner teclado = new Scanner(System.in);
		System.out.println("\n\nPrima enter para continuar...");
		teclado.nextLine();
	}
}