package kakao.config.websoket;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import kakao.config.websoket.handler.WebsocketHandler;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class websocketConfig implements WebSocketConfigurer {
    private final WebsocketHandler websocketHandler;
    
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    	
    	registry.addHandler(websocketHandler, "ws/qna").setAllowedOrigins("*");
    }
}
 
