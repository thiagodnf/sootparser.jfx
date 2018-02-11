package thiagodnf.sootparser.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;

public class FileUtil {
	
	public static List<String> getFiles(String directory){
		
		if(StringUtils.isEmpty(directory)) {
			return Arrays.asList();
		}
		
		try {
			return Files.walk(Paths.get(directory))
				     .filter(Files::isRegularFile)
				     .filter(p -> !p.endsWith(".DS_Store"))
				     .map(path -> path.toFile().getAbsolutePath())
				     .collect(Collectors.toList());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return Arrays.asList();
	}
	
	public static List<String> getClasses(String directory){
		
		if(StringUtils.isEmpty(directory)) {
			return Arrays.asList();
		}
		
		try {
			return Files.walk(Paths.get(directory))
				     .filter(Files::isRegularFile)
				     .filter(p -> !p.endsWith(".DS_Store"))
				     .map(path -> path.toFile().getAbsolutePath())
				     .map(s -> s.replaceAll(directory, ""))
				     .map(s -> s.replaceAll(File.separator, "."))
				     .map(s -> s.substring(1))
				     .map(s -> s.substring(0, s.length()-6))
				     .collect(Collectors.toList());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
		return Arrays.asList();
	}

}
