package setting;

import java.io.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import setting.DefaultSetting;

public class ReadPreviousSetting {
	
	private static ArrayList<String> array = new ArrayList<String>();//파일 내용
	private static int LEFT, RIGHT, DOWN, ROTATE, DROP = 0;
	private static int Level, Size, ColorBlindMode = 0;
	private static Map<String, String> setting = new  ConcurrentHashMap<String, String>();//파일-설정값

	
	
	DefaultSetting defaultSetting = new DefaultSetting();

	
	public ReadPreviousSetting() throws IOException {		
		readWriteFile();
		
	}
	
	public String getFileSetting(String word) {
		return setting.get(word);
	}
	
	public void changeFileSetting(String key, String value) throws IOException {
		setting.replace(key, value);
		String path = ReadPreviousSetting.class.getResource("").getPath();
		File inSamePackage = new File (path + "userSetted.txt");
		
		PrintWriter pw = new PrintWriter(inSamePackage);

		pw.println("LEFT");
        pw.println(setting.get("LEFT"));
        pw.println("RIGHT");
        pw.println(setting.get("RIGHT"));
        pw.println("DOWN");
        pw.println(setting.get("DOWN"));
        pw.println("DROP");
        pw.println(setting.get("DROP"));
        pw.println("ROTATE");
        pw.println(setting.get("ROTATE"));
        pw.println("Level");
        pw.println(setting.get("Level"));
        pw.println("Size");
        pw.println(setting.get("Size"));
        pw.println("ColorBlindMode");
        pw.println(setting.get("ColorBlindMode"));
        
        pw.close();
        
        array.clear();
        
        BufferedReader br = new BufferedReader (new FileReader(inSamePackage));
		
		int nth = 1;
		
		while(true) {
			String line = br.readLine();
			if (line==null) break;
			array.add(line);
			isItThere(line,nth);
			nth ++;
		}
		br.close();

	
	}

//지나면 무조건 파일 있음 + array에 내용 저장됨 + setting 맵 생김	
	private void readWriteFile() throws IOException {
		createSetting();

		String path = ReadPreviousSetting.class.getResource("").getPath();
		File inSamePackage = new File (path + "userSetted.txt");	
		
		
		if (inSamePackage.isFile()) {
		}
		else {
			PrintWriter pw = new PrintWriter(inSamePackage);

	        pw.println("LEFT");
	        pw.println(defaultSetting.getDefaultKey("LEFT"));
	        pw.println("RIGHT");
	        pw.println(defaultSetting.getDefaultKey("RIGHT"));
	        pw.println("DOWN");
	        pw.println(defaultSetting.getDefaultKey("DOWN"));
	        pw.println("DROP");
	        pw.println(defaultSetting.getDefaultKey("DROP"));
	        pw.println("ROTATE");
	        pw.println(defaultSetting.getDefaultKey("ROTATE"));
	        pw.println("Level");
	        pw.println(defaultSetting.getDefaultLevel());
	        pw.println("Size");
	        pw.println(defaultSetting.getDefaultSize());
	        pw.println("ColorBlindMode");
	        pw.println(defaultSetting.getDefaultColorBlindMode());
	        
	        pw.close();
			
		}
		
		BufferedReader br = new BufferedReader (new FileReader(inSamePackage));
		
		int nth = 1;
		
		while(true) {
			String line = br.readLine();
			if (line==null) break;
			array.add(line);
			isItThere(line,nth);
			nth ++;
		}
		br.close();
		
		setSetting();

		
		
		if(setting.get("LEFT")=="") {
			PrintWriter pw = new PrintWriter(new FileWriter(inSamePackage, true));
	        pw.println("LEFT");
	        pw.println(defaultSetting.getDefaultKey("LEFT"));
	        pw.close();			
		}
		if(setting.get("RIGHT")=="") {
			PrintWriter pw = new PrintWriter(new FileWriter(inSamePackage, true));
			pw.println("RIGHT");
			pw.println(defaultSetting.getDefaultKey("RIGHT"));
			pw.close();			
		}
		if(setting.get("DROP")=="") {
			PrintWriter pw = new PrintWriter(new FileWriter(inSamePackage, true));
			pw.println("DROP");
			pw.println(defaultSetting.getDefaultKey("DROP"));
			pw.close();			
		}
		if(setting.get("ROTATE")=="") {
			PrintWriter pw = new PrintWriter(new FileWriter(inSamePackage, true));
			pw.println("ROTATE");
			pw.println(defaultSetting.getDefaultKey("ROTATE"));
			pw.close();			
		}
		if(setting.get("DOWN")=="") {
			PrintWriter pw = new PrintWriter(new FileWriter(inSamePackage, true));
			pw.println("DOWN");
			pw.println(defaultSetting.getDefaultKey("DOWN"));
			pw.close();			
		}
		
		BufferedReader br1 = new BufferedReader (new FileReader(inSamePackage));
		
		nth = 1;
		
		while(true) {
			String line = br1.readLine();
			if (line==null) break;
			array.add(line);
			isItThere(line,nth);
			nth ++;
		}
		br1.close();
		
		
		
		setSetting();

		
	}
	
	private void isItThere(String word, int nth) {
		switch (word) {
			case "LEFT": LEFT = nth;
						 break;
			case "RIGHT": RIGHT = nth;
						 break;
			case "DOWN": DOWN = nth;
			 			 break;
			case "DROP": DROP= nth;
			 			 break;
			case "ROTATE": ROTATE = nth;
						 break;
			case "Level": Level= nth;
						 break;
			case "Size": Size = nth;
						 break;
			case "ColorBlindMode": ColorBlindMode = nth;
						 break;
			default: break;
		
		
		}
	}

	private void createSetting() {
		setting.put("LEFT", "");
		setting.put("RIGHT", "");
		setting.put("DOWN", "");
		setting.put("DROP", "");
		setting.put("ROTATE", "");
		setting.put("Level", "");
		setting.put("Size", "");
		setting.put("ColorBlindMode", "");
		
	}
	
	private void setSetting() {		
		if (LEFT != 0) setting.replace("LEFT", array.get(LEFT));
		if (RIGHT != 0) setting.replace("RIGHT", array.get(RIGHT));
		if (DOWN != 0) setting.replace("DOWN", array.get(DOWN));
		if (DROP != 0) setting.replace("DROP", array.get(DROP));
		if (ROTATE != 0) setting.replace("ROTATE", array.get(ROTATE));
		if (Level != 0) setting.replace("Level", array.get(Level));
		if (Size != 0) setting.replace("Size", array.get(Size));
		if (ColorBlindMode != 0) setting.replace("ColorBlindMode", array.get(ColorBlindMode));
		
	}


}
