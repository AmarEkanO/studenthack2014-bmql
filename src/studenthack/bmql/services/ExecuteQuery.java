package studenthack.bmql.services;

import com.bloomberglp.blpapi.*;

import studenthack.bmql.core.*;

public class ExecuteQuery {
	
	public Object run(Message msg, String queryStr)
	{
		QueryParser parser = new QueryParser();
		Query query = parser.getQuery(queryStr);

		if(query != null)
		{
			//AM > Message and Query are fed into the QueryEngine
			QueryEngine engine = new QueryEngine();

			//AM > Get results from query engine
			Object result = engine.getResults(msg, query);
			
			return result;
		}
		return null;
	}
}
