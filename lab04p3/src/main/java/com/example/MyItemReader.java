package com.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import com.example.domain.Game;

public class MyItemReader implements ItemReader<Game>, ItemStream {
	private static String dir = "C:/Users/bbirze/Documents/ContractInstructor/ClassMaterials/Spring/workspaceSpring/AdvSpring/lab04p3/";

	private BufferedReader bufRdr;
	
	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		FileReader fileRdr;
		try {
			fileRdr = new FileReader(dir + "games.txt");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new ItemStreamException(e);
		}
		bufRdr = new BufferedReader(fileRdr);
	}

	
	@Override
	public Game read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		String date = bufRdr.readLine();
		String team1 = bufRdr.readLine();
		String team2 = bufRdr.readLine();

		if (date == null || team1 == null || team2 == null)  {
			System.err.println("EOF: Got Null Value for (date, team1, team2)");
			return null;
		}
		Game game = new Game();
		game.setGameDate(date);
		game.setTeam1Name(team1);
		game.setTeam2Name(team2);
		return game;
	}


	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		// TODO Auto-generated method stub
	}

	@Override
	public void close() throws ItemStreamException {
		try {
			bufRdr.close();
		} catch (IOException e) {
			e.printStackTrace();
			throw new ItemStreamException(e);
		}
	}

}
