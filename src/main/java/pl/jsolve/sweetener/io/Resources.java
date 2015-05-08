package pl.jsolve.sweetener.io;

import pl.jsolve.sweetener.collection.Collections;
import pl.jsolve.sweetener.exception.FileNotFoundException;
import pl.jsolve.sweetener.exception.ResourceException;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public final class Resources {

    private static final String NEW_LINE = "\n";

    private Resources() {
        throw new AssertionError("Using constructor of this class is prohibited.");
    }

    public static List<Path> findFilePaths(String fileName) {
        return findPaths("", fileName);
    }

    public static List<Path> findFilePaths(String startingDir, String fileName) {
        return findPaths(startingDir, fileName);
    }

    private static List<Path> findPaths(String startingDirAsString, String fileName) {
        try {
            Path startingDir = Paths.get(startingDirAsString);
            Finder finder = new Finder(fileName);
            Files.walkFileTree(startingDir, finder);
            return finder.getPaths();
        } catch (IOException ioe) {
            return Collections.newArrayList();
        }
    }

    public static File asFile(Path path) {
        return path.toFile();
    }

    public static File asFile(URL url, String fileName) {
        File file = new File(fileName);
        return asFile(url, file);
    }

    public static File asFile(URL url, File file) {
        try {
            InputStream in = url.openStream();
            OutputStream outputStream = new FileOutputStream(file);
            asOutputStream(in, outputStream);
            return file;
        } catch (Exception ex) {
            throw new ResourceException(url.toString(), ex);
        }
    }

    public static OutputStream asOutputStream(InputStream is, OutputStream os) {
        try {
            int read;
            byte[] bytes = new byte[1024];

            while ((read = is.read(bytes)) != -1) {
                os.write(bytes, 0, read);
            }
            return os;
        } catch (Exception ex) {
            throw new ResourceException(ex);
        }
    }

    public static List<String> asListOfLines(Path path) {
        FileReader fileReader = createFileReader(path);
        return readLines(fileReader);
    }

    private static FileReader createFileReader(Path path) {
        try {
            return new FileReader(path.toFile());
        } catch (IOException ex) {
            throw new FileNotFoundException(path.toString());
        }
    }

    private static List<String> readLines(Reader fileReader) {
        try {
            List<String> lines = Collections.newArrayList();
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            try {
                String line = bufferedReader.readLine();
                while (line != null) {
                    lines.add(line);
                    line = bufferedReader.readLine();
                }
                return lines;
            } finally {
                bufferedReader.close();
            }
        } catch (IOException ex) {
            throw new ResourceException(ex);
        }
    }

    public static List<String> asListOfLines(URL url) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(url.openStream());
            return readLines(inputStreamReader);
        } catch (IOException e) {
            throw new ResourceException(url.toString(), e);
        }
    }

    public static String asString(Path path) {
        List<String> lines = asListOfLines(path);
        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            sb.append(line).append(NEW_LINE);
        }
        return sb.toString();
    }

    public static String asString(URL url) {
        List<String> lines = asListOfLines(url);
        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            sb.append(line).append(NEW_LINE);
        }
        return sb.toString();
    }

    public static InputStream asInputStream(Path path) {
        try {
            return new FileInputStream(path.toFile());
        } catch (java.io.FileNotFoundException e) {
            throw new FileNotFoundException(path.toString());
        }
    }

    public static InputStream asInputStream(URL url) {
        try {
            return url.openStream();
        } catch (IOException e) {
            throw new ResourceException(url.toString(), e);
        }
    }

    public static void closeStream(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
            throw new ResourceException(e);
        }
    }
}