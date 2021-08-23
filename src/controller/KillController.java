package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

public class KillController {

	public KillController() {
		super ();
		
	}
	/*Método chamado "os", identifica e retorna o sistema operacional
	para os métodos "listaProcessos", "mataPid" e "mataNome. (método privado)*/
	private String os() {
		//operação que identifica o nome do SO com retorno
		String opeSystem = System.getProperty("os.name");
		return opeSystem;
	}
	public void sequanciaProcessos(String chamasequencia) {
		
		KillController opeSystem = new KillController();
		String so = opeSystem.os();
		
		if(so.contains("Windows")) {
			chamasequencia = "cmd /c start C:\\Windows\\System32\\cmd.exe";
			
		}else {
			chamasequencia = "sudo su \\usr\\bin\\exo-open";
		}
		
		try {
			//Execução do comando para abrir o prompt de comando (CMD)
			Runtime.getRuntime().exec(chamasequencia.toString());
		} catch (IOException e) {
			e.printStackTrace();
		  }
		
		StringBuffer buffer = new StringBuffer();
		if(so.contains("Windows")) {
			buffer.append("tasklist /fo table");
		}else {
			buffer.append("ps -ef");
		}
		
		try {
			Process rodaCmd = Runtime.getRuntime().exec(buffer.toString());
			InputStream fluxo = rodaCmd.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer1 = new BufferedReader(leitor);
			String linha = buffer1.readLine();
			
			while(linha !=null) {
				System.out.println(linha);
				//JOptionPane.showMessageDialog(null, linha);
				linha = buffer1.readLine();
			}
			buffer1.close();
			leitor.close();
			fluxo.close();
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public void fimPid (int pid) {
		KillController opeSystem = new KillController();
		String so = opeSystem.os();
		
		String comando = " ";
		if(so.contains("Windows")) {
			comando = "talkkill /pid";
		}else {
			comando = "kill -9";
		}
		StringBuffer buffer = new StringBuffer();
		
		try {
			buffer.append(comando);
			buffer.append(" ");
			buffer.append(pid);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		try {
			Process rodaCmd = Runtime.getRuntime().exec(buffer.toString());
			InputStream fluxo = rodaCmd.getErrorStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer1 = new BufferedReader(leitor);
			String linha = buffer1.readLine();
			
			while (linha != null) {
				System.out.println(linha);
				//JOptionPane.showMessageDialog(null, linha);
				linha = buffer1.readLine();
				
			}
			buffer1.close();
			leitor.close();
			fluxo.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		    System.exit(0);
	}
	
	public void mataNome (String nome) {
		KillController opeSystem = new KillController();
		String so = opeSystem.os();
		
		String comando = " ";
		if(so.contains("Windows")) {
			comando = "taskkill /im";
		}else {
			comando = "pkill -f";
		}
		StringBuffer buffer = new StringBuffer();
		
		try {
			Process rodaCmd = Runtime.getRuntime().exec(buffer.toString());
			InputStream fluxo = rodaCmd.getInputStream();
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer1 = new BufferedReader(leitor);
			String linha = buffer1.readLine();
			
		while (linha != null){
			System.out.println(linha);
			//JOptionPane.showMessageDialog(null, linha);
			linha = buffer1.readLine();
		}
		buffer1.close();
		leitor.close();
		fluxo.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		    System.exit(0);
	}
}
