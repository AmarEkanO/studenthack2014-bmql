package studenthack.bmql.services;

import studenthack.bmql.core.*;

//AM > This class takes a query string and tokenizes it for validation
public class QueryParser {
	
	
	public Query getQuery(String queryStr)
	{
		Query query = new Query();
		//AM > Check for empty strings
		if(queryStr.length() < 1)
			return null;
		
		for(String token : queryStr.split("/"))
		{
			if(token.length() < 1)
				return null;
			
			query.add(token);
		}
		
		return query;
	}
}
