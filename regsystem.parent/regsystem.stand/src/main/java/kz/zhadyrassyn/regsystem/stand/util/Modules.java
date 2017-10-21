package kz.zhadyrassyn.regsystem.stand.util;

import java.io.File;
import java.io.IOException;

public class Modules {

    private static File findDir(String moduleName) {
        {
            File point = new File(".");
            if (point.getAbsoluteFile().getName().equals(moduleName)) {
                return point;
            }
        }

        {
            File dir = new File(moduleName);
            try {
                if (dir.isDirectory() &&
                        dir.toPath().resolve("..").toFile().getCanonicalFile().getName().equals("regsystem.parent")) {
                    return dir;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        {
            File dir = new File("../" + moduleName);
            if (dir.isDirectory()) return dir;
        }

        throw new IllegalArgumentException("Cannot find directory " + moduleName);
    }

    public static File clientDir() {
        return findDir("regsystem.client");
    }

    public static File standDir() {
        return findDir("regsystem.stand");
    }

    public static File controllerDir() {
        return findDir("regsystem.controller");
    }
}
