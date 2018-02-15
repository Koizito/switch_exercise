package com.ppbf.helpers;

import static com.ppbf.helpers.File.readFromFile;
import static com.ppbf.helpers.Menu.printMenu;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.ppbf.sandbox.Sandbox;

public class Setup {

	private static Scanner in = new Scanner(System.in);

	public static void startSandbox() throws FileNotFoundException {
		System.out.println("Welcome to the Switch Programme Exercise @Blip");

		boolean quit = false;

		do {
			printMenu();

			int menuItem = in.nextInt();

			switch (menuItem) {
			case 1:
				// readFromFile returns a List with each entry representing a line of the file.
				List<String> lines = readFromFile("resources/eventsWithDuplicates.csv");
				for (int i = 0; i <= 1; i++) {
					lines.remove(0);
				}
				System.out.print("Choose marketId:");
				long marketId = in.nextLong();

				System.out.println(Sandbox.ex1(lines, marketId));

				break;
			case 2:
				// readFromFile returns a List with each entry representing a line of the file.
				lines = readFromFile("resources/eventsWithDuplicates.csv");
				for (int i = 0; i <= 1; i++) {
					lines.remove(0);
				}
				System.out.println(Sandbox.ex2(lines));

				break;
			case 3:
				// readFromFile returns a List with each entry representing a line of the file.
				lines = readFromFile("resources/eventsWithoutDuplicates.csv");
				for (int i = 0; i <= 1; i++) {
					lines.remove(0);
				}
				BigDecimal TOTAL_MONEY = new BigDecimal("30.1");

				List<Long> removedMarkets = new ArrayList<>();

				Map<Long, BigDecimal> bets = new HashMap<>();

				do {
					System.out.println("Total Money:" + TOTAL_MONEY);

					System.out.println(lines);

					System.out.print("Choose marketId:");
					marketId = in.nextLong();

					System.out.print("Stake:");
					BigDecimal stake = in.nextBigDecimal();

					BigDecimal NEW_TOTAL_MONEY = Sandbox.validateAndUpdateTotalMoney(lines, TOTAL_MONEY, marketId,
							stake);

					if (NEW_TOTAL_MONEY.compareTo(TOTAL_MONEY) == -1) {
						Sandbox.addMarketAndStateToMap(lines, bets, marketId, stake);
						TOTAL_MONEY = NEW_TOTAL_MONEY;
					}

				} while (TOTAL_MONEY.compareTo(BigDecimal.ZERO) > 0);
				break;
			case 0:
				quit = true;
				break;
			default:
				System.out.println("ERROR: Invalid choice.");
			}
		} while (!quit);

		System.out.println("Bye-bye!");
	}
}
