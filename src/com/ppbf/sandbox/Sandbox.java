package com.ppbf.sandbox;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.ppbf.solutions.models.Bet;
import com.ppbf.solutions.models.Event;
import com.ppbf.solutions.models.Market;

public class Sandbox {

	// Ex1: Given a List of lines on the file;
	// Given a marketId;
	// Write a function that finds the corresponding value of marketId in the list.
	// TIP: print the whole line
	public static List<Bet> ex1(List<String> lines, long marketId) {

		List<Bet> myBets = new ArrayList<Bet>();

		for (int i = 0; i < lines.size(); i++) {

			String str = lines.get(i);
			String[] data = str.split(";");
			long myId = Long.parseLong(data[3]);

			if (myId == marketId) {
				Market myMark = new Market(data[2], Long.parseLong(data[3]), new BigDecimal(data[4]));
				Event myEvent = new Event(data[1], myMark);
				myBets.add(new Bet(data[0], myEvent));
			}

		}
		return myBets;
	}

	// Ex2: Given a List of lines on the file;
	// Write a function that sorts the market names by reversed alphabetical order.
	// TIP: sort the whole line
	public static List<Bet> ex2(List<String> lines) {
		List<Bet> myBets = new ArrayList<Bet>();

		for (int i = 0; i < lines.size(); i++) {

			String str = lines.get(i);
			String[] data = str.split(";");

			Market myMark = new Market(data[2], Long.parseLong(data[3]), new BigDecimal(data[4]));
			Event myEvent = new Event(data[1], myMark);
			myBets.add(new Bet(data[0], myEvent));

		}

		Collections.sort(myBets, new Comparator<Bet>() {
			/*
			 * resultava meter logo bet2.event.market.name.compareTo(bet1.event.market.name)
			 * A implementação do compare devia ser feito fora, num método à parte.
			 */
			@Override
			public int compare(Bet bet1, Bet bet2) {
				int result = bet1.event.market.name.compareTo(bet2.event.market.name);
				return result * (-1);
			}
		});

		return myBets;
	}

	// Ex3: Given a List of lines on the file;
	// Given the total money;
	// While you still have money available:
	// Write a function that lets you bet on one or more markets.
	//
	// TIP: use validateAndUpdateTotalMoney to validate all data and update
	// totalMoney.
	// use addMarketAndStateToMap to return a Map with the marketId and the stake.
	public static BigDecimal validateAndUpdateTotalMoney(List<String> lines, BigDecimal totalMoney, long marketId,
			BigDecimal stake) {

		if (stake.doubleValue() > 0) {

			if (stake.compareTo(totalMoney) < 1) {

				for (int i = 0; i < lines.size(); i++) {

					String str = lines.get(i);
					String[] data = str.split(";");
					long myId = Long.parseLong(data[3]);

					if (myId == marketId) {
						totalMoney = totalMoney.subtract(stake);
					}

				}

			}
		}
		return totalMoney;
	}

	public static Map<Long, BigDecimal> addMarketAndStateToMap(List<String> lines, Map<Long, BigDecimal> bets,
			long marketId, BigDecimal stake) {

		if (!(bets.containsKey(marketId))) {

			bets.put(marketId, stake);

		} else if (bets.containsKey(marketId)) {

			BigDecimal previousStake = bets.get(marketId);
			BigDecimal currentStake = previousStake.add(stake);
			bets.put(marketId, currentStake);

		}

		return bets;
	}

	// Ex3_2: Given a List of lines on the file;
	// Given a List of bets (List of marketId and the stake of the bet);
	// Write a function that calculates the possible profit for each bet
	// TIP: return a list of profit values
	public static List<BigDecimal> ex3_2(List<String> lines, Map<Long, BigDecimal> bets) {

		List<BigDecimal> profits = new ArrayList<>();

		for (int i = 0; i < lines.size(); i++) {

			String str = lines.get(i);
			String[] data = str.split(";");
			long myId = Long.parseLong(data[3]);

			if (bets.containsKey(myId)) {
				BigDecimal odd = new BigDecimal(data[4]);
				BigDecimal stake = bets.get(myId);
				BigDecimal total = odd.multiply(stake);
				profits.add(total);
			}

		}

		return profits;
	}
}
