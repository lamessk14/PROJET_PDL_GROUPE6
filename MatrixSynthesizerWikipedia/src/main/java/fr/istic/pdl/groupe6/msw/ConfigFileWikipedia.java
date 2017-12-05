package fr.istic.pdl.groupe6.msw;

public class ConfigFileWikipedia {

	private String language;
	private String title;
	
	public ConfigFileWikipedia(String language, String title) {
		this.language = language;
		this.title = title;
	}
	
	public String getLanguage() {
		return this.language;
	}
	
	public void setLanguage(String language) {
		this.language = language;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
}
