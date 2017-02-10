package com.example.messaging;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.apache.log4j.Logger;

import com.example.domain.PurchaseOrder;

public class ReceivePurchaseOrder implements MessageListener {

	private Logger log = Logger.getLogger(ReceivePurchaseOrder.class);

	@Override
	public void onMessage(Message message) {
		ObjectMessage msg = (ObjectMessage)message;
		try {
			PurchaseOrder po = (PurchaseOrder)msg.getObject();
			log.info("Receiver Got:  " + po);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
