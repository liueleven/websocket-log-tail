package cn.eleven.log;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;

@ServerEndpoint("/log")
@Component
public class LogWebSocketHandle {

	private Process process;
	private InputStream inputStream;

	private static ConcurrentHashMap<String,String> user = new ConcurrentHashMap(16);


	/**
	 * 新的WebSocket请求开启
	 */
	@OnOpen
	public void onOpen(Session session) {
        String log = user.get(session.getId());
        if (log == null || "" == log) {
            log = Application.LOG_PATH_ARR.get(0);
        }
		try {
            // 执行tail -f命令
			process = Runtime.getRuntime().exec("tail -f " + log);
			inputStream = process.getInputStream();
			
			// 一定要启动新的线程，防止InputStream阻塞处理WebSocket的线程
			TailLogThread thread = new TailLogThread(inputStream, session);
			thread.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@OnMessage
    public void onMessage(Session session,String data) {
        user.put(session.getId(),data);
        System.out.println(data);
    }
	
	/**
	 * WebSocket请求关闭
	 */
	@OnClose
	public void onClose(Session session) {
		try {
			if(inputStream != null)
				inputStream.close();
            if(process != null)
                process.destroy();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
            user.remove(session.getId());
        }

	}
	
	@OnError
	public void onError(Throwable thr) {
		thr.printStackTrace();
	}
}