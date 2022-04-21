package setting;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import setting.DefaultSetting;

public class UserSetted {
	
	private String path = UserSetted.class.getResource("").getPath() + "userSetted.txt";	
	private static Map<String, Integer> setting = new  ConcurrentHashMap<String, Integer>();//파일-설정값
	private static ArrayList<String> array = new ArrayList<String>();//파일 내용

	
	private File file = new File(path);
	
	public UserSetted() {
		setSetting();		
	}
	
	private void setSetting() {
		setting.put("LEFT", 0);
		setting.put("RIGHT", 1);
		setting.put("DROP", 2);
		setting.put("DOWN", 3);
		setting.put("ROTATE", 4);
		setting.put("colorBlindMode", 5);
		setting.put("difficultyLevel", 6);
		setting.put("size", 7);
	}
	
	private void readFile() {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(path));
			
			try {
				while(true) {
					String val = br.readLine();
					if (val == null) break;
					array.add(val);					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			try {
				if (file.createNewFile()) {
				System.out.println("File created");
				} else {
				System.out.println("File already exists");
				}
				} catch (IOException i) {
				i.printStackTrace();
				}
			e.printStackTrace();
		}
		
	}
	
	public void writeFileDefault(){
		DefaultSetting defaultSetting = new DefaultSetting();
		
		BufferedWriter bw = null;		
			try {
				FileWriter pw = new FileWriter(file, true);
				System.out.println("write it down");
				
				bw = new BufferedWriter(pw);
				
		        bw.write(defaultSetting.getDefaultKey("LEFT"));
		        bw.write(defaultSetting.getDefaultKey("RIGHT"));
		        bw.write(defaultSetting.getDefaultKey("DROP"));
		        pw.write(defaultSetting.getDefaultKey("DOWN"));
		        pw.write(defaultSetting.getDefaultKey("ROTATE"));
		        pw.write(String.valueOf(defaultSetting.getDefaultColorBlindMode()));
		        pw.write(defaultSetting.getDefaultLevel());
		        pw.write(defaultSetting.getDefaultSize());
		        
		        bw.close();
	

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

}
