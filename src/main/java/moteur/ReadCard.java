package main.java.moteur;

import java.util.List;

import javax.smartcardio.*;

public class ReadCard 
{
	private Card card;
	private CardChannel channel;
	private String idResult;

	public ReadCard() 
	{
		// TODO Auto-generated constructor stub
		card = null;
		channel = null;
	}
	
	/**
	 * Tente la connection avec le lecteur, retourne le numero de la carte
	 * si elle a �t� d�t�cter.
	 * @return Num�ro de la carte sous forme de string.
	 * @throws CardException si probl�me de lecture, Exception si aucune carte a �t� detect�.
	 */
	public  String read()throws CardException, Exception
	{
		String conteneur = "";
		if(openConnection()) 
		{
			System.out.println("Connection open!");
	    	ATR atr = card.getATR();
	        byte[] baAtr = atr.getBytes();
            byte[] cmdApduGetCardUid = new byte[]{(byte)0xFF, (byte)0xCA, (byte)0x00, (byte)0x00, (byte)0x00};
            ResponseAPDU respApdu = channel.transmit(new CommandAPDU(cmdApduGetCardUid));
			if(respApdu.getSW1() == 0x90 && respApdu.getSW2() == 0x00)
			{
				byte[] baCardUid = respApdu.getData();
				System.out.println("Id card : ");
				idResult = "";
				for(int i = 0; i < baCardUid.length; i++ )
				{
				   conteneur += String.format("%02X", baCardUid[i]);
				   idResult += String.format("%02X", baCardUid[i]);
				}
			}
	        disconnect();
	        return conteneur;
    	}
		throw new Exception("No card detected!");
	}

	private  boolean openConnection() 
	{
		TerminalFactory factory = TerminalFactory.getDefault();
		CardTerminals cardterminals = factory.terminals();
		card = null;
		try 
		{
			List<CardTerminal> terminals = cardterminals.list();
			System.out.println("Terminals: " + terminals);
			CardTerminal terminal = cardterminals.getTerminal(terminals.get(0).getName());
			terminal.waitForCardPresent(20000);
			if(terminal.isCardPresent()) 
			{
				System.out.println("Card detected!");
				card = terminal.connect("*");
				System.out.println("Card: " + card);
				channel = card.getBasicChannel();
				System.out.println("Channel: " + channel);
				return true;
			} 
			else 
			{
				System.out.println("No card detected!");
				idResult = "No card detected!";
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return false;
	}
	
	private void disconnect() 
	{
		try 
		{
			card.disconnect(false);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public String getIdResult()
	{
		return idResult;
	}
}
