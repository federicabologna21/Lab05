package it.polito.tdp.anagrammi.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.anagrammi.db.AnagrammaDAO;

public class Model {

	
	// PRIMA COSA NEL MODEL RICHIAMO IL DAO CREANDOLO
	AnagrammaDAO anagrammaDao = new AnagrammaDAO();
	
	public List<String> anagrammiCorretti(String parola){
		
		List<String> risultatoCorretto = new ArrayList<>();
		List<String> risultatoSbagliato = new ArrayList<>();
		
		// lancio la ricorsione
		permuta("", parola, 0, risultatoCorretto, risultatoSbagliato);
		
		return risultatoCorretto;
	}
	
	
	public List<String> anagrammiSbagliati(String parola){
		
		List<String> risultatoCorretto = new ArrayList<>();
		List<String> risultatoSbagliato = new ArrayList<>();
		
		// lancio la ricorsione
		permuta("", parola, 0, risultatoCorretto, risultatoSbagliato);
		
		return risultatoSbagliato;
	}


	
	// livello = lunghezza soluzione parziale
	// livello iniziale = 0
	// gli passo la stringa parziale
	// parziale = stringa che contiene l'anagramma incompleto in fase di costruzione
	// ha anche bisogno di sapere quali sono le lettere rimaste
	// lettere = lettere della parola iniziale non ancora utilizzate
	// --> sotto-problrma da analizzare
	
	private void permuta(String parziale, String lettere, int livello, List<String> risultatoCorretto,
			List<String> risultatoSbagliato) {
		
		// CASO TERMINALE
		if(lettere.length()==0) {
			
			// La soluzione parziale è anche soluzione completa
			// quindi controllo se la soluzione completa
			// genera una parola corretta o errata
			// e lo aggiungo alla rispettiva lista
			if (this.anagrammaDao.isCorrect(parziale)) {
				risultatoCorretto.add(parziale);
			}
			else 
				risultatoSbagliato.add(parziale);
		}
		
		
		// RICORSIONE
		else {
			// SOTTO-PROBLEMA = una lettera (un singolo carattere) di 'lettere'
			
			// per ogni lettera tra le lettere disponibili
			for (int pos=0; pos<lettere.length(); pos++) {
				
				// prendo la lettera dalla posizione
				char tentativo = lettere.charAt(pos);
				
				// la aggiungo alla parola parziale
				String nuovaParziale = parziale + tentativo;
				
				// lettere --> togli il carattere pos da lettere
				String nuoveLettere = lettere.substring(0, pos) + lettere.substring(pos+1);
				
				// --> RICORSIONE
				permuta(nuovaParziale, nuoveLettere, livello+1, risultatoCorretto, risultatoSbagliato);
				
				// IL BACKTRACKING 
				// NON SERVE SE FACCIO 'NUOVAPARZIALE' E 'LETTERE'
				// rimetti a posto parziale (togli ultimo carattere)
				// rimetti a posto 'lettere'
			}
		}
		
	}
	
}
