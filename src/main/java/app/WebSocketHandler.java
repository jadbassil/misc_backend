package app;

import java.io.IOException;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

public class WebSocketHandler extends AbstractWebSocketHandler {
	
	
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
		String msg = String.valueOf(message.getPayload());
		System.out.println(msg);
		session.sendMessage(new TextMessage(msg));
	}
	
}
