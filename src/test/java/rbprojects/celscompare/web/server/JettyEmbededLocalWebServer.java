package rbprojects.celscompare.web.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class JettyEmbededLocalWebServer {
	public static void main(String[] args) throws Exception {
		System.setProperty("ambiente", "teste");
		Server server = new Server(8083);
		 
		WebAppContext context = new WebAppContext();
        context.setDescriptor("src/main/webapp/WEB-INF/web.xml");
        context.setResourceBase("src/main/webapp");
        context.setContextPath("/");
        context.setParentLoaderPriority(true);
 
        server.setHandler(context);
 
        server.start();
        server.join();
	}
}
