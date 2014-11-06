package studenthack.bmql.services;
import java.util.List;

import com.bloomberglp.blpapi.*;

import studenthack.bmql.core.*;
import java.util.regex.*;
public class QueryEngine {
	
	public Object getResults(Message msg, Query query)
	{
		
		Element msgElement = msg.asElement();
		
		return getObject(msgElement, query.getElements());
		
	}
	
	private Object getObject(Element element, List<String> elements)
	{
		if(elements.size() == 0)
			return element;
		else
		{
		//AM > If element in query has an index then treat the element under consideration as array
		if( elements.get(0).endsWith("]"))
		{	
			Pattern p = Pattern.compile("\\[(\\d+)\\]");
			Matcher m = p.matcher(elements.get(0));

			if (m.find()) {
			    element = element.getValueAsElement(Integer.parseInt(m.group(1)));
			    elements.remove(0);
			    return getObject(element, elements);
			}
		}	
		else if(element.hasElement(elements.get(0)))
			{	
				element = element.getElement(elements.get(0));
				elements.remove(0);
				return getObject(element, elements);
			}
		}
		return null;
	}
}
