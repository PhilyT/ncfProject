package moteur.client;

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
	
	public static void read(String conteneur)throws CardException
	{
		if(openConnection(conteneur)) 
		{
    		conteneur += "Connection open!\n";
	    	ATR atr = card.getATR();
	        byte[] baAtr = atr.getBytes();
	        conteneur += "ATR = 0x\n";
            for(int i = 0; i < baAtr.length; i++ )
            {
                conteneur += "%02X " + baAtr[i] + "\n";
            }
            conteneur += "\n";
            byte[] cmdApduGetCardUid = new byte[]{(byte)0xFF, (byte)0xCA, (byte)0x00, (byte)0x00, (byte)0x00};
            ResponseAPDU respApdu = channel.transmit(new CommandAPDU(cmdApduGetCardUid));

			if(respApdu.getSW1() == 0x90 && respApdu.getSW2() == 0x00)
			{
				byte[] baCardUid = respApdu.getData();
				
				System.out.print("Card UID = 0x");
				for(int i = 0; i < baCardUid.length; i++ )
				{
				   conteneur += "%02X " + baCardUid[i];
				}
				conteneur += "\n";
			}
	        disconnect();
    	}
	}

	public static boolean openConnection(String conteneur) 
	{
		TerminalFactory factory = TerminalFactory.getDefault();
		CardTerminals cardterminals = factory.terminals();
		card = null;
		try 
		{
			List<CardTerminal> terminals = cardterminals.list();
			conteneur += "Terminals: " + terminals + "\n";
			CardTerminal terminal = cardterminals.getTerminal(terminals.get(0).getName());
			terminal.waitForCardPresent(20000);
			if(terminal.isCardPresent()) 
			{
				conteneur += "Card detected!\n";
				card = terminal.connect("*");
				System.out.println("Card: " + card);
				channel = card.getBasicChannel();
				conteneur += "Channel: " + channel + "\n";
				return true;
			} 
			else 
			{
				conteneur += "No card detected!\n";
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return false;
	}
	
	public static void disconnect() 
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
