package action;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.apache.struts2.dispatcher.ng.filter.StrutsPrepareAndExecuteFilter;

public class EncodeFilter extends StrutsPrepareAndExecuteFilter implements
		Filter
{
	private FilterConfig config = null;

	private String encoding = null;

	public void init(FilterConfig config) throws ServletException
	{
		this.config = config;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException
	{
		if (this.encoding == null)
		{
			this.encoding = this.config.getInitParameter("encoding");
		}
		request.setCharacterEncoding(this.encoding);
		response.setCharacterEncoding(this.encoding);
		chain.doFilter(request, response);
	}

	public void destroy()
	{
		this.config = null;
		this.encoding = null;
	}
}