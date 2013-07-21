package pl.jsolve.sweetener.io;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.FileVisitResult.CONTINUE;

public class Finder extends SimpleFileVisitor<Path> {

	private final PathMatcher matcher;
	private final List<Path> paths = new ArrayList<Path>();

	Finder(String pattern) {
		matcher = FileSystems.getDefault().getPathMatcher("glob:" + pattern);
	}

	void find(Path file) {
		Path fileName = file.getFileName();
		if (fileName != null && matcher.matches(fileName)) {
			paths.add(file);
		}
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
		find(file);
		return CONTINUE;
	}

	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
		find(dir);
		return CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) {
		// Ignore failed file
		return CONTINUE;
	}

	List<Path> getPaths() {
		return paths;
	}
}