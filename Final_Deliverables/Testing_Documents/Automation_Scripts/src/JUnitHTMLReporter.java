package Automated_JUnit_Tests;

import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import static java.awt.Color.green;
import static java.awt.Color.red;

public class JUnitHTMLReporter {

    static File junitReport;
    static BufferedWriter junitWriter;

    @BeforeClass
    public static void setUp() throws IOException {

        String junitReportFile = System.getProperty("user.dir")
                + "\\junitTestReportFile.html";
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        Date date = new Date();
        junitReport = new File(junitReportFile);
        junitWriter = new BufferedWriter(new FileWriter(junitReport, true));
        junitWriter.write("<html><body>");
        junitWriter.write("<h1>Test Execution Summary - " + dateFormat.format(date)
                + "</h1>");

    }

    @AfterClass
    public static void tearDown() throws IOException {

        junitWriter.write("</body></html>");
        junitWriter.close();
        Desktop.getDesktop().browse(junitReport.toURI());

    }

    @Rule
    public TestRule watchman = new TestWatcher() {

        @Override
        public Statement apply(Statement base, Description description) {
            return super.apply(base, description);
        }

        @Override
        protected void succeeded(Description description) {
            try {
                junitWriter.write("<br/>");
                junitWriter.write(description.getDisplayName() + "&nbsp;"
                        + "*******SUCCESS!******* ");
                junitWriter.write("<br/>");
            } catch (Exception e1) {
                System.out.println(e1.getMessage());
            }
        }

        @Override
        protected void failed(Throwable e, Description description) {
            try {
                junitWriter.write(description.getDisplayName() + " "
                        + e.getClass().getSimpleName());
                junitWriter.write("<br/>");
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }
        }
    };
}