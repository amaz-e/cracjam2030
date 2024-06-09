package cracjam2030;

import java.util.ArrayList;
import java.util.List;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.demo.charts.ExampleChart;
import org.knowm.xchart.style.Styler.LegendPosition;

public class Chart2 implements ExampleChart<CategoryChart> {

    private List<String> keys;
    private List<Double> values;


    public Chart2(List<String>  keys, List<Double> values) {
        this.keys = keys;
        this.values = values;
    }

    public static void generateChart(MessageLogger logger) {
        List<String> keys = new ArrayList<String>(logger.getRaport2Data().keySet());;
        List<Double> values = new ArrayList<Double>(logger.getRaport2Data().values());

        ExampleChart<CategoryChart> exampleChart = new Chart2(keys, values);
        CategoryChart chart = exampleChart.getChart();
        new SwingWrapper<>(chart).displayChart();
    }




    @Override
    public CategoryChart getChart() {

        // Create Chart
        CategoryChart chart =
                new CategoryChartBuilder()
                        .width(800)
                        .height(600)
                        .title(getClass().getSimpleName())
                        .xAxisTitle("Developers")
                        .yAxisTitle("Hours")
                        .build();

        // Customize Chart
        chart.getStyler().setLegendPosition(LegendPosition.InsideNW);
        chart.getStyler().setPlotGridLinesVisible(false);

        // Series
        chart.addSeries("Dane", keys, values);


        return chart;
    }

    @Override
    public String getExampleChartName() {

        return getClass().getSimpleName() + " - Basic Bar Chart";
    }
}
