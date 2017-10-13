package textRPG;

import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		Random rand = new Random();
		
		//PLAYER VARIABLES
		String playerName;
		int playerHealth = 100;
		int player_max_health = 100;
		int player_min_damage = 2;
		int player_max_damage = 7;
		int playerGold = 600;
		
		//POTIONS
		int health_potion_max_amount = 10;
		int health_potion_heal_amount = 30;
		int health_potion_amount = 2;
		int health_potion_heal_part;;
		
		//INVENTORY
		String[] inventory = new String[10];
		
		//ITEMS
		int itemValue = 0;
		int itemDamage = 0;
		int itemID = 0;
		int sellValue = 0;
		
		//MONSTERS VARIABLES
		String[] firstMonsters = {"Goblin", "Wilk"};
		String[] secondMonsters = {"Szkielet", "Szkielet - wojownik", "Zombie", "Duch"};
		int monsterHealth;
		int monster_min_damage;
		int monster_max_damage;
		int monster_min_gold_drop;
		int monster_max_gold_drop;
		int health_potion_drop;
		
		boolean game = true;
		
		//PLAYER NAME AND WELCOME MESSAGE
		System.out.print("Witaj w grze Dungeon! Podaj nazwę swojej postaci: ");
		playerName = in.nextLine();
		System.out.print("\n");
		
		for(int i = 0; i < 10; i++) {
			System.out.println();
		}
		
		GAME:
		while(game) {
			System.out.println("Zdrowie: " + playerHealth);
			System.out.println("1. Idź do lochu.");
			System.out.println("2. Idź do sklepu.");
			System.out.println("3. Ekwipunek.");			
			int input = in.nextInt();
			System.out.println("\n");
			
			if(input == 1) { //============================FIRST DUNGEON================================================
								
			
			for(int a = 0; a < 2; a++) {
			
			System.out.println("\t^^^Loch pierwszy^^^\n");
				
			//GENERATING MONSTERS
			String monster = firstMonsters[rand.nextInt(firstMonsters.length)];
			if(monster == "Goblin") {
				monsterHealth = 5;
				monster_min_damage = 1;
				monster_max_damage = 3;
				monster_min_gold_drop = 3;
				monster_max_gold_drop = 10;
				health_potion_drop = 5;
			}
			else {
				monsterHealth = 10;
				monster_min_damage = 2;
				monster_max_damage = 5;
				monster_min_gold_drop = 0;
				monster_max_gold_drop = 0;
				health_potion_drop = 5;
			}
			
			System.out.println("Napotkałeś " + monster + "!");
			
			//BATTLE
			while(monsterHealth > 0) {
				System.out.println("Twoje zdrowie: " + playerHealth);
				System.out.println("Zdrowie przeciwnika: " + monsterHealth);
				System.out.println("\nCo chcesz zrobić?");
				System.out.println("1. Zaatakować.");
				System.out.println("2. Wypić miksturę. (" + health_potion_amount + ")\n");
				int input2 = in.nextInt();
				System.out.print("\n");
				
				//COMBAT
				if(input2 == 1) {
					int damageDealt = rand.nextInt(player_max_damage - player_min_damage) 
							+ player_min_damage;
					int damageTaken = rand.nextInt(monster_max_damage - monster_min_damage)
							+ monster_min_damage;
					
					monsterHealth -= damageDealt;
					playerHealth -= damageTaken;
					
					for(int i = 0; i < 10; i++) {
						System.out.println();
					}
					
					System.out.println("Uderzasz " + monster + " za " + damageDealt 
							+ " punktów obrażeń.");
					System.out.println(playerName + " otrzymuje " + damageTaken 
							+ " punktów obrażeń.\n"); 
					
					if(playerHealth < 1) {
						System.out.println("Zostałeś pokonany.");
						break;
					}
				}
				//HEALING
				else if(input2 == 2) {
					if(health_potion_amount > 0) { 						
						playerHealth += health_potion_heal_amount;
						health_potion_amount--;
						if(playerHealth > player_max_health) {
							health_potion_heal_part = health_potion_heal_amount - (playerHealth - player_max_health);
							System.out.println("Pijesz miksturę lecząc się za " +  health_potion_heal_part 
									+ " punktów życia.");
							playerHealth = player_max_health;
							
						}
						
						else {
						System.out.println("Pijesz miksturę lecząc się za " + health_potion_heal_amount + " punktów życia.");
						}
					}
					
					else {
						System.out.println("Nie masz żadnych mikstur.");
					}
				}
				else {
					System.out.println("Niewłaściwa opcja.\n");
				}
				
			  }
			//PLAYER DEFEATED
			if(playerHealth < 1) {
				System.out.println("Ledwo żywy uciekasz z lochu.");
				playerHealth = 1; 
				continue GAME;
			}
					
			//END OF BATTLE AND DROPS
			System.out.println(monster + " został pokonany.\n");
			if(monster_min_gold_drop >= 0 && monster_max_gold_drop > 0) {
				int goldDrop = rand.nextInt(monster_max_gold_drop - monster_min_gold_drop) + monster_min_gold_drop;
				System.out.println("Zdobyłeś " + goldDrop + " złota.\n");
				playerGold += goldDrop;
			}
			
			if(rand.nextInt(100) < health_potion_drop) {
				health_potion_amount++;
				System.out.println("Zdobyto miksturę zdrowia!");
			}
			
			System.out.println("Zostało ci " + playerHealth + " punktów życia.");
			System.out.println("Masz " + playerGold + " złota.\n");
			System.out.println("Co chcesz teraz zrobić?");
			System.out.println("1. Walcz dalej.");
			System.out.println("2. Wyjdź z lochu.\n");
			
			int input3 = in.nextInt();
			System.out.print("\n");
			
			while(input3 != 1 && input3 != 2) {
				System.out.println("Niewłaściwa opcja.");
				input3 = in.nextInt();
			}
			
			if(input3 == 1) 
				System.out.println("Idziesz w głąb lochu.\n");
			
			else if(input3 == 2) {
				System.out.println("Wychodzisz z lochu.\n");
				continue GAME;
					}
			}   //=================================SECOND DUNGEON==============================================
			
			for(int b = 0; b < 4; b++) {
				System.out.println("\t^^^Loch Drugi^^^\n");
				
				//GENERATING MONSTERS
				String monster = secondMonsters[rand.nextInt(secondMonsters.length)];
				if(monster == "Szkielet") {
					monsterHealth = 13;
					monster_min_damage = 4;
					monster_max_damage = 7;
					monster_min_gold_drop = 3;
					monster_max_gold_drop = 10;
					health_potion_drop = 5;
				}
				else if(monster == "Szkielet - wojownik") {
					monsterHealth = 20;
					monster_min_damage = 7;
					monster_max_damage = 12;
					monster_min_gold_drop = 3;
					monster_max_gold_drop = 10;
					health_potion_drop = 5;
				}
				else if(monster == "Zombie") {
					monsterHealth = 22;
					monster_min_damage = 3;
					monster_max_damage = 6;
					monster_min_gold_drop = 3;
					monster_max_gold_drop = 10;
					health_potion_drop = 5;
				}
				else {
					monsterHealth = 11;
					monster_min_damage = 6;
					monster_max_damage = 14;
					monster_min_gold_drop = 3;
					monster_max_gold_drop = 10;
					health_potion_drop = 5;
				}
				
				System.out.println("Napotkałeś " + monster + "!");
				
				//BATTLE
				while(monsterHealth > 0) {
					System.out.println("Twoje zdrowie: " + playerHealth);
					System.out.println("Zdrowie przeciwnika: " + monsterHealth);
					System.out.println("\nCo chcesz zrobić?");
					System.out.println("1. Zaatakować.");
					System.out.println("2. Wypić miksturę.\n");
					int input4 = in.nextInt();
					
					//COMBAT
					if(input4 == 1) {
						int damageDealt = rand.nextInt(player_max_damage - player_min_damage) 
								+ player_min_damage;
						int damageTaken = rand.nextInt(monster_max_damage - monster_min_damage)
								+ monster_min_damage;
						
						monsterHealth -= damageDealt;
						playerHealth -= damageTaken;
						
						for(int i = 0; i < 10; i++) {
							System.out.println();
						}
						
						System.out.println("Uderzasz " + monster + " za " + damageDealt 
								+ " punktów obrażeń.");
						System.out.println(playerName + " otrzymuje " + damageTaken 
								+ " punktów obrażeń.\n"); 
						
						
						if(playerHealth < 1) {
							System.out.println("Zostałeś pokonany.\n");
							break;
						}
					}
					
					//HEALING
						else if(input4 == 2) {
							if(health_potion_amount > 0) { 						
								playerHealth += health_potion_heal_amount;
								health_potion_amount--;
								if(playerHealth > player_max_health) {
									health_potion_heal_part = health_potion_heal_amount - (playerHealth - player_max_health);
									System.out.println("Pijesz miksturę lecząc się za " +  health_potion_heal_part 
											+ " punktów życia.");
									playerHealth = player_max_health;
									
								}
								
								else {
								System.out.println("Pijesz miksturę lecząc się za " + health_potion_heal_amount + " punktów życia.");
								}
							}
							
							else {
								System.out.println("Nie masz żadnych mikstur.");
							}
						}
						
						else {
							System.out.println("Niewłaściwa opcja.\n");
						} }
						
						//PLAYER DEFEATED
						if(playerHealth < 1) {
							System.out.println("Ledwo żywy uciekasz z lochu.\n");
							playerHealth = 1;
							continue GAME;
						}
					
				
				//END OF BATTLE AND DROPS		
				System.out.println(monster + " został pokonany.\n");
				if(monster_min_gold_drop >= 0 && monster_max_gold_drop > 0) {
					int goldDrop = rand.nextInt(monster_max_gold_drop - monster_min_gold_drop) + monster_min_gold_drop;
					System.out.println("Zdobyłeś " + goldDrop + " złota.\n");
					playerGold += goldDrop;
				}
				
				if(rand.nextInt(100) < health_potion_drop) {
					health_potion_amount++;
					System.out.println("Zdobyto miksturę zdrowia!");
				}
				
				System.out.println("Zostało ci " + playerHealth + " punktów życia.\n");
				System.out.println("Masz " + playerGold + " złota.\n");
				System.out.println("Co chcesz teraz zrobić?");
				System.out.println("1. Walcz dalej.");
				System.out.println("2. Wyjdź z lochu.\n");
				
				int input5 = in.nextInt();
				System.out.print("\n");
				
				while(input5 != 1 && input5 != 2) {
					System.out.println("Niewłaściwa opcja.");
					input5 = in.nextInt();
				}
				
				if(input5 == 1) 
					System.out.println("Idziesz w głąb lochu.\n");
				
				else if(input5 == 2) {
					System.out.println("Wychodzisz z lochu.\n");
					continue GAME;
						}
				
			}} //===================================SHOP=========================================================
			
			 if(input == 2) {
				System.out.println("Witaj w sklepie " + playerName + ".\n");
				System.out.println("Co chcesz zrobić?");
				System.out.println("1. Kup.");
				System.out.println("2. Sprzedaj.");
				System.out.println("3. Wróć.\n");
				int input6 = in.nextInt();
				
				if(input6 == 1) {
					System.out.println("\nZłoto: " + playerGold);
					System.out.println("\n1. Drewniany miecz. (Atak: 5, Koszt: 50)");
					System.out.println("2. Żelazny miecz. (Atak: 10, Koszt: 150)");
					System.out.println("3. Diamentowy miecz. (Atak: 25, Koszt: 400)");
					System.out.println("4. Miksturę zdrowia. (Zdrowie: 30, Koszt: 30)");
					System.out.println("5. Wróć.\n");
									
					int input7 = in.nextInt();
					
					int number = 10;
					for(int i = 9; i >= 0; i--) {
						if(inventory[i] == null) {
							number = i;
						}
					}

					for(int i = 0; i <= inventory.length; i++) {
						int num = 0;
						num++;
					
						if(input7 == 1) {
							itemValue = 50;
							sellValue = 10;
							itemDamage = 5;
							itemID = 1;
							if(number != 10) {
								if(playerGold >= itemValue) {
									playerGold -= itemValue;
									System.out.println("\nKupiłeś drewniany miecz.\n");
									inventory[number] = "Drewniany miecz"; 
									break; 
								}
								else {
									System.out.println("\nNie masz tyle złota!\n");
									break;
								}
							}
							
							else {
								System.out.println("\nNie masz miejsca w ekwipunku.");
								break;
							}
							
						}
						else if(input7 == 2) {
							itemValue = 150;
							sellValue = 50;
							itemDamage = 10;
							itemID = 2;
							if(number != 10) {
								if(playerGold >= itemValue) {
									playerGold -= itemValue;
									System.out.println("\nKupiłeś żelazny miecz.\n");
									inventory[number] = "Żelazny miecz";
									break; 
								}
								
								else {
									System.out.println("\nNie masz tyle złota!\n");
									break;
								}								
							}
						
							else {
								System.out.println("\nNie masz miejsca w ekwipunku.");
								break;
							}
							
						}
						else if(input7 == 3) {
							itemValue = 400;
							sellValue = 150;
							itemDamage = 25;
							itemID = 3;
							if(number != 10) {
								if(playerGold >= itemValue) {
									playerGold -= itemValue;
									System.out.println("\nKupiłeś diamentowy miecz.\n");
									inventory[number] = "Diamentowy miecz";
									break;
								}
								else {
									System.out.println("\nNie masz tyle złota!\n");
									break;
								}
							}
							
							else {
								System.out.println("\nNie masz miejsca w ekwipunku.");
								break;
							}
	
						}
						else if(input7 == 4) {
							if(health_potion_amount < health_potion_max_amount) {
							itemValue = 30;
								if(playerGold >= itemValue) {
									playerGold -= itemValue;
									System.out.println("\nKupiłeś miksturę zdrowia.\n");
									health_potion_amount++;
									break;
										}
								else {
									System.out.println("\nNie masz tyle złota!\n");
									break;
								}
							}
							else {
								System.out.println("\nNie możesz unieść więcej mikstur zdrowia.\n");
								break;
							}
							
						}
						else {
							continue GAME;
						}
							
					
					} }

					if(input6 == 2) {
						int num = 1;
				 		for(int i = 0; i < 10; i++) {
				 			if(inventory[i] != null) {
				 				System.out.print(num++ + ". " + inventory[i] + "\n");
				 			}
				 		} /////	
		 				System.out.print("Wybierz przemiot: ");
				 		int input8 = in.nextInt();
				 		System.out.println("\nZłoto: " + playerGold);
				 		System.out.println("Co chcesz z nim zrobić?");
				 		System.out.println("1. Sprzedaj.");
				 		System.out.println("2. Wróć.");
				 		int input9 = in.nextInt();
				 			
				 			if(input9 == 1) {
				 				playerGold += sellValue;
				 				inventory[input8 - 1] = null;
				 			}
					}
					
					if(input6 == 3) {
						continue GAME;
					}
					
				}
			 
			 	if(input == 3) { //=========================INVENTORY==================================
			 		int num = 1;
				 		for(int i = 0; i < 10; i++) {
				 			if(inventory[i] != null) {
				 				System.out.print(num++ + ". " + inventory[i] + "\n");
				 			}
				 		} /////	
		 				System.out.print("Wybierz przemiot: ");
				 		int input8 = in.nextInt();
				 		System.out.println("Co chcesz z nim zrobić?");
				 		System.out.println("1. Załóż.");
				 		System.out.println("2. Wróć.");
				 		int input9 = in.nextInt();
				 			if(input9 == 1) {
				 				if(itemID == 1) {
				 					player_min_damage += 5;
				 					player_max_damage += 5;			 					
				 				}
				 				
				 				else if(itemID == 2) {
				 					player_min_damage += 10;
				 					player_max_damage += 10;
				 				}
				 				
				 				else {
				 					player_min_damage += 25;
				 					player_max_damage += 25;
				 				}
				 			}
			 	}
			}
		}

	}


