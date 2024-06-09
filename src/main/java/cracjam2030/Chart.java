package cracjam2030;

import java.util.ArrayList;
import java.util.List;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.demo.charts.ExampleChart;
import org.knowm.xchart.style.Styler.LegendPosition;

public class Chart {

    public static void generateChart( String chartType) {

        switch (chartType) {
            case "1": Chart1.generateChart(Main.logger);
            break;
            case "2": Chart2.generateChart(Main.logger);
                break;
            case "3": Chart3.generateChart(Main.logger);
                break;
        }
    }

}
