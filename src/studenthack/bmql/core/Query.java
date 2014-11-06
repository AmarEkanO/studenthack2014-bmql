package studenthack.bmql.core;

import java.util.*;

public class Query {
	private List<String> queryElements = new ArrayList<String>();

	public List<String> getElements()
	{
		return queryElements;
	}
	
	public void add(String element)
	{
		queryElements.add(element);
	}
}
