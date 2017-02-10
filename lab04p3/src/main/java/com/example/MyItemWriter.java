package com.example;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemWriter;

import com.example.domain.Game;

public class MyItemWriter implements ItemWriter<Game> {

	private Logger log = Logger.getLogger(MyItemWriter.class);
	
	@Override
	public void write(List<? extends Game> items) throws Exception {
		for (Game item: items)	{
			log.info(item);
		}
	}

}
