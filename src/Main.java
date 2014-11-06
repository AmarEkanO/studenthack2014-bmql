import com.bloomberglp.blpapi.*;

import studenthack.bmql.core.*;
import studenthack.bmql.services.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

	private static Session appSession = null;

	// Setup session with server
	private static Session setupSession() throws Exception {
		if (appSession == null) {
			SessionOptions sessionOptions = new SessionOptions();
			sessionOptions.setServerHost("10.8.8.1");
			sessionOptions.setServerPort(8194);

			appSession = new Session(sessionOptions);

			if (!appSession.start()) {
				System.out.println("Unable to start session");
				System.exit(1);
			}

			if (!appSession.openService("//blp/refdata")) {
				System.out.println("Unable to open service");
				System.exit(1);
			}
			return appSession;
		} else
			return appSession;

	}

	public static void main(String args[]) {

		try {
			Session session = Main.setupSession();

			// Get organization and competitors
			Scanner input = new Scanner(System.in);
			System.out.println("Choose a organization?");
			String org = input.nextLine();

			Service refDataSvc = session.getService("//blp/refdata");
			// Request request =
			// refDataSvc.createRequest("ReferenceDataRequest");
			Request request = refDataSvc.createRequest("HistoricalDataRequest");

			Element securities = request.getElement("securities");
			// Add the clients organization
			securities.appendValue(org + " US " + " Equity");

			// Get a timeframe

			DateFormat df = new SimpleDateFormat("dd/mm/yy");

			System.out.println("Enter start of timeframe (dd/mm/yyyy)");
			Date startDate = df.parse(input.nextLine());

			System.out.println("Enter end of timeframe (dd/mm/yyyy)");
			Date endDate = df.parse(input.nextLine());

			if (endDate.getTime() < startDate.getTime()) {
				System.out.println("Invalid date entries");
				System.exit(1);
			}

			Element fields = request.getElement("fields");
			fields.appendValue("PX_LAST");
			fields.appendValue("OPEN");

			request.set("periodicityAdjustment", "ACTUAL");
			request.set("periodicitySelection", "DAILY");
			request.set("startDate",
					new SimpleDateFormat("yyyymmdd").format(startDate));
			request.set("endDate",
					new SimpleDateFormat("yyyymmdd").format(endDate));
			request.set("maxDataPoints", 100);
			request.set("returnEids", true);

			// Send the request
			session.sendRequest(request, null);
			System.out.print("Enter query string:");
			String queryStr = input.nextLine();
			while (true) {
				Event event = session.nextEvent();
				MessageIterator msgIter = event.messageIterator();
				Message msg = null;
				while (msgIter.hasNext()) {
					// For each message create a record

					msg = msgIter.next();

				}

				if (event.eventType() == Event.EventType.RESPONSE) {
					ExecuteQuery exeQuery = new ExecuteQuery();

					Object result = exeQuery.run(msg, queryStr);
					// AM > If query succeeds result is either a node or a value
					// (we'll force values to string to simplify life)
					// > If the query fails then return an empty list.
					if (result == null)
						System.out.println("No results to display");
					else
						System.out.println(result.toString());
					break;
				}
			}

		} catch (Exception e) {

		}

	}
}