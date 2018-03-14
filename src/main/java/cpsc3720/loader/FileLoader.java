package cpsc3720.loader;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import cpsc3720.game.Game;
import cpsc3720.ship.Alert;
import cpsc3720.ship.Ship;
import cpsc3720.ship.ShipType;
import cpsc3720.user.Player;
import cpsc3720.userserver.*;
import cpsc3720.userservice.*;
import cpsc3720.util.Coord;
import cpsc3720.util.Loc;
import cpsc3720.base.Base;
import cpsc3720.empire.*;
import cpsc3720.weapon.*;

public class FileLoader {

	
	public static Player loadGameFile(String filename,UserService service)
	{
		try {
			Scanner scanner=new Scanner(new File(filename));
			scanner.useDelimiter("~#~$");
			String text=scanner.next();
			System.out.println("entering server: \n"+text);
			Player p=service.loadGame(text);
			return p;
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File not found: "+filename);	
			return null;
		}
			
	}
	public static Player readGame(String file)
	{
			file=file.replaceAll("\\\\n","\n");
			file=file.replaceAll("\\\\t","\t");
			file=file.replaceAll("\"","");
			Scanner scanner=new Scanner(file);
			scanner.useDelimiter("\t|\n");
			Game game=new Game();
			GameDatabase.instance().add(game);
			
			scanner.nextLine();//"Title    Stardate"
			
			game.setGameName(scanner.next());//title
			game.setStardate(scanner.nextInt());//stardate
			scanner.next();
			scanner.nextLine();
			
			//---------Empire Section-----------------
			scanner.nextLine();
			
			String next=scanner.next();
			while (!next.equals(""))
			{
				Empire emp=new Empire();
				emp.setID(next);
				emp.setName(scanner.next());
				emp.setExploration(scanner.next().equals("Exploration"));
				game.addEmpire(emp);
				next=scanner.next();
			}
			scanner.nextLine();
			//----------Weapon Section-----------------

			scanner.nextLine();
			
			ArrayList<Weapon> weapons=new ArrayList<Weapon>();
			next=scanner.next();
			while (!next.equals(""))
			{
				Weapon weapon=new Weapon();
				weapon.setId(next);
				weapon.setName(scanner.next());
				weapon.setEnergy(scanner.next().equals("ENERGY"));
				weapon.setYield(scanner.nextInt());
				weapons.add(weapon);
				next=scanner.next();
			}
			scanner.nextLine();
			//----------ShipType Section---------------

			scanner.nextLine();
			
			next=scanner.next();
			ArrayList<ShipType> shipTypes=new ArrayList<ShipType>();
			while (!next.equals(""))
			{
				ShipType ship=new ShipType();
				
				ship.setId(next);
				ship.setTitle(scanner.next());
				ship.setShipClass(scanner.next());
				ship.setEmpire(scanner.next());
				ship.setMaxEnergy(scanner.nextInt());
				ship.setMaxSpeed(scanner.nextInt());
				ship.setMaxShields(scanner.nextInt());
				String missile=scanner.next();
				String energy=scanner.next();
				for (int n=0;n<weapons.size();n++)
				{
					if (weapons.get(n).getName().equals(missile))
					{
						ship.setMissile(weapons.get(n));
					}
					else if (weapons.get(n).getName().equals(energy))
					{
						ship.setEnergy(weapons.get(n));
					}
				}
				ship.setMaxMissiles(scanner.nextInt());
				shipTypes.add(ship);
				game.getEmpire(ship.getEmpire()).addShipType(ship);
				next=scanner.next();
			}
			scanner.nextLine();
			//-------------Base Section---------------------------

			scanner.nextLine();
			
			next=scanner.next();
			while (!next.equals(""))
			{
				Base base=new Base();
				base.setId(Integer.parseInt(next));
				base.setE(game.getEmpire(scanner.next()));
				Coord sec=new Coord(scanner.nextInt(),scanner.nextInt());
				Coord sqr=new Coord(scanner.nextInt(),scanner.nextInt());
				game.getGrid().getSector(sec).getSquare(sqr).setBase(base);
				next=scanner.next();
			}
			scanner.nextLine();
			//-----------Ship Section-------------------------------

			scanner.nextLine();
			
			next=scanner.next();
			ArrayList<Loc> shipLocs=new ArrayList<Loc>();
			while (!next.equals(""))
			{
				Ship ship=new Ship();
				ship.setId(Integer.parseInt(next));
				String shipType=scanner.next();
				for (int n=0;n<shipTypes.size();n++)
				{
					if (shipType.equals(shipTypes.get(n).getId()))
					{
						ship.setShipType(shipTypes.get(n));
						break;
					}
				}
				Coord sec=new Coord(scanner.nextInt(),scanner.nextInt());
				Coord sqr=new Coord(scanner.nextInt(),scanner.nextInt());
				game.getGrid().getSector(sec).getSquare(sqr).setShip(ship);
				shipLocs.add(new Loc(sec,sqr));
				ship.setCurEnergy(scanner.nextInt());
				ship.setCurMissile(scanner.nextInt());
				String alert=scanner.next();
				if (alert.equals("RED"))
					ship.setAlert(Alert.Red);
				else if (alert.equals("YELLOW"))
					ship.setAlert(Alert.Yellow);
				else
					ship.setAlert(Alert.Green);
				ship.setCurShield(scanner.nextInt());
				next=scanner.next();
			}
			scanner.nextLine();
			//------------Player Section--------------------
			scanner.nextLine();//Empire      Ship
			
			next=scanner.next();
			Player ret=null;
			while (!next.equals(""))
			{
				Player p=new Player();
				if (ret==null) ret=p;
				p.setName(next);
				p.setAff(game.getEmpire(scanner.next()));
				p.setShipLoc(shipLocs.get(scanner.nextInt()));
	    		p.setGameId(game.getId());
				UserDatabase.instance().findUser(p.getName()).addPlayer(game.getId(),p);
				if (!scanner.hasNext())
					break;
				next=scanner.next();
			}
			
			return ret;
		} 
}
