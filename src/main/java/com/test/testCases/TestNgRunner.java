package com.test.testCases;

import org.testng.TestNG;
import org.testng.collections.Lists;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

//This runner class is used for creating an executable JAR file takes parameter *.xml file
// for running the test case while running from the command prompt
class TestNGRunner {

    public static void main(String[] args) {
        Path currentRelativePath = Paths.get("");
        String path = currentRelativePath.toAbsolutePath().toString();
        TestNG testng = new TestNG();
        List<String> suites = Lists.newArrayList();
        suites.add(path + "\\" + args[0]);
        testng.setTestSuites(suites);
        testng.run();
    }
}
