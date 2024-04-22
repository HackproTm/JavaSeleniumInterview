package Core.System;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import Core.Resources.Messages;

public class Functions {

	public static String getFileContent(String pathFile) throws IOException {
        if (pathFile == null || pathFile.isEmpty()) {
            throw new IllegalArgumentException(Messages.ErrorParameterIsNull("pathFile"));
        }
        if (!Files.exists(Paths.get(pathFile))) {
            throw new IOException(Messages.ErrorFilePathNotExist(pathFile));
        }
        try {
            return new String(Files.readAllBytes(Paths.get(pathFile)));
        } catch (IOException e) {
            return "";
        }
    }
	
}
