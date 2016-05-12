package main.java.models;

public enum PresenceEnum {
	
	p("p"), a("a");
	
	private String presence;
	
	PresenceEnum(String s)
	{
		presence = s;
	}
	
	public void setPresence(String s)
	{
		presence = s;
	}

}
