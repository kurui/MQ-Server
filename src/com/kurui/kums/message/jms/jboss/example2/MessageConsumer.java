package com.kurui.kums.message.jms.jboss.example2;

import javax.jms.Message;
import javax.jms.MessageListener;

//import org.kiral.flow.service.WorksheetService;

/*******************************************************************************
 * 消息接收者
 * 
 * @作者：kiral
 * @日期：2007-7-3
 ******************************************************************************/
public class MessageConsumer implements MessageListener {

	// private WorksheetService worksheetService;
	//   
	// public WorksheetService getWorksheetService() {
	// return worksheetService;
	// }
	//   
	// public void setWorksheetService(WorksheetService worksheetService) {
	// this .worksheetService = worksheetService;
	// }

	// 接受方一旦接收到消息，就会打印在控制台
	public void onMessage(Message message) {
		System.out.println(message);
		// worksheetService.updateRole();
	}

}
