package sid2user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Sid2User {

	private String pathSoftware;
	private String sid;
	private String netbiosHostname;
	private boolean verbose;
	private int start;
	private int end;

	public Sid2User(String pathSoftware, String netbiosHostname, String sid, int start, int end) {
		this.pathSoftware = pathSoftware;
		this.netbiosHostname = netbiosHostname;
		this.sid = sid;
		this.verbose = false;
		this.start = start;
		this.end = end;
	}

	public ProcessBuilder builderProcess(int rid) {
		ProcessBuilder pb;
		List<String> listSID = builderSID(sid);
		listSID.set(0, pathSoftware);
		listSID.set(1, netbiosHostname);
		listSID.set(listSID.size() - 1, String.valueOf(rid));
		pb = new ProcessBuilder(listSID);

		return pb;
	}

	private List<String> builderSID(String SID) {
		StringTokenizer token = new StringTokenizer(SID, "-");
		List<String> listSID = new ArrayList<>();
		while (token.hasMoreTokens()) {
			listSID.add(token.nextToken());
		}
		return listSID;
	}

	public synchronized String readResults(ProcessBuilder pb, int rid) throws IOException {
		Process p = pb.start();
		InputStream is = p.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String line = "";
		StringBuilder result = new StringBuilder();
		while ((line = br.readLine()) != null) {
			if (line.length() > 1 && !verbose) {
				if (!line.contains("failed") && line.contains("Name is")) {
					result.append("[RID] ");
					result.append(rid);
					result.append("\t");
					result.append(line);
				}

			} else if (line.length() > 1 && verbose) {
				result.append("[RID] ");
				result.append(rid);
				result.append("\t");
				result.append(line);
			}
		}
		is.close();
		isr.close();
		br.close();
		return result.toString();
	}

	public String getPathSoftware() {
		return pathSoftware;
	}

	public void setPathSoftware(String pathSoftware) {
		this.pathSoftware = pathSoftware;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getNetbiosHostname() {
		return netbiosHostname;
	}

	public void setNetbiosHostname(String netbiosHostname) {
		this.netbiosHostname = netbiosHostname;
	}

	public boolean isVerbose() {
		return verbose;
	}

	public void setVerbose(boolean verbose) {
		this.verbose = verbose;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

}
