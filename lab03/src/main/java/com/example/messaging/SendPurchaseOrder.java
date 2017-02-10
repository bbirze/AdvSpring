package com.example.messaging;

import java.util.Date;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

import com.example.domain.PurchaseOrder;

@Component
public class SendPurchaseOrder {
	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	private class MyMessageCreator implements MessageCreator {
		private PurchaseOrder po;
		
		public MyMessageCreator(PurchaseOrder po)  {
			this.po = po;
		}
		@Override
		public Message createMessage(Session session) throws JMSException {
			ObjectMessage msg = session.createObjectMessage(po);
			return msg;
		}
	}
		
	public void sendPO(int poNum, double poAmt)  {
		PurchaseOrder po = new PurchaseOrder();
		po.setPoDate(new Date());
		po.setPoNumber(1239876);
		po.setAmount(1024.99);
		
		MyMessageCreator mmc = new MyMessageCreator(po);
		jmsTemplate.send("poQueue", mmc);
	}

}
