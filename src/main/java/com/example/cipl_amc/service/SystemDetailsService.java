package com.example.cipl_amc.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Service;

import com.example.cipl_amc.entity.PMReport;

import oshi.SystemInfo;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;
import oshi.util.FormatUtil;

@Service
public class SystemDetailsService {
    private final ExecutorService executor = Executors.newFixedThreadPool(10);
	
	public PMReport fetchSystemDetails() {
		SystemInfo systemInfo = new SystemInfo();
		HardwareAbstractionLayer hardware = systemInfo.getHardware();
		OperatingSystem os = systemInfo.getOperatingSystem();
		
		PMReport pmReport = new PMReport();
		
		pmReport.setCpuMakeModel(hardware.getProcessor().getProcessorIdentifier().getName());
		pmReport.setCpuSerialNo(hardware.getProcessor().getProcessorIdentifier().getProcessorID());
		pmReport.setRamCapacity(FormatUtil.formatBytes(hardware.getMemory().getTotal()));
		pmReport.setOperatingSystem(os.getFamily());
		pmReport.setOsVersion(os.getVersionInfo().getVersion());
		pmReport.setIpAddress(getIpAddress());
		pmReport.setComputerName(os.getNetworkParams().getHostName());
		pmReport.setJavaVer(System.getProperty("java.version"));
		
		pmReport.setChromeVer(getChromeVersion());
//		pmReport.setHddType(getHDDType());
//  //	pmReport.setProcessorType(getProcessorType());
//		pmReport.setDvdType(getDVDType());
//		pmReport.setMsOfficeVer(getMSOfficeVersion());
//		pmReport.setOutlookPatchVer(getOutlookPatchVersion());
//		pmReport.setDscDriverVer(getDscDriverVersion());
//		pmReport.setAdobeVer(getAdobeReaderVersion());
//		pmReport.setSapVersion(getSapVersion());
//		
//		String monitorDetails = getMonitorDetails();
//	    pmReport.setMonitorModel(monitorDetails.contains("Model:") ? monitorDetails.split("Model:")[1].split(",")[0].trim() : "Unknown");
//	    pmReport.setMonitorSno(monitorDetails.contains("Serial:") ? monitorDetails.split("Serial:")[1].trim() : "Unknown");
//	    
//	    pmReport.setKeyboardType(getKeyboardType());
//	    pmReport.setMouseType(getMouseType());
//	    pmReport.setKb(getKeyboardSerialNumber());	
//	    pmReport.setMs(getMouseSerialNumber());
//	    
//	    pmReport.setTrellixAgentVer(getSoftwareVersion("Trellix Agent"));
//	    pmReport.setTrellixDLPVer(getSoftwareVersion("Trellix DLP"));
//	    pmReport.setTrellixEPSecVer(getSoftwareVersion("Trellix Endpoint Security"));
//	    pmReport.setTrellixThreatPre(getSoftwareVersion("Trellix Threat Prevention"));
//	    pmReport.setSymantecVer(getSoftwareVersion("Symantec Endpoint Protection"));
//	    pmReport.setTrendEPACVer(getSoftwareVersion("Trend Micro EPAC"));
//	    pmReport.setUltraVNCVer(getSoftwareVersion("UltraVNC"));
//	    pmReport.setBigFixTarget(getSoftwareVersion("BigFix Remote Control Target"));
		
		CompletableFuture<Void> ipFuture = CompletableFuture.runAsync(() -> 
        pmReport.setIpAddress(getIpAddress()), executor);
//		CompletableFuture<Void> chromeFuture = CompletableFuture.runAsync(() -> 
//		pmReport.setChromeVer(getSoftwareVersion("Google Chrome")), executor);
//		CompletableFuture<Void> hddFuture = CompletableFuture.runAsync(() -> 
//		pmReport.setHddType(getHDDType()), executor);
//		CompletableFuture<Void> dvdFuture = CompletableFuture.runAsync(() -> 
//		pmReport.setDvdType(getDVDType()), executor); 
//		CompletableFuture<Void> msOfficeFuture = CompletableFuture.runAsync(() ->
//		pmReport.setMsOfficeVer(getMSOfficeVersion()), executor);
//		CompletableFuture<Void> outlookFuture = CompletableFuture.runAsync(() ->
//		pmReport.setOutlookPatchVer(getOutlookPatchVersion()), executor);
//		CompletableFuture<Void> dscFuture = CompletableFuture.runAsync(() -> 
//		pmReport.setDscDriverVer(getDscDriverVersion()), executor);
//		CompletableFuture adobeFuture = CompletableFuture.runAsync(() ->
//		pmReport.setAdobeVer(getAdobeReaderVersion()), executor);
//		CompletableFuture sapFuture = CompletableFuture.runAsync(() -> 
//		pmReport.setSapVersion(getSapVersion()), executor);
		
		CompletableFuture<Void> softwareVersions = CompletableFuture.runAsync(() -> {
	        pmReport.setMsOfficeVer(getMSOfficeVersion());
	        pmReport.setOutlookPatchVer(getOutlookPatchVersion());
	        pmReport.setDscDriverVer(getDscDriverVersion());
	        pmReport.setAdobeVer(getAdobeReaderVersion());
	        pmReport.setSapVersion(getSapVersion());
	    }, executor);
		
		CompletableFuture<Void> hardwareVersions = CompletableFuture.runAsync(() ->{
			pmReport.setHddType(getHDDType());
			pmReport.setDvdType(getDVDType());
			pmReport.setKeyboardType(getKeyboardType());
			pmReport.setMouseType(getMouseType());
			pmReport.setKb(getKeyboardSerialNumber());
			pmReport.setMs(getMouseSerialNumber());
		}, executor);
		
		CompletableFuture<Void> securitySoftwareFuture = CompletableFuture.runAsync(() -> {
			pmReport.setTrellixAgentVer(getSoftwareVersion("Trellix Agent"));
			pmReport.setTrellixDLPVer(getSoftwareVersion("Trellix DLP"));
			pmReport.setTrellixEPSecVer(getSoftwareVersion("Trellix Endpoint Security"));
			pmReport.setTrellixThreatPre(getSoftwareVersion("Trellix Threat Prevention"));
			pmReport.setSymantecVer(getSoftwareVersion("Symantec Endpoint Protection"));
			pmReport.setTrendEPACVer(getSoftwareVersion("Trend Micro EPAC"));
			pmReport.setUltraVNCVer(getSoftwareVersion("UltraVNC"));
			pmReport.setBigFixTarget(getSoftwareVersion("BigFix Remote Control Target"));
		}, executor);
		
		CompletableFuture<Void> peripheralsFuture = CompletableFuture.runAsync(() -> {
			String monitorDetails = getMonitorDetails();
			pmReport.setMonitorModel(monitorDetails.contains("Model:") ? monitorDetails.split("Model:")[1].split(",")[0].trim() : "Unknown");
			pmReport.setMonitorSno(monitorDetails.contains("Serial:") ? monitorDetails.split("Serial:")[1].trim() : "Unknown");
		}, executor);
		
		CompletableFuture.allOf(ipFuture, softwareVersions, hardwareVersions, securitySoftwareFuture, peripheralsFuture).join();
		
	    return pmReport;
	}
	
