package ru.job4j.io;

import java.io.*;


public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader input = new BufferedReader(
                new FileReader(source));
             PrintWriter output = new PrintWriter(
                     new BufferedWriter(new FileWriter(target)))) {
            StringBuilder targetBuild = new StringBuilder();
            boolean skipFlag = false;
            for (String line : input.lines().toList()) {
                String[] words = line.split(" ");
                if (skipFlag) {
                    if ("200".equals(words[0]) || "300".equals(words[0])) {
                        targetBuild.append(words[1]).append(";\n");
                        skipFlag = false;
                    }
                } else if ("400".equals(words[0]) || "500".equals(words[0])) {
                    skipFlag = true;
                    targetBuild.append(words[1]).append(";");
                }
            }
            output.write(targetBuild.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}