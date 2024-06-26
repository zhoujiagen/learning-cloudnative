package com.spike.nio2.async.dir;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.DirectoryStream.Filter;

import static org.junit.Assert.assertTrue;

/**
 * Directory operations
 *
 * @author zhoujiagen
 */
public class DirManipulationTest {

    @Test
    public void listRootDirectories() {
        Iterable<Path> dirs = FileSystems.getDefault().getRootDirectories();
        for (Path dir : dirs) {
            System.out.println(dir);
        }
    }

    @Test
    public void create() throws Exception {
        Path newDir1 = Paths.get(System.getProperty("user.home"), "temp");
        Files.createDirectories(newDir1);
        assertTrue(Files.exists(newDir1));

        Path newDir2 = Paths.get(System.getProperty("user.home"), "temp/a/b");
        Files.createDirectories(newDir2);
        assertTrue(Files.exists(newDir2));
    }

    @Test
    public void listContent() {
        Path path = Paths.get(System.getProperty("user.home"));

        try (DirectoryStream<Path> ds = Files.newDirectoryStream(path, "*.{txt, jpg}")) {
            for (Path file : ds) {
                System.out.println(file.getFileName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void listContentUsingFilter() {
        Filter<Path> filter = new Filter<Path>() {

            @Override
            public boolean accept(Path entry) throws IOException {
                // self defined filter rules
                return entry.toString().endsWith("txt");
            }
        };

        Path path = Paths.get(System.getProperty("user.home"));
        try (DirectoryStream<Path> ds = Files.newDirectoryStream(path, filter)) {
            for (Path file : ds) {
                System.out.println(file.getFileName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
