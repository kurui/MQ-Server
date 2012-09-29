package com.kurui.kums.message.jms.jboss.ejb;
//
//import javax.ejb.MessageDrivenBean;
//import javax.ejb.MessageDrivenContext;
//import javax.ejb.EJBException;
//import javax.jms.JMSException;
//import javax.jms.Message;
//import javax.jms.MessageListener;
//import javax.jms.Queue;
//import javax.jms.QueueConnection;
//import javax.jms.QueueConnectionFactory;
//import javax.jms.QueueSender;
//import javax.jms.QueueSession;
//import javax.jms.TextMessage;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//
///** 
// * An MDB that transforms the TextMessages it receives and send the
// * transformed messages to the Queue found in the incoming message
// * JMSReplyTo header.
// * 
// * @author Scott.Stark@jboss.org
// * @version $Revision: 1.9 $
// */
public class TextMDB 
//    implements MessageDrivenBean, MessageListener
{
//    private MessageDrivenContext ctx = null;
//    private QueueConnection conn;
//    private QueueSession session;
//    
//    public TextMDB()
//    {
//        System.out.println("TextMDB.ctor, this="+hashCode());
//    }
//    
//    public void setMessageDrivenContext(MessageDrivenContext ctx)
//    {
//        this.ctx = ctx;
//        System.out.println("TextMDB.setMessageDrivenContext, this=" + 
//                           hashCode());
//    }
//    
//    public void ejbCreate()
//    {
//        System.out.println("TextMDB.ejbCreate, this="+hashCode());
//        try {
//            setupPTP();
//        } catch (Exception e) {
//            throw new EJBException("Failed to init TextMDB", e);
//        }
//    }
//
//    public void ejbRemove()
//    {
//        System.out.println("TextMDB.ejbRemove, this="+hashCode());
//        ctx = null;
//        try {
//            if (session != null) {
//                session.close();
//            }
//            if (conn != null) {
//                conn.close();
//            }
//        } catch(JMSException e) {
//            e.printStackTrace();
//        }
//    }
//                
//    public void onMessage(Message msg)
//    {
//        System.out.println("TextMDB.onMessage, this="+hashCode());
//        try {
//            TextMessage tm = (TextMessage) msg;
//            String text = tm.getText() + "processed by: "+hashCode();
//            Queue dest = (Queue) msg.getJMSReplyTo();
//            sendReply(text, dest);
//        } catch(Throwable t) {
//            t.printStackTrace();
//        }
//    }
//                
//    private void setupPTP()
//        throws JMSException, NamingException
//    {
//        InitialContext iniCtx = new InitialContext();
//        Object tmp = iniCtx.lookup("java:comp/env/jms/QCF");
//        QueueConnectionFactory qcf = (QueueConnectionFactory) tmp;
//        conn = qcf.createQueueConnection();
//        session = conn.createQueueSession(false,
//                                          QueueSession.AUTO_ACKNOWLEDGE);
//        conn.start();
//    }
//
//    private void sendReply(String text, Queue dest)
//        throws JMSException
//    {
//        System.out.println("TextMDB.sendReply, this=" + 
//                           hashCode() + ", dest="+dest);
//        QueueSender sender = session.createSender(dest);
//        TextMessage tm = session.createTextMessage(text);
//        sender.send(tm);
//        sender.close();
//    }
}