	private String getIpAddress() {
		try {
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
			while(interfaces.hasMoreElements()) {
				NetworkInterface networkInterface = interfaces.nextElement();
				if(networkInterface.isUp() && !networkInterface.isLoopback()) {
					Enumeration<InetAddress> addresses = networkInterface.getInetAddresses();
					while(addresses.hasMoreElements()) {
						InetAddress address = addresses.nextElement();
						if(!address.isLoopbackAddress() && address.isSiteLocalAddress()) {
							return address.getHostAddress();
						}
					}
				}
			}
		} catch(SocketException e) {
			e.printStackTrace();
		}
		 return "Unknown";
	}
	
	private String getChromeVersion() {
		String os = System.getProperty("os.name").toLowerCase();
		if(os.contains("win")) {
			try {
				Process process = Runtime.getRuntime().exec("reg query \"HKEY_CURRENT_USER\\Software\\Google\\Chrome\\BLBeacon\" /v version");
				BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
				String line;
				while((line = reader.readLine()) != null) {
					if(line.contains("version")) {
						return line.split("\\s+")[line.split("\\s+").length - 1];
					}
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		return "Unknown";
	}
	
	private String getMonitorDetails() {
	    try {
	        Process process = Runtime.getRuntime().exec("powershell -command \"Get-CimInstance WmiMonitorID | ForEach-Object { $_.UserFriendlyName, $_.SerialNumberID }\"");
	        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
	        String model = reader.readLine();
	        String serial = reader.readLine();

	        if (model != null && serial != null) {
	            return "Model: " + model.trim() + ", Serial: " + serial.trim();
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return "Model: Unknown, Serial: Unknown";
	}
	
	private String getKeyboardType() {
	    try {
	        Process process = Runtime.getRuntime().exec("powershell -command \"Get-PnpDevice -Class Keyboard | ForEach-Object { $_.FriendlyName }\"");
	        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
	        String device = reader.readLine();

	        if (device != null) {
	            return device.toLowerCase().contains("bluetooth") ? "Wireless" : "Wired";
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return "Unknown";
	}
	
	private String getMouseType() {
	    try {
	        Process process = Runtime.getRuntime().exec("powershell -command \"Get-PnpDevice -Class Mouse | ForEach-Object { $_.FriendlyName }\"");
	        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
	        String device = reader.readLine();

	        if (device != null) {
	            return device.toLowerCase().contains("bluetooth") ? "Wireless" : "Wired";
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return "Unknown";
	}  

	private String getKeyboardSerialNumber() {
	    try {
	        Process process = Runtime.getRuntime().exec("wmic path Win32_Keyboard get DeviceID");
	        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
	        String line;
	        while ((line = reader.readLine()) != null) {
	            if (!line.trim().isEmpty() && !line.contains("DeviceID")) {
	                return line.trim();  
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return "Unknown";
	}

	private String getMouseSerialNumber() {
	    try {
	        Process process = Runtime.getRuntime().exec("wmic path Win32_PointingDevice get DeviceID");
	        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
	        String line;
	        while ((line = reader.readLine()) != null) {
	            if (!line.trim().isEmpty() && !line.contains("DeviceID")) {
	                return line.trim();  // Serial Number for Mouse
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return "Unknown";
	}
	
	private String getHDDType() {
		try {
			Process process = Runtime.getRuntime().exec("wmic diskdrive get Size");
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while((line = reader.readLine()) != null) {
				if(line.trim().matches("\\d+")) {
					long sizeInBytes = Long.parseLong(line.trim());
					return getExactCapacity(sizeInBytes);
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		return "Unknown";
	}
	
	private String getExactCapacity(long sizeInBytes) {
		long sizeInGB = sizeInBytes / (1024 * 1024 * 1024);
		long sizeInTB = sizeInGB / 1024;
		
		if(sizeInTB >= 2) return "2TB";
		if(sizeInTB >= 1) return "1TB";
		if(sizeInGB >= 512) return "512GB";
		if(sizeInGB >= 256) return "256GB";
		if(sizeInGB >= 128) return "128GB";
		
		return sizeInGB + "GB";
	}

	private String getProcessorType() {
		try {
			Process process = Runtime.getRuntime().exec("wmic cpu get Name");
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while((line = reader.readLine()) != null) {
				if(!line.trim().isEmpty() && !line.contains("Name")) {
					return line.trim();
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		return "Unknown";
	}

	
	private String getDVDType() {
		try {
			Process process = Runtime.getRuntime().exec("wmic cdrom get Name");
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while((line = reader.readLine()) != null) {
				if(!line.trim().isEmpty() && !line.contains("Name")) {
					return line.trim();
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		return "N/A";
	}
	
	private String getMSOfficeVersion() {
		try {
			Process process = Runtime.getRuntime().exec("reg query \"HKEY_LOCAL_MACHINE\\SOFTWARE\\Microsoft\\Office\\ClickToRun\\Configuration\" /v VersionToReport");
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while((line = reader.readLine()) != null) {
				if(line.contains("VersionToReport")) {
					String version = line.replaceAll(".*VersionToReport\\s+REG_SZ\\s+(\\d+\\.\\d+).*", "$1");
					return mapOfficeVersion(version);
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		return "Unknown";
   }
	
	private static String mapOfficeVersion(String version) {
		Map<String, String> versionMap = new HashMap<>();
		versionMap.put("16.0", "Office 2016 / 2019 / 2021 / 365");
		
		if(version.startsWith("16.0")) {
			return detectOfficeEdition();
		}
		return "Unknown Office Version";
	}
	
	private static String detectOfficeEdition() {
		try {
			Process process = Runtime.getRuntime().exec("reg query \"HKEY_LOCAL_MACHINE\\SOFTWARE\\Microsoft\\Office\\ClickToRun\\Configuration\" /v ProductReleaseIds");
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while((line = reader.readLine()) != null) {
				if(line.contains("ProductReleaseIds")) {
					if(line.contains("O365")) return "Microsoft Office 365";
					if(line.contains("Retail2019")) return "Microsoft Office 2019";
					if(line.contains("Retail2021")) return "Microsoft Office 2021";
					if(line.contains("Retail2016")) return "Microsoft Office 2016";
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		return "Office 2016 / 2019 / 2021(Exact Edition Unknown)";
	}
	
	private String getOutlookPatchVersion() {
		try {
			Process process = Runtime.getRuntime().exec("reg query \"HKEY_LOCAL_MACHINE\\SOFTWARE\\Microsoft\\Office\\Outlook\" /s");
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while((line = reader.readLine()) != null) {
				if(line.contains("Version") && line.matches(".*\\d+\\.\\d+.*")) {
					return line.replaceAll(".*(\\d+\\.\\d+).*", "$1");
				}
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		return "Unknown";
	}
	
	private String getDscDriverVersion() {
		try {
			Process process = Runtime.getRuntime().exec("wmic sysdriver where \"Description like '%DSC%'\" get Version");
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while((line = reader.readLine()) != null) {
				if(line.trim().isEmpty() && !line.contains("Version")) {
					return line.trim();
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		return "Unknown";
	}
	
	private String getAdobeReaderVersion() {
	    try {
	        Process process = Runtime.getRuntime().exec("reg query \"HKEY_LOCAL_MACHINE\\SOFTWARE\\Adobe\\Acrobat Reader\" /s /f \"Version\"");
	        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
	        String line;
	        while ((line = reader.readLine()) != null) {
	            if (line.contains("Version")) {
	                // Extracting the version
	                return line.replaceAll(".*Version\\s+REG_SZ\\s+(\\d+\\.\\d+\\.\\d+).*", "$1");
	            }
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return "Unknown"; // If no version found
	}
	
	private String getLapsVersion() {
		try {
			Process process = Runtime.getRuntime().exec("reg query \"HKEY_LOCAL_MACHINE\\SOFTWARE\\Microsoft\\Windows\\CurrentVersion\\Policies\\LAPS\" /s");
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while((line = reader.readLine()) != null) {
				if(line.contains("LAPS")) {
					 String version = line.replaceAll(".*Version\\s+REG_SZ\\s+(\\d+\\.\\d+\\.\\d+).*", "$1");
		                return version;
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		return "Unknown";
	}
	
	private String getSapVersion() {
		try {
			Process process = Runtime.getRuntime().exec("reg query \"HKEY_LOCAL_MACHINE\\SOFTWARE\\SAP\\SAPgui\" /s /f \"Version\"");
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while((line = reader.readLine()) != null) {
				if (line.contains("Version")) {
	                return line.split("\\s+")[line.split("\\s+").length - 1];  // Extract and return the version
	            }
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		return "Unknown";
	}
	
	private String getSoftwareVersion(String softwareName) {
		try {
			Process process = Runtime.getRuntime().exec("wmic product where \"Name like '%" + softwareName + "%'\" get Version");
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while((line = reader.readLine()) != null) {
				if(line.trim().matches("\\d+\\.\\d+\\.\\d+.*")) {
					return line.trim();
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		return "Unknown";
	}
	
	private String getRegistryValue(String registryPath, String key) {
		try {
			Process process = Runtime.getRuntime().exec("reg query" + registryPath + " /v" + key);
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while((line = reader.readLine()) != null) {
				if(line.contains(key)) {
					return line.split("\\s+")[line.split("\\s+").length - 1];
				}
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
		return "Unknown";
	}
}

