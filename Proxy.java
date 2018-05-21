import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

public interface ReportGenerator extends Remote{

	public String generateDailyReport() throws RemoteException;

}




public class ReportGeneratorImpl extends UnicastRemoteObject implements ReportGenerator{

	private static final long serialVersionUID = 3107413009881629428L;

	protected ReportGeneratorImpl() throws RemoteException {
	}

	@Override
	public String generateDailyReport() throws RemoteException {
		StringBuilder sb = new StringBuilder();
		sb.append("********************Location X Daily Report********************");
		sb.append("\\n Location ID: 012");
		sb.append("\\n Today's Date: "+new Date());
		sb.append("\\n Total Pizza's Sell: 112");
		sb.append("\\n Total Price: $2534");
		sb.append("\\n Net Profit: $1985");
		sb.append("\\n ***************************************************************");

		return sb.toString();
	}

	public static void main(String[] args) {

		try {
			ReportGenerator reportGenerator = new ReportGeneratorImpl();
			Naming.rebind("PizzaCoRemoteGenerator", reportGenerator);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}





public class ReportGeneratorClient {

	public static void main(String[] args) {
		new ReportGeneratorClient().generateReport();
	}

	public void generateReport(){
		try {
			ReportGenerator reportGenerator = (ReportGenerator)Naming.lookup("rmi://127.0.0.1/PizzaCoRemoteGenerator");
			System.out.println(reportGenerator.generateDailyReport());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
