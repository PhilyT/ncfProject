package moteur;

import java.util.List;

import javax.smartcardio.*;

public class ReadCard 
{
	private static Card card;
	private static CardChannel channel;

	public ReadCard() 
	{
		// TODO Auto-generated constructor stub
		card = null;
		channel = null;
	}
	
	/**
	 * Tente la connection avec le lecteur, retourne le numero de la carte
	 * si elle a été détécter.
	 * @return Numéro de la carte sous forme de string.
	 * @throws CardException si problème de lecture, Exception si aucune carte a été detecté.
	 */
	public static String read()throws CardException, Exception
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
				for(int i = 0; i < baCardUid.length; i++ )
				{
				   conteneur += String.format("%02X ", baCardUid[i]);
				}
			}
	        disconnect();
	        return conteneur;
    	}
		throw new Exception("No card detected!");
	}

	private static boolean openConnection() 
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
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return false;
	}
	
	private static void disconnect() 
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
}
